package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import myJavaTetris.yysaki.com.BlockColor;
import myJavaTetris.yysaki.com.BlockIcon;
import myJavaTetris.yysaki.com.GameBlocks;
import myJavaTetris.yysaki.com.GameField;
import myJavaTetris.yysaki.com.MyPoint;


/**
 * テトリス画面(JPanel)の管理
 * @author yysaki
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private GameBlocks _blocks;
	private GameField _field;

	private final MyPoint _start; // アクティブブロックのスタート地点

	private final static int DX = 2;
	private final static int DY = 2;


	public GamePanel(int blockWidth, int blockHeight) {
		super();

		_field = new GameField(blockWidth, blockHeight);
		_blocks = new GameBlocks(); // 空のブロック
		_start = new MyPoint(blockWidth/2, 0);

		setBackground(Color.BLACK);
		/* panel size */
		setPreferredSize(new Dimension(getPanelWidth() + DX * 2, getPanelHeight() + DY * 2));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBG(g);
		drawBlocks(g);
	}

	/**
	 * draw piled blocks
	 * @param g
	 */
	private void drawBG(Graphics g){
		for(int i=0;i<getField().getWidth();i++){
			for(int j=0;j<getField().getHeight();j++){
				this.drawBlock(g, getField().getStatus(i, j), i, j);
			}
		}
	}
	
	/**
	 *  アクティブブロックの描画
	 * @param g
	 */
	private void drawBlocks(Graphics g){
		GameBlocks b = getBlocks();
		for(int i=0;i<b.getPoints().length;i++){
			final MyPoint p = b.getPoints()[i];
			final int dx = b.getX()
					+ p.getX() * (int)Math.cos(b.getRotate() * Math.PI/2) 
					+ p.getY() * (int)Math.sin(b.getRotate() * Math.PI/2);
			final int dy = b.getY() 
					+ -1 * p.getX() * (int)Math.sin(b.getRotate() * Math.PI/2) 
					+ p.getY() * (int)Math.cos(b.getRotate() * Math.PI/2);

			drawBlock(g, b.getColor(), dx, dy);
		}
	}

	private void drawBlock(Graphics g, BlockColor c, int x, int y){
		g.drawImage(BlockIcon.getIcon(c), x * BlockIcon.getSize() + DX,
				y * BlockIcon.getSize() + DY, this);		
	}
	
	public GameField getField(){ return _field; }
	public void setField(GameField arg){ _field = arg; }

	public GameBlocks getBlocks(){ return _blocks; }
	public void setBlocks(GameBlocks arg){ _blocks = arg; }

	public MyPoint getStartPoint(){ return _start; }

	public int getPanelWidth(){
		return getField().getWidth() * BlockIcon.getSize();
	}

	public int getPanelHeight(){
		return getField().getHeight() * BlockIcon.getSize();
	}

	/**
	 * @return 新しくテトリスブロックをセットできない時true
	 */
	public Boolean isGameOver(){
		return !getField().canSetBlocks(getBlocks());
	}
	
	/**
	 * dir方向にブロックが移動可能かどうか調べる
	 */
	public Boolean isMovable(MyPoint dir, int rotate){
		GameBlocks candidate = new GameBlocks(getBlocks());
		candidate.setDir(new MyPoint(candidate.getX() + dir.getX(), candidate.getY() + dir.getY()));
		candidate.setRotate(candidate.getRotate() + rotate);

		return getField().canSetBlocks(candidate);
	}
	
	/**
	 * GamePanelを一色に染める
	 * @param color GameFieldのカラー定数
	 */
	public void fill(BlockColor color){
		getField().setAll(color);
		setBlocks(new GameBlocks(getStartPoint(), 0, color));
	}

}