package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.Model;
import myJavaTetris.yysaki.com.MyPoint;

/**
 * �L�[���͂ɂ��C�x���g����̊Ǘ�
 * Colleague�̋�ۃN���X
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Controller extends Colleague {
	/** �A�N�V�������̈ړ����� */
	private final MyPoint _dir;
	/** �A�N�V�������̉�]����(0�̎���]���Ȃ�) */
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
	 * �L�[���͂ɂ��A�N�e�B�u�u���b�N�𑀍삷��
	 */
	public void actionPerformed(ActionEvent e){
		Changed();		
	}

	public MyPoint getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}