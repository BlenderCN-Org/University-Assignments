rem batch.bat
echo off

rem %1 path (relative to the main input file) to the files to be iterated 
rem %2 output file name
rem remaining args represent the extension of file to be iterated

set curdir=%CD%
cd "%~1"
shift

rem output must be enclosed with "" to allow spaces in the path
set output="%curdir%\%~1.list"

if exist %output% del %output%
copy nul %output%
shift

:loop
if "%~1"=="" goto :eof
dir /b *.%~1 >> %output%
shift
goto :loop
