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

<h3>Grammar modification to LL(1) form</h3>

The initial grammar needs to be converted into LL(1) form, by removing the ambiguities and left recursions, left factoring, and resolving the associativity and precedence of supported operators.

The Expr production has left recursion, and one of the right hand sides has 'op' symbol, which requires resolving associativity and presedence. A new production will be created for each of the operators such that the productions create a recursion, and thus resolves the associativities and presedences.

IdAccess production has to be left-factorized, since both of it's right hand sides begin with identifier. This is resolved by changing the production to:

```
IdAccess -> identifier | identifier [ Expr ]
```
to
```
IdAccess -> identifier IdAccessRest
IdAccessRest -> [ Expr ] |
```

<h3>Forming the Coco/R productions</h3>
<h3>Symbol table</h3>
<h3>Type Checking</h3>
<h3>Code generator</h3>
