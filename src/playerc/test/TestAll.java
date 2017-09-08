/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class TestAll {
  public static Test suite() {
    TestSuite suite = new TestSuite("playerc tests");
    suite.addTest(new TestSuite(ScannerTest.class));
    suite.addTest(new TestSuite(TokenTest.class));
    suite.addTest(new TestSuite(PlayerTokensTest.class));
    suite.addTest(new TestSuite(ProductionTest.class));
    suite.addTest(new TestSuite(GrammarReaderTest.class));    
    suite.addTest(new TestSuite(GrammarTest.class));
    

    // suite.addTest(new TestSuite(TestParsingTable.class));
    // suite.addTest(new TestSuite(TestPlayerParsingTable.class));
    // 
    
    // suite.addTest(new TestSuite(TestTypeChecker.class));
    
    // suite.addTest(new TestSuite(TestCodeGenerator.class));

    return suite;
  }
}
