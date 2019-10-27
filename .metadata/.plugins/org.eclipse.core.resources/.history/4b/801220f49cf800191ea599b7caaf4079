package forces;

public class force {
	int startx;
	int starty;
	
	int intezitet;
	double angleH;
	
	int x;
	int y;
	public force(int startx, int starty, int inezitet, double angleH) {
		super();
		this.startx = startx;
		this.starty = starty;
		this.intezitet = inezitet;
		this.angleH = angleH;
		
		this.x = (int) (startx + Math.cos(angleH));
		this.y = (int) (starty - Math.sin(angleH));
		
		this.intezitet = (int)Math.sqrt((double)((x-startx)*(x-startx) + (y-starty)*(y-starty)));
	}
	
	public force(int startx, int starty, int x, int y) {
		super();
		this.startx = startx;
		this.starty = starty;
		this.x = x;
		this.y = y;
	}
	
	
}
