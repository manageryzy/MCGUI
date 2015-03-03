package manageryzy.mcmod.api.mcgui.dom;


public abstract class RelativeLayout extends DOM {
	@Override
	Point ReSize(Point BasePoint) {
		this.left = BasePoint.x1;
		this.top = BasePoint.y1;
		
		if(this.Father!=null)
		{
			this.left += this.Father.getLeftCalc();
			this.top += this.Father.getTopCalc();
		}
		
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
		
		return super.ReSize(BasePoint);
	}
}
