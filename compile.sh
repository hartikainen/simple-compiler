#!/bin/sh
#
# This script expects that the script running Coco/R compiler
# generator can be found from PATH. In Niksula this script has been
# installed to the directory /u/courses/t106550/bin/. To add this
# directory to PATH run the following command (C shell)
#   setenv PATH /u/courses/t106550/bin:$PATH

GRAMMAR_FILE="Compiler.atg"
COCOR="cocor"

$COCOR $GRAMMAR_FILE
javac *.java
