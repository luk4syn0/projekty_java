public class WielomianKwadratowy {
    /** Klasa służy do opisu właściwości wielomianu kwadratowego oraz
    * obsługi podstawowych operacji na wielomianach. */
    int a; //< Współczynnik przy x^2
    int b; //< Współczynnik przy x
    int c; //< Stały współczynnik
    WielomianKwadratowy(int a, int b, int c) {
        //Ax^2+Bx+C
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public WielomianKwadratowy plus(WielomianKwadratowy x) {
        /*Funkcja plus() służąca do dodania do siebie dwóch wielomianów stopnia 2.
        * Zwraca nowy obiekt WielomianKwadratowy. */
        int nowe_a,nowe_b,nowe_c;
        nowe_a = this.a+x.a;
        nowe_b = this.b+x.b;
        nowe_c = this.c+x.c;
        return new WielomianKwadratowy(nowe_a,nowe_b,nowe_c);
    }

    public WielomianKwadratowy minus(WielomianKwadratowy x) {
        /*Funkcja minus() służąca do odjęcia od siebie dwóch wielomianów stopnia 2.
         * Zwraca nowy obiekt WielomianKwadratowy. */
        int nowe_a,nowe_b,nowe_c;
        nowe_a = this.a-x.a;
        nowe_b = this.b-x.b;
        nowe_c = this.c-x.c;
        return new WielomianKwadratowy(nowe_a,nowe_b,nowe_c);
    }

    public String razy(WielomianKwadratowy x) {
        /*Funkcja razy() służąca do pomnożenia ze sobą dwóch wielomianów stopnia 2.
        * Zwraca String jako wynik działania. */
        int x4,x3,x2,x1,x0;
        x4 = this.a*x.a;
        x3 = this.b*x.a+this.a*x.b;
        x2 = this.a*x.c+this.c*x.a+this.b*x.b;
        x1 = this.b*x.c+this.c*x.b;
        x0 = this.c*x.c;
        return "Wynikiem mnożenia podanych wielomianów jest: "+x4+"x^4 + "+x3+"x^3 + "+x2+"x^2 + "+x1+"x + "+x0;
    }

    public String miejscaZerowe() {
        /*Funkcja miejscaZerowe() służąca do wypisania pierwiastków wielomianu stopnia 2.
        * Jeżeli takowe istnieją w zbiorze liczb rzeczywistych.
        * Zwraca String jako wynik działań. */
        int delta = b*b-4*a*c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Miejsca zerowe to: x1 = "+x1+" i x2 = "+x2;
        } else if (delta == 0) {
            double x1 = -b / (2 * a);
            return "Miejsce zerowe to x = "+x1;
        } else {
            return "Brak miejsc zerowych dla zbioru liczb rzeczywistych -> delta < 0";
        }
    }
}
