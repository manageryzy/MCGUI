package manageryzy.mcmod.api.mcgui.draw;

import java.io.IOException;

/**
 * @author manageryzy@gmail.com
 */
public interface IDraw {
	/**
	 * draw a rectangle in specific color
	 * @param g 
	 * @param x 
	 * @param y
	 * @param w
	 * @param h
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	void Rect(Object obj, int x,int y, int w,int h,int r,int g,int b,int a);
	
	/**
	 * draw a rectangle with bitmap as backround
	 * @param g 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param bitmap
	 * @throws IOException 
	 */
	void RectBitmap(Object obj, int x,int y,int w,int h,String bitmap) throws IOException;
	
	/**
	 * draw text in specific color
	 * @param x
	 * @param y
	 * @param size
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	void DrawText(Object obj,String str, int x, int y, int size, int r, int g, int b, int a);
}
