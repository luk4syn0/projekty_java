/**
 * \brief       Klasa opisuje właściwości wektora (trójwymiarowego).
 * \details     Umożliwia operacje na wektorach takie jak: dodawanie, odejmowanie wektorów,
 *              iloczyn wektorowy, iloczyn skalarny wektorów, mnożenie wektora przez liczbę
 *              oraz obliczenie długości wektora.
 * \author      Łukasz Łuszczyk
 * \version     0.714a
 * \date        16.01.2024
 * \bug         Na pewno jakieś są
 * \warning     Klasa pisana na kolanie, używać z przymrużeniem oka
 * \copyright   GNU Public License.*/
public class Wektor {
    int a; //< Pierwsza składowa
    int b; //< Druga składowa
    int c; //< Trzecia składowa
    Wektor(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**\brief Funkcja umożliwiająca dodanie do siebie dwóch wektorów
     * @param[in]   x           Wektor dodawany do bieżącego wektora
     * @param[out]  Wektor      Zwraca nowy obiekt Wektor
     * */
    public Wektor plus(Wektor x) {

        int nowa_a,nowa_b,nowa_c;
        nowa_a = this.a+x.a;
        nowa_b = this.b+x.b;
        nowa_c = this.c+x.c;
        return new Wektor(nowa_a,nowa_b,nowa_c);
    }
    /**\brief Funkcja umożliwiająca odjęcie od siebie dwóch wektorów
     * @param[in]   x           Wektor odejmowany od bieżącego wektora
     * @param[out]  Wektor      Zwraca nowy obiekt Wektor
     * */
    public Wektor minus(Wektor x) {
        /*Funkcja minus() umożliwiająca operację różnicy dwóch wektorów
        * Zwraca nowy obiekt Wektor*/
        int nowa_a,nowa_b,nowa_c;
        nowa_a = this.a-x.a;
        nowa_b = this.b-x.b;
        nowa_c = this.c-x.c;
        return new Wektor(nowa_a,nowa_b,nowa_c);
    }
    /**\brief Funkcja umożliwiająca operację mnożenia dwóch wektorów.
     * @param[in]   x           Obiekt Wektor
     * @param[out]  Wektor      Zwraca nowy obiekt Wektor
     * */
    public Wektor iloczynWektorowy(Wektor x) {
        int nowa_a,nowa_b,nowa_c;
        nowa_a = (this.b*x.c)-(this.c*x.b);
        nowa_b = (this.c*x.a)-(this.a*x.c);
        nowa_c = (this.a*x.b)-(this.b*x.a);
        return new Wektor(nowa_a,nowa_b,nowa_c);
    }
    /**\brief Funkcja służąca do obliczenia iloczynu skalarnego dwóch wektorów
     * @param[in]   x           Obiekt Wektor
     * @param[out]  Wektor      Zwraca nowy obiekt - Wektor
     * */
    public int iloczynSkalarnyWW(Wektor x) {
        /*Funkcja iloczynSkalarnyWW() służąca do obliczenia iloczynu skalarnego dwóch wektorów
        * Zwraca int jako wynik działania. */
        return (this.a*x.a)+(this.b*x.b)+(this.c*x.c);
    }
    /**\brief Funkcja służąca do obliczenia iloczynu skalarnego wektora oraz liczby
     * @param[in]   x           Liczba, przez którą mnożymy wektor
     * \return ILOCZYN SKALARNY LICZBY ORAZ WEKTORA
     * */
    public int iloczynSkalarnyWL(int x) {
        return (this.a*x)+(this.b*x)+(this.c*x);
    }
    /**\brief Funkcja służąca do obliczenia długości Wektora
     * @param[out]  Wektor      Zwraca nowy obiekt - Wektor
     * */
    public double dlugoscWektora() {
        /*Funkcja dlugoscWektora() służąca do obliczenia długości wektora.
        * Zwraca double jako wynik działania. */
        return Math.sqrt((this.a*this.a)+(this.b*this.b)+(this.c*this.c));
    }
    /**\brief Funkcja służąca do opisu obiektu w formie String
     * @param[out]  String      Zwraca opis obiektu
     * */
    @Override
    public String toString() {
        return "Wektor("+this.a+","+this.b+","+this.c+")";
    }
}
