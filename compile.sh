#!/bin/bash

echo Attempting to compile
javac -d ./bin/ ./src/*.java

echo Attempting to package as a jar
jar -cf GPIOLib.jar ./bin/*

echo Copying the jar to the test directory
cp ./GPIOLib.jar ./test/lib/
