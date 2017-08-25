package playerc.test;


import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Author: sergei
 * Created: Jun 18, 2005
 */
public class TestAll
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("compiler test");		
		
		//suite.addTest(new TestSuite(TestCodeGenerator.class));		
		suite.addTest(new TestSuite(TestGrammar.class));
		suite.addTest(new TestSuite(TestGrammarReader.class));
		suite.addTest(new TestSuite(TestParsingTable.class));	
		//suite.addTest(new TestSuite(TestPlayerParsingTable.class));		
		suite.addTest(new TestSuite(TestPlayerTokens.class));
		suite.addTest(new TestSuite(TestProduction.class));		
		suite.addTest(new TestSuite(TestScanner.class));
		suite.addTest(new TestSuite(TestToken.class));
		//suite.addTest(new TestSuite(TestTypeChecker.class));

		
		return suite;
	}
}
