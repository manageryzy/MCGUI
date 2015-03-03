package manageryzy.mcmod.api.mcgui.dom;

import java.util.ArrayList;


/**
 * a class for DOM to deal with length with unit
 * @author manageryzy@gmail.com
 */
public class DOMLength {
	enum Status{READY,NUM};
	enum Unit{px,percent};
	enum Sign{Positive,Negative};
	
	String originalString;
	ArrayList<Node> NodeList = new ArrayList<Node>();
	
	public DOMLength(String Str) throws Throwable{
		this.originalString = Str;
		this.split();
	}
	
	public void setLength(String Str) throws Throwable{
		this.originalString = Str;
		this.split();
	}
	
	
	void split() throws Throwable{
		Status status = Status.READY;
		Node node = null;
		
		for(int i = 0 ; i < this.originalString.length() ; i++){
			char ch = this.originalString.charAt(i);
			
			switch(status){
			case NUM:
				if(Character.isAlphabetic(ch))
				{
					if(ch == 'p' || ch == 'x')
						continue;
					else throw new Throwable("unexpected input! expected px or %");
				}
				else
				{
					if(Character.isDigit(ch))// if the input keep as digit
					{
						node.NumStr += ch;
					}
					else if (ch == '%')
					{
						status = Status.READY;
						//node.NumStr = Integer.parseInt(node.NumStr);
					}
					else throw new Throwable("unexpected input! expected px or %");
				}
				break;
			case READY:
				if(Character.isAlphabetic(ch))
				{
					throw new Throwable("unexpected input! expected digit or + -.");
				}
				else
				{
					if(Character.isDigit(ch)||ch == '+'||ch == '-')
					{
						status = Status.NUM;
						node = new Node();
						NodeList.add(node);
						if(ch == '+')
						{
							node.sig = Sign.Positive;
						}
						else if(ch == '-')
						{
							node.sig = Sign.Negative;
						}
						else
						{
							node.NumStr += ch; 
						}
					}
					else throw new Throwable("Unexpected input! ecpected digit or sign");
				}
				break;
			default:
				throw new Throwable("Unknown status");
			}
		}
	}
	
	/**
	 * @return the length in px
	 */
	public int Calc(int parent){
		float res = 0;
		
		for(Node n:this.NodeList)
		{
			float t = 0.0f;
			if(n.unit == Unit.percent)
			{
				t = Float.parseFloat(n.NumStr)/100f * parent;
			}
			else
			{
				t = Integer.parseInt(n.NumStr);
			}
			
			if(n.sig == Sign.Negative)
				t = -t;
			
			res += t;
		}
		
		return (int)res;
	}
	
	class Node{
		public Unit unit = Unit.px;
		public Sign sig = Sign.Positive;
		public String NumStr = "";
	}
}
