/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import junit.framework.TestCase;
import playerc.LexicalException;
import playerc.PlayerScanner;
import playerc.PlayerTokens;
import playerc.Scanner;
import playerc.Token;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ScannerTest extends TestCase {
  public void testLineNumber() throws IOException, LexicalException {
    Scanner scanner = makeScanner("a b \n c d \n e \n f");
    assertEquals(1, scanner.lineNumber());
    scanner.nextToken(); // a
    assertEquals(1, scanner.lineNumber());
    scanner.nextToken(); // b
    assertEquals(1, scanner.lineNumber());
    scanner.nextToken(); // c
    assertEquals(2, scanner.lineNumber());
    scanner.nextToken(); // d
    assertEquals(2, scanner.lineNumber());
    scanner.nextToken(); // e
    assertEquals(3, scanner.lineNumber());
    scanner.nextToken(); // f
    assertEquals(4, scanner.lineNumber());
  }

  public void testSingleNoValToken() throws IOException, LexicalException {
    runSingleNoValTokenTest(":", PlayerTokens.Colon);
    runSingleNoValTokenTest(":=", PlayerTokens.AssignOp);
    runSingleNoValTokenTest("(", PlayerTokens.LParen);
    runSingleNoValTokenTest("[", PlayerTokens.LBracket);
    runSingleNoValTokenTest("[<", PlayerTokens.LArrBracket);
    runSingleNoValTokenTest("<", PlayerTokens.Less);
    runSingleNoValTokenTest("<=", PlayerTokens.LessEql);
    runSingleNoValTokenTest("<>", PlayerTokens.NotEql);
    runSingleNoValTokenTest(">", PlayerTokens.Greater);
    runSingleNoValTokenTest(">=", PlayerTokens.GreaterEql);
    runSingleNoValTokenTest(">]", PlayerTokens.RArrBracket);
    runSingleNoValTokenTest("+", PlayerTokens.PlusOp);
    runSingleNoValTokenTest("-", PlayerTokens.MinusOp);
    runSingleNoValTokenTest("*", PlayerTokens.MultOp);
    runSingleNoValTokenTest("/", PlayerTokens.DivOp);
    runSingleNoValTokenTest("=", PlayerTokens.Eql);
    runSingleNoValTokenTest(".", PlayerTokens.Period);
    runSingleNoValTokenTest(",", PlayerTokens.Comma);
    runSingleNoValTokenTest(";", PlayerTokens.SemiColon);
    runSingleNoValTokenTest("{", PlayerTokens.LBrace);
    runSingleNoValTokenTest("}", PlayerTokens.RBrace);
    runSingleNoValTokenTest("]", PlayerTokens.RBracket);
    runSingleNoValTokenTest(")", PlayerTokens.RParen);
    runSingleNoValTokenTest("and", PlayerTokens.KeyAnd);
    runSingleNoValTokenTest("array", PlayerTokens.KeyArray);
    runSingleNoValTokenTest("begin", PlayerTokens.KeyBegin);
    runSingleNoValTokenTest("boolean", PlayerTokens.KeyBoolean);
    runSingleNoValTokenTest("by", PlayerTokens.KeyBy);
    runSingleNoValTokenTest("do", PlayerTokens.KeyDo);
    runSingleNoValTokenTest("else", PlayerTokens.KeyElse);
    runSingleNoValTokenTest("elseif", PlayerTokens.KeyElseif);
    runSingleNoValTokenTest("end", PlayerTokens.KeyEnd);
    runSingleNoValTokenTest("exit", PlayerTokens.KeyExit);
    runSingleNoValTokenTest("false", PlayerTokens.KeyFalse);
    runSingleNoValTokenTest("for", PlayerTokens.KeyFor);
    runSingleNoValTokenTest("if", PlayerTokens.KeyIf);
    runSingleNoValTokenTest("integer", PlayerTokens.KeyInteger);
    runSingleNoValTokenTest("loop", PlayerTokens.KeyLoop);
    runSingleNoValTokenTest("not", PlayerTokens.KeyNot);
    runSingleNoValTokenTest("null", PlayerTokens.KeyNull);
    runSingleNoValTokenTest("of", PlayerTokens.KeyOf);
    runSingleNoValTokenTest("or", PlayerTokens.KeyOr);
    runSingleNoValTokenTest("procedure", PlayerTokens.KeyProcedure);
    runSingleNoValTokenTest("program", PlayerTokens.KeyProgram);
    runSingleNoValTokenTest("read", PlayerTokens.KeyRead);
    runSingleNoValTokenTest("real", PlayerTokens.KeyReal);
    runSingleNoValTokenTest("record", PlayerTokens.KeyRecord);
    runSingleNoValTokenTest("return", PlayerTokens.KeyReturn);
    runSingleNoValTokenTest("then", PlayerTokens.KeyThen);
    runSingleNoValTokenTest("to", PlayerTokens.KeyTo);
    runSingleNoValTokenTest("true", PlayerTokens.KeyTrue);
    runSingleNoValTokenTest("type", PlayerTokens.KeyType);
    runSingleNoValTokenTest("var", PlayerTokens.KeyVar);
    runSingleNoValTokenTest("write", PlayerTokens.KeyWrite);
  }

  public void testSingleIdToken() throws IOException, LexicalException {
    PlayerTokens tokens = new PlayerTokens();
    String id = "anduril680365test";
    Scanner scanner = makeScanner(id);
    Token t1 = tokens.getWordToken(id);
    Token t2 = scanner.nextToken();
    assertEquals(t1.type(), t2.type());
    assertEquals(t1.getSValue(), t2.getSValue());
    assertEquals(id, t2.getSValue());
  }

  public void testSingleIntToken() throws IOException, LexicalException {
    Scanner scanner = makeScanner("59067");
    Token t1 = new Token(PlayerTokens.IntegerNumber, 59067);
    Token t2 = scanner.nextToken();
    assertEquals(t1.type(), t2.type());
    assertEquals(t1.getIValue(), t2.getIValue());
    assertEquals(59067, t2.getIValue());
  }

  public void testSingleRealToken() throws IOException, LexicalException {
    Scanner scanner = makeScanner("69057.4591");
    Token t1 = new Token(PlayerTokens.RealNumber, 69057.4591);
    Token t2 = scanner.nextToken();
    assertEquals(t1.type(), t2.type());
    assertTrue(t1.getDValue() == t2.getDValue());
    assertTrue(69057.4591 == t2.getDValue());
  }

  public void testSingleStringToken() throws IOException, LexicalException {
    String s = "~!@#$%^&*()_+=-098 7654321qwertyuiop[]\\';" + "lkjhgfdsazxcvbnm,./|}{POIUYTREWQASDFGHJKL:?><MNBVCXZ";
    Scanner scanner = makeScanner("\"" + s + "\"");
    Token t1 = new Token(PlayerTokens.StringToken, s);
    Token t2 = scanner.nextToken();
    assertEquals(t1.type(), t2.type());
    assertEquals(t1.getSValue(), t2.getSValue());
    assertEquals(s, t2.getSValue());
  }

  public void testIgnoreComment() throws IOException, LexicalException {
    String s = "(*\"\b\f\n\r\t~!@#$%^&*()_+=-098 7654321qwertyuiop[]\\';"
        + "lkjhgfdsazxcvbnm,./|}{POIUYTREWQASDFGHJKL:?><MNBVCXZ*)";
    Scanner scanner = makeScanner(12 + s + 3.2);
    Token t1 = new Token(PlayerTokens.IntegerNumber, 12);
    Token t2 = new Token(PlayerTokens.RealNumber, 3.2);
    Token t3 = scanner.nextToken();
    Token t4 = scanner.nextToken();
    assertEquals(t1.type(), t3.type());
    assertEquals(t2.type(), t4.type());
    assertEquals(t1.getIValue(), t3.getIValue());
    assertEquals(12, t3.getIValue());
    assertTrue(t2.getDValue() == t4.getDValue());
    assertTrue(3.2 == t4.getDValue());
  }

  public void testExceptionQ0() throws IOException, LexicalException {
    // illegal = illegal start of token characters
    char[] illegal = { '~', '`', '!', '@', '#', '$', '%', '^', '&', '_', '|', '\\', '\'', '?' };

    for (int i = 0; i < illegal.length; i++) {
      Scanner scanner = makeScanner("" + illegal[i]);
      try {
        scanner.nextToken();
        fail();
      } catch (LexicalException e) {
      }
    }
  }

  public void testExceptionQ2() throws IOException, LexicalException {
    // illegal = all non digits
    for (int i = 32; i < 48; i++) {
      Scanner scanner = makeScanner("1." + (char) i);
      try {
        scanner.nextToken();
        fail();
      } catch (LexicalException e) {
      }
    }
    for (int i = 58; i < 127; i++) {
      Scanner scanner = makeScanner("1." + (char) i);
      try {
        scanner.nextToken();
        fail();
      } catch (LexicalException e) {
      }
    }
  }

  public void testExceptionQ4() throws IOException, LexicalException {
    // illegal = all non-string
    for (int i = 0; i < 32; i++) {
      Scanner scanner = makeScanner("\"" + (char) i + "\"");
      try {
        scanner.nextToken();
        fail();
      } catch (LexicalException e) {
      }
    }
    Scanner scanner = makeScanner("\"" + (char) 127 + "\"");
    try {
      scanner.nextToken();
      fail();
    } catch (LexicalException e) {
    }
  }

  public void testisWhitespace() {
    Scanner scanner = makeScanner("");
    assertTrue(scanner.isWhitespace(' '));
    assertTrue(scanner.isWhitespace('\b'));
    assertTrue(scanner.isWhitespace('\f'));
    assertTrue(scanner.isWhitespace('\n'));
    assertTrue(scanner.isWhitespace('\r'));
    assertTrue(scanner.isWhitespace('\t'));
  }

  public void testIsNumericDigit() {
    Scanner scanner = makeScanner("");
    for (int i = 0; i < 48; i++)
      assertFalse(scanner.isNumericDigit((char) i));
    for (int i = 48; i < 58; i++)
      assertTrue(scanner.isNumericDigit((char) i));
    for (int i = 58; i < 128; i++)
      assertFalse(scanner.isNumericDigit((char) i));
  }

  public void testIsLetter() {
    Scanner scanner = makeScanner("");
    for (int i = 0; i < 65; i++)
      assertFalse(scanner.isLetter((char) i));
    for (int i = 65; i < 91; i++)
      assertTrue(scanner.isLetter((char) i));
    for (int i = 91; i < 97; i++)
      assertFalse(scanner.isLetter((char) i));
    for (int i = 97; i < 123; i++)
      assertTrue(scanner.isLetter((char) i));
    for (int i = 123; i < 128; i++)
      assertFalse(scanner.isLetter((char) i));
  }

  public void testIsStringChar() {
    Scanner scanner = makeScanner("");
    for (int i = 0; i < 32; i++)
      assertFalse(scanner.isStringChar((char) i));
    for (int i = 32; i < 34; i++)
      assertTrue(scanner.isStringChar((char) i));
    for (int i = 35; i < 127; i++)
      assertTrue(scanner.isStringChar((char) i));
    assertFalse(scanner.isStringChar((char) 34));
    assertFalse(scanner.isStringChar((char) 127));
  }

  public void testMultipleTokensWithSpaces() throws IOException, LexicalException {
    PlayerTokens tokens = new PlayerTokens();
    String s = " 92 + (*12\nx*) ; 50.67 and [< \"some string\" x := ";
    Scanner scanner = makeScanner(s);
    Token t1 = scanner.nextToken();
    Token t2 = scanner.nextToken();
    Token t3 = scanner.nextToken();
    Token t4 = scanner.nextToken();
    Token t5 = scanner.nextToken();
    Token t6 = scanner.nextToken();
    Token t7 = scanner.nextToken();
    Token t8 = scanner.nextToken();
    Token t9 = scanner.nextToken();
    Token t1a = new Token(PlayerTokens.IntegerNumber, 92);
    Token t2a = new Token(PlayerTokens.PlusOp);
    Token t3a = new Token(PlayerTokens.SemiColon);
    Token t4a = new Token(PlayerTokens.RealNumber, 50.67);
    Token t5a = tokens.getWordToken("and");
    Token t6a = new Token(PlayerTokens.LArrBracket);
    Token t7a = new Token(PlayerTokens.StringToken, "some string");
    Token t8a = tokens.getWordToken("x");
    Token t9a = new Token(PlayerTokens.AssignOp);

    assertEquals(t1.type(), t1a.type());
    assertEquals(t2.type(), t2a.type());
    assertEquals(t3.type(), t3a.type());
    assertEquals(t4.type(), t4a.type());
    assertEquals(t5.type(), t5a.type());
    assertEquals(t6.type(), t6a.type());
    assertEquals(t7.type(), t7a.type());
    assertEquals(t8.type(), t8a.type());
    assertEquals(t9.type(), t9a.type());

    assertEquals(t1.getIValue(), t1a.getIValue());
    assertEquals(92, t1a.getIValue());

    assertTrue(t4.getDValue() == t4a.getDValue());
    assertTrue(50.67 == t4a.getDValue());

    assertEquals(t7.getSValue(), t7a.getSValue());
    assertEquals("some string", t7a.getSValue());

    Token t10 = scanner.nextToken();
    assertEquals(PlayerTokens.EOFsymbol, t10.type());
  }

  public void testMultipleTokensWithoutSpaces() throws IOException, LexicalException {
    PlayerTokens tokens = new PlayerTokens();
    String s = "92+(*12\nx*);50.67and[<\"some string\"x:=";
    Scanner scanner = makeScanner(s);
    Token t1 = scanner.nextToken();
    Token t2 = scanner.nextToken();
    Token t3 = scanner.nextToken();
    Token t4 = scanner.nextToken();
    Token t5 = scanner.nextToken();
    Token t6 = scanner.nextToken();
    Token t7 = scanner.nextToken();
    Token t8 = scanner.nextToken();
    Token t9 = scanner.nextToken();
    Token t1a = new Token(PlayerTokens.IntegerNumber, 92);
    Token t2a = new Token(PlayerTokens.PlusOp);
    Token t3a = new Token(PlayerTokens.SemiColon);
    Token t4a = new Token(PlayerTokens.RealNumber, 50.67);
    Token t5a = tokens.getWordToken("and");
    Token t6a = new Token(PlayerTokens.LArrBracket);
    Token t7a = new Token(PlayerTokens.StringToken, "some string");
    Token t8a = tokens.getWordToken("x");
    Token t9a = new Token(PlayerTokens.AssignOp);

    assertEquals(t1.type(), t1a.type());
    assertEquals(t2.type(), t2a.type());
    assertEquals(t3.type(), t3a.type());
    assertEquals(t4.type(), t4a.type());
    assertEquals(t5.type(), t5a.type());
    assertEquals(t6.type(), t6a.type());
    assertEquals(t7.type(), t7a.type());
    assertEquals(t8.type(), t8a.type());
    assertEquals(t9.type(), t9a.type());

    assertEquals(t1.getIValue(), t1a.getIValue());
    assertEquals(92, t1a.getIValue());

    assertTrue(t4.getDValue() == t4a.getDValue());
    assertTrue(50.67 == t4a.getDValue());

    assertEquals(t7.getSValue(), t7a.getSValue());
    assertEquals("some string", t7a.getSValue());

    Token t10 = scanner.nextToken();
    assertEquals(PlayerTokens.EOFsymbol, t10.type());
  }

  public void testMaxIdLength() throws IOException, LexicalException {
    int max = PlayerScanner.MAX_ID_LENGTH;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < max + 1; i++)
      sb.append('a');

    Scanner scanner = makeScanner(sb.toString());
    try {
      scanner.nextToken();
      fail();
    } catch (LexicalException e) {
    }
  }

  public void testMaxIntLength() throws IOException, LexicalException {
    // not implemented
  }

  private void runSingleNoValTokenTest(String text, int type) throws IOException, LexicalException {
    Scanner scanner = makeScanner(text);
    Token t1 = new Token(type);
    Token t2 = scanner.nextToken();
    assertEquals(t1.type(), t2.type());
  }

  private Scanner makeScanner(String in) {
    return new PlayerScanner(new PushbackReader(new StringReader(in)));
  }
}