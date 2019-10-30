package forces;

import java.awt.Point;

import objects.Body;
import start.Main;

public class Force {
	private Body body;
	
	private boolean masoCentricnaSila = false;
	
	double startx;
	double starty;
	
	// in m/s^2
	double intezitet;
	double angleH;

	double momentumX = 0;
	double momentumY = 0;
	
	double x;
	double y;
	
	double drawableX = 0;
	double drawableY = 0;
	
	public Force(double startx, double starty, double inezitet, double angleH, Body body) {
		super();
		this.startx = startx;
		this.starty = starty;
		this.intezitet = inezitet;
		this.angleH = angleH;
		this.setBody(body);
		
		this.x =  (startx + intezitet * Math.cos((double)angleH));
		this.y =  (starty - intezitet * Math.sin((double)angleH));
		
		
	}
	
	
	
	public Force(float startx, double starty, double x, double y, Body body) {
		super();
		this.startx = startx;
		this.starty = starty;
		this.x = startx + x;
		this.y = starty - y;
		this.setBody(body);
		
		this.intezitet = Math.sqrt((double)((x-startx)*(x-startx) + (y-starty)*(y-starty)));
		
		this.angleH = (float)Math.acos(Math.abs((double)(y-starty)/intezitet));
	}
	
	public void uppdateMomentum() {
		momentumX+=(intezitet*Math.cos(angleH)/Main.GAME_SPEED/Main.PHISICS_SIMULATION_TIME_SLOWDOWN_FACTOR);
		momentumY-=(intezitet*Math.sin(angleH)/Main.GAME_SPEED/Main.PHISICS_SIMULATION_TIME_SLOWDOWN_FACTOR);
		
		if(masoCentricnaSila) {
			body.setCentarMase(new Point((body.getCentarMase().x +(int) momentumX),(body.getCentarMase().y +(int) momentumY)));
		}
			
		this.startx =  (body.getCentarMase().x);
		this.starty =  (body.getCentarMase().y);
		
		this.x = (startx + intezitet * Math.cos(angleH));
		this.y = (starty - intezitet * Math.sin(angleH));
				
		drawableX = (startx + ((intezitet * Math.cos(angleH)) * 10));
		drawableY = (starty - ((intezitet * Math.sin(angleH)) * 10));
	}

	public double getIntezitet() {
		return intezitet;
	}



	public void setIntezitet(double intezitet) {
		this.intezitet = intezitet;
	}



	public double getMomentumX() {
		return momentumX;
	}



	public void setMomentumX(double momentumX) {
		this.momentumX = momentumX;
	}



	public double getMomentumY() {
		return momentumY;
	}



	public void setMomentumY(double momentumY) {
		this.momentumY = momentumY;
	}



	public double getDrawableX() {
		return drawableX;
	}



	public void setDrawableX(double drawableX) {
		this.drawableX = drawableX;
	}



	public double getDrawableY() {
		return drawableY;
	}



	public void setDrawableY(double drawableY) {
		this.drawableY = drawableY;
	}



	public double getStartx() {
		return startx;
	}

	public void setStartx(double startx) {
		this.startx = startx;
	}

	public double getStarty() {
		return starty;
	}

	public void setStarty(double starty) {
		this.starty = starty;
	}

	public double getdoubleezitet() {
		return intezitet;
	}

	public void setdoubleezitet(double doubleezitet) {
		this.intezitet = doubleezitet;
	}

	public double getAngleH() {
		return angleH;
	}

	public void setAngleH(double d) {
		this.angleH = d;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}



	public Body getBody() {
		return body;
	}



	public void setBody(Body body) {
		this.body = body;
	}



	public boolean isMasoCentricnaSila() {
		return masoCentricnaSila;
	}



	public void setMasoCentricnaSila(boolean masoCentricnaSila) {
		this.masoCentricnaSila = masoCentricnaSila;
	}





	
}
