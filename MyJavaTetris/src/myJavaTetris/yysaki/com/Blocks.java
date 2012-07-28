package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Point;

import java.util.Random;

/**
 * アクティブなテトリスブロックを生成、管理する
 * @author yysaki
 *
 */
public class Blocks {
	private final int color; // ブロックの色(imageはView classで管理)
	private final Point[] points; // 各ブロック位置
	private final int rotatable; // 回転可能な回数

	private Point dir;
	private int rotate;

	/**
	 *  idをランダム生成したテトリスブロック
	 * @param dir
	 * @param rotate
	 */
	public Blocks(Point dir, int rotate){
		this(dir, rotate, (new Random().nextInt(7))+1);
	}

	/**
	 * コピーコンストラクタ
	 * @param b
	 */
	public Blocks(Blocks b){
		this(b.getDir(), b.getRotate(), b.getColor());
	}

	/**
	 * 1<=id<=8
	 * @param dir
	 * @param rotate
	 * @param id
	 */
	public Blocks(Point dir, int rotate, int id){
		// おかしい値が入ってくる時 棒型とする
		if(id<1 || 8<id){
			id = 1;
		}

		System.out.println("new Blocks id:" + id);

		color = id;
		points = new Point[4];

		switch(id){
		case 1: // 棒形
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(2,0);
			break;
		case 2: // 正方形
			rotatable = 1;
			points[0] = new Point(0,0);
			points[1] = new Point(1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(1,1);
			break;
		case 3: // S型1
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(1,1);
			break;
		case 4: // S型2
			rotatable = 2;
			points[0] = new Point(0,0);
			points[1] = new Point(1,0);
			points[2] = new Point(0,1);
			points[3] = new Point(-1,1);
			break;
		case 5: // 鍵形1
			rotatable = 4;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(-1,1);
			break;
		case 6: // 鍵型2
			rotatable = 4;
			points[0] = new Point(0,0);
			points[1] = new Point(-1,0);
			points[2] = new Point(1,0);
			points[3] = new Point(1,1);
			break;
		default: // T形
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
