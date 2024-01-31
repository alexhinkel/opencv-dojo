@echo off

set MODEL_NAME=model
set INPUT_PATH=%MODEL_NAME%\input
set OUTPUT_PATH=%MODEL_NAME%\output

set WIDTH=24
set HEIGHT=24
set NUMBER_OF_POSITIVES=100

set NUMBER_OF_NEGATIVES=300

set NUMBER_OF_STAGES=15

robocopy .\%INPUT_PATH% .\ pos.vec
robocopy .\%INPUT_PATH% .\ neg.txt

call opencv_traincascade.exe -data %OUTPUT_PATH% -vec pos.vec -bg neg.txt -w %WIDTH% -h %HEIGHT% -numPos %NUMBER_OF_POSITIVES% -numNeg %NUMBER_OF_NEGATIVES% -numStages %NUMBER_OF_STAGES%
pause