package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.Point;

import java.util.Random;

/**
 * �A�N�e�B�u�ȃe�g���X�u���b�N�𐶐��A�Ǘ�����
 * @author yysaki
 *
 */
public class GameBlocks {
	private final BlockColor _color; // �u���b�N�̐F(image��View class�ŊǗ�)
	private final Point[] _points; // �e�u���b�N�ʒu
	private final int _rotatable; // ��]�\�ȉ�

	private Point _dir;
	private int _rotate;
	
	/**
	 * Null�I�u�W�F�N�g�Ƃ��ĕǂƓ����F�̃u���b�N�𐶐�����
	 * @param color GameField�̒萔
	 */
	public GameBlocks(BlockColor color){
		_color = color;
		_rotatable = 0;
		_points = new Point[4];
		for(int i = 0; i < 4; i++){
			_points[i] = new Point();
		}
		_dir = new Point();
		_rotate = 0;
	}

	/**
	 *  id�������_�����������e�g���X�u���b�N
	 * @param dir
	 * @param rotate
	 */
	public GameBlocks(Point dir, int rotate){
		this(dir, rotate, (new BlockColor(new Random().nextInt(7)+1)));
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
	public GameBlocks(Point dir, int rotate, BlockColor color){
		int id = color.getId();
		// ���������l�������Ă��鎞 �_�^�Ƃ���
		if(id<1 || 8<id){
			id = 1;
		}

		_color = new BlockColor(id);
		_points = new Point[4];
		_dir = dir;

		switch(id){
		case 1: // �_�`
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(2,0);
			break;
		case 2: // �����`
			_rotatable = 1;
			_points[0] = new Point(0,0);
			_points[1] = new Point(1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(1,1);
			break;
		case 3: // S�^1
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(1,1);
			break;
		case 4: // S�^2
			_rotatable = 2;
			_points[0] = new Point(0,0);
			_points[1] = new Point(1,0);
			_points[2] = new Point(0,1);
			_points[3] = new Point(-1,1);
			break;
		case 5: // ���`1
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(-1,1);
			break;
		case 6: // ���^2
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(1,1);
			break;
		default: // T�`
			_rotatable = 4;
			_points[0] = new Point(0,0);
			_points[1] = new Point(-1,0);
			_points[2] = new Point(1,0);
			_points[3] = new Point(0,1);
			break;
		}
		
		_rotate = rotate % _rotatable;
	}

	public BlockColor getColor()    { return _color; }
	public Point[] getPoints(){ return _points; }
	public int getRotatable(){ return _rotatable; }

	public Point getDir()    { return _dir; }
	public int getRotate()   { return _rotate; }
	public void setDir(Point p)      { _dir = p; }
	public void setRotate(int r){
		_rotate = r % _rotatable;
	}
}
