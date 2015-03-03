package manageryzy.mcmod.api.mcgui.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import manageryzy.mcmod.api.mcgui.McGui;
import manageryzy.mcmod.api.mcgui.draw.IDraw;
import manageryzy.mcmod.api.mcgui.logger.Logger;

/**
 * DOM for MCGUI , pretty like relative layout in android.
 * @author manageryzy@gmail.com
 */
public abstract class DOM implements IDraw{
	static int inf = 9999;
	
	protected ArrayList<DOM> childElement;
	protected DOM Father = null;
	
	protected Map<String,String> attr = null;
	
	/**
	 * these are the basic 
	 */
	DOMLength 
		Height,
		Width,
		MaxHeight,
		MaxWidth,
		MinHeight,
		MinWidth,
		Top,
		Left,
		Right,
		Bottom;
	
	/**
	 * these are the values calculated form DOM length
	 */
	int height,
		width,
		left,
		top;
	
	int parentHeight,parentWidth;
	
	DOMColor BackgroundColor,Color;
	
	String BackgroundPic;//if backgound picture is not null the backgroud color will be ignored
	
	public DOM() {
		childElement = new ArrayList<DOM>();
		attr = new HashMap<String, String>();
		
		BackgroundColor = new DOMColor(255, 255, 255, 100);
		BackgroundPic = "";
		
	}
	
	/**
	 * add an child without father element
	 * 
	 * if the child have an father DOM element, it will do nothing 
	 * @param e the child Element
	 * @return true if succeed
	 */
	public boolean addChild(DOM e){
		if(e.getFather()!=null)
		{
			return false;
		}
		childElement.add(e);
		e.setFather(this);
		return true;
	}
	
	/**
	 * add or set attribute to the element
	 * 
	 * @param Name
	 * @param Value
	 * @throws Throwable
	 */
	public void addAttr(String Name,String Value) throws Throwable
	{
		switch(	Name )
		{
		case "height":
			this.Height = new DOMLength(Value);
			break;
		case "min-height":
			this.MinHeight = new DOMLength(Value);
			break;
		case "max-height":
			this.MaxHeight = new DOMLength(Value);
			break;
			
		case "width":
			this.Width = new DOMLength(Value);
			break;
		case "min-width":
			this.MinWidth = new DOMLength(Value);
			break;
		case "max-width":
			this.MaxWidth = new DOMLength(Value);
			break;
		
		case "left":
			this.Left = new DOMLength(Value);
			break;
		case "right":
			this.Right = new DOMLength(Value);
			break;
		case "top":
			this.Top = new DOMLength(Value);
			break;
		case "bottom":
			this.Bottom = new DOMLength(Value);
			break;
			
		case "background":
			this.BackgroundColor = new DOMColor(Value);
			break;
		case "color":
			this.Color = new DOMColor(Value);
			break;
		case "background-pictur":
			this.BackgroundPic = Value;
			break;
		default:
			Logger.Log("Unkown attr " + Name + "will be ignored");
		}
		
		this.attr.put(Name, Value);
	}
	
	/**
	 * Draw the whole Dom tree
	 */
	public void OnDraw(Object g){
		this.Draw(g);//draw this element first
		for(DOM element : childElement)
		{
			element.OnDraw(g);//draw each child element,the child index is just z-index
		}
	}
	
	/**
	 * Draw this element
	 */
	void Draw(Object g)
	{
		if(this.BackgroundPic == null || this.BackgroundPic == "")
		{
			Rect(g,this.left, this.top, this.width, this.height, 
					this.BackgroundColor.red, this.BackgroundColor.green, this.BackgroundColor.blue, 
					this.BackgroundColor.alpha);
			
		}
		else
		{
			try {
				RectBitmap(g,this.left, this.top, this.width, this.top, this.BackgroundPic);
			} catch (IOException e) {
				Logger.Log("error in display bitmap "+ BackgroundPic);
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * Resize the DOM tree
	 */
	public Point OnResize(Point BasePoint){
		Point t,res;
		
		if(BasePoint == null)
		{
			BasePoint = new Point(0, 0, this.parentWidth, this.parentHeight);
		}
		res = t = this.ReSize(BasePoint);
		
		for(DOM element : this.childElement)
		{
			t = element.OnResize(t);
		}
		
		return res; 
	}
	
	/**
	 * Re-calculate the size of this element
	 * it will return (0,0) when in element like relative layout
	 */
	Point ReSize(Point BasePoint){
		
		//Calc height
		int minHeight = 0;
		int maxHeight = inf;
		
		if(this.Father == null)
		{
			this.parentHeight = McGui.WindowHeight;
			this.parentWidth = McGui.WindowWidth;
		}
		else
		{
			this.parentHeight = this.Father.getHeightCalc();
			this.parentWidth = this.Father.getWidthCalc();
		}
		
		if(this.MinHeight != null)
			minHeight = this.MinHeight.Calc(this.parentHeight);
		if(this.MaxHeight != null)
			maxHeight = this.MaxHeight.Calc(this.parentHeight);
		
		if(this.Height != null)
		{
			this.height = this.Height.Calc(this.parentHeight);
			
			if(this.height > maxHeight)
				this.height = maxHeight;
		}
		else
		{
			this.height = minHeight;
			if(minHeight > maxHeight)
			{
				Logger.Log("min height is lager than max height");
			}
		}
		
		// Check father height
		for(DOM p = this.Father;p != null; p = p.getFather())
		{
			if(p.getHeightCalc() < this.height)
			{
				Logger.Log("Child height is larger than father height!");
				p.setHeightCalc(this.height);
			}
		}
		
		
		//Calc width
		int minWidth = 0;
		int maxWidth = inf;
		
		if(this.MinWidth != null)
			minWidth = this.MinWidth.Calc(this.parentWidth);
		if(this.MaxWidth != null)
			maxWidth = this.MaxWidth.Calc(this.parentWidth);
		
		if(this.Width != null)
		{
			this.width = this.Width.Calc(this.parentWidth);
			
			if(this.width > maxWidth)
				this.width = maxWidth;
		}
		else
		{
			this.width = minWidth;
			if(minWidth > maxWidth)
			{
				Logger.Log("min width is lager than max width");
			}
		}
		
		this.left = 0;
		this.top = 0;
		
		
		
		// Check father width
		for(DOM p = this.Father;p != null; p = p.getFather())
		{
			if(p.getWidthCalc() < this.width)
			{
				Logger.Log("Child width is larger than father width!");
				p.setWidthCalc(this.width);
			}
		}
		
		return new Point(0,0,this.width,this.width);
	}
	
	public DOMLength getBottom() {
		return Bottom;
	}
	
	public DOM getFather() {
		return Father;
	}
	
	/**
	 * You should not use it by yourself!
	 * @param father
	 */
	@Deprecated
	public void setFather(DOM father) {
		Father = father;
	}
	
	public DOMLength getHeight() {
		return Height;
	}
	
	public DOMLength getLeft() {
		return Left;
	}
	
	public DOMLength getMaxHeight() {
		return MaxHeight;
	}
	
	public DOMLength getMaxWidth() {
		return MaxWidth;
	}
	
	public DOMLength getMinHeight() {
		return MinHeight;
	}
	
	public DOMLength getMinWidth() {
		return MinWidth;
	}
	
	public DOMLength getRight() {
		return Right;
	}
	
	public DOMLength getTop() {
		return Top;
	}
	
	public DOMLength getWidth() {
		return Width;
	}
	
	public int getHeightCalc()
	{
		return height;
	}
	
	public int getWidthCalc()
	{
		return width;
	}
	
	public int getLeftCalc()
	{
		return left;
	}
	
	public int getTopCalc()
	{
		return top;
	}
	
	@Deprecated
	public void setHeightCalc(int height)
	{
		this.height = height;
	}
	
	@Deprecated
	public void setWidthCalc(int width)
	{
		this.width = width;
	}
	
	public class Point{
		public int x1,y1,x2,y2;
		public Point(int x1,int y1,int x2,int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
}
