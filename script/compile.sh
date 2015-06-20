#!/bin/sh
#
# This script expects that the script running Coco/R compiler
# generator can be found from PATH. In Niksula this script has been
# installed to the directory /u/courses/t106550/bin/. To add this
# directory to PATH run the following command (C shell)
#   setenv PATH /u/courses/t106550/bin:$PATH


CPATH=$1
rm -f ${CPATH}/Parser.java ${CPATH}/Parser.java.old ${CPATH}/Scanner.java ${CPATH}/Scanner.java.old
#"/Users/Kristian/code/T-106.4200 Introduction to Compiling"
COCO_BIN="${CPATH}/bin"
COCO_JAR="${COCO_BIN}/Coco.jar"

COCOR="java -jar ${COCO_JAR} -frames ${COCO_BIN}"
GRAMMAR_FILE="${CPATH}/Compiler.atg"

echo "${COCOR} ${GRAMMAR_FILE}"
$COCOR $GRAMMAR_FILE

echo "javac ${CPATH}/*.java"
javac ${CPATH}/*.java
