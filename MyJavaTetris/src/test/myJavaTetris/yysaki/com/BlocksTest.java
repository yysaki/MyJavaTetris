package test.myJavaTetris.yysaki.com;

//import static org.junit.Assert.*;
//import junit.framework.TestCase;

import myJavaTetris.yysaki.com.Blocks;
import myJavaTetris.yysaki.com.Point;

import org.junit.Test;

public class BlocksTest extends ProjectTests {

	@Test
	public void testGetColor() {
		Blocks b1 = new Blocks(new Point(), 0);
		Blocks b2 = new Blocks(new Point(1,2), 0, 1);
		Blocks b3 = new Blocks(b2);
		
		assertTrue(1<=b1.getColor() && b1.getColor()<=8);
		assertEquals(1, b2.getColor());
		assertEquals(1, b3.getColor());
	}

	@Test
	public void testGetPoints() {
		Blocks b1 = new Blocks(new Point(), 0, 1);
		Point[] points = b1.getPoints();

		assertEqualsPoint(points[0], new Point(0,0));
		assertEqualsPoint(points[1], new Point(-1,0));
		assertEqualsPoint(points[2], new Point(1,0));
		assertEqualsPoint(points[3], new Point(2,0));

	}

	@Test
	public void testGetRotatable() {
		Blocks b1 = new Blocks(new Point(), 0);
		Blocks b2 = new Blocks(new Point(1,2), 0, 1);
		Blocks b3 = new Blocks(b2);
		
		assertTrue(0<=b1.getRotatable() && b3.getRotatable()<=4);
		assertEquals(2, b2.getRotatable());
		assertEquals(2, b3.getRotatable());
	}

	@Test
	public void testGetDir() {
		Point p = new Point(1,1);
		Blocks b1 = new Blocks(new Point(1,1), 0, 1);
		Blocks b2 = new Blocks(b1);
		Blocks b3 = new Blocks(new Point(100,100), 10, 1);
		assertEqualsPoint(p, b1.getDir());
		assertEqualsPoint(p, b2.getDir());
		assertEqualsPoint(new Point(100,100), b3.getDir());
	}

	@Test
	public void testGetRotate() {
		Blocks b1 = new Blocks(new Point(), 0, 1);
		Blocks b2 = new Blocks(b1);
		Blocks b3 = new Blocks(new Point(), 10, 1);
		
		assertEquals(0, b1.getRotate());
		assertEquals(0, b2.getRotate());
		assertEquals(0, b3.getRotate());
	}

	@Test
	public void testSetDir() {
		Point p = new Point(1,1);
		Blocks b1 = new Blocks(p, 0);
		Blocks b2 = new Blocks(b1);
		assertEqualsPoint(p, b1.getDir());
		Point p2 = new Point(2,0);
		b1.setDir(p2);
		assertEqualsPoint(p2, b1.getDir());
		assertEqualsPoint(p, b2.getDir());
	}

	@Test
	public void testSetRotate() {
		Blocks b = new Blocks(new Point(), 0, 1);
		assertEquals(0, b.getRotate());
		b.setRotate(b.getRotate()+1);
		assertEquals(1, b.getRotate());
		b.setRotate(b.getRotate()+1);
		assertEquals(0, b.getRotate());
	}

//	private void test
}
