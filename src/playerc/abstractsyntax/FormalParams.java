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
public class FormalParams extends AbstractSyntaxTree {
  private FPSectionList fpsections;

  public FormalParams(FPSectionList fpsections, int lineNumber) {
    super(lineNumber);
    this.fpsections = fpsections;
  }

  public FPSectionList fpsections() {
    return fpsections;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
