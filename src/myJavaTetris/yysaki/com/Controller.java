package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import myJavaTetris.yysaki.com.Colleague;
import myJavaTetris.yysaki.com.Model;

/**
 * �L�[���͂ɂ��C�x���g����̊Ǘ�
 * Colleague�̋�ۃN���X
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class Controller extends Colleague {
	/** �A�N�V�������̈ړ����� */
	private final Point _dir;
	/** �A�N�V�������̉�]����(0�̎���]���Ȃ�) */
	private final int _rotate;

	/**
	 * �L�[���͎��ʎq
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
	 * �L�[���͂ɂ��A�N�e�B�u�u���b�N�𑀍삷��
	 */
	public void actionPerformed(ActionEvent e){
		Changed();		
	}

	public String getKey(){ return _key; }
	public Point getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}