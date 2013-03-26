package myJavaTetris.yysaki.com;

/**
 * (x,y)座標オブジェクト
 * (Dimensionクラスを使えば不要？)
 * @author yysaki
 *
 */
public class Point {
	private final int _x, _y;

	public Point(){
		this(0,0);
	}
	public Point(int x, int y){
		this._x = x; this._y = y;
	}
	public int getX(){
		return _x;
	}
	public int getY(){
		return _y;
	}
}