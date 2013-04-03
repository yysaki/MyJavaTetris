package test.myJavaTetris.yysaki.com;

//import static org.junit.Assert.*;
//import junit.framework.TestCase;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.MyPoint;

import org.junit.Test;

public class GameBlocksTest extends ProjectTests {

	@Test
	public void testGetColor() {
		GameBlocks b1 = new GameBlocks(new MyPoint(), 0);
		GameBlocks b2 = new GameBlocks(new MyPoint(1,2), 0, new BlockColor(1));
		GameBlocks b3 = new GameBlocks(b2);
		
		assertTrue(1<=b1.getColor().getId() && b1.getColor().getId()<=8);
		assertEquals(1, b2.getColor().getId());
		assertEquals(1, b3.getColor().getId());
	}

	@Test
	public void testGetPoints() {
		GameBlocks b1 = new GameBlocks(new MyPoint(), 0, new BlockColor(1));
		MyPoint[] points = b1.getPoints();

		assertEqualsPoint(points[0], new MyPoint(0,0));
		assertEqualsPoint(points[1], new MyPoint(-1,0));
		assertEqualsPoint(points[2], new MyPoint(1,0));
		assertEqualsPoint(points[3], new MyPoint(2,0));

	}

	@Test
	public void testGetRotatable() {
		GameBlocks b1 = new GameBlocks(new MyPoint(), 0);
		GameBlocks b2 = new GameBlocks(new MyPoint(1,2), 0, new BlockColor(1));
		GameBlocks b3 = new GameBlocks(b2);
		
		assertTrue(0<=b1.getRotatable() && b3.getRotatable()<=4);
		assertEquals(2, b2.getRotatable());
		assertEquals(2, b3.getRotatable());
	}

	@Test
	public void testGetDir() {
		MyPoint p = new MyPoint(1,1);
		GameBlocks b1 = new GameBlocks(new MyPoint(1,1), 0, new BlockColor(1));
		GameBlocks b2 = new GameBlocks(b1);
		GameBlocks b3 = new GameBlocks(new MyPoint(100,100), 10, new BlockColor(1));
		assertEqualsPoint(p, b1.getDir());
		assertEqualsPoint(p, b2.getDir());
		assertEqualsPoint(new MyPoint(100,100), b3.getDir());
	}

	@Test
	public void testGetRotate() {
		GameBlocks b1 = new GameBlocks(new MyPoint(), 0, new BlockColor(1));
		GameBlocks b2 = new GameBlocks(b1);
		GameBlocks b3 = new GameBlocks(new MyPoint(), 10, new BlockColor(1));
		
		assertEquals(0, b1.getRotate());
		assertEquals(0, b2.getRotate());
		assertEquals(0, b3.getRotate());
	}

	@Test
	public void testSetDir() {
		MyPoint p = new MyPoint(1,1);
		GameBlocks b1 = new GameBlocks(p, 0);
		GameBlocks b2 = new GameBlocks(b1);
		assertEqualsPoint(p, b1.getDir());
		MyPoint p2 = new MyPoint(2,0);
		b1.setDir(p2);
		assertEqualsPoint(p2, b1.getDir());
		assertEqualsPoint(p, b2.getDir());
	}

	@Test
	public void testSetRotate() {
		GameBlocks b = new GameBlocks(new MyPoint(), 0, new BlockColor(1));
		assertEquals(0, b.getRotate());
		b.setRotate(b.getRotate()+1);
		assertEquals(1, b.getRotate());
		b.setRotate(b.getRotate()+1);
		assertEquals(0, b.getRotate());
	}

//	private void test
}
