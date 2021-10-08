import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class Midpoint extends Applet{
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

    public void MidpointLine(Graphics g, int x1, int y1, int x2, int y2){
        double dx,dy,x,y,d;
        dx=x2-x1;
        dy=y2-y1;
        x=x1;
        y=y1;
        if(dy<=dx){
            d = (dy - (dx/2))*10
        }
        g.fillRect((int) (X+ x1 * scale) - scale / 2, (int) (X - y1 * scale) - scale / 2, scale, scale);
        for(int i=1; i<=steps; i++){
            x+=xinc;
            y+=yinc;
            g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
        }
    }

    public void paint(Graphics g){
        X = getWidth() / 2;
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
        MidpointLine(g, 5, 10, 23, 31);

        g.setColor(Color.green);
        MidpointLine(g, -3, 17, -3, 41);

        g.setColor(Color.red);
        MidpointLine(g, 10, 0, 47, 0);


        g.setColor(Color.blue);
        MidpointLine(g, -15, 10, 17, -31);
        
    }
}


/* 
<applet code="Midpoint" width="800" height="800"> </applet> 
*/