package myJavaTetris.yysaki.com;


import java.awt.*;
import javax.swing.*;

import myJavaTetris.yysaki.com.Blocks;
import myJavaTetris.yysaki.com.Field;
import myJavaTetris.yysaki.com.View;


/**
 * �e�g���X���(JPanel)�̊Ǘ�
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private View v;
	private Blocks _blocks;
	private Field _field;
	private Boolean _isGameOver = false;
	
	private final Point start; // �A�N�e�B�u�u���b�N�̃X�^�[�g�n�_
	private final static int BLOCKSIZE = 25; // �����`�u���b�N�̕ӂ̒���
	private Image[] icon; // gray, red, yellow, purple, green, blue, orange, water

	public GamePanel(View v, int w, int h) {
		super();
		System.out.println("GamePanel Constructor");
		
		this.v = v;
		start = new Point(w/2, 0);
		setBlocks(new Blocks(start, 0));
		setField(new Field(w, h));
		
		/* panel size */
		setPreferredSize(new Dimension(getField().getWidth()*BLOCKSIZE, getField().getHeight()*BLOCKSIZE));

		/* set image */
		icon = new Image[8];
		icon[0] = Toolkit.getDefaultToolkit().getImage("res/tetris_gray.jpg");
		icon[1] = Toolkit.getDefaultToolkit().getImage("res/tetris_red.jpg");
		icon[2] = Toolkit.getDefaultToolkit().getImage("res/tetris_yellow.jpg");
		icon[3] = Toolkit.getDefaultToolkit().getImage("res/tetris_purple.jpg");
		icon[4] = Toolkit.getDefaultToolkit().getImage("res/tetris_green.jpg");
		icon[5] = Toolkit.getDefaultToolkit().getImage("res/tetris_blue.jpg");
		icon[6] = Toolkit.getDefaultToolkit().getImage("res/tetris_orange.jpg");
		icon[7] = Toolkit.getDefaultToolkit().getImage("res/tetris_water.jpg");
		
	}

	public void paintComponent(Graphics g){
//		System.out.println("GamePanel.paintComponent");
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g); // �A�N�e�B�u�u���b�N�̕`��
	}

	/**
	 * �n�ʂɐݒu�������A�V�����e�g���X�u���b�N���o��������
	 */
	public void next(){
		System.out.println("next");

		// check isGameOver
		if(getIsGameOver()){
			return;
		}

		Boolean pileSucceeded = getField().pileBlocks(getBlocks());
		if(!pileSucceeded){
			System.out.println("pile is failed");
		}

		getField().deleteLines();

		repaint();

		// setNextBlocks
		setBlocks(new Blocks(start, 0));

		// set blocks & check game over
		if(!getField().canBeSetBlocks(getBlocks())){
			System.out.println("Game Over!!!");
			setIsGameOver(true);
			getField().setAll(1);

			setBlocks(new Blocks(start,0,1)); // �o�b�h�m�E�n�E �A�N�e�B�u�u���b�N��GameOver�F�w�i�ɖ��߂�
			repaint();

		}
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

	private void drawBlocks(Graphics g){
		Blocks b = getBlocks();
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int dx = b.getDir().getX() + p.getX() * (int)Math.cos(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.sin(b.getRotate()*Math.PI/2);
			final int dy = b.getDir().getY() + -1 * p.getX() * (int)Math.sin(b.getRotate()*Math.PI/2) + p.getY() * (int)Math.cos(b.getRotate()*Math.PI/2);

			//			System.out.println("x: " +  x + ", y:" + y);
			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, int color, int x, int y){
		//			System.out.println("drawBlock color:" + color + ", x:" + x + ", y:" + y);
		//		System.out.println("drawBlock color:" + color);
		g.drawImage(icon[color], x*BLOCKSIZE, y*BLOCKSIZE, this);		
	}

	public Boolean getIsGameOver(){ return _isGameOver; }
	public void setIsGameOver(Boolean arg){ _isGameOver = arg; }
	
	public Field getField(){ return _field; }
	public void setField(Field arg){ _field = arg; }

	public Blocks getBlocks(){ return _blocks; }
	public void setBlocks(Blocks arg){ _blocks = arg; }

}
