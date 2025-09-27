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
mvn clean package -DskipTests
#1994
# === STEP 2: Run the test module jar with all provided arguments ===
echo "🏃 Running test module with args: $@"
java -jar test/target/rpp-test-1.0-SNAPSHOT.jar "$@"
