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
public abstract class Expression extends AbstractSyntaxTree {
  private Expression parent;

  public Expression(int lineNumber) {
    super(lineNumber);
  }

  public Expression parent() {
    return parent;
  }

  public void setParent(Expression parent) {
    this.parent = parent;
  }
}
