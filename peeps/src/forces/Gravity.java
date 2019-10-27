package forces;

import objects.Body;

public class Gravity extends Force{

	public Gravity(double startx, double starty,Body body) {
		super(startx, starty, 9.81, -Math.PI/2,body);
		super.setMasoCentricnaSila(true);
	}

}
