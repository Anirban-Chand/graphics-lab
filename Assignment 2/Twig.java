import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Twig extends Applet{
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

    public void twig(Graphics g, int x2, int y2, double blen, double angle, int count){
        if(count >8){
            return;
        }
        
        double currAngle = Math.toRadians(angle / 2);
        int yn1= (int)(blen/Math.sin(currAngle))+y2;
        int xn1= x2+((yn1-y2)/(int)(Math.tan(Math.toRadians(90)-currAngle)));
        int yn2= yn1;
        int xn2= x2+((yn2-y2)/(int)(Math.tan(Math.toRadians(90)+currAngle)));
        DDALine(g, x2, y2, xn1, yn1);
        DDALine(g, x2, y2, xn2, yn2);
        twig(g, xn1, yn1, blen/2, angle, count+1);
        twig(g, xn2, yn2, blen/2, angle, count+1);
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


        g.setColor(Color.black);
        DDALine(g, -50, 8, -50, 16);
        

        g.setColor(Color.black);
        DDALine(g, -30, 8, -30, 16);
        twig(g, -30,16, 5, 60, 8);
        

        g.setColor(Color.black);
        DDALine(g, 0, 8, 0, 16);
        twig(g, 0, 16, 5, 60,7);


        g.setColor(Color.black);
        DDALine(g, 38, 8, 38, 16);
        twig(g, 38, 16, 5, 60, 6);


        g.setColor(Color.black);
        DDALine(g, 0, -50, 0, -40);
        twig(g, 0, -40, 10, 60, 2);
    }
}


/* 
<applet code="Twig" width="800" height="800"> </applet> 
*/