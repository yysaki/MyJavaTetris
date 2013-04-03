package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.MyPoint;

import java.util.Random;

/**
 * アクティブなテトリスブロックを生成、管理する
 * @author yysaki
 *
 */
public class GameBlocks {
	private final BlockColor _color; // ブロックの色(imageはView classで管理)
	private final MyPoint[] _points; // 各ブロック位置
	private final int _rotatable; // 回転可能な回数

	private MyPoint _dir;
	private int _rotate;
	
	/**
	 * Nullオブジェクトとして壁と同じ色のブロックを生成する
	 */
	public GameBlocks(){
		_color = BlockColor.EMPTY;
		_points = new MyPoint[4];
		for(int i = 0; i < _points.length; i++){
			_points[i] = new MyPoint();
		}
		_rotatable = 0;
		_dir = new MyPoint();
		_rotate = 0;
	}
	
	public GameBlocks(BlockColor color){
		this(new MyPoint(), 0, color);
	}

	/**
	 *  idをランダム生成したテトリスブロック
	 * @param dir
	 * @param rotate
	 */
	public GameBlocks(MyPoint dir, int rotate){
		this(dir, rotate, (new BlockColor(new Random().nextInt(7)+1)));
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
	public GameBlocks(MyPoint dir, int rotate, BlockColor color){
		int id = color.getId();
		// おかしい値が入ってくる時 棒型とする
		if(id<1 || 8<id){
			id = 1;
		}

		_color = new BlockColor(id);
		_points = new MyPoint[4];
		_dir = dir;

		switch(id){
		case 1: // 棒形
			_rotatable = 2;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(-1,0);
			_points[2] = new MyPoint(1,0);
			_points[3] = new MyPoint(2,0);
			break;
		case 2: // 正方形
			_rotatable = 1;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(1,0);
			_points[2] = new MyPoint(0,1);
			_points[3] = new MyPoint(1,1);
			break;
		case 3: // S型1
			_rotatable = 2;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(-1,0);
			_points[2] = new MyPoint(0,1);
			_points[3] = new MyPoint(1,1);
			break;
		case 4: // S型2
			_rotatable = 2;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(1,0);
			_points[2] = new MyPoint(0,1);
			_points[3] = new MyPoint(-1,1);
			break;
		case 5: // 鍵形1
			_rotatable = 4;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(-1,0);
			_points[2] = new MyPoint(1,0);
			_points[3] = new MyPoint(-1,1);
			break;
		case 6: // 鍵型2
			_rotatable = 4;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(-1,0);
			_points[2] = new MyPoint(1,0);
			_points[3] = new MyPoint(1,1);
			break;
		default: // T形
			_rotatable = 4;
			_points[0] = new MyPoint(0,0);
			_points[1] = new MyPoint(-1,0);
			_points[2] = new MyPoint(1,0);
			_points[3] = new MyPoint(0,1);
			break;
		}
		
		_rotate = rotate % _rotatable;
	}

	public BlockColor getColor()    { return _color; }
	public MyPoint[] getPoints(){ return _points; }
	public int getRotatable(){ return _rotatable; }

	public MyPoint getDir()    { return _dir; }
	public int getX()        { return _dir.getX(); }
	public int getY()        { return _dir.getY(); }
	public int getRotate()   { return _rotate; }
	public void setDir(MyPoint p)      { _dir = p; }
	public void setRotate(int r){
		_rotate = r % _rotatable;
	}
}
