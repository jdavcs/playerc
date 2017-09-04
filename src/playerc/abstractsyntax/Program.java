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
public class Program extends AbstractSyntaxTree {
  private Identifier id;
  private DeclarationList decls;
  private StatementList stms;

  public Program(Identifier id, DeclarationList decls, StatementList stms, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.decls = decls;
    this.stms = stms;
  }

  public Identifier id() {
    return id;
  }

  public DeclarationList declarations() {
    return decls;
  }

  public StatementList statements() {
    return stms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
