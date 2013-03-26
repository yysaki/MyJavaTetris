package myJavaTetris.yysaki.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.MyAction;

/**
 * ゲームウインドウの管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private GamePanel _gamePanel; 

	/**
	 * @param w GamePanelの幅毎のブロック数
	 * @param h GamePanelの高さ毎のブロック数
	 */
	View(int w, int h){
		System.out.println("View constructor");

		/* set Frame */
		_gamePanel = new GamePanel(this, w, h);
		getContentPane().add(_gamePanel);
		pack();
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		setKeyStrokes();

		/* Tick */
		new Timer(1000, new MyAction("tick", this)).start();

		setVisible(true);
	}
	
	/**
	 * キー入力の設定
	 */
	private void setKeyStrokes() {
		InputMap imap = _gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = _gamePanel.getActionMap();

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
	}

	public void paint(Graphics g){
		super.paint(g);
		System.out.println("paint");
		_gamePanel.paint(_gamePanel.getGraphics());
	}
	
	public GamePanel getGamePanel(){ return _gamePanel; }
}
