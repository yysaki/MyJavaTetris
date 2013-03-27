package myJavaTetris.yysaki.com;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.GameTimer;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.Colleague;


/**
 * Controller, GameTimerからPanelの状態を決定する
 * MediatorパターンのMediator役, Colleagueのサブクラスからイベントを受け取る
 * @author yysaki
 *
 */
public class Model {
	private View _view;
	private Boolean _isGameOver = false;
	
	private GameTimer _timer;


	public Model(View arg){
		_view = arg;

		/* Tick */
		_timer = new GameTimer(_view, this);
		new Timer(1000, _timer).start();
	}

	/**
	 * キー入力の設定
	 */
	public void setKeyStrokes() {
		InputMap imap = _view.getGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = _view.getGamePanel().getActionMap();

		Controller actionUp = new Controller("UP", this);
		Controller actionDown = new Controller("DOWN", this);
		Controller actionRight = new Controller("RIGHT", this);
		Controller actionLeft = new Controller("LEFT", this);

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

	
	/**
	 * 地面に設置した時、新しいテトリスブロックを出現させる
	 */
	public void next(){
		System.out.println("next");

		GamePanel gp = _view.getGamePanel();

		if(_isGameOver){
			return;
		}

		Boolean pileSucceeded = gp.getField().pileBlocks(gp.getBlocks());
		if(!pileSucceeded){
			System.out.println("pile is failed");
		}

		gp.getField().deleteLines();

		gp.repaint();

		// setNextBlocks
		gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

		// set blocks & check game over
		if(!gp.getField().canBeSetBlocks(gp.getBlocks())){
			System.out.println("Game Over!!!");
			_isGameOver = true;
			gp.getField().setAll(1);

			gp.setBlocks(new GameBlocks(gp.getStartPoint(),0,1)); // バッドノウハウ アクティブブロックをGameOver色背景に埋める
			gp.repaint();
		}
	}
	
	/**
	 * dir方向にブロックが移動可能かどうか調べる
	 * 
	 * @return
	 */
	private Boolean isMovable(Point dir, int rotate){
		GameBlocks next = new GameBlocks(_view.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + dir.getX(), next.getDir().getY() + dir.getY()));
		next.setRotate(next.getRotate() + rotate);
		
		if(_view.getGamePanel().getField().canBeSetBlocks(next)){
			return true;
		}else{
			return false;
		}

	}
	
	public void ColleagueChanged(Colleague c){
		System.out.println("call from " + c.getKey());
		if(isMovable(c.getDir(), c.getRotate())){
			final GameBlocks b = _view.getGamePanel().getBlocks();
			b.setDir(new Point(b.getDir().getX()+c.getDir().getX(), b.getDir().getY()+c.getDir().getY()));
			b.setRotate((b.getRotate() + c.getRotate()) % b.getRotatable());
			_view.repaint();
		}else if(c.getKey()=="DOWN" || c.getKey()=="tick"){
			_view.getGamePanel().next();
		}
	}
}
