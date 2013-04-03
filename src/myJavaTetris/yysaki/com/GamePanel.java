package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.BlockIcon;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.MyPoint;


/**
 * �e�g���X���(JPanel)�̊Ǘ�
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private GameBlocks _blocks;
	private GameField _field;

	private final MyPoint _start; // �A�N�e�B�u�u���b�N�̃X�^�[�g�n�_

	private final static int DX = 2;
	private final static int DY = 2;


	public GamePanel(int blockWidth, int blockHeight) {
		super();

		_field = new GameField(blockWidth, blockHeight);
		_blocks = new GameBlocks(); // ��̃u���b�N
		_start = new MyPoint(blockWidth/2, 0);

		setBackground(Color.BLACK);
		/* panel size */
		setPreferredSize(new Dimension(getPanelWidth() + DX * 2, getPanelHeight() + DY * 2));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g);
	}

	/**
	 * draw piled blocks
	 * @param g
	 */
	private void drawBG(Graphics g){
		for(int i=0;i<getField().getWidth();i++){
			for(int j=0;j<getField().getHeight();j++){
				this.drawBlock(g, getField().getStatus(i, j), i, j);
			}
		}
	}
	
	/**
	 *  �A�N�e�B�u�u���b�N�̕`��
	 * @param g
	 */
	private void drawBlocks(Graphics g){
		GameBlocks b = getBlocks();
		for(int i=0;i<b.getPoints().length;i++){
			final MyPoint p = b.getPoints()[i];
			final int dx = b.getX()
					+ p.getX() * (int)Math.cos(b.getRotate() * Math.PI/2) 
					+ p.getY() * (int)Math.sin(b.getRotate() * Math.PI/2);
			final int dy = b.getY() 
					+ -1 * p.getX() * (int)Math.sin(b.getRotate() * Math.PI/2) 
					+ p.getY() * (int)Math.cos(b.getRotate() * Math.PI/2);

			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, BlockColor c, int x, int y){
		g.drawImage(BlockIcon.getIcon(c), x * BlockIcon.getSize() + DX,
				y * BlockIcon.getSize() + DY, this);		
	}
	
	public GameField getField(){ return _field; }
	public void setField(GameField arg){ _field = arg; }

	public GameBlocks getBlocks(){ return _blocks; }
	public void setBlocks(GameBlocks arg){ _blocks = arg; }

	public MyPoint getStartPoint(){ return _start; }

	public int getPanelWidth(){
		return getField().getWidth() * BlockIcon.getSize();
	}

	public int getPanelHeight(){
		return getField().getHeight() * BlockIcon.getSize();
	}

	/**
	 * @return �V�����e�g���X�u���b�N���Z�b�g�ł��Ȃ���true
	 */
	public Boolean isGameOver(){
		return !getField().canSetBlocks(getBlocks());
	}
	
	/**
	 * dir�����Ƀu���b�N���ړ��\���ǂ������ׂ�
	 */
	public Boolean isMovable(MyPoint dir, int rotate){
		GameBlocks candidate = new GameBlocks(getBlocks());
		candidate.setDir(new MyPoint(candidate.getX() + dir.getX(), candidate.getY() + dir.getY()));
		candidate.setRotate(candidate.getRotate() + rotate);

		return getField().canSetBlocks(candidate);
	}
	
	/**
	 * GamePanel����F�ɐ��߂�
	 * @param color GameField�̃J���[�萔
	 */
	public void fill(BlockColor color){
		getField().setAll(color);
		setBlocks(new GameBlocks(getStartPoint(), 0, color));
	}

}