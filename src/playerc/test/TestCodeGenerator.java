package playerc.test;

import java.io.*;

import playerc.CodeGenerator;
import playerc.Grammar;
import playerc.PlayerScanner;
import playerc.PlayerTokens;
import playerc.TableDrivenParser;
import playerc.TypeChecker;
import playerc.abstractsyntax.*;
import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Sep 5, 2005
 */
public class TestCodeGenerator extends TestCase
{
   private CodeGenerator generator;

//   public void testOne()
//   {
//      Program program = makeAST("compiler/testCodeGenerator1.txt");
//      TypeChecker checker = new TypeChecker();
//      checker.check(program);
//      if (checker.foundErrors() == 0)
//         generator.generate(program);       
//   }
   
   public void testTwo()
   {
      Program program = makeAST("compiler/samples/BinaryInsertionSorter2.player");
      TypeChecker checker = new TypeChecker();
      checker.check(program);
     // if (checker.foundErrors() == 0)
       //  generator.generate(program);       
   }   
   
   private Program makeAST(String file)
   {
      PlayerScanner source = null;
      try { source = new PlayerScanner(new BufferedReader(new FileReader(file))); }
      catch (FileNotFoundException e) { e.printStackTrace(); }
      Grammar gP = new Grammar("test/source/newgrammar", "EMPTY", "->", "|");
      TableDrivenParser parser = new TableDrivenParser(source, gP, new PlayerTokens());      
      return (Program)parser.parse();
   } 
   
   public void setUp()
   {
      generator = new CodeGenerator("output");
   }
}
