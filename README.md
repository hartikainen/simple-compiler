

Cource project work for the Aalto University course 'T-106.4200 Introduction to Compiling'.

The project implements a minimalistic compiler by applying syntax-directed translation, for a language specified in the /easy/grammar and /hard/grammar files. Simple assembly language, called SLX, is used as the target.

The compiler is implemented using a Coco/R compiler generator which, given an attribute grammar, symbol table and code generator, generates a compiler with a recursive descent parser.

The compiler is tested by using the test input files found in /easy/tests/ and /hard/tests/.
