package playerc;

import java.io.*;

import playerc.abstractsyntax.AbstractSyntaxTree;


/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 03 2005
 * modified Sep 04 2017
 */
public abstract class Parser
{
   private Scanner source;

   public Parser(Scanner source) { this.source = source; }

   public AbstractSyntaxTree parse()
   {
       AbstractSyntaxTree answer = null;
       try { answer = parseProgram(); }
       catch (Exception e) { System.err.println(e); }
       return answer;
   }

   protected abstract AbstractSyntaxTree parseProgram() 
   	throws SemanticException, ParsingException, LexicalException, IOException;   

   protected Scanner source() { return source; }
}
