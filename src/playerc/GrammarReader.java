/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * * Author: sergei
 * 
 * @version 3.1 Comment: used to read a grammar file and return a collection of
 *          production objects Requirement: new productions must have no leading
 *          spaces Modified: 8/11/05 added bool param to makeRHS() to exclude or
 *          include semantic actions
 */
public class GrammarReader {
  private String derivesSymbol;

  public GrammarReader(String derivesSymbol) {
    this.derivesSymbol = derivesSymbol;
  }

  public Vector getProductions(String file, boolean includeSemActions) throws IOException {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
    Vector p = new Vector();
    String lhs = null;
    while (true) {
      String line = reader.readLine();
      if (line == null)
        break;
      if (hasLHS(line))
        lhs = getLHS(line);
      if (hasRHS(line))
        p.addElement(new Production(lhs, makeRHS(line, includeSemActions)));
    }
    return p;
  }

  public boolean hasLHS(String line) {
    return line.length() > 0 && isLetter(line.charAt(0));
  }

  public boolean hasRHS(String line) {
    return line.indexOf("|") > -1 || line.indexOf(derivesSymbol) > -1;
  }

  public String getLHS(String line) {
    int end = 0;
    int tab = line.indexOf("\t");
    int space = line.indexOf(" ");
    if (space == -1)
      end = tab;
    else if (tab == -1)
      end = space;
    else
      end = Math.min(space, tab);
    return line.substring(0, end);
  }

  public Vector makeRHS(String line, boolean includeSemActions) {
    // get rid of leading 'nonterminal 'derivesSymbol' or '|'
    if (line.indexOf("|") > -1)
      line = line.substring(line.indexOf("|") + 1);
    else if (line.indexOf(derivesSymbol) > -1)
      line = line.substring(line.indexOf(derivesSymbol) + derivesSymbol.length());

    Vector rhs = new Vector();
    StringTokenizer st = new StringTokenizer(line);
    while (st.hasMoreTokens()) {
      String next = st.nextToken();
      if (includeSemActions || !(Grammar.IsSemanticAction(next)))
        rhs.addElement(next);
    }
    return rhs;
  }

  private boolean isLetter(char c) {
    return ((65 <= c) && (c <= 90)) || ((97 <= c) && (c <= 122));
  }
}
