package myJavaTetris.yysaki.com;

/**
 * �u���b�N�̐F�����Ǘ�����N���X
 * _id�̔z�u��BlockIcon::_icon�Ɉˑ�����
 * @author yysaki
 *
 */
public class BlockColor {
	public static final BlockColor EMPTY    = new BlockColor(0);
	public static final BlockColor GAMEOVER = new BlockColor(1);
	public static final BlockColor WALL     = new BlockColor(7);

	private int _id;
	
	public BlockColor(int id){
		_id = id;
	}
	
	public BlockColor(BlockColor arg){
		_id = arg._id;
	}
	
	public int getId(){ return _id; }
	
	/**
	 * ���F���ǂ�������
	 * @param arg
	 * @return
	 */
	public Boolean equals(BlockColor arg){ return this._id == arg._id; }
}
