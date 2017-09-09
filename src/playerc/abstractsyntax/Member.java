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
public class Member extends AbstractSyntaxTree {
  private Identifier id;
  private Typename typename;

  public Member(Identifier id, Typename typename, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.typename = typename;
  }

  public void accept(Visitor v) {
  }

  public Identifier id() {
    return id;
  }

  public Typename typename() {
    return typename;
  }
}
