package myJavaTetris.yysaki.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.Controller;
import myJavaTetris.yysaki.com.Model;

/**
 * ゲームウインドウの管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private GamePanel _gamePanel; 
	private Model _model;

	/**
	 * @param w GamePanelの幅毎のブロック数
	 * @param h GamePanelの高さ毎のブロック数
	 */
	View(int w, int h){
		System.out.println("View constructor");

		/* set Frame */
		_model = new Model(this);
		_gamePanel = new GamePanel(this, _model, w, h);
		getContentPane().add(_gamePanel);
		pack();
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		_model.setKeyStrokes();

		setVisible(true);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		System.out.println("paint");
		_gamePanel.paint(_gamePanel.getGraphics());
	}
	
	public GamePanel getGamePanel(){ return _gamePanel; }
}
