<h1>Simple Compiler</h1>

Cource project work for the Aalto University course 'T-106.4200 Introduction to Compiling'.

The project implements a minimalistic compiler by applying syntax-directed translation, for a language specified in the /easy/grammar and /hard/grammar files. Simple assembly language, called SLX, is used as the target.

The compiler is implemented using a Coco/R compiler generator which, given an attribute grammar, symbol table and code generator, generates a compiler with a recursive descent parser.

The compiler is tested by using the test input files found in /easy/tests/ and /hard/tests/.

<h2>Implementation</h2>

The starting point for the project is the grammar files found in the folders /easy and /hard. The hard grammar is an extension for the easy grammar, so all the features for the easy grammar work with the hard grammar.

The implementation steps needed to create the compiler specification:
<ol>
  <li>Grammar modification to LL(1) form</li>
  <li>Forming the Coco/R compatible productions</li>
  <li>Design and implementation of a Symbol Table</li>
  <li>Type checking</li>
  <li>Implementation of the code generator</li>
</ol>

These parts are discussed in more detail below.
