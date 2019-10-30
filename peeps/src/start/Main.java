package start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;

import forces.Force;
import forces.Gravity;
import objects.Body;
import objects.geometry.Rect;

public class Main extends JFrame {

	Vector<Body> tela = new Vector<Body>();
	Vector<Force> sile = new Vector<Force>();
	
	AffineTransform tx = new AffineTransform();
	int width = 600;
	int height = 700;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	public static final int GAME_SPEED = 25;
	public static final int PHISICS_SIMULATION_TIME_SLOWDOWN_FACTOR = 10;
	private static final long serialVersionUID = 1L;

	Force g;
	
	Point pocetakZemlja; 
	Point krajZemlja;                      
	Main() {
		setTitle("Статика");

		/////////////////////////////////////
		tela.add(new Rect(true, 0, 200, 200, 100, 100, 2 * 0));
		
		g = new Gravity(tela.get(0).getCentarMase().x, tela.get(0).getCentarMase().y, tela.get(0));
		sile.add(g);
		
		Force g23 = new Force((double)tela.get(0).getCentarMase().x,(double) tela.get(0).getCentarMase().y, 9.81, Math.PI/5, tela.get(0));

		g23.setMasoCentricnaSila(true);
		//sile.add(g23);
		
		pocetakZemlja = new Point(0,height-100);
		krajZemlja = new Point(width,height-100); 
		//////////////////////////////////////
		
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.setVisible(true);
		m.run();
	}

	private void run() {
		
		
		long timeran = 0;
		long timeran2 = 0;

		long counter = 0;
		while (true) {
			/// fizics tick
			long currentTime = System.currentTimeMillis();
			if (timeran != currentTime && currentTime % (1000 / GAME_SPEED) == 1) {
				counter++;
			//	((Rect) tela.get(0)).setAngleH(counter * Math.PI / 100);
				tela.get(0).setPoly(((Rect) tela.get(0)).generatePolygon());
				   
			//	sile.get(0).setAngleH(counter * Math.PI / 40);
				
				for(int i = 0; i < sile.size(); i++) {
					sile.get(i).uppdateMomentum();
				}
				
				
				
				timeran = currentTime;
			}
			// render
			if (timeran2 != currentTime && currentTime % (1000 / 30) == 15) {
				 render(); 
				timeran = currentTime;
			}
		}
	
	}
	
		
	void collisionDetectionWithErth(int compare) {             
		
	}
		
	void collisionDetection() {
		
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g =  bs.getDrawGraphics();
		
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		
		
		
//////////here u draw n shit



		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.yellow);                                                     
		g2.drawLine(pocetakZemlja.x, pocetakZemlja.y, krajZemlja.x, krajZemlja.y);     
				                                                                              
		g2.setColor(Color.green);
		
		for (int i = 0; i < tela.size(); i++) {
			g2.drawPolygon(tela.get(i).getPoly());
			
		}
		g2.setColor(Color.red);
		//crtanje sila
		for(int i = 0; i < sile.size(); i++) {
			g2.drawLine((int)sile.get(i).getStartx(),(int) sile.get(i).getStarty(),(int) sile.get(i).getDrawableX(),(int)sile.get(i).getDrawableY());
		}	

		for(int i = 0; i < sile.size(); i++) {  	                                                  
			////////////////////
			Polygon arrowHead = new Polygon();  
			arrowHead.addPoint( 0, ((int)(sile.get(i).getIntezitet()/1.5)));
			arrowHead.addPoint( -((int)(sile.get(i).getIntezitet()/1.5)), -((int)(sile.get(i).getIntezitet()/1.5)));
			arrowHead.addPoint( ((int)(sile.get(i).getIntezitet()/1.5)),-((int)(sile.get(i).getIntezitet()/1.5)));

			///////////////////
			
		    tx.setToIdentity();
		    double angle = Math.atan2((int)sile.get(i).getDrawableY()-(int) sile.get(i).getStarty(), (int) sile.get(i).getDrawableX()-(int)sile.get(i).getStartx());
		    tx.translate((int) sile.get(i).getDrawableX(), (int)sile.get(i).getDrawableY());
		    tx.rotate((angle-Math.PI/2d));  
		    
		    
			
		    g2.setTransform(tx);

		    g2.fill(arrowHead);
		}	
			
		
/////////

		g2.dispose();
		
		g.drawImage(toCompatibleImage(image), 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		
		bs.show();
	}

	private BufferedImage toCompatibleImage(BufferedImage image)
	{
	    // obtain the current system graphical settings
	    GraphicsConfiguration gfxConfig = GraphicsEnvironment.
	        getLocalGraphicsEnvironment().getDefaultScreenDevice().
	        getDefaultConfiguration();

	    /*
	     * if image is already compatible and optimized for current system 
	     * settings, simply return it
	     */
	    if (image.getColorModel().equals(gfxConfig.getColorModel()))
	        return image;

	    // image is not optimized, so create a new image that is
	    BufferedImage newImage = gfxConfig.createCompatibleImage(
	            image.getWidth(), image.getHeight(), image.getTransparency());

	    // get the graphics context of the new image to draw the old image on
	    Graphics2D g2d = newImage.createGraphics();

	    // actually draw the image and dispose of context no longer needed
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();

	    // return the new optimized image
	    return newImage; 
	}
	
	@Override
	public void paint(Graphics g) {
	}

}
