package myJavaTetris.yysaki.com;

import java.awt.*;

import javax.swing.*;

import myJavaTetris.yysaki.com.BlockColor;

/**
 * テトリスブロックの画像を管理するクラス
 * @author yysaki
 *
 */
public class BlockIcon {
	/** アイコンの画像 */
	private static final Image[] _icon = {
		Toolkit.getDefaultToolkit().getImage("res/tetris_gray.jpg"),   // _icon[0], EMPTY
		Toolkit.getDefaultToolkit().getImage("res/tetris_red.jpg"),    // _icon[1], GAMEOVER
		Toolkit.getDefaultToolkit().getImage("res/tetris_yellow.jpg"), // _icon[2]
		Toolkit.getDefaultToolkit().getImage("res/tetris_purple.jpg"), // _icon[3]
		Toolkit.getDefaultToolkit().getImage("res/tetris_green.jpg"),  // _icon[4]
		Toolkit.getDefaultToolkit().getImage("res/tetris_blue.jpg"),   // _icon[5]
		Toolkit.getDefaultToolkit().getImage("res/tetris_orange.jpg"), // _icon[6]
		Toolkit.getDefaultToolkit().getImage("res/tetris_water.jpg")   // _icon[7], WALL
	};
	
	/** 正方形ブロックの辺の長さ */
	private final static int _blocksize = 25; 

	private BlockIcon(){}
	
	public static int getSize(){ return _blocksize; }
	public static Image getIcon(BlockColor color){ return _icon[color.getId()]; }
}
