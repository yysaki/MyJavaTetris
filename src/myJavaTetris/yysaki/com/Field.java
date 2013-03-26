package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Blocks;

/**
 * ブロックの堆積状況を表すクラス
 * @author yysaki
 *
 */
public class Field {
	private final static int EMPTY = 0;
	private final static int WALL = 7;
	
	private final int width, height;
	/** フィールドの堆積状況 */
	private int[][] statuses;
	
	public Field(int w, int h){
		width = w; height = h;
		statuses = new int[w+1][h+1];
		
		resetStatuses();
	}
	
	private void resetStatuses(){
		for(int i=0;i<statuses.length;i++){
			for(int j=0;j<statuses[0].length;j++){
				statuses[i][j] = EMPTY;
			}
		}
		for(int i=0;i<statuses.length;i++){
			statuses[i][statuses[0].length-1] = WALL;
		}
		for(int j=0;j<statuses[0].length;j++){
			statuses[statuses.length-1][j] = WALL;
		}		
	}
	
	public void setAll(int id){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				statuses[i][j] = id;
			}
		}
	}
	
	/**
 	 * statusesにblocksを描画する
	 * 空で無い地点に積もうとした時falseを返す

	 * @param b
	 * @return フィールド上のブロックを塗り替えること無く配置出来たか
	 */
	public Boolean pileBlocks(Blocks b){
		Boolean ret = true;
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int x = b.getDir().getX() + p.getX() * (int)Math.cos((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((b.getRotate())*Math.PI/2);
			final int y = b.getDir().getY() + -1 * p.getX() * (int)Math.sin((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((b.getRotate())*Math.PI/2);
			if(statuses[x][y]!=EMPTY){
				ret = false;
			}
			statuses[x][y] = b.getColor();
		}
		
		return ret;
	}
	
	public void deleteLines(){
		int deletableY = hasDeletable();
		
		while(deletableY>=0){
			for(int j=deletableY;j>=0;j--){
				for(int i=0;i<width;i++){
					if(j!=0){
						statuses[i][j] = statuses[i][j-1];
					}else{
						statuses[i][j] = 0;
					}
				}
			}
			
			deletableY = hasDeletable();
		}
	}

	/**
	 * fieldに消去出来るlineがあるか調べる
	 * 
	 * @return lineが存在する場合はその行数、存在しない場合は -1
	 */
	private int hasDeletable(){
		// check isDeletable
		for(int j=height-1;j>=0;j--){
			Boolean isDeletable = true;
			for(int i=0;i<width;i++){
				if(statuses[i][j]==0){
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
	public Boolean canBeSetBlocks(Blocks b){
		for(int i=0;i<b.getPoints().length;i++){
			final Point p = b.getPoints()[i];
			final int x = b.getDir().getX() + p.getX() * (int)Math.cos((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.sin((b.getRotate())*Math.PI/2);
			final int y = b.getDir().getY() + -1 * p.getX() * (int)Math.sin((b.getRotate())*Math.PI/2) + p.getY() * (int)Math.cos((b.getRotate())*Math.PI/2);

			if(x<0 || (statuses.length-1)<x || y<0 || (statuses[0].length-1)<y){
				return false;
			}
			if(statuses[x][y]!=0){
				return false;
			}
		}
		return true;
	}
	
	public int getStatus(int x, int y){ return statuses[x][y]; }
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
}
