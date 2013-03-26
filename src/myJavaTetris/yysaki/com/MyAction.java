package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.View;

/**
 * キー入力、タイマーによるイベント操作の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class MyAction extends AbstractAction {
	private View _v;

	/**
	 * アクション時の移動方向
	 */
	private final Point dir;

	/**
	 * アクション時の回転方向(0の時回転しない)
	 */
	private final int rotate;

	/**
	 * イベント識別子
	 * "tick", "UP", "DOWN", "RIGHT", "LEFT"
	 */
	private final String KEY;

	public MyAction(String key, View v){
		this._v = v;
		this.KEY = key;
		if(key=="UP"){
			dir = new Point(0,0);
			rotate = 1;
		}else if(key=="DOWN" || key=="tick"){
			dir = new Point(0,1);
			rotate = 0;
		}else if(key=="RIGHT"){
			dir = new Point(1,0);
			rotate = 0;
		}else if(key=="LEFT"){
			dir = new Point(-1,0);
			rotate = 0;
		}else{ // if(key=="tick")
			// dummy
			dir = new Point(0,0); 
			rotate = 0;
		}
	}

	/**
	 * キー入力、タイマーによりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		System.out.println(KEY);

		if(isMovable()){
			final Blocks b = _v.getGamePanel().getBlocks();
			b.setDir(new Point(b.getDir().getX()+dir.getX(), b.getDir().getY()+dir.getY()));
			b.setRotate((b.getRotate() + rotate) % b.getRotatable());
			_v.repaint();
		}else if(KEY=="DOWN" || KEY=="tick"){
			_v.getGamePanel().next();
		}
	}

	/**
	 * キー入力方向にブロックが移動可能かどうか調べる
	 * 
	 * @return
	 */
	private Boolean isMovable(){
		Blocks next = new Blocks(_v.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + dir.getX(), next.getDir().getY() + dir.getY()));
		next.setRotate(next.getRotate() + rotate);
		
		if(_v.getGamePanel().getField().canBeSetBlocks(next)){
			return true;
		}else{
			return false;
		}

	}
}