#!/bin/bash

# Define an array of project directories
projects=("gateway" "car-management" "person-management" "angular-front")

# Loop through each project directory and run its build script
for project in "${projects[@]}"; do
  echo "Building project: $project"
  # shellcheck disable=SC2164
  cd "$project"
  chmod +x build.sh
  ./build.sh
  # shellcheck disable=SC2181
  if [ $? -ne 0 ]; then
    echo "Build failed for project: $project"
    exit 1
  fi
  # shellcheck disable=SC2103
  cd ..
done

echo "All projects built successfully."