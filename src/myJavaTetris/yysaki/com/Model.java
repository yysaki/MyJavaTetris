package myJavaTetris.yysaki.com;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import myJavaTetris.yysaki.com.View;
import myJavaTetris.yysaki.com.Tick;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GamePanel;
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
	private Tick _tick;
	private Controller _actionEnter;
	private Controller _actionUp;
	private Controller _actionDown;
	private Controller _actionLeft;
	private Controller _actionRight;

	/* Game�̏�� */
	private int _type;
	private static final int READY = 0;
	private static final int PLAYING = 1;
	private static final int END = 2;
	
	/* �Q�[���X�R�A */
	private int _hiscore;
	private int _score;
	private int _lines;

	/** tick�̎��� */
	private static final int INTERVAL = 1000;
	
	public Model(View v){
		_view = v;
		_type = READY;

		/* Tick */
		_tick = new Tick(this);
		_timer = new Timer(INTERVAL, _tick);
		
		_score = 0;
		_lines = 0;
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
	 * �o���o���Ȃ��ꍇGameOver�Ɣ���
	 */
	private void droped(){
		if(_type != PLAYING){ return; }

		GamePanel gp = _view.getGamePanel();
		gp.getField().pileBlocks(gp.getBlocks());

		int num = gp.getField().deleteLines();

		_score += num * num;
		_hiscore = Math.max(_hiscore, _score);
		_lines += num;
		setScoreLabels();
		
		_view.repaint();

		// setNextBlocks
		gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

		// set blocks & check game over
		if(!gp.getField().canSetBlocks(gp.getBlocks())){
			_type = END;
			_view.getInfoPanel().setStatus(_type);

			_timer.stop();

			gp.getField().setAll(gp.getField().getGameOverColor());

			gp.setBlocks(new GameBlocks(gp.getStartPoint(),0,1));
			_view.repaint();
		}
	}

	/**
	 * dir�����Ƀu���b�N���ړ��\���ǂ������ׂ�
	 */
	private Boolean isMovable(Point dir, int rotate){
		GameBlocks next = new GameBlocks(_view.getGamePanel().getBlocks());
		next.setDir(new Point(next.getDir().getX() + dir.getX(), next.getDir().getY() + dir.getY()));
		next.setRotate(next.getRotate() + rotate);

		if(_view.getGamePanel().getField().canSetBlocks(next)){
			return true;
		}else{
			return false;
		}
	}

	public void ColleagueChanged(Colleague c){
		final GamePanel gp = _view.getGamePanel();

		if(_actionEnter.equals(c)){
			switch(_type){
			case READY:
				/* START���� */
				_type = PLAYING;
				_view.getInfoPanel().setStatus(_type);
				gp.getField().setAll(gp.getField().getEmptyColor());
				gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));

				_view.repaint();
				_timer.start();
				break;
			case PLAYING:
				break;
			case END:
				/* replay���� */
				_type = PLAYING;
				_view.getInfoPanel().setStatus(_type);
				gp.getField().setAll(gp.getField().getEmptyColor());
				gp.setBlocks(new GameBlocks(gp.getStartPoint(), 0));
				
				_score = 0;
				_lines = 0;
				setScoreLabels();

				_view.repaint();
				_timer.start();
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
			}else if(_actionDown.equals(c) || _tick.equals(c)){
				droped();
			}
		}
	}
	
	private void setScoreLabels(){
		_view.getInfoPanel().setScore(Integer.toString(_score));
		_view.getInfoPanel().setHiscore(Integer.toString(_hiscore));		
		_view.getInfoPanel().setLines(Integer.toString(_lines));
	}
}
