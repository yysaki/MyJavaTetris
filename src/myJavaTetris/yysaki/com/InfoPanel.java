package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import javax.swing.JPanel;
import myJavaTetris.yysaki.com.InfoNextBlocksPanel;

/**
 * ゲームの操作方法や状況を表示するパネル
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	private MessagePanel _message;
	private InfoNextBlocksPanel _nextPanel;
	private PointsPanel _hiscore;
	private PointsPanel _score;
	private PointsPanel _lines;
	private KeybindsPanel _keybinds;
	
	public static final String MASSAGE[] = {
		"READY: PRESS 'ENTER' KEY", 
		"NOW PLAYING", 
		"GAMEOVER: PRESS 'ENTER' KEY"
		};
	public static final int STRINGHEIGHT = 25;

	
	/**
	 * スコアやキー操作方法を表示するパネル
	 * GamePanelのパネルサイズをコピーする
	 * 
	 * @param width パネルの幅
	 * @param height パネルの高さ
	 */
	public InfoPanel(int width, int height){
		super();
		int stringWidth = width - 30;
		
		setPreferredSize(new Dimension(width, height));
		setLayout(new FlowLayout());
		setBackground(new Color(200, 200, 200));
		
		_message = new MessagePanel(stringWidth);
		_nextPanel = new InfoNextBlocksPanel();
		_hiscore = new PointsPanel(stringWidth, "HISCORE");
		_score = new PointsPanel(stringWidth, "SCORE");
		_lines = new PointsPanel(stringWidth, "DELETED LINES");
		_keybinds = new KeybindsPanel(stringWidth);
		
		add(_message);
		add(_nextPanel);
		add(_hiscore);
		add(_score);
		add(_lines);
		add(_keybinds);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_nextPanel.paintComponent(_nextPanel.getGraphics());
	}

	
	private class MessagePanel extends JPanel {
		JLabel _label;
		private MessagePanel(int stringWidth){
			super();
					
			setOpaque(false);
			setPreferredSize(new Dimension(stringWidth, STRINGHEIGHT));

			_label = new JLabel(MASSAGE[0]); 
			add(_label, SwingConstants.CENTER);					
		}
		
		private void setMessage(int type){
			_label.setText(MASSAGE[type]);
		}
	}
	
	private class PointsPanel extends JPanel {
		private JLabel _points;
		private PointsPanel(int stringWidth, String message){
			super();
			
			setOpaque(false);
			setPreferredSize(new Dimension(stringWidth, STRINGHEIGHT));
			setLayout(new BorderLayout());
			add(new JLabel(message + ": "), BorderLayout.WEST);
			_points = new JLabel("0");
			_points.setHorizontalAlignment(SwingConstants.RIGHT);
			add(_points, BorderLayout.EAST);
		}

		private void setPoints(int arg){
			_points.setText(Integer.toString(arg));
		}				
	}
	
	private class KeybindsPanel extends JPanel {
		private KeybindsPanel(int stringWidth){
			super();
			String keybindsInformation[] = {
					"HOW TO MOVE",
					"move to left: ← or 'h'",
					"move to right: → or 'l'",
					"rotate: ↑ or 'j'", 
					"fall down: ↓ or 'k"
					};
			setBackground(Color.LIGHT_GRAY);
			setPreferredSize(new Dimension(stringWidth, STRINGHEIGHT
					* keybindsInformation.length));
			setLayout(new GridLayout(keybindsInformation.length, 1));
			for(int i = 0; i < keybindsInformation.length; i++){
				JLabel l = new JLabel(keybindsInformation[i]);
				l.setHorizontalAlignment(SwingConstants.CENTER);
				add(l);
			}
		}

	}
	public void setNextBlocks(GameBlocks arg){ _nextPanel.setNextBlocks(arg); }
	public void setStatus(int type){ _message.setMessage(type); }
	public void setHiscore(int arg){ _hiscore.setPoints(arg); }
	public void setScore(int arg){ _score.setPoints(arg); }
	public void setLines(int arg){ _lines.setPoints(arg); }
}
