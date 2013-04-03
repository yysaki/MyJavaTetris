package test.myJavaTetris.yysaki.com;

//import static org.junit.Assert.*;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.GameField;

import org.junit.Test;

//import myJavaTetris.yysaki.com.Field;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.MyPoint;

public class GameFieldTest extends ProjectTests {

	@Test
	public void testSetAll() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		f.setAll(new BlockColor(3));
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j).getId(), 7);					
				}else{
					assertEquals(f.getStatus(i, j).getId(), 3);
				}
			}
		}
	}

	@Test
	public void testpileBlocks() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		GameBlocks b = new GameBlocks(new MyPoint(2,h-1), 0, new BlockColor(1));
		
		assertTrue(f.pileBlocks(b));
		assertFalse(f.pileBlocks(b));

		GameBlocks b2 = new GameBlocks(new MyPoint(2,h), 0, new BlockColor(1));
		assertFalse(f.pileBlocks(b2));
		
	}

	@Test
	public void testDeleteLines() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		f.setAll(new BlockColor(3));

		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j).getId(), 7);					
				}else{
					assertEquals(f.getStatus(i, j).getId(), 3);
				}
			}
		}
		
		f.deleteLines();
		
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j).getId(), 7);					
				}else{
					assertEquals(f.getStatus(i, j).getId(), 0);
				}
			}
		}

	}

	@Test
	public void testcanBeSetBlocks() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		GameBlocks b = new GameBlocks(new MyPoint(1,0), 0, new BlockColor(1));
		assertTrue(f.canSetBlocks(b));
		b = new GameBlocks(new MyPoint(w-3,0), 0, new BlockColor(1));
		assertTrue(f.canSetBlocks(b));
		b = new GameBlocks(new MyPoint(w-2,0), 0, new BlockColor(1));
		assertFalse(f.canSetBlocks(b));
		b = new GameBlocks(new MyPoint(3,h), 0, new BlockColor(1));
		assertFalse(f.canSetBlocks(b));
	}

	@Test
	public void testGetStatus() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		for(int i=0;i<w+1;i++){
			for(int j=0;j<h+1;j++){
				if(i==w || j==h){
					assertEquals(f.getStatus(i, j).getId(), 7);					
				}else{
					assertEquals(f.getStatus(i, j).getId(), 0);
				}
			}
		}
	}

	@Test
	public void testGetWidth() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		assertEquals(w, f.getWidth());
	}

	@Test
	public void testGetHeight() {
		int w = 10; int h = 20;
		GameField f = new GameField(w,h);
		assertEquals(h, f.getHeight());
	}

}
