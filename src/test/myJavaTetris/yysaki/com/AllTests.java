package test.myJavaTetris.yysaki.com;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(FieldTest.class);
		suite.addTestSuite(BlocksTest.class);
		//$JUnit-END$
		return suite;
	}

}
