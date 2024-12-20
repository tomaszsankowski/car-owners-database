#!/bin/bash
echo "Building angular-front project"
npm install
npm run build
# shellcheck disable=SC2181
if [ $? -ne 0 ]; then
  echo "Build failed for angular-front project"
  exit 1
fi
echo "Angular-front project built successfully."
