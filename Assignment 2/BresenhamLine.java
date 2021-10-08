import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class BresenhamLine extends Applet{
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

    public void BresenhamLine(Graphics g, int x1, int y1, int x2, int y2){

        if(x1>x2 || y1>y2){
            int u,v;
            u=x1;v=y1;
            x1=x2;y1=y2;
            x2=u;y2=u;
        }

        double dx,dy,x,y,m,p;
        double xinc, yinc;
        dx=x2-x1;
        dy=y2-y1;
        m=Math.abs(dy/dx);
        x=x1;
        y=y1;
        if(m<1){
            p = 2*dy-dx;
            while(x<x2){
                if(p>=0){
                    x += 1;
                    y += 1;
                    p += 2*(dy-dx);
                    g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
                }
                else{
                    x += 1;
                    p += 2*dy;
                    g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
                }
            }
        }else{
            p = 2*dx-dy;
            while(y<y2){
                if(p>=0){
                    x += 1;
                    y += 1;
                    p += 2*(dx-dy);
                    g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
                }
                else{
                    y += 1;
                    p += 2*dx;
                    g.fillRect((int) (X + ((int) x) * scale) - scale / 2, (int) (X - ((int) y) * scale) - scale / 2, scale, scale);
                }
            }
        }
        // g.fillRect((int) (X+ x1 * scale) - scale / 2, (int) (X - y1 * scale) - scale / 2, scale, scale);
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
        BresenhamLine(g, 5, 10, 23, 31);

        g.setColor(Color.red);
        BresenhamLine(g, -3, 17, -3, 41);

        g.setColor(Color.green);
        BresenhamLine(g, 10, 0, 47, 0);
    }
}


/* 
<applet code="BresenhamLine" width="800" height="800"> </applet> 
*/