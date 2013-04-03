package test.myJavaTetris.yysaki.com;

import junit.framework.TestCase;
import myJavaTetris.yysaki.com.MyPoint;

public class ProjectTests extends TestCase {
	public void assertEqualsPoint(MyPoint lhs, MyPoint rhs){
		assertEquals(lhs.getX(), rhs.getX());
		assertEquals(lhs.getY(), rhs.getY());
	}
}
