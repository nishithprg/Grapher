/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;


import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static java.lang.Math.max;
import static java.lang.Math.min;
import javax.swing.SwingUtilities;

/**
 *
 * @author corbillc
 */
   
public class Interactions implements MouseListener,  MouseWheelListener, MouseMotionListener {
    
    int aX = -1;
    int aY = -1;
    int bX = -1;
    int bY = -1;
    
    boolean select = false;
    
    Grapher grapher;
    
    Interactions(Grapher g) {
        this.grapher = g;
        g.addMouseMotionListener(this);
        g.addMouseListener(this);
        g.addMouseWheelListener(this);
    }
              
    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e))
            grapher.zoom(new Point(e.getX(), e.getY()), 5);
        else if(SwingUtilities.isRightMouseButton(e))
            grapher.zoom(new Point(e.getX(), e.getY()), -5);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        grapher.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if( SwingUtilities.isRightMouseButton(e)){
            aX = e.getX();
            aY = e.getY();
        }                           
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(select){
            effectuer_selection(e);
            select = false;
        }
        fin_zoom();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) { grapher.zoom(new Point(e.getX(), e.getY()), 5*(-e.getWheelRotation())); }
    
    
    @Override
    public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e) && (bX != -1 && bY != -1))  
                                    grapher.translate((e.getX() - bX), (e.getY() - bY));
				bX = e.getX();
				bY = e.getY();
				if(SwingUtilities.isRightMouseButton(e)) { 
                                        select = true;
					grapher.repaint();                         
				}
    }

    public void effectuer_selection(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e) && aX != -1 && aY != -1) 
			grapher.zoom(new Point(aX, aY), new Point(e.getX(), e.getY()));
    }
    
    public void fin_zoom() {
		aX = -1;
		aY = -1;
        bX = -1;
		bY = -1;
		grapher.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
            public void dessiner_selection_zoom(Graphics g) {
                
		if(aX != -1 && aY != -1 && bX != -1 && bY != -1) {
			int x =  min(aX, bX);
			int y = min(aY, bY);
			
			if(x < grapher.MARGIN){ x = grapher.MARGIN; } 
			if(y < grapher.MARGIN) { y = grapher.MARGIN; }	
		    
			g.drawRect(x,y, max(aX, bX) - x, max(aY, bY) - y);  
		}
	}
    
    
    
    
    
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    }
    
   
