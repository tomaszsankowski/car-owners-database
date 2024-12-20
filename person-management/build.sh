#!/bin/bash
echo "Building person-management project"
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 ./mvnw clean package -DskipTests
# shellcheck disable=SC2181
if [ $? -ne 0 ]; then
  echo "Build failed for person-management project"
  exit 1
fi
echo "Person-management project built successfully."