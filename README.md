# Compiler for the Player programming language

This is an ad hoc compiler for [Player](docs/language.md), a small
imperative language. It started as a term project back in 2004, but then
took on a life of its own and for a while became a fun way to pass the
time. Even with a moderately nontrivial language, a compiler can get
rather large, and with so many classes to play with, who doesn't want
to play Java architect! So when I recently found this compiler, I found
several versions, all completely different. This one was not the most
elegant or sophisticated. It was the one that worked. I'm posting it
for the same reason I played with it years ago - for fun. And also for
personal, sentimental value.

Full documentation for Player [is available here](docs/language.md).

The compiler reads in a Player source file, and launches the scanner
that produces a stream of tokens, which is then fed into the parser
that determines the syntactic validity of the input and produces an
abstract syntax tree (AST). After this, a symbol table is produced,
which is then used by the type checker that checks the semantic validity
of the program. Finally, the code generator walks the AST and generates
Java code. There is no intermediate code representation: the target of
compilation is Java and the output is a Java source file.

## Usage

```
javac playerc/*.java
java playerc.PlayerCompiler source-file output-directory
```

## What's inside

* **docs**: language documentation, FSA diagram for lexical analysis
* **input**: 
 * sample programs
 * files used in tests
 * language grammar (augmented with semantic actions)
* **output**: generated Java files (from sample program + tests)
* **scr/playerc**: source code

## Disclaimer
This project is deprecated and is not maintained. It exists for historical
reasons only.

## License
[Public domain](LICENSE)
