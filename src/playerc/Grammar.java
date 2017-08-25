package playerc;

import java.io.IOException;
import java.util.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	Jul 15, 2005
 * Comment: 	Grammar MUST start with the start symbol production.
 * 					"derives", "or", and "null" symbols are passed to the constructor.
 */
public class Grammar
{
   public static final String END_MARKER= "$";   
   public static boolean IsSemanticAction(String s) { return s.startsWith("MAKE-"); }
   
   private final String FIRST_NAME = "FIRST";
   private final String FOLLOWS_NAME = "FOLLOWS";
   
   private String nullSymbol;
   private Vector productions;			//WITHOUT semantic actions
   private Vector semProductions; 	//WITH semantic actions
   private HashMap firsts; 
   private HashMap follows;   
   public Vector terminals;
   private Vector nonterminals; 
      
   public Grammar(String file, String nullSymbol, String derivesSymbol, String orSymbol)
   {
      this.nullSymbol = nullSymbol;      
      GrammarReader r = new GrammarReader(derivesSymbol, orSymbol);
      try {    	
         productions = r.getProductions(file, false); 
         semProductions = r.getProductions(file, true);
         initData();  
      }
      catch (IOException e) { e.printStackTrace(); }
      catch (Exception e) { e.printStackTrace(); }        
   }
   
   public String toString()
   {
      StringBuffer buffer = new StringBuffer();      
      for (int i=0; i<productions.size(); i++)
         buffer.append( ((Production)productions.elementAt(i)).toString() );
      return buffer.toString();
   }  
   
   public String toStringWithSemActions()
   {
      StringBuffer buffer = new StringBuffer();      
      for (int i=0; i<semProductions.size(); i++)
         buffer.append( ((Production)semProductions.elementAt(i)).toString() );
      return buffer.toString();
   }    
   
   public String firstsToString() { return mapToString(firsts, FIRST_NAME); }   
   
   public String followsToString() { return mapToString(follows, FOLLOWS_NAME); }
        
   public String getStartSymbol() { return ( (Production)productions.elementAt(0) ).lhs(); }
   
   public boolean isTerminal(String s) 
   {
	   return terminals.contains(s); 
	}
   
   public boolean isNonterminal(String s) { return nonterminals.contains(s); }
   
   public int numberOfTerminals() { return terminals.size(); }
   
   public int numberOfNonterminals() { return nonterminals.size(); }
   
   public Vector terminals() { return terminals; } //for testing only
   
   public Vector nonterminals() { return nonterminals; } //for testing only
    
   /*----------------------protected methods-------------------------------*/
   public ParsingTable getParsingTable()
   {
      ParsingTable table = new ParsingTable(terminals, nonterminals);
      for (int i=0; i<productions.size(); i++)
      {
         Production p = (Production)productions.elementAt(i);
         Production semP = (Production)semProductions.elementAt(i);
         
         String symbolLeft = p.lhs();
         Vector symbols = semP.rhs();       
        
         //prepare ParseAction for production
         ParseAction actions[] = new ParseAction[symbols.size()];
         for (int j=0; j<symbols.size(); j++)
            actions[j] = makeParseAction((String)symbols.elementAt(j));                          
         PushSequence action = new PushSequence(actions);

         //add entries for each first symbol
         HashSet firstsSubset = makeFirstSubset(p);
         
         Iterator iFirsts = firstsSubset.iterator();
         while (iFirsts.hasNext())
         {    
            String terminal = (String)iFirsts.next();   
            try { table.add(symbolLeft, terminal, action); }
            catch (ParsingException e) { e.printStackTrace(); } 
         }

         // if first set contains null, add entries for each follows symbol
         if (firstsSubset.contains(nullSymbol))
         {
            HashSet nonterminalFirsts = getFirstSet(symbolLeft);
            Iterator iFollows = getFollowsSet(symbolLeft).iterator();
            while (iFollows.hasNext())
            {
               String terminal = (String)iFollows.next();
               //add only if terminal is not in firsts -> because then it's right recursion?
               if (!nonterminalFirsts.contains(terminal))
               {
                  try { table.add(symbolLeft, terminal, action); }
                  catch (ParsingException e) { e.printStackTrace(); }
               }
            }
         }
      }      
      return table;
   }   
   
   protected HashMap firsts() { return firsts; }
   
   protected HashMap follows() { return follows; }
   
   
   /*----------------------private methods-------------------------------*/
   
   private void initData()
   {
	  
      initNonterminals();
      initTerminals(); //must be called after initNonterminals()
      initFirsts();           
      initFollows();    
   }
   
   // add all LHS strings from grammar to nonterminals
   private void initNonterminals()
   {	  
      nonterminals = new Vector();
      for (int i=0; i<productions.size(); i++)
      {
         String symbol = ( (Production)productions.elementAt(i) ).lhs();
         if ( !nonterminals.contains(symbol) )
            nonterminals.addElement(symbol);           
      }      
   }
   
   // add all symbols from grammar and end-marker to terminals except nonterminals
   private void initTerminals()
   {
      terminals = new Vector();
      for (int i=0; i<productions.size(); i++)
      {      
         Vector symbols = ( (Production)productions.elementAt(i) ).rhs();
         for (int j=0; j<symbols.size(); j++)
         {
            String s = (String)symbols.elementAt(j);
            if ( !(nonterminals.contains(s) || terminals.contains(s) ) )               
               terminals.addElement(s);            
         }
      }       
      terminals.addElement(Grammar.END_MARKER);
   }
   
   private void initFirsts()
   {
      firsts = new HashMap();
      // make firsts for terminals
      for (int i=0; i<terminals.size(); i++)
      {
         String terminal = (String)terminals.elementAt(i);
         HashSet hs = new HashSet();
         hs.add(terminal);         
         firsts.put(terminal, hs);
      }
      // make firsts for nonterminals
      for (int i=0; i<nonterminals.size(); i++)
      {
         String nonterminal = (String)nonterminals.elementAt(i);
         firsts.put(nonterminal, getFirstSet(nonterminal));
      }
   }
   
   private HashSet getFirstSet(String nonterminal)
   {      
      if (firsts.containsKey(nonterminal))
         return (HashSet)firsts.get(nonterminal);
      
      HashSet symbols = new HashSet();    
      Iterator i = getProductions(nonterminal).iterator();
      // loop through all productions for this nonterminal
      while (i.hasNext()) 
      {
         Production p = (Production)i.next();
         symbols.addAll(makeFirstSubset(p));
      }
      return symbols;
   }
   
   private HashSet makeFirstSubset(Production p) 
   {
      HashSet symbols = new HashSet();    
      
      Vector elements = p.rhs();
      boolean addMore = true;      
      int i = 0;      
      while(addMore)
      {         
         String symbol = (String)elements.elementAt(i);
         if (!isNonterminal(symbol))
         {
            symbols.add(symbol); 
            return symbols;
         }         
         else
         {
            HashSet temp = new HashSet();
            temp.addAll(getFirstSet(symbol)); 
            
            if (derivesNull(symbol) && (i < elements.size()-1))
            {
               temp.remove(nullSymbol);
               i++;
            }
            else
               addMore = false;
            
            symbols.addAll(temp);
         }         
      }      
      return symbols;
   }
   
   private void initFollows()
   {
      follows = new HashMap();  
      for (int i=0; i<nonterminals.size(); i++)
      {
         String nonterminal = (String)nonterminals.elementAt(i);
         follows.put(nonterminal, new HashSet());
      }      
      execFollowsRule1();
      execFollowsRule2();
      execFollowsRule3();
   }  
   
   private HashSet getFollowsSet(String nonterminal)
   {      
      return (HashSet)follows.get(nonterminal);
   }
   
   private void execFollowsRule1()
   {
      String startSymbol = getStartSymbol();
      HashSet symbols = (HashSet)follows.get(startSymbol);
      symbols.add(END_MARKER);
   }

   private void  execFollowsRule2()
   {
      boolean addMore; 
      int k;
      String symbolA, symbolB;
      HashSet followsA; 
      
      for (int i=0; i<productions.size(); i++)
      {
         Production p = (Production)productions.elementAt(i);
         Vector symbols = p.rhs();
         for (int j=0; j<symbols.size(); j++)
         {
            symbolA = (String)symbols.elementAt(j);           
            if (isNonterminal(symbolA))
            {                     
               followsA = (HashSet)follows.get(symbolA);                              
               addMore = true;      
               k = j+1;      
               while(addMore && k<symbols.size())
               {
                  symbolB = (String)symbols.elementAt(k);                 
                  followsA.addAll(getFirstSet(symbolB)); 
                  followsA.remove(nullSymbol);                  
                  addMore = derivesNull(symbolB);             
                  k++;
               }             
            }
         }
      }                   
   }
   
   private void  execFollowsRule3()
   {
      boolean rerun = true;
      int setSize;
      
      while (rerun)
      {
         rerun = false;
	      for (int i=0; i<productions.size(); i++)
	      {
	         Production p = (Production)productions.elementAt(i);
	         HashSet followsA = (HashSet)follows.get(p.lhs());
	         Vector symbols = p.rhs();       
	         boolean addMore = true;
	         int k = symbols.size()-1;         
	         while(addMore && k > -1)
	         {
	            String symbolB = (String)symbols.elementAt(k); 
	            if (isNonterminal(symbolB))
	            {         
	               HashSet followsB = (HashSet)follows.get(symbolB);
	               setSize = followsB.size();
	               followsB.addAll(followsA);
	               if (setSize != followsB.size())
	               	rerun = true;
	               
	               if ( derivesNull(symbolB) )
	                  k--;
	               else
	                  addMore = false;
	            }
	            else
	               addMore = false;            
	         }
	      }
      }
   }  
   
   private String mapToString(HashMap map, String name)
   {
      StringBuffer buffer = new StringBuffer();
      Iterator i = map.keySet().iterator();
      while (i.hasNext())
      {
         String symbol = (String)i.next();
         buffer.append(name + "(" + symbol + ") = { ");
         Iterator j = ( (HashSet)map.get(symbol) ).iterator();
         while (j.hasNext())
            buffer.append(j.next() + " ");
         buffer.append("}\n");  
      }
      return buffer.toString();      
   }
   
   private ParseAction makeParseAction(String symbol)
   {
      if (symbol.compareTo(nullSymbol) == 0)
         return new PushNothing();
      else
         return new PushSymbol(symbol);
   }
      
   private HashSet getProductions(String nonterminal)
   {
      HashSet set = new HashSet();
      for (int i=0; i<productions.size(); i++)
      {
         Production p = (Production)productions.elementAt(i);
         if (p.lhs().compareTo(nonterminal) == 0)
            set.add(p);
      }
      return set;
   } 
      
   public boolean derivesNull(String nonterminal)
   {
      HashSet symbols = getFirstSet(nonterminal);
      return symbols.contains(nullSymbol);
   }   
  
   //for testing only
   public String getSemanticActions()
   {
      HashSet actions = new HashSet();
      for (int i=0; i<semProductions.size(); i++)
      {
         Production p = (Production)semProductions.elementAt(i);
         Vector symbols = p.rhs();
         for (int j=0; j<symbols.size(); j++)
         {
            String symbol = (String)symbols.elementAt(j);
            if (IsSemanticAction(symbol))
               actions.add(symbol);
         }         
      }
      StringBuffer buffer = new StringBuffer();
      Iterator i = actions.iterator();
      while (i.hasNext())
         buffer.append(i.next() + "\n");
      
      return buffer.toString();
   }
}