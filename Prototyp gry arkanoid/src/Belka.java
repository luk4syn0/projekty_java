import java.awt.geom.Rectangle2D;

class Belka extends Rectangle2D.Float
{
    double xDest;
    double dx;
    Belka(int x,int dx)
    {
        this.x=x;

        this.xDest = x; //Docelowy punkt x
        this.dx = dx; //Prędkość belki

        this.y=210;
        this.width=60;
        this.height=10;

    }

    void setXDest(int xDest) //Określamy do jakiego punktu belka ma sie poruszać
    {
        this.xDest=xDest;
    }

    void moveBelka() {
        if (xDest>x) {
            x+= (float) dx;
        } else if (xDest<x) {
            x-= (float) dx;
        }
    }
}