/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1 Comment: token type list MUST be 0-based and be sequential:
 *          after adding all token names to a vector, their indexes will be
 *          interpreted as their token types.
 * 
 *          Aug 18 - Added keyword 'string' and token type KeyString = 29
 */
public class PlayerTokens {
  // value token types
  public static final int IntegerNumber = 0;
  public static final int RealNumber = 1;
  public static final int StringToken = 2;
  public static final int Identifier = 3;

  // keyword token types
  public static final int KeyAnd = 4;
  public static final int KeyArray = 5;
  public static final int KeyBegin = 6;
  public static final int KeyBoolean = 7;
  public static final int KeyBy = 8;
  public static final int KeyDo = 9;
  public static final int KeyElse = 10;
  public static final int KeyElseif = 11;
  public static final int KeyEnd = 12;
  public static final int KeyExit = 13;
  public static final int KeyFalse = 14;
  public static final int KeyFor = 15;
  public static final int KeyIf = 16;
  public static final int KeyInteger = 17;
  public static final int KeyLoop = 18;
  public static final int KeyNot = 19;
  public static final int KeyNull = 20;
  public static final int KeyOf = 21;
  public static final int KeyOr = 22;
  public static final int KeyProcedure = 23;
  public static final int KeyProgram = 24;
  public static final int KeyRead = 25;
  public static final int KeyReal = 26;
  public static final int KeyRecord = 27;
  public static final int KeyReturn = 28;
  public static final int KeyString = 29; // added; otherwize can't declare a
                                          // string variable!
  public static final int KeyThen = 30;
  public static final int KeyTo = 31;
  public static final int KeyTrue = 32;
  public static final int KeyType = 33;
  public static final int KeyVar = 34;
  public static final int KeyWrite = 35;

  // operator, punctuation & EOF token types
  public static final int AssignOp = 36;
  public static final int PlusOp = 37;
  public static final int MinusOp = 38;
  public static final int MultOp = 39;
  public static final int DivOp = 40;
  public static final int Less = 41;
  public static final int LessEql = 42;
  public static final int Greater = 43;
  public static final int GreaterEql = 44;
  public static final int Eql = 45;
  public static final int NotEql = 46;
  public static final int Colon = 47;
  public static final int SemiColon = 48;
  public static final int Comma = 49;
  public static final int Period = 50;
  public static final int LParen = 51;
  public static final int RParen = 52;
  public static final int LBracket = 53;
  public static final int RBracket = 54;
  public static final int LBrace = 55;
  public static final int RBrace = 56;
  public static final int LArrBracket = 57;
  public static final int RArrBracket = 58;
  public static final int EOFsymbol = 59;

  private Vector tokenNames;
  private HashMap wordTokens; // predefined(keywords) +
                              // user-defined(identifiers) symbols (for scanner)

  public PlayerTokens() {
    initTokenNames();
    initWordTokens();
  }

  public Token getWordToken(String s) // used by scanner
  {
    Token t = (Token) wordTokens.get(s);
    if (t == null) // if hasn't found - create new token and add to table, then
                   // return
    {
      t = new Token(Identifier, s);
      wordTokens.put(s, t);
    }
    return t;
  }

  public int getTokenType(String s) {
    return tokenNames.indexOf(s);
  }

  public String getTokenName(int type) {
    return (String) tokenNames.elementAt(type);
  }

  private void initWordTokens() // load all keywords
  {
    wordTokens = new HashMap();
    for (int i = KeyAnd; i <= KeyWrite; i++) {
      String symbol = trim(getTokenName(i));
      wordTokens.put(symbol, new Token(i));
    }
  }

  private void initTokenNames() {
    tokenNames = new Vector();
    tokenNames.addElement("integer");
    tokenNames.addElement("real");
    tokenNames.addElement("string");
    tokenNames.addElement("identifier");
    tokenNames.addElement("'and'");
    tokenNames.addElement("'array'");
    tokenNames.addElement("'begin'");
    tokenNames.addElement("'boolean'");
    tokenNames.addElement("'by'");
    tokenNames.addElement("'do'");
    tokenNames.addElement("'else'");
    tokenNames.addElement("'elseif'");
    tokenNames.addElement("'end'");
    tokenNames.addElement("'exit'");
    tokenNames.addElement("'false'");
    tokenNames.addElement("'for'");
    tokenNames.addElement("'if'");
    tokenNames.addElement("'integer'");
    tokenNames.addElement("'loop'");
    tokenNames.addElement("'not'");
    tokenNames.addElement("'null'");
    tokenNames.addElement("'of'");
    tokenNames.addElement("'or'");
    tokenNames.addElement("'procedure'");
    tokenNames.addElement("'program'");
    tokenNames.addElement("'read'");
    tokenNames.addElement("'real'");
    tokenNames.addElement("'record'");
    tokenNames.addElement("'return'");
    tokenNames.addElement("'string'"); // added; otherwize can't declare a
                                       // string variable!
    tokenNames.addElement("'then'");
    tokenNames.addElement("'to'");
    tokenNames.addElement("'true'");
    tokenNames.addElement("'type'");
    tokenNames.addElement("'var'");
    tokenNames.addElement("'write'");
    tokenNames.addElement("':='");
    tokenNames.addElement("'+'");
    tokenNames.addElement("'-'");
    tokenNames.addElement("'*'");
    tokenNames.addElement("'/'");
    tokenNames.addElement("'<'");
    tokenNames.addElement("'<='");
    tokenNames.addElement("'>'");
    tokenNames.addElement("'>='");
    tokenNames.addElement("'='");
    tokenNames.addElement("'<>'");
    tokenNames.addElement("':'");
    tokenNames.addElement("';'");
    tokenNames.addElement("','");
    tokenNames.addElement("'.'");
    tokenNames.addElement("'('");
    tokenNames.addElement("')'");
    tokenNames.addElement("'['");
    tokenNames.addElement("']'");
    tokenNames.addElement("'{'");
    tokenNames.addElement("'}'");
    tokenNames.addElement("'[<'");
    tokenNames.addElement("'>]'");
    tokenNames.addElement(Grammar.END_MARKER);
  }

  // removes single quotes around symbol
  private String trim(String s) {
    return s.substring(1, s.length() - 1);
  }
}
