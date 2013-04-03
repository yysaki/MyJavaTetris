package test.myJavaTetris.yysaki.com;

import org.junit.Test;

import myJavaTetris.yysaki.com.BlockColor;

public class BlockColorTest extends ProjectTests {
	@Test
	public void testequals(){
		BlockColor c1 = BlockColor.EMPTY;
		assertTrue(c1 == BlockColor.EMPTY);
		assertTrue(c1.equals(BlockColor.EMPTY));
		
		BlockColor c2 = new BlockColor(BlockColor.EMPTY);
		assertTrue(c2 != BlockColor.EMPTY);
		assertTrue(c2.equals(BlockColor.EMPTY));

		BlockColor c3 = new BlockColor(0);
		assertTrue(c3 != BlockColor.EMPTY);
		assertTrue(c3.equals(BlockColor.EMPTY));

		BlockColor c4 = new BlockColor(0);
		assertTrue(c4 != c3);
		assertTrue(c4.equals(c3));
	}
}