#!/bin/bash

echo Attempting to compile
javac -d ./bin/ ./src/*.java

echo Attempting to package as a jar
cd bin
jar -cf GPIOLib.jar *
cp GPIOLib.jar ..
rm -f GPIOLib.jar
cd ..

echo Copying the jar to the test directory
cp ./GPIOLib.jar ./test/lib/
