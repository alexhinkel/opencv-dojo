@echo off
setlocal enabledelayedexpansion

set MODEL_NAME=model
set POSITIVE_DATA_PATH=%MODEL_NAME%\pos
set NEGATIVE_DATA_PATH=%MODEL_NAME%\neg
set INPUT_PATH=%MODEL_NAME%\input

call opencv_annotation.exe -a=%INPUT_PATH%\pos_tmp.txt -i=%POSITIVE_DATA_PATH%

for /f "tokens=*" %%f in (%INPUT_PATH%\pos_tmp.txt) do (
	set input=%%f
	if "!input:~-3!" neq "g 0" (
		echo .\%%f>>%INPUT_PATH%\pos.txt
	)
)

for %%f in (%NEGATIVE_DATA_PATH%\*.*) do (
	echo .\%%f>>%INPUT_PATH%\neg.txt
)
pause