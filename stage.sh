#!/bin/bash
echo Removing temp files
rm -f ./src/*~
rm -f ./test/*~
rm -f ./*~

echo Staging changes
git add -A

echo Printing status
git status
