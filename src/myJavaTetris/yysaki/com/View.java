package myJavaTetris.yysaki.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.Model;

/**
 * ゲームウインドウの管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private GamePanel _gamePanel;
	private InfoPanel _infoPanel;
	private Model _model;

	/**
	 * @param width GamePanelの幅毎のブロック数
	 * @param height GamePanelの高さ毎のブロック数
	 */
	View(int width, int height){
		System.out.println("View constructor");

		/* set Frame */
		_model = new Model(this);
		_gamePanel = new GamePanel(width, height);
		_infoPanel = new InfoPanel(_gamePanel.getPanelWidth(), _gamePanel.getPanelHeight());
		getContentPane().setLayout(new GridLayout(1,2));
		getContentPane().add(_gamePanel);
		getContentPane().add(_infoPanel);
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
	public InfoPanel getInfoPanel(){ return _infoPanel; }
}
