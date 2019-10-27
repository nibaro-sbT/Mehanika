package objects.geometry;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import objects.Body;

public class Rect extends Body{
	
	private int x;
	private int y;
	private int z = 0;
	
	//horizontalna
	private int a;
	//vertikalna
	private int b;
	
	//ugao horizontalne stranice i horizontale
	private double angleH;
	
		public Rect(boolean homogeno,int mass,int x, int y,int a,int b,double angleH) {
		super.setHomogeno(homogeno);
		super.setMass(mass);
		super.setCentarMase(new Point(x + (int)((a/Math.sqrt((a*a+b*b)))*Math.sqrt((a*a+b*b))/2), y - (int)((b/Math.sqrt((a*a+b*b)))*Math.sqrt((a*a+b*b))/2)));
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.angleH = angleH;
		
		super.setPoly(generatePolygon());
	}
	
	public Polygon generatePolygon() {
		uppdatePositionBasedOnCentreOfMass();		
		
		int[]xx= {x,(int) (x + a * Math.cos(angleH)),(int) (x + (a * Math.cos(angleH))+ (int)(b * Math.sin(angleH))),x + (int) (b * Math.sin(angleH))};
		int[]yy= {y,(int) (y - a * Math.sin(angleH)),(int) (y - (a * Math.sin(angleH))+ (int)(b * Math.cos(angleH))), y + (int) (b * Math.cos(angleH))};
		int numberOfPoints = 4;
		
		return new Polygon(xx,yy,numberOfPoints);
	}
	
	private void uppdatePositionBasedOnCentreOfMass() {
		x = getCentarMase().x + (int)((a/Math.sqrt((a*a+b*b)))*Math.sqrt((a*a+b*b))/2);
		y = getCentarMase().y + (int)((b/Math.sqrt((a*a+b*b)))*Math.sqrt((a*a+b*b))/2);
	}
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public double getAngleH() {
		return angleH;
	}

	public void setAngleH(float angleH) {
		this.angleH = angleH;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}


	public void setAngleH(double angleH) {
		this.angleH = angleH;
	}
	
}
