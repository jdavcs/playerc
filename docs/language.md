# The Player Programming Language Reference Manual 

*By [Eugene Wallingford](http://www.cs.uni.edu/~wallingf/) - September 19,
2003*

## Introduction 

Player is a small imperative programming language with boolean, integer, real,
and string values; arrays and records with implicit pointers; user-defined
types; nested procedures; and a few simple control structures. 

This manual gives an informal definition for the language. Fragments of syntax
are specified in BNF, as needed. The complete concrete syntax appears at the
end of the manual. 

## Lexical Issues 

Player's character set is the standard ASCII set. Player is case-sensitive:
upper- and lower-case letters are not considered equivalent. 

Whitespace consists of blanks, tabs, and end-of-line characters. It serves to
separate tokens and is otherwise ignored. Delimiters and operators do not need
whitespace to separate them from their neighbors on either side. White space
may not appear in any token except a string literal. 

Comments are enclosed between the tokens (* and *). They cannot be nested. Any
character is legal in a comment. Comments may appear anywhere a token may
appear. They are self-delimiting, that is, they do not need to be separated
from their surroundings by whitespace. 
 
## Tokens 

Tokens consist of keywords, literal constants, identifiers, operators, and
delimiters. 

The following are reserved keywords. They must be written in lower-case. 

```
    and         array       begin       boolean     by   
    do          else        elseif      end         exit
    false       for         if          integer     loop
    not         null        of          or          procedure
    program     read        real        record      return
    then        to          true        type        var
    write
```

Literal constants are boolean, integer, real, or string. They may consist of no
more than 255 characters. 

* The boolean literals are TRUE and FALSE. 
* Integers contain only digits. They must be in the range 0 to 231-1. 
* Reals consist of one or more digits, followed by a decimal point, followed by
  one or more digits. There is no range constraint on reals. 

  Note that no numeric literal can be negative, because there is no provision
  for a leading minus sign. 

* Strings begin and end with a double quote (") and contain any sequence of
  printable ASCII characters (those with decimal character codes in the range
  32 - 126) except for double quotes. Note in particular that strings may not
  contain tabs or newlines. 

Identifiers are sequences of letters and digits starting that begin with a
letter, excluding the reserved keywords. Identifiers are also limited to 255
characters in length. 

The following are Player's operators: 
```
:=  +   -   *   /   <   <=  >   >=  =   <>
```
and delimiters: 
```        
:   ;   ,   .   (   )   [   ]   {   }   [<  >]
```
 
## Programs 
 
The program is the unit of compilation for Player. Programs have the following
syntax: 

```
    program := PROGRAM name body
    name    := identifier
    body    := {declaration} BEGIN {statement} END
```
The execution of a program is the result of executing its sequence of
statements in order and terminating. 

Each file read by the compiler must consist of exactly one program. There is no
facility for linking multiple programs or for separate compilation of parts of
a program. 

## Declarations 

All identifiers occurring in a program must be introduced by a declaration.
Declarations serve to specify whether the identifier represents a type, a
variable, or a procedure (all of which live in a single name space), or a
record member name (which lives in a separate name space). 

```
    declaration := VAR {var-decl}
                 | TYPE {type-decl}
                 | PROCEDURE {procedure-decl}
```

Declarations may be global to the program or local to a procedure. The scope of
a declaration extends roughly from the point of declaration to the end of the
enclosing module (for local declarations) or the end of the program (for global
declarations). The specific scope rules differ for each kind of declaration. 

A local declaration of an identifier hides any outer declarations and makes
them inaccessible in the inner scope. No identifier may be declared twice in
the same procedure or at global level. 

## Types 

Player is strongly typed. Every expression has a unique type, and types must
match at all assignments to names and intermediate values, with one exception:
an integer can be used where a real is expected. 

Types are referred to by type names. The primitive types (boolean, integer,
real, and string) have reserved-word names. The use can create a new type with
a type declaration, in which the type constructors array and record are applied
to existing types. 

```
    declaration := TYPE {type-decl}
    type-decl   := typename = type ';'
    typename    := identifier
    type        := ARRAY OF typename
                 | RECORD member {member} END
    member      := identifier ':' typename ';'
```
Player uses a name equivalence model for types. Each type declaration produces
a unique type that is incompatible with all the others. 

### Primitive Types 

Player provides four primitive types: boolean, integer, real, and string. The
primitive values true and false have type boolean. Integer, real, and string
literals have their corresponding type. 

Collectively, integer and real constitute the numeric types. An integer value
will be implicitly coerced to a real value whenever necessary. The boolean type
has no relation to the numeric types, and a boolean value cannot be converted
to or from a numeric value. 

### Array Types 

An array is a structure consisting of zero or more elements of the same element
type. The elements of an array can be accessed using an index that ranges from
0 to the length of the array minus 1. The length of an array is not fixed by
its type, but is determined when the array is created at runtime. It is a
checked run-time error to dereference outside the bounds of an array. 

### Record Types 

A record type is a structure consisting of a fixed number of members, each of
which can be a different type. A record type declaration specifies the name and
type of each member. Member names are used to initialize and dereference
components. The members of each record type form a separate namespace, so that
different record types may use the same member names. 

The primitive value null belongs to every record type. It is a checked run-time
error to dereference a component from a null record. 

### Constructed Types 

Arrays and records are always manipulated by value, so an array or record is
"really" a pointer to a heap-allocated object that contains the array or
record. This pointer cannot be directly manipulated by the programmer. Thus, a
record type that contains other record types as members actually contains
pointers to these values. In particular, a record type may contain a member of
the same type, that is, it may be recursive. 

To permit mutually recursive types, the sequence of type declarations that
follows a single type keyword is taken to be a recursive. The scope of all the
declarations in the sequence begins at the first declaration. 

(Note the utility of the null record for building values of recursive types. 

Records and arrays have unlimited lifetimes; the heap object that contains a
record or array exists from the moment when the expression that defines it is
evaluated until the end of the program. In principle, a garbage collector could
be used to remove heap objects when no more pointers to them exist, but this is
invisible to the Player programmer. 
 
## Constants 

Player provides three primitive constants: true and false (of type boolean) and
null (of every record type). There is no provision for user-defined constants. 

## Variables 

Variable declarations take this form: 

```
    declaration := VAR {var-decl}
    var-decl    := identifier { ',' identifier } [ ':' typename ] ':=' expression ';'
```

Every variable must have an initial value, given by expression. The type
designator can be omitted when the type can be deduced from the initial value,
that is, any time except when the initial value is null. 

The scope of each variable declaration begins just before next declaration; it
does not include the initializing expression, so declarations are never
recursive. 

## Procedures 

Procedure declarations take this form: 

```
    declaration    := PROCEDURE {procedure-decl}
    procedure-decl := identifier formal-params [':' typename] body ';'
    formal-params  := '(' fp-section {';' fp-section } ')' 
                    | '(' ')' 
    fp-section     := identifier {',' identifier} ':' typename
    body           := {declaration} BEGIN {statement} END
```

A procedure declaration can create either a "proper" procedure, which does not
return a value, or a function, which returns a value that becomes the value of
the calling expression. Proper procedure declarations are distinguished by the
lack of a return type. 

A procedure may have zero or more formal parameters, whose names and types are
specified in the procedure declaration, and whose actual values are specified
when the procedure is activated. The scope of formal parameters is the body of
the procedure, including its local declarations. Parameters are always passed
by value. 

There is an implicit return statement at the bottom of every procedure body.
Function bodies must have an explicit return statement. 

Each sequence of procedures declared following a single procedure keyword is
treated as potentially mutually recursive. That is, the scope of each procedure
name begins at the point of declaration of the first procedure in the sequence
and includes the bodies of all the procedures in the sequence as well as the
body of the enclosing procedure (or, for top-level procedures, the whole
program). 

## L-values 

An l-value is a location whose value can be either read or assigned. Variables,
procedure parameters, record members, and array elements are all l-values. 

```
    lvalue := identifier
            | lvalue '[' expression ']'
            | lvalue '.' identifier
```

The square brackets ([]) denote the dereferencing of an array element; the
expression within the brackets must evaluate to an integer expression within
the bounds of the array. 

The dot (.) denotes the dereferencing of a record member; the identifier after
the dot must be a member name of the corresponding record type. 

## Expressions 

Expressions can be simple or compound. 

### Simple expressions 

Simple expressions are values of the primitive types: 

```
    expression := boolean
                | number
                | string
                | lvalue
                | '(' expression ')'
    number     := integer | real
```

A number expression evaluates to the literal value specified. Note that reals
are distinguished from integers by lexical criteria. An l-value expression
evaluates to the current contents of the specified location. Parentheses can be
used to alter precedence in the usual way. 

### Arithmetic expressions 

Arithmetic expressions are built by combining expressions using a set of
primitive operators: 

```
    expression := unary-op expression
                | expression binary-op expression
    unary-op   := '+' | '-' 
    binary-op  := '+' | '-' |  '*' | '/' | %
```

The operators +, -, *, / require integer or real arguments. If both arguments
are integers, then an integer operation is performed and the integer result is
returned; otherwise, any integer arguments are coerced to reals, a real
operation is performed, and the real result is returned. The operator %
requires two integer operands and returns an integer result, the integer
remainder. 

All the binary operators evaluate their left argument first. 

### Logical operators 

Logical expressions are built by combining expressions using a set of primitive
operators: 

```
    expression := unary-op expression
                | expression binary-op expression
    unary-op   := NOT
    binary-op  := OR | AND
```

These operators require boolean operands and return a boolean result. or and
and are "short-circuit" operators; they do not evaluate their right-hand
operands if the result is determined by the left-hand one. 

### Relational operators 

Relational expressions are built by combining expressions using a set of
primitive operators: 

```
    expression := expression binary-op expression
    binary-op  := '>' | '<' | '=' | '>=' | '<=' | '!='
```

These operators all return a boolean result. 

These operators all work on numeric operands. If both operands are integer,
then an integer comparison is made; otherwise, any integer operand is coerced
to real and a real comparison is made. 

The operators = and != also work on pairs of boolean operands, or on pairs of
record or array operands of the same type. In the case of record and array
operands, they test pointer equality -- that is, they test whether two records
or arrays are the same instance, not whether they have the same contents. 

These operators all evaluate their left operand first. 

### Function call 

A function call serves as a compound expression. 

```
    expression    := identifier actual-params
    actual-params := '(' expression {',' expression} ')'
                   | '(' ')'
```

A function call is evaluated by first evaluating the argument expressions left
to right to obtain actual parameter values, and then executing the function
specified by identifier with its formal parameters bound to the actual
parameter values. The function returns by executing an explicit return
statement with an expression for the value to be returned. The returned value
becomes the value of the function call expression. 

### Record construction 

A programmer can create a record in place as an expression: 

```
    expression   := typename record-inits
    record-inits := '{' identifier ':=' expression { ';' identifier ':=' expression} '}'
```

If typename is a record type name, then the expression typename {identifier1 =
exp1, identifier2 = exp2, ...} first evaluates the expi left to right, and then
creates a new record instance of type typename with named members initialized
to the resulting values. The names and types of the member initializers must
match those of the named type, though they need not be in the same order. 

### Array construction 

A programmer can create an array in place as an expression: 

```
    expression  := typename array-inits
    array-inits := '[<' array-init { ',' array-init } '>]'
    array-init  := [ expression 'OF' ] expression
```

If typename is an array type name, then the expression [< count1 of value1,
count2 of value2, ... >] first evaluates each pair of expressions left to right
to yield a sequence of count/value pairs n1, v1, and then creates a new array
of type typename whose contents consist of n1 copies of v1, followed by n2
copies of v2, etc. 

If any of the counts is 1, it may be omitted. If any of the ni evaluates to
less than 1, then no copies of the corresponding vi are included. The types of
the vi must match the element type of the named array type. 

For example, the expression: ```[< 1, 2 OF 3, 3 OF 2, 4 >]``` 

creates an array of length 7 with contents ```1, 3, 3, 2, 2, 2, 4.``` 

### Precedence and associativity 

Procedure call and parenthesization have the highest precedence; followed by
the unary operators; followed by *, /, %, and and; followed by +, -, and or. 

Within precedence classes, binary operators are left-associative. 

However, relational expressions do not associate. That means they cannot be
embedded in other expressions unless parenthesized. For example, a < b or e > f
and a < b = c are not legal expressions, but (a < b) or (e > f) and (a < b) = c
are legal (as long as c is of boolean type). 
 
## Statements 

Player has the following kinds of statements: 

### Assignment 

The assignment statement takes this form: 

```
    statement := lvalue ':=' expression ';'
```

First the l-value is evaluated to a location, then the expression is evaluated, and finally its value is stored in the location. 

Assigning a record or array value actually assigns a pointer to the record or array. 

### Procedure call 

Procedure call statements take nearly the same form as function call exprerssions: 

```
    statement     := identifier actual-params ';'
    actual-params := '(' expression {',' expression} ')'
                   | '(' ')'
```

This statement is executed by evaluating the argument expressions left to right to obtain actual parameter values, then executing the procedure specified by identifier with its formal parameters bound to the actual parameter values. The procedure returns when its final statement is executed or when an explicit return statement is executed. 

### Read and Write 

Player provides a primitive input and output statements: 

```
    statement := READ '(' lvalue {',' lvalue} ')' ';'
```

This statement is executed by evaluating the l-values to locations in left to right order, reading numeric literals from standard input, evaluating them, and finally storing the values into the locations. The l-values must have type integer, real, or string, and their types guide the evaluation of the corresponding literals. Input literals are delimited by whitespace, and the last one must be followed by a carriage return. 

```
    statement    := WRITE write-params ';'
    write-params := '(' write-expr {',' write-expr } ')'
                  | '(' ')'
    write-expr   := string
                  | expression
```

Executing a write statement evaluates the specified expressions in left to right order, and then writes the resulting values to standard output with no separation between values, followed by a new line. 
 
### If-then-else 

Player provides one control structure for selection: 

```
    statement := IF expression THEN statement {statement}
                    {ELSEIF expression THEN statement {statement}}
                    [ELSE statement {statement}] END ';'

```

This statement specifies the conditional execution of guarded statements. Each 'guard' expression must evaluate to a boolean. The guards are evaluated in order until one evaluates to true, after which its associated statement sequence is executed. If no guard evaluates to true, then the statement sequence following the else (if any) is executed. 

### Loop and For 

Player provides two control structures for repetition. 

```
    statement := LOOP statement {statement} END ';'
```

A loop's statement sequence is executed repeatedly. The only way to terminate the repetition is by executing an exit statement within the sequence -- but not within a nested loop or for statement. 

```
    statement := FOR identifier ':=' expression TO expression [ BY expression ]
                      DO statement {statement} END ';'
```

Executing the statement for identifier := exp1 to exp2 by exp3 do stmts is equivalent to: 

1. evaluate expressions exp1, exp2, and exp3, in that order. The result will be the values val1, val2, and val3. All three must be integers. 
2. create a local integer variable named identifier and initialize it to val1. 
3. if the value of identifier is less than or equal to val2, execute the statements in the do block; otherwise terminate the loop. 
4. set identifier := identifier + val3 and repeat Step 3. 

If the for statement does not have a by clause, then val3 defaults to 1. 

If an exit statement is executed within the body of the for statement (but not within the body of a nested loop or for statement), the loop is prematurely terminated, and control passes to the statement following the for. 

### Exit 

Programmers can terminate a loop on demand with this statement: 

```
    statement := EXIT ';'
```

Executing exit causes control to pass immediately to the next statement following the nearest enclosing loop or for statement. If there is no such enclosing statement, the exit is illegal. 
 
### Return 

Programmers can cause a procedure to terminate with this statement: 

```
    statement := RETURN [expression] ';'
```

Executing return terminates execution of the current procedure and returns control to the calling context. There can be multiple returns within one procedure body. There is also an implicit return at the bottom of every procedure. A return from a function procedure must specify a return value expression of the function's type; a return from a proper procedure must not. The main program body must not include a return. 
 
## Concrete Syntax 

```
program         -> PROGRAM name body
name            -> identifier
body            -> {declaration} BEGIN {statement} END
declaration     |  VAR {var-decl}
                |  TYPE {type-decl}
                |  PROCEDURE {procedure-decl}
var-decl        -> identifier { ',' identifier } [ ':' typename ] ':=' expression ';' 
type-decl       -> typename = type ';' 
procedure-decl  -> identifier formal-params [':' typename] body ';'
typename        -> identifier
type            -> ARRAY OF typename
                |  RECORD member {member} END
member          -> identifier ':' typename ';'
formal-params   -> '(' fp-section {';' fp-section } ')' 
                -> '(' ')' 
fp-section      -> identifier {',' identifier} ':' typename
statement       -> lvalue ':=' expression ';'
                -> identifier actual-params ';'
                -> READ '(' lvalue {',' lvalue} ')' ';'
                -> WRITE write-params ';'
                -> IF expression THEN statement {statement}
                      {ELSEIF expression THEN statement {statement}}
                      [ELSE statement {statement}] END ';'
                -> LOOP statement {statement} END ';'
                -> FOR identifier ':=' expression TO expression [ BY expression ] 
                       DO {statement} END ';'
                -> EXIT ';'
                -> RETURN [expression] ';'
write-params    -> '(' write-expr {',' write-expr } ')'
                -> '(' ')'
write-expr      -> string
                -> expression
expression      -> number
                -> lvalue
                -> '(' expression ')'
                -> unary-op expression
                -> expression binary-op expression
                -> identifier actual-params
                -> typename record-inits
                -> typename array-inits
lvalue          -> identifier
                -> lvalue '[' expression ']'
                -> lvalue '.' ID
actual-params   -> '(' expression {',' expression} ')'
                -> '(' ')'
record-inits    -> '{' identifier ':=' expression { ';' identifier ':=' expression} '}'
array-inits     -> '[<' array-init { ',' array-init } '>]'
array-init      -> [ expression OF ] expression
number          -> INTEGER | REAL
unary-op        -> '+' | '-' | NOT
binary-op       -> '+' | '-' |  '*' | '/' | AND | OR
                -> '>' | '<' | '=' | '>=' | '<=' | '<>'
```
