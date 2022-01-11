/* grapher.ui.Grapher
 * (c) blanch@imag.fr 2021–                                                */


package grapher.ui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.JPanel;

import grapher.fc.*;

import java.awt.Point;
import static java.lang.Math.*;

import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.util.Vector;


@SuppressWarnings("serial")
public class Grapher extends JPanel {
	static final int MARGIN = 40;
	static final int STEP = 5;
	
	static final BasicStroke dash = new BasicStroke(1, BasicStroke.CAP_ROUND,
	                                                   BasicStroke.JOIN_ROUND,
	                                                   1.f,
	                                                   new float[] { 4.f, 4.f },
	                                                   0.f);
	                                                   
	protected int W = 400;
	protected int H = 300;
	
    Interactions interactions;
    public int indiceFonctionSelect = -1;
        
	protected double xmin, xmax;
	protected double ymin, ymax;

	protected Vector<Function> fonctions;
	protected Vector<Color> couleurs;
	
	public Grapher() {
		xmin = -PI/2.; xmax = 3*PI/2;
		ymin = -1.5;   ymax = 1.5;
		
		couleurs = new Vector<Color>();
		fonctions = new Vector<Function>();
        interactions = new Interactions(this);
        }
	
	public Dimension getPreferredSize() { return new Dimension(W, H); }
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		W = getWidth();
		H = getHeight();

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		                    RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, W, H);
		
		g2.setColor(Color.BLACK);

		g2.translate(MARGIN, MARGIN);
		W -= 2*MARGIN;
		H -= 2*MARGIN;
		if(W < 0 || H < 0) { 
			return; 
		}
		
		g2.drawRect(0, 0, W, H);
		
		g2.drawString("x", W, H+10);
		g2.drawString("y", -10, 0);
		
		g2.clipRect(0, 0, W, H);
		g2.translate(-MARGIN, -MARGIN);

		final int N = W/STEP + 1;
		final double dx = dx(STEP);
		double xs[] = new double[N];
		int    Xs[] = new int[N];
		for(int i = 0; i < N; i++) {
			double x = xmin + i*dx;
			xs[i] = x;
			Xs[i] = X(x);
		}
		
		for(int i = 0; i < fonctions.size(); i++) {
			g2.setColor(couleurs.get(i));
			// y values
			int Ys[] = new int[N];
			for(int j = 0; j < N; j++) {
				Ys[j] = Y(fonctions.get(i).y(xs[j]));
			}
			
			if(i == this.indiceFonctionSelect)
				g2.setStroke(new BasicStroke(3f));
			else
				g2.setStroke(new BasicStroke(1f));
			g2.drawPolyline(Xs, Ys, N);
			
		}
		g2.setStroke(new BasicStroke(1f));
		g2.setColor(Color.black);

		g2.setClip(null);

		// axes
		drawXTick(g2, BigDecimal.ZERO);
		drawYTick(g2, BigDecimal.ZERO);
		
		BigDecimal xstep = unit((xmax-xmin)/10);
		BigDecimal ystep = unit((ymax-ymin)/10);
		
		g2.setStroke(dash);
		for(BigDecimal x = xstep; x.doubleValue() < xmax; x = x.add(xstep))  { drawXTick(g2, x); }
		for(BigDecimal x = xstep.negate(); x.doubleValue() > xmin; x = x.subtract(xstep)) { drawXTick(g2, x); }
		for(BigDecimal y = ystep; y.doubleValue() < ymax; y = y.add(ystep))  { drawYTick(g2, y); }
		for(BigDecimal y = ystep.negate(); y.doubleValue() > ymin; y = y.subtract(ystep)) { drawYTick(g2, y); }
                
        interactions.dessiner_selection_zoom(g);
	}
        

	
	protected double dx(int dX) { return  (double)((xmax-xmin)*dX/W); }
	protected double dy(int dY) { return -(double)((ymax-ymin)*dY/H); }

	protected double x(int X) { return xmin+dx(X-MARGIN); }
	protected double y(int Y) { return ymin+dy((Y-MARGIN)-H); }
	
	protected int X(double x) { 
		int Xs = (int)round((x-xmin)/(xmax-xmin)*W);
		return Xs + MARGIN; 
	}
	protected int Y(double y) { 
		int Ys = (int)round((y-ymin)/(ymax-ymin)*H);
		return (H - Ys) + MARGIN;
	}
		
	protected void drawXTick(Graphics2D g2, BigDecimal x) {
		double _x = x.doubleValue();
		if(_x > xmin && _x < xmax) {
			final int X0 = X(_x);
			g2.drawLine(X0, MARGIN, X0, H+MARGIN);
			g2.drawString(x.toString(), X0, H+MARGIN+15);
		}
	}
	
	protected void drawYTick(Graphics2D g2, BigDecimal y) {
		double _y = y.doubleValue();
		if(_y > ymin && _y < ymax) {
			final int Y0 = Y(_y);
			g2.drawLine(0+MARGIN, Y0, W+MARGIN, Y0);
			g2.drawString(y.toString(), 5, Y0);
		}
	}
	
	protected static BigDecimal unit(double w) {
		int scale = (int)floor(log10(w));
		w /= pow(10, scale);
		BigDecimal value;
		if(w < 2)      { value = new BigDecimal(2); }
		else if(w < 5) { value = new BigDecimal(5); }
		else           { value = new BigDecimal(10); }
		return value.movePointRight(scale);
	}
	
	
	void translate(int dX, int dY) {
		double dx = dx(dX);
		double dy = dy(dY);
		xmin -= dx; xmax -= dx;
		ymin -= dy; ymax -= dy;
		repaint();
	}
	
	void zoom(Point center, int dz) {
		double x = x(center.x);
		double y = y(center.y);
		double ds = exp(dz*.01);
		xmin = x + (xmin-x)/ds; xmax = x + (xmax-x)/ds;
		ymin = y + (ymin-y)/ds; ymax = y + (ymax-y)/ds;
		repaint();
	}
	
	void zoom(Point p0, Point p1) {
		double x0 = x(p0.x);
		double y0 = y(p0.y);
		double x1 = x(p1.x);
		double y1 = y(p1.y);
		xmin = min(x0, x1); xmax = max(x0, x1);
		ymin = min(y0, y1); ymax = max(y0, y1);
		repaint();
	}	
	
	
	public void ajouterFonction(String fonction, Color couleur) {
		add(FunctionFactory.createFunction(fonction));
		this.couleurs.add(couleur);
		repaint();
	}
        
    public void retirerFonction(int index) {
		if(fonctions.size() == 0)
			return;
		fonctions.remove(index);
		couleurs.remove(index);
		this.indiceFonctionSelect = -1;
		repaint();
	}
	
	void add(String expression) {
		add(FunctionFactory.createFunction(expression));
		repaint();
	}
	
	void add(Function function) {
		fonctions.add(function);
		repaint();
	}
        
    public void fonctionSelect(int i) {
		this.indiceFonctionSelect = i;
		repaint();
	}
    public void changerCouleur(Color couleur, int index) {
    	this.couleurs.set(index, couleur);
    	repaint();
    };
    
    public void setSelectedFunction(int i) {
	this.indiceFonctionSelect = i;
	repaint();
}

}