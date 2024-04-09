import java.awt.Rectangle;
import java.awt.Point;
public class Prostokat extends Rectangle {

    Prostokat(Point wierzcholek, int dlugosc, int szerokosc) {
        super(wierzcholek.x, wierzcholek.y, dlugosc, szerokosc);

    }

    public boolean czyPrzylegaja(Prostokat y) {
        //Funkcja służąca do sprawdzenia, czy krawędzie tego prostokąta oraz innego prostokąta przylegają do siebie.
        //Wykorzystuje funkcjonalność metody intersection().

        if (this.intersection(y).getHeight() >= 0 && this.intersection(y).getWidth() >=0) {
//            System.out.println("Krawędzie przylegają");
            return true;
        }
        else {
//            System.out.println("Krawędzie nie przylegają");
            return false;
        }


    }

    public String toString()
    {
        return "Prostokąt o wym. "+this.width+" na "+this.height+", leżący w punkcie x = "+this.x+ " y = "+this.y;
    }

}
