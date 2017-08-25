package playerc.test;

import java.io.*;
import playerc.*;
import playerc.abstractsyntax.*;
import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Aug 24, 2005
 */
public class TestTypeChecker extends TestCase
{
   private TypeChecker checker;
   
   public void testOne() throws IOException
   {
	   //checker.check(makeAST("playerc/test/source/TestTypeChecker1.txt"));
	   checker.check(makeAST("playerc/test/source/TestTypeChecker2.txt"));
   
     // checker.check(makeAST("Compiler/testTypeChecker2.txt"));
      //assertEquals(39, checker.foundErrors());
   }
   
   private Program makeAST(String file)
   {
      PlayerScanner source = null;
      try { source = new PlayerScanner(new BufferedReader(new FileReader(file))); }
      catch (FileNotFoundException e) { e.printStackTrace(); }
      Grammar gP = new Grammar("../playerc/playergrammar", "EMPTY", "->", "|");
      TableDrivenParser parser = new TableDrivenParser(source, gP, new PlayerTokens());      
      return (Program)parser.parse();
   } 
   
   public void setUp()
   {
      checker = new TypeChecker();
   }
}