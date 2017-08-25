package playerc;

import java.io.*;

import playerc.abstractsyntax.AbstractSyntaxTree;


/**
 * Author: sergei
 * Created: Jul 3, 2005
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
