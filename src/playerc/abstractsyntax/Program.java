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
public class Program extends AbstractSyntaxTree {
  private Identifier name;
  private Body body;

  public Program(Identifier name, Body body, int lineNumber) {
    super(lineNumber);
    this.name = name;
    this.body = body;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Identifier name() {
    return name;
  }

  public Body body() {
    return body;
  }
}