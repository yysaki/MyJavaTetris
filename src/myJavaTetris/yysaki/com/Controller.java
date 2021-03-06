package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.Model;
import myJavaTetris.yysaki.com.MyPoint;

/**
 * キー入力によるイベント操作の管理
 * Colleagueの具象クラス
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Controller extends Colleague {
	/** アクション時の移動方向 */
	private final MyPoint _dir;
	/** アクション時の回転方向(0の時回転しない) */
	private final int _rotate;

	public Controller(String key, Model m){
		super(m);

		if(key=="UP"){
			_dir = new MyPoint(0,0);
			_rotate = 1;
		}else if(key=="DOWN" || key=="tick"){
			_dir = new MyPoint(0,1);
			_rotate = 0;
		}else if(key=="RIGHT"){
			_dir = new MyPoint(1,0);
			_rotate = 0;
		}else if(key=="LEFT"){
			_dir = new MyPoint(-1,0);
			_rotate = 0;
		}else{ // dummy
			_dir = new MyPoint();
			_rotate = 0;
		}
	}

	/**
	 * キー入力によりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		Changed();		
	}

	public MyPoint getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}