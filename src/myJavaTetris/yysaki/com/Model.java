package myJavaTetris.yysaki.com;

import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.GameTimer;


/**
 * Controller, GameTimer����Panel�̏�Ԃ����肷��
 * @author yysaki
 *
 */
public class Model {
	private View _view;
	private Boolean _isGameOver = false;

	
	public Model(View arg){
		_view = arg;

		/* Tick */
		new Timer(1000, new GameTimer(_view)).start();
	}
	
	/**
	 * �n�ʂɐݒu�������A�V�����e�g���X�u���b�N���o��������
	 */
	public void next(){
		System.out.println("next");

		GamePanel gp = _view.getGamePanel();
	
		if(_isGameOver){
			return;
		}

		Boolean pileSucceeded = gp.getField().pileBlocks(gp.getBlocks());
		if(!pileSucceeded){
			System.out.println("pile is failed");
		}

		gp.getField().deleteLines();

		gp.repaint();

		// setNextBlocks
		gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

		// set blocks & check game over
		if(!gp.getField().canBeSetBlocks(gp.getBlocks())){
			System.out.println("Game Over!!!");
			_isGameOver = true;
			gp.getField().setAll(1);

			gp.setBlocks(new GameBlocks(gp.getStartPoint(),0,1)); // �o�b�h�m�E�n�E �A�N�e�B�u�u���b�N��GameOver�F�w�i�ɖ��߂�
			gp.repaint();
		}
	}
}
