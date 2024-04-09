import java.awt.*;
import java.awt.geom.Ellipse2D;

class Kulka extends Ellipse2D.Float
{
    Plansza p;
    float dx,dy;

    Kulka(Plansza p,int x,int y,int dx,int dy)
    {
        this.x=x;
        this.y=y;
        this.width=10;
        this.height=10;

        this.p=p;
        this.dx=dx;
        this.dy=dy;
    }

    void nextKrok()
    {
        x+=dx;
        y+=dy;

        if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
        if(getMinY()<0) dy=-dy;
        if (getMaxY()>p.getHeight()) {
            if (p.getHeight()!=0) {
                p.koniecGry();
//                System.out.println("Game Over");
            }

        }

        p.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}