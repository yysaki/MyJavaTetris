package myJavaTetris.yysaki.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myJavaTetris.yysaki.com.Blocks;
import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.MyAction;
import myJavaTetris.yysaki.com.Point;

/**
 * ゲームウインドウの管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private GamePanel panel; // ContentPane
	private final int width = 9, height = 15; // 横に置けるブロック数、縦に置けるブロック数
	private final Point start = new Point(width/2, 0); // アクティブブロックのスタート地点

	/**
	 *  アクティブなテトリスブロック
	 *  ランダム生成する
	 */
	private Blocks blocks = new Blocks(start, 0);

	private int[][] status; // ブロックの堆積状況

	View(){
		System.out.println("constructor");

		/* set Frame */
		panel = new GamePanel(this);
		getContentPane().add(panel);
		pack();
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		/* status */
		status = new int[width+1][height+1];
		for(int i=0;i<status.length;i++){
			for(int j=0;j<status[0].length;j++){
				status[i][j] = 0;
			}
		}
		for(int i=0;i<status.length;i++){
			status[i][height] = 7;
		}
		for(int j=0;j<status[0].length;j++){
			status[width][j] = 7;
		}

		/* set KeyStroke */
		InputMap imap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = panel.getActionMap();

		MyAction actionUp = new MyAction("UP", this);
		MyAction actionDown = new MyAction("DOWN", this);
		MyAction actionRight = new MyAction("RIGHT", this);
		MyAction actionLeft = new MyAction("LEFT", this);

		imap.put(KeyStroke.getKeyStroke("UP"), actionUp);
		amap.put(actionUp, actionUp);
		imap.put(KeyStroke.getKeyStroke("DOWN"), actionDown);
		amap.put(actionDown, actionDown);
		imap.put(KeyStroke.getKeyStroke("RIGHT"), actionRight);
		amap.put(actionRight, actionRight);
		imap.put(KeyStroke.getKeyStroke("LEFT"), actionLeft);
		amap.put(actionLeft, actionLeft);

		/* Tick */
		new Timer(1000, new MyAction("tick", this)).start();

		setVisible(true);
}

	/**
	 * 地面に設置した時、新しいテトリスブロックを出現させる
	 */
	public void next(){
		// check isGameOver
		Boolean isGameOver = true;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(status[i][j]==0){
					isGameOver = false;
				}
			}
		}
		if(isGameOver){
			return;
		}

		System.out.println("next");
		// pile
		for(int i=0;i<blocks.getPoint().length;i++){
			final Point p = blocks.getPoint()[i];
			final int x = blocks.getDir().getX() + p.getX() * (int)Math.cos((blocks.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((blocks.getRotate())*Math.PI/2);
			final int y = blocks.getDir().getY() + -1 * p.getX() * (int)Math.sin((blocks.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((blocks.getRotate())*Math.PI/2);
			status[x][y] = blocks.getColor();
		}
		// check isDeletable
		for(int j=height-1;j>=0;j--){
			Boolean isDeletable = true;
			for(int i=0;i<width;i++){
				if(status[i][j]==0){
					isDeletable = false;
				}
			}
			// delete j-th line
			if(isDeletable){
				for(int j2=j;j2>=0;j2--){
					for(int i2=0;i2<width;i2++){
						if(j2!=0){
							status[i2][j2] = status[i2][j2-1];
						}else{
							status[i2][j2] = 0;
						}
					}
				}
				// j行についてもう一度checkする
				j++;
				continue;
			}
		}
		repaint();

		// setNextBlocks
		blocks = new Blocks(start, 0);

		// check hasNext
		Boolean hasNext = true;
		for(int i=0;i<blocks.getPoint().length;i++){
			final Point p = blocks.getPoint()[i];
			final int x = blocks.getDir().getX() + p.getX() * (int)Math.cos((blocks.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((blocks.getRotate())*Math.PI/2);
			final int y = blocks.getDir().getY() + -1 * p.getX() * (int)Math.sin((blocks.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((blocks.getRotate())*Math.PI/2);
			if(status[x][y]!=0){
				hasNext = false;
				break;
			}
		}

		// game over phase
		if(!hasNext){
			System.out.println("Game Over!!!");

			for(int i=0;i<width;i++){
				for(int j=0;j<height;j++){
					status[i][j] = 1;
				}
			}
			blocks = new Blocks(start,0,1); // バッドノウハウ アクティブブロックをGameOver色背景に埋める
			repaint();

		}
	}

	public void paint(Graphics g){
		super.paint(g);
		System.out.println("paint");
		panel.paint(panel.getGraphics());
	}

	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	public int[][] getStatus(){ return status; } 
	public Blocks getBlocks(){ return blocks; }
}
