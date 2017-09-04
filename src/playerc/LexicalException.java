/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class LexicalException extends Exception {
  public LexicalException(String s) {
    super(s);
  }

  public String toString() {
    return "LEXICAL EXCEPTION -- " + super.toString();
  }
}