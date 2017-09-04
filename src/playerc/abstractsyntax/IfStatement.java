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
public class IfStatement extends Statement {
  private IfthenFragmentList ifthenFrags;
  private ElseFragment elseFrag;

  public IfStatement(IfthenFragmentList ifthenFrags, ElseFragment elseFrag, int lineNumber) {
    super(lineNumber);
    this.ifthenFrags = ifthenFrags;
    this.elseFrag = elseFrag;
  }

  public IfthenFragmentList ifthenFrags() {
    return ifthenFrags;
  }

  public ElseFragment elseFrag() {
    return elseFrag;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
