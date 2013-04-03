package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.BlockIcon;

import myJavaTetris.yysaki.com.GameBlocks;

@SuppressWarnings("serial")
public class InfoNextBlocksPanel extends JPanel {
	private GameBlocks _blocks;
	private final static int WIDTH = 4;
	private final static int HEIGHT = 3;
	private final static MyPoint CENTER = new MyPoint(1,1);
	private final static int DX = 2;
	private final static int DY = 2;
	
	public InfoNextBlocksPanel(){
		super();
		
		_blocks = new GameBlocks();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(BlockIcon.getSize() * WIDTH + DX * 2,
				BlockIcon.getSize() * HEIGHT + DY * 2));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g); 
	}

	/**
	 * Next表示の背景ブロックの表示
	 * @param g
	 */
	private void drawBG(Graphics g){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<HEIGHT;j++){
				drawBlock(g, BlockColor.EMPTY, i, j);
			}
		}
	}

	/**
	 * Nextブロックの描画
	 * @param g
	 */
	private void drawBlocks(Graphics g){
		for(int i=0;i<_blocks.getPoints().length;i++){
			final MyPoint p = _blocks.getPoints()[i];
			final int dx = p.getX()
					+ CENTER.getX() * (int)Math.cos(_blocks.getRotate() * Math.PI/2) 
					+ CENTER.getY() * (int)Math.sin(_blocks.getRotate() * Math.PI/2);
			final int dy = p.getY() 
					+ -1 * CENTER.getX() * (int)Math.sin(_blocks.getRotate() * Math.PI/2) 
					+ CENTER.getY() * (int)Math.cos(_blocks.getRotate() * Math.PI/2);
			drawBlock(g, _blocks.getColor(), dx, dy);
		}
	}
	
	private void drawBlock(Graphics g, BlockColor c, int x, int y){
		g.drawImage(BlockIcon.getIcon(c), BlockIcon.getSize() * x + DX,
				BlockIcon.getSize() * y + DY, this);		
	}
	
	public void setNextBlocks(GameBlocks blocks){ _blocks = blocks; }
}
