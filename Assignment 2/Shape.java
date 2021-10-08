import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Shape extends Applet{
    int X,Y, scale = 7;
    int convertX(int x) {
        return X + x * scale;
    }
    int convertY(int y) {
        return X- y * scale;
    }

    public void init() {
        setSize(900, 900);
        setBackground(new Color(255,255,255));
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                scale += event.getWheelRotation();
                repaint();
            }
        });
    }

    public void DDALine(Graphics g, int x1, int y1, int x2, int y2){
        double dx,dy,steps,x,y;
        double xinc, yinc;
        dx=x2-x1;
        dy=y2-y1;
        steps = Math.max(Math.abs(dx), Math.abs(dy));
        xinc = dx/steps;
        yinc = dy/steps;
        x=x1;
        y=y1;
        g.fillRect((int) (X+ x1 * scale) - scale / 2, (int) (X - y1 * scale) - scale / 2, scale, scale);
        for(int i=1; i<=steps; i++){
            x+=xinc;
            y+=yinc;
            g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
        }
    }

    public void drawShape(Graphics g, int x1, int y1){
        // Upper Part
        DDALine(g, -15,8, -17,3);
        DDALine(g, -10,8, -12,3);
        DDALine(g, -5,8, -7,3);
        DDALine(g, 0,8, -2,3);
        DDALine(g, 5,8, 3,3);
        DDALine(g, 10,8, 8,3);
        DDALine(g, 15,8, 13,3);
        DDALine(g, 20,8, 18,3);

        DDALine(g, -20,8, 20,8);
        DDALine(g, 20,8, 20,16);
        DDALine(g, 20, 16, -20, 8);



        // Lower Part
        DDALine(g, -15,-8, -17,-3);
        DDALine(g, -10,-8, -12,-3);
        DDALine(g, -5,-8, -7,-3);
        DDALine(g, 0,-8, -2,-3);
        DDALine(g, 5,-8, 3,-3);
        DDALine(g, 10,-8, 8,-3);
        DDALine(g, 15,-8, 13,-3);
        DDALine(g, 20,-8, 18,-3);

        DDALine(g, -20,-8, 20,-8);
        DDALine(g, 20,-8, 20,-16);
        DDALine(g, 20, -16, -20, -8);
    }

    public void paint(Graphics g){
        X= getWidth() / 2;
        Y = getHeight() / 2;

        g.setColor(Color.green);

        for (int x = scale; x <= getWidth(); x += scale) {
            g.drawLine(X + x, 0, X + x, getHeight());
            g.drawLine(X - x, 0, X - x, getHeight());
        }
        for (int y = scale; y <= getWidth(); y += scale) {
            g.drawLine(0, Y + y, getWidth(), Y + y);
            g.drawLine(0, Y- y, getWidth(), Y- y);
        }

        g.setColor(Color.red);
        g.drawLine(X, 0, X, getHeight());
        g.drawLine(0, Y, getWidth(), Y);


        g.setColor(new Color(130, 54, 203));
        drawShape(g, 40, 20);
        
    }
}


/* 
<applet code="Shape" width="800" height="800"> </applet> 
*/