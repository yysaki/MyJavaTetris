package test.myJavaTetris.yysaki.com;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(GameFieldTest.class);
		suite.addTestSuite(GameBlocksTest.class);
		//$JUnit-END$
		return suite;
	}

}
