#!/bin/bash
echo "Building car-management project"
JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 ./mvnw clean package -DskipTests
# shellcheck disable=SC2181
if [ $? -ne 0 ]; then
  echo "Build failed for car-management project"
  exit 1
fi
echo "Car-management project built successfully."