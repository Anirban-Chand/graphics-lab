import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class MidpointEllipse extends Applet{
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

    public void midpointEllipse(Graphics g, int xc, int yc, int rx, int ry){
        float dx, dy, d1, d2, x, y;
        x=0;
        y=ry;
        d1=(ry*ry)-(rx*rx*ry)+(0.25f*rx*rx);
        dx=2*ry*ry*x;
        dy=2*rx*rx*y;

        while(x<=y){
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            
            if (d1 < 0){
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            }
            else{
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }
        
        d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))+((rx * rx) * ((y - 1) * (y - 1)))-(rx * rx * ry * ry);
        while(y>=0){
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            }
            else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
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

        g.setColor(Color.blue);
        midpointEllipse(g, 0, 0, 10, 20);

        g.setColor(Color.green);
        midpointEllipse(g, -45, -30, 25,35);

        g.setColor(Color.red);
        midpointEllipse(g, 30, 30, 20, 8);

        g.setColor(Color.black);
        midpointEllipse(g, 30, -30, 20, 15);  

        g.setColor(Color.black);
        midpointEllipse(g, -40, 30, 8, 12);
    }
}

/* 
<applet code="MidpointEllipse" width="900" height="900"> </applet> 
*/