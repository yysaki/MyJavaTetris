package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.MyPoint;

/**
 * タイマーによるイベント操作の管理
 * Colleagueの具象クラス
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Tick extends Colleague {
	/** アクション時の移動方向 */
	private final MyPoint _dir;
	/**　アクション時の回転方向(0の時回転しない) */
	private final int _rotate;

	public Tick(Model m){
		super(m);
		_dir = new MyPoint(0,1);
		_rotate = 0;
	}

	/**
	 * タイマーによりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		Changed();
	}
	
	public MyPoint getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}