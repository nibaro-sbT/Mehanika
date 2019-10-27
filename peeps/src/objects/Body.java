package objects;

import java.awt.Polygon;

public class Body {
	
	//all bodies are homogen at start
	private boolean homogeno = true;
	
	//all bodies start light
	private int mass = 0;
	
	private Polygon poly;

	public Polygon getPoly() {
		return poly;
	}

	public void setPoly(Polygon poly) {
		this.poly = poly;
	}

	public int getMass() {
		return mass;
	}

	public boolean isHomogeno() {
		return homogeno;
	}

	public void setHomogeno(boolean homogeno) {
		this.homogeno = homogeno;
	}


	public void setMass(int mass) {
		this.mass = mass;
	}

}
