package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Point;

import java.util.Random;

/**
 * �A�N�e�B�u�ȃe�g���X�u���b�N�𐶐��A�Ǘ�����
 * @author yysaki
 *
 */
public class Blocks {
	private final int color; // �u���b�N�̐F(image��View class�ŊǗ�)
	private final Point[] point; // �e�u���b�N�ʒu
	private final int rotatable; // ��]�\�ȉ�

	private Point dir;
	private int rotate;

	/**
	 *  id�������_�����������e�g���X�u���b�N
	 * @param dir
	 * @param rotate
	 */
	public Blocks(Point dir, int rotate){
		this(dir, rotate, (new Random().nextInt(7))+1);
	}

	/**
	 * 1<=id<=8
	 * @param dir
	 * @param rotate
	 * @param id
	 */
	public Blocks(Point dir, int rotate, int id){
		// ���������l�������Ă��鎞 �_�^�Ƃ���
		if(id<1 || 8<id){
			id = 1;
		}

		System.out.println("new Blocks id:" + id);

		this.dir = dir;
		this.rotate = rotate;

		color = id;
		point = new Point[4];

		switch(id){
		case 1: // �_�`
			rotatable = 2;
			point[0] = new Point(0,0);
			point[1] = new Point(-1,0);
			point[2] = new Point(1,0);
			point[3] = new Point(2,0);
			break;
		case 2: // �����`
			rotatable = 1;
			point[0] = new Point(0,0);
			point[1] = new Point(1,0);
			point[2] = new Point(0,1);
			point[3] = new Point(1,1);
			break;
		case 3: // S�^1
			rotatable = 2;
			point[0] = new Point(0,0);
			point[1] = new Point(-1,0);
			point[2] = new Point(0,1);
			point[3] = new Point(1,1);
			break;
		case 4: // S�^2
			rotatable = 2;
			point[0] = new Point(0,0);
			point[1] = new Point(1,0);
			point[2] = new Point(0,1);
			point[3] = new Point(-1,1);
			break;
		case 5: // ���`1
			rotatable = 4;
			point[0] = new Point(0,0);
			point[1] = new Point(-1,0);
			point[2] = new Point(1,0);
			point[3] = new Point(-1,1);
			break;
		case 6: // ���^2
			rotatable = 4;
			point[0] = new Point(0,0);
			point[1] = new Point(-1,0);
			point[2] = new Point(1,0);
			point[3] = new Point(1,1);
			break;
		default: // T�`
			rotatable = 4;
			point[0] = new Point(0,0);
			point[1] = new Point(-1,0);
			point[2] = new Point(1,0);
			point[3] = new Point(0,1);
			break;
		}
	}

	public int getColor()    { return color; }
	public Point[] getPoint(){ return point; }
	public int getRotatable(){ return rotatable; }

	public Point getDir()    { return dir; }
	public int getRotate()   { return rotate; }
	public void setDir(Point p)      { dir = p; }
	public void setRotate(int rotate){ this.rotate = rotate; }
}
