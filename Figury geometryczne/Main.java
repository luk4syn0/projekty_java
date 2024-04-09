import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Pytania do zadania 4.2
        // Konstruktory klasy nadrzędnej są dziedziczone, gdy tworzymy obiekt podklasy, a rzeczona podklasa
        // nie posiada swojego konstruktora.

        // Metoda super() to sposób na odwołanie się do konstruktora klasy nadrzędnej.
        // Może być użyta w konstruktorze podklasy jako pierwsze polecenie. Dzięki temu zapewnia
        // poprawne zainicjowanie pól i wykonanie innych działań zdefiniowanych w
        // konstruktorze nadklasy przed przejściem do konstruktora podklasy.

        //Krótki przykład implementacji Point w konstruktorze
        System.out.println("Prostokąt o konstruktorze wykorzystującym Point");
        Prostokat z = new Prostokat(new Point(-5,0),8,2);
        System.out.println(z);
        System.out.println();

        //Zadanie 4.3

        // Moim pomysłem na rozwiązanie tego zadania jest wykorzystanie funkcjonalności intersection()
        // Metoda intersection() zwraca obiekt Rectangle z pewnymi parametrami zależnymi od podanych
        // prostokątów. Ważną obserwacją w tym przypadku jest fakt iż intersection() tworzy obiekt
        // nawet jeżeli obszar wspólny nie występuje i jest to kluczowe dla rozwiązania.

        // Gdy damy dwa prostokąty np. 2x2 leżące obok siebie to obszarem przecięcia będzie prostokąt
        // o szerokości 0, co świadczy o tym, iż krawędzie osi Y się stykają w tej płaszczyźnie, otrzymamy
        // również wysokość 2, co oznacza, iż odcinek styczności ma długość 2.
        // Natomiast gdy w szerokości lub wysokości otrzymamy liczby ujemne, to mamy pewność, że krawędzie
        // są rozbieżne wobec siebie w konkretnej płaszczyźnie o daną odległość.

        System.out.println("Zadanie 4.3");
        // Tworzę dwa prostokąty oparte o wcześniejsze zadanie i rozszerzone o funkcjonalność czyPrzylegają()
        // z parametrami takimi jak opisane powyżej dla przykładu.
        z = new Prostokat(new Point(0,0),2,2);
        Prostokat y = new Prostokat(new Point(2,0),2,2);

        System.out.print("Czy krawędzie przylegają? - ");
        System.out.println(z.czyPrzylegaja(y));
        System.out.println(z.intersection(y));
        System.out.println("Jak widać został zwrócony obiekt Rectangle z szerokością 0 świadczącą o styczności " +
                "w osi Y oraz wysokości 2 określającej długość odcinka styczności");

        // Sprawdźmy teraz tę metodę dla innych prostokątów np. leżących jeden pod drugim
        System.out.println();
        Prostokat a = new Prostokat(new Point(0,0),2,2);
        Prostokat b = new Prostokat(new Point(0,2),2,2);

        System.out.print("Czy krawędzie przylegają? - ");
        System.out.println(a.czyPrzylegaja(b));
        System.out.println(a.intersection(b));
        System.out.println("W tym przypadku zwrócony obiekt posiada nieujemną szerokość 2, która jest teraz długością odcinka styczności" +
                " oraz wysokość równą 0, co świadczy o przyleganiu krawędzi w osi X");

        // Sprawdźmy też prostokąty, które nie są ze sobą styczne
        System.out.println();
        Prostokat c = new Prostokat(new Point(0,0),2,2);
        Prostokat d = new Prostokat(new Point(6,2),2,2);

        System.out.print("Czy krawędzie przylegają? - ");
        System.out.println(c.czyPrzylegaja(d));
        System.out.println(c.intersection(d));
        System.out.println("Jak widać zwrócony obiekt ma wysokość 0 co świadczy o tym że krawędzie na osi X lezą w tej samej płaszczyżnie," +
                "natomiast długość odcinka styczności jest ujemna więc nie istnieje pokrycie dla krawędzi");

        //Zadanie 4.4
        //Dodałem do zbioru figur jeszcze trójkąt równoramienny oraz uproszczony równoległobok.
        System.out.println();
        System.out.println("Zadanie 4.4");

        Figura o=new Okrag(2);
        o.info();

        Figura[] figura={new Prostokat2(3,5),new Okrag(8),new Okrag(3),
                new TrojkatRowRam(5,10), new Rownoleglobok(4,7)};

        Figura x;
        double suma=0;

        for(int i=0;i<figura.length;i++)
        {
            x=figura[i];
            x.info();
            suma=suma+x.pole();
        }

        System.out.println("suma pol figur: "+suma);
    }
}