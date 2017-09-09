/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class TypeDeclaration extends Declaration {
  private NewTypename typename;
  private NewType type;

  public TypeDeclaration(NewTypename typename, NewType type, int lineNumber) {
    super(lineNumber);
    this.typename = typename;
    this.type = type;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public NewTypename typename() {
    return typename;
  }

  public NewType type() {
    return type;
  }
}
