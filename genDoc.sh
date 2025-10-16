
#!/usr/bin/env bash
set -e  # stop script if any command fails

# === Validate user input ===
if [ $# -lt 1 ]; then
  echo "Usage: $0 <arg1> [arg2 arg3 ...]"
  echo "Example: $0 hello world foo bar"
  exit 1
fi

# === STEP 1: Build project ===
echo "🚀 Building project with Maven..."
mvn clean install -DskipTests

# === STEP 2: Run the test module with exec plugin ===
echo "🏃 Running test module with args: $@"
cd test
mvn exec:java -Dexec.args="$*"