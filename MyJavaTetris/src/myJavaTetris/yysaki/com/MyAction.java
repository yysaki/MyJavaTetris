package myJavaTetris.yysaki.com;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import myJavaTetris.yysaki.com.View;

/**
 * �L�[���́A�^�C�}�[�ɂ��C�x���g����̊Ǘ�
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
class MyAction extends AbstractAction {
	private View v;

	/**
	 * �A�N�V�������̈ړ�����
	 */
	private final Point dir;

	/**
	 * �A�N�V�������̉�]����(0�̎���]���Ȃ�)
	 */
	private final int rotate;

	/**
	 * �C�x���g���ʎq
	 * "tick", "UP", "DOWN", "RIGHT", "LEFT"
	 */
	private final String key;

	public MyAction(String key, View v){
		this.v = v;
		this.key = key;
		if(key=="UP"){
			dir = new Point(0,0);
			rotate = 1;
		}else if(key=="DOWN" || key=="tick"){
			dir = new Point(0,1);
			rotate = 0;
		}else if(key=="RIGHT"){
			dir = new Point(1,0);
			rotate = 0;
		}else{ // if(key=="LEFT")
			dir = new Point(-1,0);
			rotate = 0;
		}
	}

	/**
	 * �L�[���́A�^�C�}�[�ɂ��A�N�e�B�u�u���b�N�𑀍삷��
	 */
	public void actionPerformed(ActionEvent e){
		System.out.println(key);

		if(isMovable()){
			final Blocks b = v.getBlocks();
			b.setDir(new Point(b.getDir().getX()+dir.getX(), b.getDir().getY()+dir.getY()));
			b.setRotate((b.getRotate() + rotate) % b.getRotatable());
			v.repaint();
		}else if(key=="DOWN" || key=="tick"){
			v.next();
		}
	}

	/**
	 * �L�[���͕����Ƀu���b�N���ړ��\���ǂ������ׂ�
	 * 
	 * @return
	 */
	private Boolean isMovable(){
		final Blocks b = v.getBlocks();
		final int[][] s = v.getStatus();
		for(int i=0;i<b.getPoint().length;i++){ // p.size()
			final Point p = b.getPoint()[i];
			final int nextRotate = (b.getRotate() + rotate) % b.getRotatable();
			final int nextX = b.getDir().getX() + dir.getX() +      p.getX() * (int)Math.cos((nextRotate)*Math.PI/2) + p.getY() * (int)Math.sin((nextRotate)*Math.PI/2);
			final int nextY = b.getDir().getY() + dir.getY() + -1 * p.getX() * (int)Math.sin((nextRotate)*Math.PI/2) + p.getY() * (int)Math.cos((nextRotate)*Math.PI/2);

			if(nextX<0 || s.length-1<nextX || nextY<0 || s[0].length-1<nextY){
				return false;
			}else if(s[nextX][nextY]!=0){
				return false;
			}
		}			
		return true;
	}
}