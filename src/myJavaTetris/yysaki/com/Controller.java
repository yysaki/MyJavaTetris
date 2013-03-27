package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.Model;

/**
 * キー入力によるイベント操作の管理
 * Colleagueの具象クラス
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Controller extends Colleague {
	/** アクション時の移動方向 */
	private final Point _dir;
	/** アクション時の回転方向(0の時回転しない) */
	private final int _rotate;

	/**
	 * キー入力識別子
	 * "UP", "DOWN", "RIGHT", "LEFT", "ENTER"
	 */
	private final String _key;

	public Controller(String key, Model m){
		super(m);

		this._key = key;
		if(key=="UP"){
			_dir = new Point(0,0);
			_rotate = 1;
		}else if(key=="DOWN" || key=="tick"){
			_dir = new Point(0,1);
			_rotate = 0;
		}else if(key=="RIGHT"){
			_dir = new Point(1,0);
			_rotate = 0;
		}else if(key=="LEFT"){
			_dir = new Point(-1,0);
			_rotate = 0;
		}else{ // dummy
			_dir = new Point();
			_rotate = 0;
		}
	}

	/**
	 * キー入力によりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		Changed();		
	}

	public String getKey(){ return _key; }
	public Point getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}