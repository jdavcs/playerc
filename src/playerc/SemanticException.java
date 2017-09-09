/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class SemanticException extends Exception {
  private int lineNumber;

  public SemanticException(String s, int line) {
    super(s);
    lineNumber = line;
  }

  public String toString() {
    return "LINE " + lineNumber + ": " + super.toString();
  }
}