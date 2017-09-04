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
public class FPSection extends AbstractSyntaxTree {
  private Identifier id;
  private Typename tn;

  public FPSection(Identifier id, Typename tn, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.tn = tn;
  }

  public Identifier id() {
    return id;
  }

  public Typename typename() {
    return tn;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
