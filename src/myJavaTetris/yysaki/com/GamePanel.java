package myJavaTetris.yysaki.com;


import java.awt.*;
import javax.swing.*;

import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.GameTimer;
import myJavaTetris.yysaki.com.View;


/**
 * テトリス画面(JPanel)の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private View _v;
	private GameBlocks _blocks;
	private GameField _field;
	private Boolean _isGameOver = false;
	
	private final Point _start; // アクティブブロックのスタート地点
	private final static int _blocksize = 25; // 正方形ブロックの辺の長さ
	private Image[] _icon; // gray, red, yellow, purple, green, blue, orange, water

	public GamePanel(View v, int w, int h) {
		super();
		System.out.println("GamePanel Constructor");
		
		this._v = v;
		_start = new Point(w/2, 0);
		setBlocks(new GameBlocks(_start, 0));
		setField(new GameField(w, h));
		
		/* panel size */
		setPreferredSize(new Dimension(getField().getWidth()*_blocksize, getField().getHeight()*_blocksize));

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


		/* Tick */
		new Timer(1000, new GameTimer(_v)).start();
	}

	public void paintComponent(Graphics g){
//		System.out.println("GamePanel.paintComponent");
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g); // アクティブブロックの描画
	}

	/**
	 * 地面に設置した時、新しいテトリスブロックを出現させる
	 */
	public void next(){
		System.out.println("next");

		// check isGameOver
		if(getIsGameOver()){
			return;
		}

		Boolean pileSucceeded = getField().pileBlocks(getBlocks());
		if(!pileSucceeded){
			System.out.println("pile is failed");
		}

		getField().deleteLines();

		repaint();

		// setNextBlocks
		setBlocks(new GameBlocks(_start, 0));

		// set blocks & check game over
		if(!getField().canBeSetBlocks(getBlocks())){
			System.out.println("Game Over!!!");
			setIsGameOver(true);
			getField().setAll(1);

			setBlocks(new GameBlocks(_start,0,1)); // バッドノウハウ アクティブブロックをGameOver色背景に埋める
			repaint();

		}
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
			final int dx = b.getDir().getX() + p.getX() * (int)Math.cos(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.sin(b.getRotate()*Math.PI/2);
			final int dy = b.getDir().getY() + -1 * p.getX() * (int)Math.sin(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.cos(b.getRotate()*Math.PI/2);

			//			System.out.println("x: " +  x + ", y:" + y);
			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, int color, int x, int y){
		//			System.out.println("drawBlock color:" + color + ", x:" + x + ", y:" + y);
		//		System.out.println("drawBlock color:" + color);
		g.drawImage(_icon[color], x*_blocksize, y*_blocksize, this);		
	}

	public Boolean getIsGameOver(){ return _isGameOver; }
	public void setIsGameOver(Boolean arg){ _isGameOver = arg; }
	
	public GameField getField(){ return _field; }
	public void setField(GameField arg){ _field = arg; }

	public GameBlocks getBlocks(){ return _blocks; }
	public void setBlocks(GameBlocks arg){ _blocks = arg; }

}
