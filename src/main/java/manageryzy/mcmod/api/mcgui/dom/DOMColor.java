package manageryzy.mcmod.api.mcgui.dom;

public class DOMColor {
	int red;
	int green;
	int blue;
	int alpha;
	
	public DOMColor(int r,int g,int b,int alpha)
	{
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = alpha;
	}
	
	public DOMColor(String Color) throws Throwable
	{
		String t = "";
		
		if(Color.length() != 7 && Color.length() != 9)
		{
			throw new Throwable("Unkown Color format");
		}
		
		if(Color.charAt(0) != '#')
		{
			throw new Throwable("Unknown Color format");
		}
		
		for(int i = 0 ; i<Color.length() ;i++)
		{
			if(i%2 == 1)
				t = "";
			
			t += Color.charAt(i);
			
			if(i%2 == 0 && i!=0)
			{
				int val = Integer.parseInt(t,16);
				
				switch(i/2)
				{
				case 1:
					red = val;
					break;
				case 2:
					green = val;
					break;
				case 3:
					blue = val;
					break;
				case 4:
					alpha = val;
				}
			}
		}
	}
	
	public int getAlpha() {
		return alpha;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getRed() {
		return red;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha>255?255:alpha;
	}
	
	public void setBlue(int blue) {
		this.blue = blue>255?255:blue;
	}
	
	public void setGreen(int green) {
		this.green = green>255?255:green;
	}
	
	public void setRed(int red) {
		this.red = red>255?255:red;
	}
}
