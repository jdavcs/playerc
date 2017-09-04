/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public abstract class AbstractSyntaxTree {
  private int lineNumber;

  public AbstractSyntaxTree(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public abstract void accept(Visitor v);

  public String toString() {
    return "AST";
  }

  public void setLineNumber(int number) {
    lineNumber = number;
  }

  public int lineNumber() {
    return lineNumber;
  }
}
