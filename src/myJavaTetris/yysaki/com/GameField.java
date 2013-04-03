package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.BlockColor;

/**
 * �u���b�N�̑͐Ϗ󋵂�\���N���X
 * @author yysaki
 *
 */
public class GameField {
	private final int _width, _height;
	/** �t�B�[���h�̑͐Ϗ� */
	private BlockColor[][] _statuses;

	public GameField(int blockWidth, int blockHeight){
		_width = blockWidth; _height = blockHeight;
		_statuses = new BlockColor[blockWidth+1][blockHeight+1];

		resetStatuses();
	}

	private void resetStatuses(){
		for(int i=0;i<_statuses.length;i++){
			for(int j=0;j<_statuses[0].length;j++){
				_statuses[i][j] = BlockColor.EMPTY;
			}
		}
		for(int i=0;i<_statuses.length;i++){
			_statuses[i][_statuses[0].length-1] = BlockColor.WALL;
		}
		for(int j=0;j<_statuses[0].length;j++){
			_statuses[_statuses.length-1][j] = BlockColor.WALL;
		}
	}

	public void setAll(BlockColor color){
		for(int i=0;i<_width;i++){
			for(int j=0;j<_height;j++){
				_statuses[i][j] = color;
			}
		}
	}

	/**
	 * statuses��blocks��`�悷��
	 * ��Ŗ����n�_�ɐς����Ƃ�����false��Ԃ�

	 * @param b
	 * @return �t�B�[���h��̃u���b�N��h��ւ��邱�Ɩ����z�u�o������
	 */
	public Boolean pileBlocks(GameBlocks b){
		Boolean ret = true;
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int x = b.getDir().getX() + p.getX() * (int)Math.cos((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((b.getRotate())*Math.PI/2);
			final int y = b.getDir().getY() + -1 * p.getX() * (int)Math.sin((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((b.getRotate())*Math.PI/2);
			if(!_statuses[x][y].equals(BlockColor.EMPTY)){
				ret = false;
			}
			_statuses[x][y] = new BlockColor(b.getColor());
		}

		return ret;
	}

	/**
	 * ����Ԃ���\�Ȍ���u���b�N�s���폜����
	 */
	public int deleteLines(){
		int deletableY = hasDeletable();
		int count = 0;

		while(deletableY>=0){
			count++;
			for(int j=deletableY;j>=0;j--){
				for(int i=0;i<_width;i++){
					if(j!=0){
						_statuses[i][j] = _statuses[i][j-1];
					}else{
						_statuses[i][j] = BlockColor.EMPTY;
					}
				}
			}

			deletableY = hasDeletable();
		}
		
		return count;
	}

	/**
	 * field�ɏ����o����line�����邩���ׂ�
	 * 
	 * @return line�����݂���ꍇ�͂��̍s���A���݂��Ȃ��ꍇ�� -1
	 */
	private int hasDeletable(){
		// check isDeletable
		for(int j=_height-1;j>=0;j--){
			Boolean isDeletable = true;
			for(int i=0;i<_width;i++){
				if(_statuses[i][j].equals(BlockColor.EMPTY)){
					isDeletable = false;
				}
			}
			if(isDeletable){
				return j;
			}
		}

		// field doesn't have deletable line
		return -1;
	}

	/**
	 * �t�B�[���h�Ɏw�肳�ꂽ�u���b�N��z�u�o���邩���ׂ�
	 * @param b
	 * @return �t�B�[���h�ɔz�u�o���邩
	 */
	public Boolean canSetBlocks(GameBlocks b){
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int x = b.getDir().getX() + p.getX() * (int)Math.cos((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((b.getRotate())*Math.PI/2);
			final int y = b.getDir().getY() + -1 * p.getX() * (int)Math.sin((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((b.getRotate())*Math.PI/2);

			if(x<0 || (_statuses.length-1)<x || y<0 || (_statuses[0].length-1)<y){
				return false;
			}
			if(!_statuses[x][y].equals(BlockColor.EMPTY)){
				return false;
			}
		}
		return true;
	}

	public BlockColor getStatus(int x, int y){ return new BlockColor(_statuses[x][y]); }
	public int getWidth(){ return _width; }
	public int getHeight(){ return _height; }
}
