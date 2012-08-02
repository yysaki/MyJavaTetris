package myJavaTetris.yysaki.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myJavaTetris.yysaki.com.Blocks;
import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.MyAction;
import myJavaTetris.yysaki.com.Point;
import myJavaTetris.yysaki.com.Field;

/**
 * ゲームウインドウの管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private final Point start; // アクティブブロックのスタート地点

	private GamePanel panel; 
	private Blocks blocks;
	private Field field;
	
	private Boolean isGameOver = false;

	View(int w, int h){
		System.out.println("View constructor");
		
		start = new Point(w/2, 0);

		blocks = new Blocks(start, 0);
		field = new Field(w, h);

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


		/* set KeyStroke */
		InputMap imap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = panel.getActionMap();

		MyAction actionUp = new MyAction("UP", this);
		MyAction actionDown = new MyAction("DOWN", this);
		MyAction actionRight = new MyAction("RIGHT", this);
		MyAction actionLeft = new MyAction("LEFT", this);

		imap.put(KeyStroke.getKeyStroke("UP"), actionUp);
		amap.put(actionUp, actionUp);
		imap.put(KeyStroke.getKeyStroke('k'), actionUp);
		amap.put(actionUp, actionUp);
		imap.put(KeyStroke.getKeyStroke("DOWN"), actionDown);
		amap.put(actionDown, actionDown);
		imap.put(KeyStroke.getKeyStroke('j'), actionDown);
		amap.put(actionDown, actionDown);
		imap.put(KeyStroke.getKeyStroke("RIGHT"), actionRight);
		amap.put(actionRight, actionRight);
		imap.put(KeyStroke.getKeyStroke('l'), actionRight);
		amap.put(actionRight, actionRight);
		imap.put(KeyStroke.getKeyStroke("LEFT"), actionLeft);
		amap.put(actionLeft, actionLeft);
		imap.put(KeyStroke.getKeyStroke('h'), actionLeft);
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
		if(isGameOver){
			return;
		}

		System.out.println("next");
		
		Boolean pileSucceeded = field.pileBlocks(blocks);
		if(!pileSucceeded){
			System.out.println("pile is failed");
		}
		
		field.deleteLines();

		repaint();

		// setNextBlocks
		blocks = new Blocks(start, 0);
		
		// set blocks & check game over
		if(!field.canBeSetBlocks(blocks)){
			System.out.println("Game Over!!!");
			isGameOver = true;
			field.setAll(1);

			blocks = new Blocks(start,0,1); // バッドノウハウ アクティブブロックをGameOver色背景に埋める
			repaint();

		}
	}

	public void paint(Graphics g){
		super.paint(g);
		System.out.println("paint");
		panel.paint(panel.getGraphics());
	}

	public Field getField(){ return field; } 
	public Blocks getBlocks(){ return blocks; }
}
