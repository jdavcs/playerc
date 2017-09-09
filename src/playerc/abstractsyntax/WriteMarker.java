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
public class WriteMarker extends AbstractSyntaxTree {
  public WriteMarker() {
    super(-1);
  }

  public void accept(Visitor v) {
  }
}