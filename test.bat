@echo off
:process_bat_start
set /p response=End batch file early? (Y/N): 
echo You chose: "%response%"
if /I "%response%" == "Y" goto :process_bat_end
if /I "%response%" == "N" goto :process_end_false
echo Whoops! This shouldn't run!
goto :EOF

:process_end_false
echo Returning to beginning.
goto process_bat_start

:process_bat_end
echo You chose to end the batch file.
goto :EOF

echo This line should never execute!!