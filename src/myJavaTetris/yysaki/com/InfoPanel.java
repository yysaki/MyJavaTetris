package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import myJavaTetris.yysaki.com.Model;
import myJavaTetris.yysaki.com.View;
import javax.swing.JPanel;

/**
 * �Q�[���̑�����@��󋵂�\������p�l��
 * @author yysaki
 *
 */
public class InfoPanel extends JPanel {
	private Model _model;
	private View _view;

	private JLabel _status;
	private JLabel _hiscore;
	private JLabel _score;
	private JLabel _lines;
	
	private static final String MASSAGE[] = {"READY: PRESS 'ENTER' KEY", "NOW PLAYING", 
			"GAMEOVER: PRESS 'ENTER' KEY"};

	public InfoPanel(View v, Model m, int w, int h){
		super();
		_view = v;
		_model = m;
		int width = _view.getGamePanel().getPanelWidth();
		int height = _view.getGamePanel().getPanelHeight();
		int labelWidth = width - 30;
		
		setPreferredSize(new Dimension(width, height));
		setLayout(new FlowLayout());
		setBackground(new Color(200, 200, 200));

		_status = new JLabel(MASSAGE[0]);
		_status.setHorizontalAlignment(SwingConstants.CENTER);
		_status.setPreferredSize(new Dimension(labelWidth, 25));

		JPanel hiscorePanel = new JPanel();
		hiscorePanel.setOpaque(false);
		hiscorePanel.setPreferredSize(new Dimension(labelWidth, 25));
		hiscorePanel.setLayout(new BorderLayout());
		hiscorePanel.add(new JLabel("HISCORE: "), BorderLayout.WEST);
		_hiscore = new JLabel("0");
		_hiscore.setHorizontalAlignment(SwingConstants.RIGHT);
		hiscorePanel.add(_hiscore, BorderLayout.EAST);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setOpaque(false);
		scorePanel.setPreferredSize(new Dimension(labelWidth, 25));
		scorePanel.setLayout(new BorderLayout());
		scorePanel.add(new JLabel("SCORE: "), BorderLayout.WEST);
		_score = new JLabel("0");
		_score.setHorizontalAlignment(SwingConstants.RIGHT);
		scorePanel.add(_score, BorderLayout.EAST);
		
		JPanel linesPanel = new JPanel();
		linesPanel.setOpaque(false);
		linesPanel.setPreferredSize(new Dimension(labelWidth, 25));
		linesPanel.setLayout(new BorderLayout());
		linesPanel.add(new JLabel("DELETE LINES: "), BorderLayout.WEST);
		_lines = new JLabel("0");
		_lines.setHorizontalAlignment(SwingConstants.RIGHT);
		linesPanel.add(_lines, BorderLayout.EAST);
		
		/* keybinds infomation */
		String keybinds[] = {
				"HOW TO MOVE",
				"move to left: �� or 'h'",
				"move to right: �� or 'l'",
				"rotate: �� or 'j'", 
				"fall down: �� or 'k"
				};
		JPanel keybindsPanel = new JPanel();
		setBackground(Color.LIGHT_GRAY);
		keybindsPanel.setPreferredSize(new Dimension(labelWidth, 25 * keybinds.length));
		keybindsPanel.setLayout(new FlowLayout());
		for(int i = 0; i < keybinds.length; i++){
			JLabel l = new JLabel(keybinds[i]);
			l.setHorizontalAlignment(SwingConstants.CENTER);
			keybindsPanel.add(l);
		}
		
		add(_status);
		add(hiscorePanel);
		add(scorePanel);
		add(linesPanel);
		add(keybindsPanel);
	}
	
	public void setStatus(int type){ _status.setText(MASSAGE[type]); }
	public void setHiscore(String arg){ _hiscore.setText(arg); }
	public void setScore(String arg){ _score.setText(arg); }
	public void setLines(String arg){ _lines.setText(arg); }
}
