package playerc;

import java.io.*;
import java.util.*;


/** * 
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 13 2005
 * modified Sep 04 2017
 * Comment:	used to read a grammar file and return a collection of production objects
 *          the only requirement to the source file is to have spaces between symbols: A -> b c | c b
 * 			  now it's okay to have leading spaces and multiple "ORs" on the same line
 * 
 *    Grammar requirements: all terminals are lower case without any quotation marks! Non-lower case are: 
 *    - nonterminals ("XYZ") 
 *    - semantic actions ("MAKE-XYZ")
 *    - grammar symbols (derives, or, empty set)
 *    - identifier symbol ("IDENTIFIER")
 *    - literals ("LITERAL-STRING", "LITERAL-REAL", etc...)
 */
public class GrammarReader
{
	private String derivesSymbol;
	private String orSymbol;
	
	public GrammarReader(String derivesSymbol, String orSymbol) 
	{
		this.derivesSymbol = derivesSymbol;
		this.orSymbol = orSymbol;
	}

	public Vector getProductions(String file, boolean includeSemActions) throws IOException, Exception
	{		  
		Vector p = new Vector();
		InputStream grammarIn = this.getClass().getResourceAsStream(file);
		StringBuffer temp = new StringBuffer();
		String lhs = null;
		int next;
		while(true)
		{      
			if ( (next = grammarIn.read()) == -1 )
			{
				lhs = processLine(temp.toString(), lhs, p, includeSemActions); //process whatever is left in the buffer   						
				break;
			}		
			char c = (char)next;
			if (c != '\n' && c != '\r') 
				temp.append(c); //build-up line			
			else
			{
				lhs = processLine(temp.toString(), lhs, p, includeSemActions);       
				temp.delete(0, temp.length());
			}          
		}      
		return p;
	}  	
	
	private String processLine(String line, String lhs, Vector productions, boolean includeSemActions) throws Exception
	{		
		if (line != null || line.length() > 0)
		{
			lhs = makeLHS(lhs, line);
			String rhs = removeLHS(line); 
			StringTokenizer st = new StringTokenizer(rhs, orSymbol); //splits "b c | c d" into "b c" and "c d" 
			while (st.hasMoreTokens())
			{
				String production = st.nextToken().trim();
				if (production.length() > 0) //leading "or" will cause an empty token - so must check for empty string
					productions.addElement(new Production(lhs, makeRHS(production, includeSemActions)));
			}
		}    
		return lhs;
	}
	
	private String removeLHS(String s)
	{
		int i = s.indexOf(derivesSymbol);
		if (i > -1) 	return s.substring(i + derivesSymbol.length());
		else			return s;
	}
	
	private String makeLHS(String lhs, String line)
	{	 
		if (line.indexOf(derivesSymbol) > -1)
			return new StringTokenizer(line).nextToken();	   
		else
			return lhs;	  
	}   
	
	private Vector makeRHS(String line, boolean includeSemActions)
	{         
		Vector rhs = new Vector();
		StringTokenizer st = new StringTokenizer(line);
		while (st.hasMoreTokens())
		{
			String next = st.nextToken();
			if ( includeSemActions || !(Grammar.IsSemanticAction(next)) ) 
				rhs.addElement(next);      
		}
		return rhs;
	}

	
	/*//alternative implementation: uses a filereader; in this case, include the package name in the path
	public Vector getProductionsFromFile(String file, boolean includeSemActions) throws IOException, Exception
	{
		Vector p = new Vector();  
		BufferedReader reader = null;      
		try { 
			reader = new BufferedReader(new FileReader(file));          
			String lhs = null;
			String rhs = null;
			while(true)
			{        
				String line = reader.readLine(); 
				if (line == null) break;
				if (line.length() > 0)
				{
					lhs = makeLHS(lhs, line);
					rhs = removeLHS(line); 
					StringTokenizer st = new StringTokenizer(rhs, orSymbol); //splits "b c | c d" into "b c" and "c d" 
					while (st.hasMoreTokens())
					{
						String token = st.nextToken().trim();
						if (token.length() > 0) //leading "or" will cause an empty token
							p.addElement(new Production(lhs, makeRHS(token, includeSemActions)));
					}
				}
			}       	      	  
		}  
		catch (FileNotFoundException e) { System.err.println(e.getMessage()); }
		return p;        
	}
	*/
}
