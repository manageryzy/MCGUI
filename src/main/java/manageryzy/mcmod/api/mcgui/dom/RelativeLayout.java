package manageryzy.mcmod.api.mcgui.dom;

import manageryzy.mcmod.api.mcgui.McGui;
import manageryzy.mcmod.api.mcgui.logger.Logger;


public abstract class RelativeLayout extends DOM {
	
	public RelativeLayout() {
		this.DOMType = "RelativeLayout";
	}
	
	@SuppressWarnings("deprecation")
	@Override
	Point ReSize(Point BasePoint) {

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
		
		if(this.Father!=null)
		{
			this.left += this.Father.getLeftCalc();
			this.top += this.Father.getTopCalc();
		}
		
		this.left += BasePoint.x1;
		this.top += BasePoint.y1;
		
		if(this.Left !=null)
		{
			this.left += this.Left.Calc(parentWidth);
		}
		else if(this.Right != null)
		{
			this.left += BasePoint.x2 - this.Width.Calc(parentWidth);
		}
		
		if(this.Top != null)
		{
			this.top += this.Top.Calc(parentHeight);
		}
		else if(this.Bottom != null)
		{
			this.top += BasePoint.y2 - this.Bottom.Calc(parentHeight);
		}
		
		// Check father height
		for(DOM p = this.Father;p != null; p = p.getFather())
		{
			if(p.getHeightCalc() + p.top< this.height + this.top)
			{
				Logger.Log("Child height is larger than father height!");
				p.setHeightCalc( this.height + this.top - p.top );
			}
		}
		// Check father width
		for(DOM p = this.Father;p != null; p = p.getFather())
		{
			if(p.getWidthCalc() < this.width)
			{
				Logger.Log("Child width is larger than father width!");
				p.setWidthCalc(this.width);
			}
		}
		
		if(this.Father!=null)return Father.ClacChildPos(BasePoint,this);
		
		return BasePoint;
	}
	
}
