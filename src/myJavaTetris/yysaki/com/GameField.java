package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.BlockColor;

/**
 * ブロックの堆積状況を表すクラス
 * @author yysaki
 *
 */
public class GameField {
	private final int _width, _height;
	/** フィールドの堆積状況 */
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
	 * statusesにblocksを描画する
	 * 空で無い地点に積もうとした時falseを返す

	 * @param b
	 * @return フィールド上のブロックを塗り替えること無く配置出来たか
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
	 * 現状態から可能な限りブロック行を削除する
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
	 * fieldに消去出来るlineがあるか調べる
	 * 
	 * @return lineが存在する場合はその行数、存在しない場合は -1
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
	 * フィールドに指定されたブロックを配置出来るか調べる
	 * @param b
	 * @return フィールドに配置出来るか
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
