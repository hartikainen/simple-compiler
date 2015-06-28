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
  <li>Type checking and error handling</li>
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

In the Factor non-terminal (in the final Compiler.atg), I have resolved to conflict with the multiple 'identifier' beginning right-hand sides by using the Coco/R's peek functionality.

<h3>Forming the Coco/R productions</h3>
Once the grammar is in LL(1) form, it can be written in the Coco/R readable form. The resulting productions can be found in the file Compiler.atg. This file will also be the place for all the Coco/R specifications and helper functions to guide the parsing. More informations of the Coco/R productions can be found at [http://www.ssw.uni-linz.ac.at/coco/Doc/UserManual.pdf].

<h3>Symbol table</h3>
The symbol table is used to keep track of the necessary info of the variables and functions. This data will be used for type checking and code generation. My implementation is heavily based on the symbol table example found from [http://www.ssw.uni-linz.ac.at/coco/CPP/Taste.zip]. However, I some Java specific implementation decisions have been made.

The SymbolTable class includes openScope, closeScope, addFunction, addVariable and findSymbol functions which are used to handle the function scopes, variables and functions during the parsing. The SymbolTable instance keeps track of the current top-most scope. Each time a new scope is opened, it's assigned to the SymbolTable's top_scope variable. Each scope has a reference ('next') to the scope it was created at.

Each time a new symbol (variable or function) is added to a scope, the uniqueness of its name is checked. Each variable ne can be declared in each scope only once. Also, every time a variable is added to a scope, it gets assigned the 'adr' attribute, which refers to the local index of the variable in that scope. The local variables and functions ('locals') are stored in a HashMap structure where the symbol names are keys and the actual symbol is the value. A simpler ArrayList would've done the job equally well.

For each function, we need to keep track of its parameters. The Variable instances related to each function parameter are stored in the ArraList 'parameters' of that function. When the function is declared, a new variable is created for each parameter, and the added to the function's parameter list by calling the addParameter function.

The code and organization for the SymbolTable is in no way the best possible. Some of the functionalities that are now handled manually in the Compiler.atg, could (and should) be isolated in the SymbolTable and used through suitable methods. Due to the nature of this project and my lack of experience with compilers, it was hard to design the SymbolTable better before hand. If I were to rewrite the SymbolTable again, I would handle all the functionalities through simpler API that leaves no direct attribute accesses in the Compiler.atg. However, when used with care, the SymboTable functions just as required for the compiler to produce correct output.

<h3>Type Checking and error handling</h3>
The compiler has to be able to detect (and report) semantic errors that it runs into. Most of the type checking functionality is handled in the functions described in the beginning of the Compiler.atg. The expected types for the productions are checked against the actual types, and an SemErr is raised in case of error. The parser does not stop in case of type errors, but keeps going to and tries to recover from the error.

There are couple of tests than make the parser program crash, due to an invalid syntax. For example parametersMissing.tst correctly raises an error for the function call with missing parameters. However, since the program keeps going, the parser crashes due to a java.lang.IndexOutOfBoundsException.

<h3>Code generator</h3>
The code generation is done via the CodeGenerator class API. CodeGenerator has an emit function that maps straights to the SlxProgram.emit functions. CodeGenerator keeps track of the label counter, and servers the parser through the newLabel function. The resulting slx program, which can be accessed by the getProgram function, is needed by the Compiler program to write the slx output in to a file.

<h2>Running the compiler</h2>
Compiler class, which implements SlxCompiler, includes the main function for the program. The class takes an test file path as a command line parameter, and writes the resulting slx program in the out.slx file located in the same folder as the Compiler class file.

The parser and scanner can be created by calling
```
java -jar <path-to-Coco.jar> -frames <path-to-Coco-frame-files> <path-to-atg-file>
```
For example
```
java -jar ./bin/Coco.jar -frames ./bin Compiler.atg
```

The slx program can be generated by calling
```
java <path-to-compiler-class> <path-to-input-file>
```
For example
```
java ./Compiler ./hard/tests/fib-rec.tst
```
Which then inputs the slx program into 'out.slx' found in the same folder as Compiler file. The slx program can be interpreted by
```
java -jar <path-to-slx-interpreter> <path-to-slx-file>
```
For Example
```
java -jar ./SlxInterpreter.jar ./out.slx
```
