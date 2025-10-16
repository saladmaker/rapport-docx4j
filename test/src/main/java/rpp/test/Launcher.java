package rpp.test;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class Launcher {

    private static final String THEMES_DIR = "../themes";
    private static final String OUTPUT_DIR = "../output";

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java -jar test.jar <GeneratorClassNameOrFQCN> <ThemeName> [--clean]");
            System.exit(1);
        }

        String generatorArg = args[0];
        String themeName = args[1];
        boolean cleanOutput = args.length > 2 && "--clean".equals(args[2]);

        if (cleanOutput) cleanOutputDir();

        DocumentGenerator generator = loadGenerator(generatorArg);

        File themeFile = new File(THEMES_DIR, themeName + "/theme.docx");
        if (!themeFile.exists()) {
            throw new IllegalArgumentException("Theme not found: " + themeFile.getAbsolutePath());
        }

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(themeFile);

        // Let generator work
        generator.generate(wordMLPackage);

        // Build output filename: GeneratorName_ThemeName_Timestamp.docx
        String generatorSimple = generator.getClass().getSimpleName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String safeThemeName = themeName.replaceAll("[^A-Za-z0-9_\\-]", "_");
        String outputFileName = generatorSimple + "_" + safeThemeName + "_" + timestamp + ".docx";

        File outputFolder = new File(OUTPUT_DIR);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        File outputFile = new File(outputFolder, outputFileName);
        wordMLPackage.save(outputFile);

        System.out.println("Generated: " + outputFile.getAbsolutePath());
        openInWord(outputFile.toPath());
    }

    private static DocumentGenerator loadGenerator(String arg) throws Exception {
        Class<?> clazz;
        try {
            clazz = Class.forName(arg); // Try FQCN first
        } catch (ClassNotFoundException e) {
            clazz = Class.forName("rpp.test.generators." + arg); // fallback
        }

        if (!DocumentGenerator.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " does not implement DocumentGenerator");
        }
        Constructor<?> ctor = clazz.getDeclaredConstructor();
        ctor.setAccessible(true);
        return (DocumentGenerator) ctor.newInstance();
    }

    private static void cleanOutputDir() throws IOException {
        Path outputPath = Paths.get(OUTPUT_DIR);
        if (Files.exists(outputPath)) {
            try (Stream<Path> paths = Files.walk(outputPath)) {
                paths.filter(p -> !p.equals(outputPath))
                        .sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        });
            }
            System.out.println("Output directory cleaned: " + OUTPUT_DIR);
        } else {
            Files.createDirectories(outputPath);
        }
    }

    /**
     * Universal file opener: works in WSL, Windows, macOS, Linux.
     */
    private static void openInWord(Path file) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String path = file.toAbsolutePath().toString();

            if (isWSL()) {
                // Convert Linux path to Windows path using wslpath
                String winPath = toWindowsPath(path);
                new ProcessBuilder("explorer.exe", winPath)
                        .inheritIO()
                        .start();
            } else if (os.contains("win")) {
                new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", path)
                        .inheritIO()
                        .start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", path)
                        .inheritIO()
                        .start();
            } else {
                new ProcessBuilder("xdg-open", path)
                        .inheritIO()
                        .start();
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to open file in Word: " + file, e);
        }
    }

    private static boolean isWSL() {
        return System.getenv("WSL_INTEROP") != null || System.getenv("WSL_DISTRO_NAME") != null;
    }

    private static String toWindowsPath(String linuxPath) throws IOException {
        Process p = new ProcessBuilder("wslpath", "-w", linuxPath)
                .redirectErrorStream(true)
                .start();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            return r.readLine().trim();
        }
    }
}
