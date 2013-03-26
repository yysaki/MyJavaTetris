package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Point;

import java.util.Random;

/**
 * �A�N�e�B�u�ȃe�g���X�u���b�N�𐶐��A�Ǘ�����
 * @author yysaki
 *
 */
public class GameBlocks {
	private final int color; // �u���b�N�̐F(image��View class�ŊǗ�)
	private final Point[] points; // �e�u���b�N�ʒu
	private final int rotatable; // ��]�\�ȉ�

	private Point dir;
	private int rotate;

	/**
	 *  id�������_�����������e�g���X�u���b�N
	 * @param dir
	 * @param rotate
	 */
	public GameBlocks(Point dir, int rotate){
		this(dir, rotate, (new Random().nextInt(7))+1);
	}

	/**
	 * �R�s�[�R���X�g���N�^
	 * @param b
	 */
	public GameBlocks(GameBlocks b){
		this(b.getDir(), b.getRotate(), b.getColor());
	}

	/**
	 * 1<=id<=8
	 * @param dir
	 * @param rotate
	 * @param id
	 */
	public GameBlocks(Point dir, int rotate, int id){
		// ���������l�������Ă��鎞 �_�^�Ƃ���
		if(id<1 || 8<id){
			id = 1;
		}

		System.out.println("new Blocks id:" + id);

		color = id;
		points = new Point[4];

		switch(id){
		case 1: // �_�`
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(2,0);
			break;
		case 2: // �����`
			rotatable = 1;
			points[0] = new Point(0,0);
			points[1] = new Point(1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(1,1);
			break;
		case 3: // S�^1
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(1,1);
			break;
		case 4: // S�^2
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(-1,1);
			break;
		case 5: // ���`1
			rotatable = 4;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(-1,1);
			break;
		case 6: // ���^2
			rotatable = 4;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(1,1);
			break;
		default: // T�`
			rotatable = 4;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(0,1);
			break;
		}
		
		this.rotate = rotate % rotatable;
		this.dir = dir;

	}

	public int getColor()    { return color; }
	public Point[] getPoints(){ return points; }
	public int getRotatable(){ return rotatable; }

	public Point getDir()    { return dir; }
	public int getRotate()   { return rotate; }
	public void setDir(Point p)      { dir = p; }
	public void setRotate(int r){
		rotate = r % rotatable;
	}
}
