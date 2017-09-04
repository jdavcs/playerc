package playerc;

import java.util.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 15 2005
 * modified Sep 04 2017
 */
public class PrintingUtility
{
   public static void main(String[] args) throws ParsingException
   {  
      
      Grammar gp = new Grammar("Compiler/playerGrammar.txt", "?", "->", "|");
      System.out.println(gp.toString());
      
      
      
      
      System.out.println("GRAMMAR playerGrammar.txt:\n\n" + gp.toString());
      System.out.println("GRAMMAR playerGrammar.txt with semanic actions:\n\n" + gp.toStringWithSemActions());
      System.out.println("FIRSTS:\n\n" + gp.firstsToString());    
      System.out.println("FOLLOWS:\n\n" + gp.followsToString()); 
      System.out.println("PARSING TABLE:\n\n" + gp.getParsingTable().toString() + "\n\n"); 
      
      
      Grammar g1 = new Grammar("Compiler/testGrammar1.txt", "null", "->", "|"); 
      System.out.println("GRAMMAR testGrammar1.txt:\n\n" + g1.toString());
      System.out.println("FIRSTS:\n\n" + g1.firstsToString());  
      System.out.println("FOLLOWS:\n\n" + g1.followsToString());       
      System.out.println("PARSING TABLE:\n\n" + g1.getParsingTable().toString() + "\n\n"); 
      
      Grammar g2 = new Grammar("Compiler/testGrammar2.txt", "null", "->", "|");
      System.out.println("GRAMMAR testGrammar2.txt:\n\n" + g2.toString());
      System.out.println("FIRSTS:\n\n" + g2.firstsToString());    
      System.out.println("FOLLOWS:\n\n" + g2.followsToString()); 
      System.out.println("PARSING TABLE:\n\n" + g2.getParsingTable().toString() + "\n\n"); 
      
      Grammar g3 = new Grammar("Compiler/testGrammar3.txt", "null", "->", "|");
      System.out.println("GRAMMAR testGrammar3.txt:\n\n" + g3.toString());
      System.out.println("FIRSTS:\n\n" + g3.firstsToString());            
      System.out.println("FOLLOWS:\n\n" + g3.followsToString());  
      System.out.println("PARSING TABLE:\n\n" + g3.getParsingTable().toString() + "\n\n"); 
      
      Grammar g4 = new Grammar("Compiler/testGrammar4.txt", "?", ":=", "|");
      System.out.println("GRAMMAR testGrammar4.txt:\n\n" + g4.toString());
      System.out.println("FIRSTS:\n\n" + g4.firstsToString());         
      System.out.println("FOLLOWS:\n\n" + g4.followsToString()); 
      System.out.println("PARSING TABLE:\n\n" + g4.getParsingTable().toString() + "\n\n"); 
      
      Grammar g5 = new Grammar("Compiler/testGrammar5.txt", "null", "->", "|");
      System.out.println("GRAMMAR testGrammar5.txt:\n\n" + g5.toString());
      System.out.println("FIRSTS:\n\n" + g5.firstsToString());    
      System.out.println("FOLLOWS:\n\n" + g5.followsToString()); 
      System.out.println("PARSING TABLE:\n\n" + g5.getParsingTable().toString() + "\n\n");       
      
      Grammar g6 = new Grammar("Compiler/testGrammar6.txt", "null", "->", "|");
      System.out.println("GRAMMAR testGrammar6.txt:\n\n" + g6.toString());
      System.out.println("FIRSTS:\n\n" + g6.firstsToString());          
      System.out.println("FOLLOWS:\n\n" + g6.followsToString()); 
      System.out.println("PARSING TABLE:\n\n" + g6.getParsingTable().toString() + "\n\n"); 
                
      Grammar g7 = new Grammar("Compiler/testGrammar7.txt", "null", "->", "|"); 
      System.out.println("GRAMMAR testGrammar7.txt:\n\n" + g7.toString());
      System.out.println("FIRSTS:\n\n" + g7.firstsToString()); 
      System.out.println("FOLLOWS:\n\n" + g7.followsToString());  
      System.out.println("PARSING TABLE:\n\n" + g7.getParsingTable().toString() + "\n\n"); 
      
      Grammar g8 = new Grammar("Compiler/testGrammar8.txt", "?", ":=", "|");
      System.out.println("GRAMMAR testGrammar4.txt:\n\n" + g8.toString());
      System.out.println("FIRSTS:\n\n" + g8.firstsToString());         
      System.out.println("FOLLOWS:\n\n" + g8.followsToString());    
      System.out.println("PARSING TABLE:\n\n" + g8.getParsingTable().toString() + "\n\n"); 
      
      Grammar g9 = new Grammar("Compiler/testGrammar9.txt", "?", "->", "|");
      System.out.println("GRAMMAR testGrammar9.txt:\n\n" + g9.toString());
      System.out.println("FIRSTS:\n\n" + g9.firstsToString());         
      System.out.println("FOLLOWS:\n\n" + g9.followsToString());           
      System.out.println("PARSING TABLE:\n\n" + g9.getParsingTable().toString() + "\n\n"); 
      
      
      
      
      
      //this is used to generate the code for testing a grammar's firsts function    
      
      String gn1 = "P";
      Grammar gP1 = new Grammar("Compiler/playerGrammar.txt", "?", "->", "|");
      HashMap allSets1 = gP1.firsts();
      Iterator i1 = allSets1.keySet().iterator();      
      int n1 = 0;
      while (i1.hasNext())
      {
         n1++;
         String key = (String)i1.next();
         HashSet firsts = (HashSet)allSets1.get(key);
         System.out.println("\nHashSet hs" + n1 + " = g" + gn1 + ".getFirstSet(\"" + key + "\");");
         System.out.println("assertEquals(" + firsts.size() + ", hs" + n1 + ".size());");  
         Iterator j = firsts.iterator();
         while (j.hasNext())
            System.out.println("assertTrue(hs" + n1 + ".contains(\"" + j.next() + "\"));");
      }
      
      
      //this is used to generate the code for testing a grammar's follows function
      
      String gn2 = "P";
      Grammar gP2 = new Grammar("Compiler/playerGrammar.txt", "?", "->", "|");      
      HashMap allSets2 = gP2.follows();
      Iterator i2 = allSets2.keySet().iterator();      
      int n2 = 0;
      while (i2.hasNext())
      {
         n2++;
         String key = (String)i2.next();
         HashSet follows = (HashSet)allSets2.get(key);
         System.out.println("\nHashSet hs" + n2 + " = g" + gn2 + ".getFollowsSet(\"" + key + "\");");
         System.out.println("assertEquals(" + follows.size() + ", hs" + n2 + ".size());");  
         Iterator j = follows.iterator();
         while (j.hasNext())
            System.out.println("assertTrue(hs" + n2 + ".contains(\"" + j.next() + "\"));");
      } 
                
   }
}
