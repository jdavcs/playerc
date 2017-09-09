/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
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
    TestSuite suite = new TestSuite("compiler test");

    suite.addTest(new TestSuite(GrammarTest.class));
    suite.addTest(new TestSuite(GrammarReaderTest.class));
    suite.addTest(new TestSuite(ParsingTableTest.class));
    suite.addTest(new TestSuite(PlayerParsingTableTest.class));
    suite.addTest(new TestSuite(PlayerTokensTest.class));
    suite.addTest(new TestSuite(ProductionTest.class));
    suite.addTest(new TestSuite(ScannerTest.class));
    suite.addTest(new TestSuite(TokenTest.class));
    suite.addTest(new TestSuite(TypeCheckerTest.class));
    suite.addTest(new TestSuite(CodeGeneratorTest.class));

    return suite;
  }
}
