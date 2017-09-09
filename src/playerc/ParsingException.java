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
public class ParsingException extends Exception {
  public ParsingException(String s) {
    super(s);
  }

  public String toString() {
    return "PARSING EXCEPTION -- " + super.toString();
  }
}
