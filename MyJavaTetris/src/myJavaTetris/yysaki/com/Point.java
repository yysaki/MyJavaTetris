package myJavaTetris.yysaki.com;

/**
 * (x,y)座標オブジェクト
 * (Dimensionクラスを使えば不要？)
 * @author yysaki
 *
 */
public class Point {
	private final int x, y;

	public Point(int x, int y){
		this.x = x; this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}