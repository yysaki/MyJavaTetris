package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import myJavaTetris.yysaki.com.Model;
import myJavaTetris.yysaki.com.MyPoint;


/**
 * タイマー、キー入力などのイベント管理
 * Mediator役のModelクラスにactionPerformedを伝達する
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public abstract class Colleague extends AbstractAction {
	private Model _model;
	
	public Colleague(Model m){
		_model = m;
	}
	
	void Changed(){
		_model.ColleagueChanged(this);
	}

	@Override
	abstract public void actionPerformed(ActionEvent arg0);

	/** アクション時の移動方向 */
	abstract public MyPoint getDir();
	/** アクション時の回転方向(0の時回転しない) */
	abstract public int getRotate();
}
