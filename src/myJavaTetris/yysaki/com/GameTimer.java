package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.Colleague;

/**
 * �^�C�}�[�ɂ��C�x���g����̊Ǘ�
 * Colleague�̋�ۃN���X
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class GameTimer extends Colleague {
	/** �A�N�V�������̈ړ����� */
	private final Point _dir;
	/**�@�A�N�V�������̉�]����(0�̎���]���Ȃ�) */
	private final int _rotate;

	public GameTimer(View v, Model m){
		super(m);
		_dir = new Point(0,1);
		_rotate = 0;
	}

	/**
	 * �^�C�}�[�ɂ��A�N�e�B�u�u���b�N�𑀍삷��
	 */
	public void actionPerformed(ActionEvent e){
		Changed();
	}
	
	public String getKey(){ return "tick"; }
	public Point getDir(){ return _dir; }
	public int getRotate(){ return _rotate; }
}