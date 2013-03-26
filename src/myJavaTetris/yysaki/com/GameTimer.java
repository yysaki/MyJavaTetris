package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.View;

/**
 * タイマーによるイベント操作の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class GameTimer extends AbstractAction {
	private View _v;

	/**
	 * アクション時の移動方向
	 */
	private final Point _dir;

	/**
	 * アクション時の回転方向(0の時回転しない)
	 */
	private final int _rotate;

	public GameTimer(View v){
		this._v = v;
		_dir = new Point(0,1);
		_rotate = 0;
	}

	/**
	 * タイマーによりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		System.out.println("tick");

		if(isMovable()){
			final GameBlocks b = _v.getGamePanel().getBlocks();
			b.setDir(new Point(b.getDir().getX()+_dir.getX(), b.getDir().getY()+_dir.getY()));
			b.setRotate((b.getRotate() + _rotate) % b.getRotatable());
			_v.repaint();
		}else{
			_v.getGamePanel().next();
		}
	}

	/**
	 * 下方向にブロックが移動可能かどうか調べる
	 * 
	 * @return
	 */
	private Boolean isMovable(){
		GameBlocks next = new GameBlocks(_v.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + _dir.getX(), next.getDir().getY() + _dir.getY()));
		next.setRotate(next.getRotate() + _rotate);
		
		if(_v.getGamePanel().getField().canBeSetBlocks(next)){
			return true;
		}else{
			return false;
		}
	}
}