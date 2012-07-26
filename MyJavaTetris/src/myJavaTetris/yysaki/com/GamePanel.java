package myJavaTetris.yysaki.com;


import java.awt.*;
import javax.swing.*;

import myJavaTetris.yysaki.com.View;

/**
 * テトリス画面(JPanel)の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private View v;
	private final static int BLOCKSIZE = 25; // 正方形ブロックの辺の長さ
	private Image[] icon; // gray, red, yellow, purple, green, blue, orange, water

	public GamePanel(View v) {
		super();
		System.out.println("GamePanel Constructor");
		
		this.v = v;
		
		/* panel size */
		setPreferredSize(new Dimension(v.getField().getWidth()*BLOCKSIZE, v.getField().getHeight()*BLOCKSIZE));

		/* set image */
		icon = new Image[8];
		icon[0] = Toolkit.getDefaultToolkit().getImage("res/tetris_gray.jpg");		
		icon[1] = Toolkit.getDefaultToolkit().getImage("res/tetris_red.jpg");
		icon[2] = Toolkit.getDefaultToolkit().getImage("res/tetris_yellow.jpg");
		icon[3] = Toolkit.getDefaultToolkit().getImage("res/tetris_purple.jpg");
		icon[4] = Toolkit.getDefaultToolkit().getImage("res/tetris_green.jpg");
		icon[5] = Toolkit.getDefaultToolkit().getImage("res/tetris_blue.jpg");
		icon[6] = Toolkit.getDefaultToolkit().getImage("res/tetris_orange.jpg");
		icon[7] = Toolkit.getDefaultToolkit().getImage("res/tetris_water.jpg");
	}

	public void paintComponent(Graphics g){
//		System.out.println("GamePanel.paintComponent");
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g); // アクティブブロックの描画
	}

	/**
	 * draw piled blocks
	 * @param g
	 */
	private void drawBG(Graphics g){
		for(int i=0;i<v.getField().getWidth();i++){
			for(int j=0;j<v.getField().getHeight();j++){
				this.drawBlock(g, v.getField().getStatus(i, j), i, j);
			}
		}
	}

	private void drawBlocks(Graphics g){
		Blocks b = v.getBlocks();
		for(int i=0;i<b.getPoint().length;i++){
			final Point p = b.getPoint()[i];
			final int dx = b.getDir().getX() + p.getX() * (int)Math.cos(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.sin(b.getRotate()*Math.PI/2);
			final int dy = b.getDir().getY() + -1 * p.getX() * (int)Math.sin(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.cos(b.getRotate()*Math.PI/2);

			//			System.out.println("x: " +  x + ", y:" + y);
			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, int color, int x, int y){
		//			System.out.println("drawBlock color:" + color + ", x:" + x + ", y:" + y);
		//		System.out.println("drawBlock color:" + color);
		g.drawImage(icon[color], x*BLOCKSIZE, y*BLOCKSIZE, this);		
	}
}
