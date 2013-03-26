package myJavaTetris.yysaki.com;

import myJavaTetris.yysaki.com.Blocks;

/**
 * �u���b�N�̑͐Ϗ󋵂�\���N���X
 * @author yysaki
 *
 */
public class Field {
	private final static int EMPTY = 0;
	private final static int WALL = 7;
	
	private final int width, height;
	/** �t�B�[���h�̑͐Ϗ� */
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
 	 * statuses��blocks��`�悷��
	 * ��Ŗ����n�_�ɐς����Ƃ�����false��Ԃ�

	 * @param b
	 * @return �t�B�[���h��̃u���b�N��h��ւ��邱�Ɩ����z�u�o������
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
	 * field�ɏ����o����line�����邩���ׂ�
	 * 
	 * @return line�����݂���ꍇ�͂��̍s���A���݂��Ȃ��ꍇ�� -1
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
	 * �t�B�[���h�Ɏw�肳�ꂽ�u���b�N��z�u�o���邩���ׂ�
	 * @param b
	 * @return �t�B�[���h�ɔz�u�o���邩
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
