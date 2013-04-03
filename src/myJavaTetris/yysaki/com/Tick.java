package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.Point;

/**
 * タイマーによるイベント操作の管理
 * Colleagueの具象クラス
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Tick extends Colleague {
	/** アクション時の移動方向 */
	private final Point _dir;
	/**　アクション時の回転方向(0の時回転しない) */
	private final int _rotate;

	public Tick(Model m){
		super(m);
		_dir = new Point(0,1);
		_rotate = 0;
	}

	/**
	 * タイマーによりアクティブブロックを操作する
	 */
	public void actionPerformed(ActionEvent e){
		Changed();
	}
	
	public Point getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}