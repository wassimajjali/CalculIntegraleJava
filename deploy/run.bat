@echo off

set JRE_PATH=./jre/bin
set PATH=%PATH%;%JRE_PATH%
java -version

java -cp .;./*;./ext/* -Xmx100m -Xms80m integrale.UseJFrame

pause