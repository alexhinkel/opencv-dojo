@echo off

set MODEL_NAME=model
set INPUT_PATH=%MODEL_NAME%\input

set WIDTH=24
set HEIGHT=24
set NUMBER_OF_POSITIVES=10

robocopy .\%INPUT_PATH% .\ pos.txt

call opencv_createsamples.exe -info pos.txt -w %WIDTH% -h %HEIGHT% -num %NUMBER_OF_POSITIVES% -vec pos.vec

robocopy .\ .\%INPUT_PATH% pos.vec
pause