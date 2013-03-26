package myJavaTetris.yysaki.com;

import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.GameTimer;


/**
 * Controller, GameTimerからPanelの状態を決定する
 * @author yysaki
 *
 */
public class Model {
	private View _view;
	private Boolean _isGameOver = false;

	
	public Model(View arg){
		_view = arg;

		/* Tick */
		new Timer(1000, new GameTimer(_view)).start();
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
}
