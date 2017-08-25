package playerc.test;

import playerc.*;
import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Jun 18, 2005
 */
public class TestPlayerTokens extends TestCase
{
   private PlayerTokens tokens;
   
   public void testGetWordTokenKey()
   {
      Token t = tokens.getWordToken("begin");
      assertEquals(PlayerTokens.KeyBegin, t.type());
      assertEquals(0, t.getIValue());
      assertTrue(t.getDValue() == 0);
      assertEquals("", t.getSValue());
   }     
   
   public void testGetWordTokenVariable()
   {
      Token t = tokens.getWordToken("anduril");
      assertEquals(PlayerTokens.Identifier, t.type());
      assertEquals(0, t.getIValue());
      assertTrue(t.getDValue() == 0);
      assertEquals("anduril", t.getSValue());
   }  
   
   public void testGetTokenType()
   {
	   assertEquals(PlayerTokens.IntegerNumber, tokens.getTokenType("LITERAL-INTEGER"));
	   assertEquals(PlayerTokens.RealNumber, tokens.getTokenType("LITERAL-REAL"));	   
	   assertEquals(PlayerTokens.StringToken, tokens.getTokenType("LITERAL-STRING"));
	   assertEquals(PlayerTokens.Identifier, tokens.getTokenType("IDENTIFIER"));   
	   assertEquals(PlayerTokens.KeyAnd, tokens.getTokenType("and"));
	   assertEquals(PlayerTokens.KeyArray, tokens.getTokenType("array"));
	   assertEquals(PlayerTokens.KeyBegin, tokens.getTokenType("begin"));
	   assertEquals(PlayerTokens.KeyBoolean, tokens.getTokenType("boolean"));
	   assertEquals(PlayerTokens.KeyBy, tokens.getTokenType("by"));
	   assertEquals(PlayerTokens.KeyDo, tokens.getTokenType("do"));
	   assertEquals(PlayerTokens.KeyElse, tokens.getTokenType("else"));
	   assertEquals(PlayerTokens.KeyElseif, tokens.getTokenType("elseif"));
	   assertEquals(PlayerTokens.KeyEnd, tokens.getTokenType("end"));
	   assertEquals(PlayerTokens.KeyExit, tokens.getTokenType("exit"));
	   assertEquals(PlayerTokens.KeyFalse, tokens.getTokenType("false"));
	   assertEquals(PlayerTokens.KeyFor, tokens.getTokenType("for"));
	   assertEquals(PlayerTokens.KeyIf, tokens.getTokenType("if"));
	   assertEquals(PlayerTokens.KeyInteger, tokens.getTokenType("integer"));
	   assertEquals(PlayerTokens.KeyLoop, tokens.getTokenType("loop"));
	   assertEquals(PlayerTokens.KeyNot, tokens.getTokenType("not"));	
	   assertEquals(PlayerTokens.KeyNull, tokens.getTokenType("null"));
	   assertEquals(PlayerTokens.KeyOf, tokens.getTokenType("of"));
	   assertEquals(PlayerTokens.KeyOr, tokens.getTokenType("or"));
	   assertEquals(PlayerTokens.KeyProcedure, tokens.getTokenType("procedure"));
	   assertEquals(PlayerTokens.KeyProgram, tokens.getTokenType("program"));
	   assertEquals(PlayerTokens.KeyRead, tokens.getTokenType("read"));
	   assertEquals(PlayerTokens.KeyReal, tokens.getTokenType("real"));
	   assertEquals(PlayerTokens.KeyRecord, tokens.getTokenType("record"));
	   assertEquals(PlayerTokens.KeyReturn, tokens.getTokenType("return"));
	   assertEquals(PlayerTokens.KeyString, tokens.getTokenType("string"));
	   assertEquals(PlayerTokens.KeyThen, tokens.getTokenType("then"));
	   assertEquals(PlayerTokens.KeyTo, tokens.getTokenType("to"));
	   assertEquals(PlayerTokens.KeyTrue, tokens.getTokenType("true"));	
	   assertEquals(PlayerTokens.KeyType, tokens.getTokenType("type"));
	   assertEquals(PlayerTokens.KeyVar, tokens.getTokenType("var"));
	   assertEquals(PlayerTokens.KeyWrite, tokens.getTokenType("write"));
	   assertEquals(PlayerTokens.AssignOp, tokens.getTokenType(":="));
	   assertEquals(PlayerTokens.PlusOp, tokens.getTokenType("+"));
	   assertEquals(PlayerTokens.MinusOp, tokens.getTokenType("-"));
	   assertEquals(PlayerTokens.MultOp, tokens.getTokenType("*"));
	   assertEquals(PlayerTokens.DivOp, tokens.getTokenType("/"));
	   assertEquals(PlayerTokens.Less, tokens.getTokenType("<"));
	   assertEquals(PlayerTokens.LessEql, tokens.getTokenType("<="));
	   assertEquals(PlayerTokens.Greater, tokens.getTokenType(">"));
	   assertEquals(PlayerTokens.GreaterEql, tokens.getTokenType(">="));
	   assertEquals(PlayerTokens.Eql, tokens.getTokenType("="));
	   assertEquals(PlayerTokens.NotEql, tokens.getTokenType("<>"));
	   assertEquals(PlayerTokens.Colon, tokens.getTokenType(":"));
	   assertEquals(PlayerTokens.SemiColon, tokens.getTokenType(";"));
	   assertEquals(PlayerTokens.Comma, tokens.getTokenType(","));
	   assertEquals(PlayerTokens.Period, tokens.getTokenType("."));
	   assertEquals(PlayerTokens.LParen, tokens.getTokenType("("));
	   assertEquals(PlayerTokens.RParen, tokens.getTokenType(")"));
	   assertEquals(PlayerTokens.LBracket, tokens.getTokenType("["));
	   assertEquals(PlayerTokens.RBracket, tokens.getTokenType("]"));
	   assertEquals(PlayerTokens.LBrace, tokens.getTokenType("{"));;
	   assertEquals(PlayerTokens.RBrace, tokens.getTokenType("}"));
	   assertEquals(PlayerTokens.LArrBracket, tokens.getTokenType("[<"));
	   assertEquals(PlayerTokens.RArrBracket, tokens.getTokenType(">]"));   
   }
   
   public void testGetTokenName()
   {	
	   assertEquals("LITERAL-INTEGER", tokens.getTokenName(PlayerTokens.IntegerNumber));
	   assertEquals("LITERAL-REAL", tokens.getTokenName(PlayerTokens.RealNumber));
	   assertEquals("LITERAL-STRING", tokens.getTokenName(PlayerTokens.StringToken));
	   assertEquals("IDENTIFIER", tokens.getTokenName(PlayerTokens.Identifier));	   
	   assertEquals("and", tokens.getTokenName(PlayerTokens.KeyAnd));
	   assertEquals("array", tokens.getTokenName(PlayerTokens.KeyArray));
	   assertEquals("begin", tokens.getTokenName(PlayerTokens.KeyBegin));
	   assertEquals("boolean", tokens.getTokenName(PlayerTokens.KeyBoolean));
	   assertEquals("by", tokens.getTokenName(PlayerTokens.KeyBy));
	   assertEquals("do", tokens.getTokenName(PlayerTokens.KeyDo));
	   assertEquals("else", tokens.getTokenName(PlayerTokens.KeyElse));
	   assertEquals("elseif", tokens.getTokenName(PlayerTokens.KeyElseif));
	   assertEquals("end", tokens.getTokenName(PlayerTokens.KeyEnd));
	   assertEquals("exit", tokens.getTokenName(PlayerTokens.KeyExit));
	   assertEquals("false", tokens.getTokenName(PlayerTokens.KeyFalse));
	   assertEquals("for", tokens.getTokenName(PlayerTokens.KeyFor));
	   assertEquals("if", tokens.getTokenName(PlayerTokens.KeyIf));
	   assertEquals("integer", tokens.getTokenName(PlayerTokens.KeyInteger));	   
	   assertEquals("loop", tokens.getTokenName(PlayerTokens.KeyLoop));
	   assertEquals("not", tokens.getTokenName(PlayerTokens.KeyNot));	
	   assertEquals("null", tokens.getTokenName(PlayerTokens.KeyNull));
	   assertEquals("of", tokens.getTokenName(PlayerTokens.KeyOf));
	   assertEquals("or", tokens.getTokenName(PlayerTokens.KeyOr));
	   assertEquals("procedure", tokens.getTokenName(PlayerTokens.KeyProcedure));	
	   assertEquals("program", tokens.getTokenName(PlayerTokens.KeyProgram));	
	   assertEquals("read", tokens.getTokenName(PlayerTokens.KeyRead));
	   assertEquals("real", tokens.getTokenName(PlayerTokens.KeyReal));
	   assertEquals("record", tokens.getTokenName(PlayerTokens.KeyRecord));
	   assertEquals("return", tokens.getTokenName(PlayerTokens.KeyReturn));
	   assertEquals("string", tokens.getTokenName(PlayerTokens.KeyString));
	   assertEquals("then", tokens.getTokenName(PlayerTokens.KeyThen));
	   assertEquals("to", tokens.getTokenName(PlayerTokens.KeyTo));
	   assertEquals("true", tokens.getTokenName(PlayerTokens.KeyTrue));	
	   assertEquals("type", tokens.getTokenName(PlayerTokens.KeyType));
	   assertEquals("var", tokens.getTokenName(PlayerTokens.KeyVar));
	   assertEquals("write", tokens.getTokenName(PlayerTokens.KeyWrite));
	   assertEquals(":=", tokens.getTokenName(PlayerTokens.AssignOp)); 
	   assertEquals("+", tokens.getTokenName(PlayerTokens.PlusOp));
	   assertEquals("-", tokens.getTokenName(PlayerTokens.MinusOp));
	   assertEquals("*", tokens.getTokenName(PlayerTokens.MultOp));
	   assertEquals("/", tokens.getTokenName(PlayerTokens.DivOp));
	   assertEquals("<", tokens.getTokenName(PlayerTokens.Less));
	   assertEquals("<=", tokens.getTokenName(PlayerTokens.LessEql));
	   assertEquals(">", tokens.getTokenName(PlayerTokens.Greater));
	   assertEquals(">=", tokens.getTokenName(PlayerTokens.GreaterEql));
	   assertEquals("=", tokens.getTokenName(PlayerTokens.Eql));
	   assertEquals("<>", tokens.getTokenName(PlayerTokens.NotEql));
	   assertEquals(":", tokens.getTokenName(PlayerTokens.Colon));
	   assertEquals(";", tokens.getTokenName(PlayerTokens.SemiColon));
	   assertEquals(",", tokens.getTokenName(PlayerTokens.Comma));
	   assertEquals(".", tokens.getTokenName(PlayerTokens.Period));
	   assertEquals("(", tokens.getTokenName(PlayerTokens.LParen));
	   assertEquals(")", tokens.getTokenName(PlayerTokens.RParen));
	   assertEquals("[", tokens.getTokenName(PlayerTokens.LBracket));
	   assertEquals("]", tokens.getTokenName(PlayerTokens.RBracket));
	   assertEquals("{", tokens.getTokenName(PlayerTokens.LBrace));
	   assertEquals("}", tokens.getTokenName(PlayerTokens.RBrace));
	   assertEquals("[<", tokens.getTokenName(PlayerTokens.LArrBracket));
	   assertEquals(">]", tokens.getTokenName(PlayerTokens.RArrBracket));
   }
      
   protected void setUp() { tokens = new PlayerTokens(); }
}