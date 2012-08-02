package test.myJavaTetris.yysaki.com;

//import static org.junit.Assert.*;

import myJavaTetris.yysaki.com.Field;

import org.junit.Test;

//import myJavaTetris.yysaki.com.Field;
import myJavaTetris.yysaki.com.Blocks;
import myJavaTetris.yysaki.com.Point;

public class FieldTest extends ProjectTests {

	@Test
	public void testSetAll() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		f.setAll(3);
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j), 7);					
				}else{
					assertEquals(f.getStatus(i, j), 3);
				}
			}
		}
	}

	@Test
	public void testPileBlocks() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		Blocks b = new Blocks(new Point(2,h-1), 0, 1);
		
		assertTrue(f.pileBlocks(b));
		assertFalse(f.pileBlocks(b));

		Blocks b2 = new Blocks(new Point(2,h), 0, 1);
		assertFalse(f.pileBlocks(b2));
		
	}

	@Test
	public void testDeleteLines() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		f.setAll(3);

		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j), 7);					
				}else{
					assertEquals(f.getStatus(i, j), 3);
				}
			}
		}
		
		f.deleteLines();
		
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j), 7);					
				}else{
					assertEquals(f.getStatus(i, j), 0);
				}
			}
		}

	}

	@Test
	public void testCanBeSetBlocks() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		Blocks b = new Blocks(new Point(1,0), 0, 1);
		assertTrue(f.canBeSetBlocks(b));
		b = new Blocks(new Point(w-3,0), 0, 1);
		assertTrue(f.canBeSetBlocks(b));
		b = new Blocks(new Point(w-2,0), 0, 1);
		assertFalse(f.canBeSetBlocks(b));
		b = new Blocks(new Point(3,h), 0, 1);
		assertFalse(f.canBeSetBlocks(b));
	}

	@Test
	public void testGetStatus() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j), 7);					
				}else{
					assertEquals(f.getStatus(i, j), 0);
				}
			}
		}
	}

	@Test
	public void testGetWidth() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		assertEquals(w, f.getWidth());
	}

	@Test
	public void testGetHeight() {
		int w = 10; int h = 20;
		Field f = new Field(w,h);
		assertEquals(h, f.getHeight());
	}

}
