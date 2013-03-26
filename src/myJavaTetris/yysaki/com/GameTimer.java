package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.View;

/**
 * �^�C�}�[�ɂ��C�x���g����̊Ǘ�
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class GameTimer extends AbstractAction {
	private View _v;

	/**
	 * �A�N�V�������̈ړ�����
	 */
	private final Point _dir;

	/**
	 * �A�N�V�������̉�]����(0�̎���]���Ȃ�)
	 */
	private final int _rotate;

	public GameTimer(View v){
		this._v = v;
		_dir = new Point(0,1);
		_rotate = 0;
	}

	/**
	 * �^�C�}�[�ɂ��A�N�e�B�u�u���b�N�𑀍삷��
	 */
	public void actionPerformed(ActionEvent e){
		System.out.println("tick");

		if(isMovable()){
			final GameBlocks b = _v.getGamePanel().getBlocks();
			b.setDir(new Point(b.getDir().getX()+_dir.getX(), b.getDir().getY()+_dir.getY()));
			b.setRotate((b.getRotate() + _rotate) % b.getRotatable());
			_v.repaint();
		}else{
			_v.getGamePanel().next();
		}
	}

	/**
	 * �������Ƀu���b�N���ړ��\���ǂ������ׂ�
	 * 
	 * @return
	 */
	private Boolean isMovable(){
		GameBlocks next = new GameBlocks(_v.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + _dir.getX(), next.getDir().getY() + _dir.getY()));
		next.setRotate(next.getRotate() + _rotate);
		
		if(_v.getGamePanel().getField().canBeSetBlocks(next)){
			return true;
		}else{
			return false;
		}
	}
}