package myJavaTetris.yysaki.com;

/**
 * (x,y)���W�I�u�W�F�N�g
 * (Dimension�N���X���g���Εs�v�H)
 * @author yysaki
 *
 */
public class MyPoint {
	private final int _x, _y;

	public MyPoint(){
		this(0,0);
	}
	public MyPoint(int x, int y){
		this._x = x; this._y = y;
	}
	public int getX(){
		return _x;
	}
	public int getY(){
		return _y;
	}
}