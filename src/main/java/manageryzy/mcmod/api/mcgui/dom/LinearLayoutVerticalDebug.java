package manageryzy.mcmod.api.mcgui.dom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class LinearLayoutVerticalDebug extends LinearLayoutVertical {

	@Override
	public void RectBitmap(Object obj,int x, int y, int w, int h, String bitmap) throws IOException {
		BufferedImage bimg;
		File img = new File(bitmap);
        
        bimg = ImageIO.read(img);
        
        Graphics gra= (Graphics) obj;
        
        gra.drawImage(bimg, x, y, w, h, null);
	}
	
	@Override
	public void Rect(Object obj,int x, int y, int w, int h, int r, int g, int b, int a) {
		Graphics gra= (Graphics) obj;
		gra.setColor(new Color(r, g, b, a));
		gra.fillRect(x, y, w, h);
	}
	
	@Override
	public void DrawText(Object obj,String str,int x, int y, int size, int r, int g, int b, int a) {
		Graphics gra= (Graphics) obj;
		gra.setColor(new Color(r, g, b, a));
		gra.drawString(str, x, y);
	}

}
