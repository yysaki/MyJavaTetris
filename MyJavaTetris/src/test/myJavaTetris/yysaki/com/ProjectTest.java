package test.myJavaTetris.yysaki.com;

import junit.framework.TestCase;
import myJavaTetris.yysaki.com.Point;

public class ProjectTest extends TestCase {
	public void assertEqualsPoint(Point lhs, Point rhs){
		assertEquals(lhs.getX(), rhs.getX());
		assertEquals(lhs.getY(), rhs.getY());
	}
}
