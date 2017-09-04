package playerc;

import java.io.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jun 22 2005
 * modified Sep 04 2017
 */
public class PlayerScanner extends Scanner
{
	public static final int MAX_ID_LENGTH = 255;
	public static final int MAX_INT_LENGTH = (int)(Math.pow(2, 31)-1);   
 	private int counter;
 	private PlayerTokens tokenHelper;

	public PlayerScanner(Reader in) 
	{ 
	   super(in); 
	   counter = 0;
	   tokenHelper = new PlayerTokens();
   }
	
	protected Token getNextToken() throws IOException, LexicalException
	{
		int nextByte = getNextByte();
		if ( nextByte == -1 )
		   return new Token(PlayerTokens.EOFsymbol);       
		char c = (char) nextByte;
	   
		if (isNumericDigit(c))
		   return q1(c + "");
		else if (isLetter(c))
		{
		   counter++;
			return q8(c + "");
		}
		else if (c == '"')
		   return q4(""); //drop the opening quote
		else if (c == ':')
		   return q6(c + "");
		else if (c == '(')
		   return q9(c + "");		
		else if (c == '[')
		   return q13(c + "");	
		else if (c == '<')
		   return q15(c + "");	
		else if (c == '>')
		   return q18(c + "");			
		else if (c == '+')
		   return new Token(PlayerTokens.PlusOp);
		else if (c == '-')
		   return new Token(PlayerTokens.MinusOp);
		else if (c == '*')
		   return new Token(PlayerTokens.MultOp);
		else if (c == '/')
		   return new Token(PlayerTokens.DivOp);
		else if (c == '=')
		   return new Token(PlayerTokens.Eql);
		else if (c == '.')
		   return new Token(PlayerTokens.Period);
		else if (c == ',')
		   return new Token(PlayerTokens.Comma);
		else if (c == ';')
		   return new Token(PlayerTokens.SemiColon);
		else if (c == '{')
		   return new Token(PlayerTokens.LBrace);
		else if (c == '}')
		   return new Token(PlayerTokens.RBrace);
		else if (c == ']')
		   return new Token(PlayerTokens.RBracket);
		else if (c == ')')
		   return new Token(PlayerTokens.RParen);		
		else
		   throw new LexicalException( "Illegal character: " + c );
	}
	
	private Token q1(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (isNumericDigit(c))
	      return q1(s + c);
	   else if (c == '.')
	      return q2(s + c);
	   else
	   {
	      int val = Integer.parseInt(s);
	      if (val <= MAX_INT_LENGTH)
	      {
	         unread(c);
	         return new Token(PlayerTokens.IntegerNumber, val);
	      }
	      else
	         throw new LexicalException( "Identifier longer than max (" + 
	               MAX_ID_LENGTH + "): " + val);	   	      
	   }
	}
	
	private Token q2(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (isNumericDigit(c))
	      return q3(s + c);
	   else
	      throw new LexicalException( "Expected digit, found illegal character: " + c );
	}
	
	private Token q3(String s) throws IOException
	{
	   char c = read();
	   if (isNumericDigit(c))
	      return q3(s + c);
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.RealNumber, Double.parseDouble(s));
	   }
	}
	
	private Token q4(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (isStringChar(c))
	      return q4(s + c);
	   else if (c == '"')
	      return new Token(PlayerTokens.StringToken, s);	//drop the closing quote
	   else
	      throw new LexicalException( "Illegal string character: " + c );
	}
	
	private Token q6(String s) throws IOException
	{
	   char c = read();
	   if (c == '=')
	      return new Token(PlayerTokens.AssignOp);
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.Colon);
	   }
	}
	
	private Token q8(String s) throws IOException, LexicalException
	{   
	   char c = read();
	   if (isLetter(c) || isNumericDigit(c))
	   {
	      counter++;
	      return q8(s + c);
	   }
	   else
	      if (counter <= MAX_ID_LENGTH)
	      {
	         counter = 0;
	         unread(c);
	         return tokenHelper.getWordToken(s);
	      }
	      else
	         throw new LexicalException( "Identifier longer than max (" + 
	               MAX_ID_LENGTH + "): " + s);	   
	}
	
	private Token q9(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (c == '*')
	      return q10("");	//drop the opening (*
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.LParen);
	   }
	}
	
	private Token q10(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (c == '*')
	      return q11("");
	   else
	      return q10("");
	}
	
	private Token q11(String s) throws IOException, LexicalException
	{
	   char c = read();
	   if (c == ')')
	      return getNextToken();
	   else
	      return q10("");
	}
	
	private Token q13(String s) throws IOException
	{
	   char c = read();
	   if (c == '<')
	      return new Token(PlayerTokens.LArrBracket);
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.LBracket);
	   }
	}
	
	private Token q15(String s) throws IOException
	{
	   char c = read();
	   if (c == '=')
	      return new Token(PlayerTokens.LessEql);
	   else if (c == '>')
	      return new Token(PlayerTokens.NotEql);
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.Less);
	   }
	}
	
	private Token q18(String s) throws IOException
	{
	   char c = read();
	   if (c == '=')
	      return new Token(PlayerTokens.GreaterEql);
	   else if (c == ']')
	      return new Token(PlayerTokens.RArrBracket);
	   else
	   {
	      unread(c);
	      return new Token(PlayerTokens.Greater);
	   }
	}	
}