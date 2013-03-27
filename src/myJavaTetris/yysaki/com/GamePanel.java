package myJavaTetris.yysaki.com;

import java.awt.*;
import javax.swing.*;

import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.View;

/**
 * テトリス画面(JPanel)の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private GameBlocks _blocks;
	private GameField _field;

	private final Point _start; // アクティブブロックのスタート地点
	private final static int _blocksize = 25; // 正方形ブロックの辺の長さ
	private Image[] _icon; // gray, red, yellow, purple, green, blue, orange, water

	public GamePanel(int blockWidth, int blockHeight) {
		super();

		_start = new Point(blockWidth/2, 0);

		setField(new GameField(blockWidth, blockHeight));
		setBlocks(new GameBlocks(getField().getEmptyColor()));

		/* panel size */
		setPreferredSize(new Dimension(getPanelWidth(), getPanelHeight()));

		/* set image */
		_icon = new Image[8];
		_icon[0] = Toolkit.getDefaultToolkit().getImage("res/tetris_gray.jpg");
		_icon[1] = Toolkit.getDefaultToolkit().getImage("res/tetris_red.jpg");
		_icon[2] = Toolkit.getDefaultToolkit().getImage("res/tetris_yellow.jpg");
		_icon[3] = Toolkit.getDefaultToolkit().getImage("res/tetris_purple.jpg");
		_icon[4] = Toolkit.getDefaultToolkit().getImage("res/tetris_green.jpg");
		_icon[5] = Toolkit.getDefaultToolkit().getImage("res/tetris_blue.jpg");
		_icon[6] = Toolkit.getDefaultToolkit().getImage("res/tetris_orange.jpg");
		_icon[7] = Toolkit.getDefaultToolkit().getImage("res/tetris_water.jpg");
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g); // アクティブブロックの描画
	}

	/**
	 * draw piled blocks
	 * @param g
	 */
	private void drawBG(Graphics g){
		for(int i=0;i<getField().getWidth();i++){
			for(int j=0;j<getField().getHeight();j++){
				this.drawBlock(g, getField().getStatus(i, j), i, j);
			}
		}
	}

	private void drawBlocks(Graphics g){
		GameBlocks b = getBlocks();
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int dx = b.getDir().getX()
					+ p.getX() * (int)Math.cos(b.getRotate() *Math.PI/2) 
					+ p.getY() * (int)Math.sin(b.getRotate()*Math.PI/2);
			final int dy = b.getDir().getY() 
					+ -1 * p.getX() * (int)Math.sin(b.getRotate()*Math.PI/2) 
					+ p.getY() * (int)Math.cos(b.getRotate()*Math.PI/2);

			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, int color, int x, int y){
		g.drawImage(_icon[color], x*_blocksize, y*_blocksize, this);		
	}

	public GameField getField(){ return _field; }
	public void setField(GameField arg){ _field = arg; }

	public GameBlocks getBlocks(){ return _blocks; }
	public void setBlocks(GameBlocks arg){ _blocks = arg; }

	public Point getStartPoint(){ return _start; }
	public int getBlockSize(){ return _blocksize; }

	public int getPanelWidth(){
		return getField().getWidth()*_blocksize;
	}

	public int getPanelHeight(){
		return getField().getHeight()*_blocksize;
	}
}