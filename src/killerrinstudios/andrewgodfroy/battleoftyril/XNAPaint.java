package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Paint;
import android.graphics.Rect;

public class XNAPaint {
	int a;
	int r;
	int g;
	int b;
	
	Paint paint;
	
	public XNAPaint(int a, int r, int g, int b)
	{
		paint = new Paint();
		paint.setARGB(a,  r,  g,  b);
	}
	
	public XNAPaint(Paint p)
	{
		a = p.getAlpha();
		paint = p;		
	}
	
	public Rectangle GetTextBounds(String text)
	{
		Rect rect = new Rect();
		paint.getTextBounds(text, 0, text.length(), rect);

		return new Rectangle(rect.left, rect.top, rect.right, rect.bottom);
	}
	
	public static XNAPaint White() { return new XNAPaint(255,255,255,255); }
	public static XNAPaint Black() { return new XNAPaint(255,0,0,0); }
	public static XNAPaint Silver() { return new XNAPaint(255,192,192,192); }
	
	public static XNAPaint Red() { return new XNAPaint(255,255,0,0); }
	public static XNAPaint Green() { return new XNAPaint(255,0,255,0); } 
	public static XNAPaint Blue() { return new XNAPaint(255,0,0,255); }
	
	public static XNAPaint LimeGreen() { return new XNAPaint(255,50,205,50); }
	public static XNAPaint DarkRed() { return new XNAPaint(255,139,0,0); }
	public static XNAPaint Sienna() { return new XNAPaint(255,160,82,45); }
	public static XNAPaint BlanchedAlmond() { return new XNAPaint(255,255,235,205); }
}
