package myJavaTetris.yysaki.com;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.Tick;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GamePanel;
import myJavaTetris.yysaki.com.Colleague;


/**
 * Controller, GameTimerからPanelの状態を決定する
 * MediatorパターンのMediator役, Colleagueのサブクラスからイベントを受け取る
 * @author yysaki
 *
 */
public class Model {
	private View _view;

	private Timer _timer;
	/** Colleagueインスタンス衆 */
	private Tick _tick;
	private Controller _actionEnter;
	private Controller _actionUp;
	private Controller _actionDown;
	private Controller _actionLeft;
	private Controller _actionRight;

	/* Gameの状態 */
	private int _type;
	private static final int READY = 0;
	private static final int PLAYING = 1;
	private static final int END = 2;
	
	/* ゲームスコア */
	private int _hiscore;
	private int _score;
	private int _lines;

	/** tickの周期 */
	private static final int INTERVAL = 1000;
	
	public Model(View v){
		_view = v;
		_type = READY;

		/* Tick */
		_tick = new Tick(this);
		_timer = new Timer(INTERVAL, _tick);
		
		_score = 0;
		_lines = 0;
	}

	/**
	 * キー入力の設定
	 */
	public void setKeyStrokes() {
		InputMap imap = _view.getGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = _view.getGamePanel().getActionMap();

		_actionEnter = new Controller("ENTER", this);
		_actionUp = new Controller("UP", this);
		_actionDown = new Controller("DOWN", this);
		_actionRight = new Controller("RIGHT", this);
		_actionLeft = new Controller("LEFT", this);

		imap.put(KeyStroke.getKeyStroke("ENTER"), _actionEnter);
		amap.put(_actionEnter, _actionEnter);
		imap.put(KeyStroke.getKeyStroke("UP"), _actionUp);
		amap.put(_actionUp, _actionUp);
		imap.put(KeyStroke.getKeyStroke('k'), _actionUp);
		amap.put(_actionUp, _actionUp);
		imap.put(KeyStroke.getKeyStroke("DOWN"), _actionDown);
		amap.put(_actionDown, _actionDown);
		imap.put(KeyStroke.getKeyStroke('j'), _actionDown);
		amap.put(_actionDown, _actionDown);
		imap.put(KeyStroke.getKeyStroke("RIGHT"), _actionRight);
		amap.put(_actionRight, _actionRight);
		imap.put(KeyStroke.getKeyStroke('l'), _actionRight);
		amap.put(_actionRight, _actionRight);
		imap.put(KeyStroke.getKeyStroke("LEFT"), _actionLeft);
		amap.put(_actionLeft, _actionLeft);
		imap.put(KeyStroke.getKeyStroke('h'), _actionLeft);
		amap.put(_actionLeft, _actionLeft);
	}


	/**
	 * _type == PLAYINGのみ呼び出される
	 * 地面に設置した時、新しいテトリスブロックを出現させる
	 * 出現出来ない場合GameOverと判定
	 */
	private void droped(){
		if(_type != PLAYING){ return; }

		GamePanel gp = _view.getGamePanel();
		
		// Fieldにブロックを設置する
		gp.getField().pileBlocks(gp.getBlocks());
		int num = gp.getField().deleteLines();
		addPoints(num);
		setScoreLabels();
		_view.repaint();

		// 新しいテトリスブロックを出現させる
		gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

		// check game over
		if(gp.isGameOver()){
			_timer.stop();
			_type = END;
			_view.getInfoPanel().setStatus(_type);
			gp.fill(gp.getField().getGameOverColor());
			_view.repaint();
		}
	}

	public void ColleagueChanged(Colleague c){
		final GamePanel gp = _view.getGamePanel();

		if(_actionEnter.equals(c)){
			switch(_type){
			case READY:
				/* START動作 */
				_type = PLAYING;
				_view.getInfoPanel().setStatus(_type);
				gp.getField().setAll(gp.getField().getEmptyColor());
				gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

				_view.repaint();
				_timer.start();
				break;
			case PLAYING:
				break;
			case END:
				/* replay動作 */
				_type = PLAYING;
				_view.getInfoPanel().setStatus(_type);
				gp.getField().setAll(gp.getField().getEmptyColor());
				gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));
				
				_score = 0;
				_lines = 0;
				setScoreLabels();

				_view.repaint();
				_timer.start();
				break;
			default:
				break;
			}

			return ;
		}

		if(_type == PLAYING){
			if(_view.getGamePanel().isMovable(c.getDir(), c.getRotate())){
				final GameBlocks b = _view.getGamePanel().getBlocks();

				b.setDir(new Point(b.getDir().getX()+c.getDir().getX(), b.getDir().getY()+c.getDir().getY()));
				b.setRotate((b.getRotate() + c.getRotate()) % b.getRotatable());
				_view.repaint();
			}else if(_actionDown.equals(c) || _tick.equals(c)){
				droped();
			}
		}
	}

	private void addPoints(int arg){
		_score += arg * arg;
		_hiscore = Math.max(_hiscore, _score);
		_lines += arg;
	}

	private void setScoreLabels(){
		_view.getInfoPanel().setScore(_score);
		_view.getInfoPanel().setHiscore(_hiscore);		
		_view.getInfoPanel().setLines(_lines);
	}
}
