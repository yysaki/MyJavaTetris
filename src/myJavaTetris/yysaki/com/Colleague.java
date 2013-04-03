package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import myJavaTetris.yysaki.com.Model;
import myJavaTetris.yysaki.com.MyPoint;


/**
 * �^�C�}�[�A�L�[���͂Ȃǂ̃C�x���g�Ǘ�
 * Mediator����Model�N���X��actionPerformed��`�B����
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

	/** �A�N�V�������̈ړ����� */
	abstract public MyPoint getDir();
	/** �A�N�V�������̉�]����(0�̎���]���Ȃ�) */
	abstract public int getRotate();
}
