#!/bin/bash
echo Removing temp files
rm -f ./src/~*.java
rm -f ./test/~*.java

echo Staging changes
git add .

echo Printing status
git status
