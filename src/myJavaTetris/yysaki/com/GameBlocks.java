package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Point;

import java.util.Random;

/**
 * アクティブなテトリスブロックを生成、管理する
 * @author yysaki
 *
 */
public class GameBlocks {
	private final int _color; // ブロックの色(imageはView classで管理)
	private final Point[] _points; // 各ブロック位置
	private final int _rotatable; // 回転可能な回数

	private Point _dir;
	private int _rotate;

	/**
	 *  idをランダム生成したテトリスブロック
	 * @param dir
	 * @param rotate
	 */
	public GameBlocks(Point dir, int rotate){
		this(dir, rotate, (new Random().nextInt(7))+1);
	}

	/**
	 * コピーコンストラクタ
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
		// おかしい値が入ってくる時 棒型とする
		if(id<1 || 8<id){
			id = 1;
		}

		System.out.println("new Blocks id:" + id);

		_color = id;
		_points = new Point[4];

		switch(id){
		case 1: // 棒形
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(2,0);
			break;
		case 2: // 正方形
			_rotatable = 1;
			_points[0] = new Point(0,0);
			_points[1] = new Point(1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(1,1);
			break;
		case 3: // S型1
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(1,1);
			break;
		case 4: // S型2
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(-1,1);
			break;
		case 5: // 鍵形1
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(-1,1);
			break;
		case 6: // 鍵型2
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(1,1);
			break;
		default: // T形
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(0,1);
			break;
		}
		
		this._rotate = rotate % _rotatable;
		this._dir = dir;

	}

	public int getColor()    { return _color; }
	public Point[] getPoints(){ return _points; }
	public int getRotatable(){ return _rotatable; }

	public Point getDir()    { return _dir; }
	public int getRotate()   { return _rotate; }
	public void setDir(Point p)      { _dir = p; }
	public void setRotate(int r){
		_rotate = r % _rotatable;
	}
}
