import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class OtherShapes3 extends Applet{
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


    public void midpointCircle(Graphics g, int xc, int yc, int r){
        int x=0;
        int y=r;
        int p = 1-r;
        
        while(x<=y){
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y+ (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            x+=1;
            if(p>0){
                y-=1;
                p+=(2*x-2*y-1);
            }
            else if(p<=0){
                p+=(2*x+1);
            }
        }

        x=r;
        y=0;
        p=1-r;
        while(x>y){
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) - ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) + ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            g.fillRect((int) (X+ (xc*scale) - ((int) x) * scale) - scale / 2, (int) (Y + (yc*scale) + ((int) y) * scale) - scale / 2, scale, scale);
            y+=1;
            if(p<=0)
                p+=(2*y+1);
            else{
                x-=1;
                p+=(2*y-2*x+1);
            }
        }
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

        g.setColor(new Color(142, 68, 173));
        midpointCircle(g, 0, 0, 10);
        midpointCircle(g, 0, 0, 18);

        g.setColor(Color.black);
        DDALine(g, 7, 0, 45, 15);
        DDALine(g, -7, 0, -45, 15);

        g.setColor(new Color(52, 152, 219));
        DDALine(g, 0, 8, 0, 45);

        g.setColor(new Color(7, 153, 146));
        DDALine(g, 5, 3, 30, 35);
        DDALine(g, -5, 3, -30, 35);

        g.setColor(new Color(181, 52, 113));
        DDALine(g, 10, 4, 50, 30);
        DDALine(g, -10, 4, -50, 30);

        g.setColor(new Color(235, 47, 6));
        DDALine(g, 2, 4, 15, 40);
        DDALine(g, -2, 4, -15, 40);
    }
}

/* 
<applet code="OtherShapes3" width="900" height="900"> </applet> 
*/