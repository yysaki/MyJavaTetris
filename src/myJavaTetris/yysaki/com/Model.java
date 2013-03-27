package myJavaTetris.yysaki.com;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.GameTimer;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.Colleague;


/**
 * Controller, GameTimer����Panel�̏�Ԃ����肷��
 * Mediator�p�^�[����Mediator��, Colleague�̃T�u�N���X����C�x���g���󂯎��
 * @author yysaki
 *
 */
public class Model {
	private View _view;

	private Timer _timer;
	/** Colleague�C���X�^���X�O */
	private GameTimer _gameTimer;
	private Controller _actionEnter;
	private Controller _actionUp;
	private Controller _actionDown;
	private Controller _actionLeft;
	private Controller _actionRight;

	/** Game�̃X�e�[�^�X */
	private int _type;
	static final int READY = 0;
	static final int PLAYING = 1;
	static final int END = 2;

	/** tick�̎��� */
	static final int INTERVAL = 1000;
	
	public Model(View arg){
		_view = arg;
		_type = READY;

		/* Tick */
		_gameTimer = new GameTimer(_view, this);
		_timer = new Timer(INTERVAL, _gameTimer);
	}

	/**
	 * �L�[���͂̐ݒ�
	 */
	public void setKeyStrokes() {
		InputMap imap = _view.getGamePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = _view.getGamePanel().getActionMap();

		_actionEnter = new Controller("ENTER", this);
		_actionUp = new Controller("UP", this);
		_actionDown = new Controller("DOWN", this);
		_actionRight = new Controller("RIGHT", this);
		_actionLeft = new Controller("LEFT", this);

		imap.put(KeyStroke.getKeyStroke("ENTER"), _actionEnter);
		amap.put(_actionEnter, _actionEnter);
		imap.put(KeyStroke.getKeyStroke("UP"), _actionUp);
		amap.put(_actionUp, _actionUp);
		imap.put(KeyStroke.getKeyStroke('k'), _actionUp);
		amap.put(_actionUp, _actionUp);
		imap.put(KeyStroke.getKeyStroke("DOWN"), _actionDown);
		amap.put(_actionDown, _actionDown);
		imap.put(KeyStroke.getKeyStroke('j'), _actionDown);
		amap.put(_actionDown, _actionDown);
		imap.put(KeyStroke.getKeyStroke("RIGHT"), _actionRight);
		amap.put(_actionRight, _actionRight);
		imap.put(KeyStroke.getKeyStroke('l'), _actionRight);
		amap.put(_actionRight, _actionRight);
		imap.put(KeyStroke.getKeyStroke("LEFT"), _actionLeft);
		amap.put(_actionLeft, _actionLeft);
		imap.put(KeyStroke.getKeyStroke('h'), _actionLeft);
		amap.put(_actionLeft, _actionLeft);
	}


	/**
	 * �n�ʂɐݒu�������A�V�����e�g���X�u���b�N���o��������
	 */
	public void next(){
		System.out.println("next");

		GamePanel gp = _view.getGamePanel();

		if(_type == END){
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
			_type = END;
			_timer.stop();

			gp.getField().setAll(1);

			gp.setBlocks(new GameBlocks(gp.getStartPoint(),0,1)); // �o�b�h�m�E�n�E �A�N�e�B�u�u���b�N��GameOver�F�w�i�ɖ��߂�
			gp.repaint();
		}
	}

	/**
	 * dir�����Ƀu���b�N���ړ��\���ǂ������ׂ�
	 */
	private Boolean isMovable(Point dir, int rotate){
		GameBlocks next = new GameBlocks(_view.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + dir.getX(), next.getDir().getY() + dir.getY()));
		next.setRotate(next.getRotate() + rotate);

		if(_view.getGamePanel().getField().canBeSetBlocks(next)){
			return true;
		}else{
			return false;
		}

	}

	public void ColleagueChanged(Colleague c){
		System.out.println("call from " + c.getKey());

		if(_actionEnter.equals(c)){
			/* TODO GameMode�̕ύX */
			switch(_type){
			case READY:
				_type = PLAYING;
				_timer.start();
				break;
			case PLAYING:
				break;
			case END:
				/* TODO PLAYING�ɖ߂肽�� */
				break;
			default:
				break;
			}

			return ;
		}

		if(_type == PLAYING){
			if(isMovable(c.getDir(), c.getRotate())){
				final GameBlocks b = _view.getGamePanel().getBlocks();
				b.setDir(new Point(b.getDir().getX()+c.getDir().getX(), b.getDir().getY()+c.getDir().getY()));
				b.setRotate((b.getRotate() + c.getRotate()) % b.getRotatable());
				_view.repaint();
			}else if(_actionDown.equals(c) || _gameTimer.equals(c)){
				_view.getGamePanel().next();
			}
		}
	}
}
