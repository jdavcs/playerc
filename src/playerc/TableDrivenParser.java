/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.io.IOException;
import java.util.Stack;

import playerc.abstractsyntax.AbstractSyntaxTree;
import playerc.abstractsyntax.Program;
import playerc.abstractsyntax.SemanticActionFactory;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */

public class TableDrivenParser extends Parser {
  private Grammar grammar;
  private PlayerTokens tokens;
  private Token previousToken; // to be used in analysing declarations
  // private SymbolTable symbols; - for now...

  public TableDrivenParser(Scanner source, Grammar g, PlayerTokens t) {
    super(source);
    grammar = g;
    tokens = t;
    previousToken = null;
  }

  protected AbstractSyntaxTree parseProgram()
      throws SemanticException, ParsingException, LexicalException, IOException {
    ParsingTable table = grammar.getParsingTable();
    Stack stack = new Stack();
    stack.push(Grammar.END_MARKER);
    stack.push(grammar.getStartSymbol());
    Stack semanticStack = new Stack(); // semantic stack
    Token lastToken = null;

    do {
      Token nextToken = source().peek(); // from input

      // this is a string. it can be a nonterminal, a terminal, or a sem.action
      String nextSymbol = (String) stack.pop(); // from parse stack

      if (isTerminal(nextSymbol)) {
        int symbolType = tokens.getTokenType(nextSymbol);
        if (symbolType == nextToken.type())
          lastToken = source().nextToken(); // advance to next and assign it to
                                            // lastToken
        else
          throw new ParsingException("Expected " + nextSymbol + ", saw " + nextToken.toString());
      } else if (isSemanticAction(nextSymbol)) {
        SemanticAction action = SemanticActionFactory.MakeAction(nextSymbol, source().lineNumber());
        action.execute(semanticStack, lastToken);
      } else {
        ParseAction action = table.lookup(nextSymbol, tokens.getTokenName(nextToken.type()));
        action.execute(stack);
      }
      previousToken = nextToken;

    } while (!stack.empty());

    System.out.println("Parse complete!");
    // Return the value on top of the semantic stack
    Program p = (Program) semanticStack.pop();
    return p;
  }

  private boolean isTerminal(String symbol) {
    return grammar.isTerminal(symbol);
  }

  private boolean isSemanticAction(String symbol) {
    return Grammar.IsSemanticAction(symbol);
  }
}
