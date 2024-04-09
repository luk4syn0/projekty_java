/**
 * \brief       Klasa opisuje właściwości ułamka.
 * \details     pozwala na wykonanie operacji takich jak: jak dodawanie, odejmowanie,
 *              mnożenie ułamków, rozwinięcie ich do formy dziesiętnej, skrócenie
 *              oraz odwrócenie licznika z mianownikiem.
 * \author      Łukasz Łuszczyk
 * \version     TEST
 * \date        16.01.2024
 * \copyright   GNU Public License.*/
public class Ulamek {
    int licznik; //< Licznik ułamka
    int mianownik; //< Mianownik ułamka
    Ulamek(int a, int b) {
        licznik = a;
        mianownik = b;
    }

    /**\brief Getter parametru licznik
     * @param[out] licznik Aktualny licznik ułamka
     * */
    public int getLicznik() {
        return licznik;
    }
    /**\brief Setter parametru licznik
     * @param[in] nowyLicznik Nowy licznik ułamka
     * */
    public void setLicznik(int nowyLicznik) {
        this.licznik = nowyLicznik;
    }
    /**\brief Getter parametru mianownik
     * @param[out] mianownik Aktualny mianownik ułamka
     * */
    public int getMianownik() {
        return mianownik;
    }
    /**\brief Setter parametru mianownik
     * @param[in] nowyMianownik Nowy mianownik ułamka
     * */
    public void setMianownik(int nowyMianownik) {
        this.mianownik = nowyMianownik;
    }
    /**\brief Funkcja służąca do dodania do siebie dwóch ułamków
     * @param[in] Ułamek Inny obiekt Ułamek, który chcemy dodać do bieżącego
     * @param[out] Ułamek Nowy obiekt Ułamek
     * */
    public Ulamek plus(Ulamek x) {
        int nowy_licznik,nowy_mianownik;

        nowy_licznik = this.licznik*x.mianownik+x.licznik*this.mianownik;
        nowy_mianownik = this.mianownik*x.mianownik;

        return new Ulamek(nowy_licznik,nowy_mianownik);
    }
    /**\brief Funkcja służąca do odjęcia od siebie dwóch ułamków
     * @param[in] Ułamek Inny obiekt Ułamek, który chcemy odjąć bieżącego
     * @param[out] Ułamek Nowy obiekt Ułamek
     * */
    public Ulamek minus(Ulamek x) {
        int nowy_licznik,nowy_mianownik;

        nowy_licznik = this.licznik*x.mianownik-x.licznik*this.mianownik;
        nowy_mianownik = this.mianownik*x.mianownik;
        return new Ulamek(nowy_licznik,nowy_mianownik);
    }
    /**\brief Funkcja służąca do odjęcia od siebie dwóch ułamków
     * @param[in] Ułamek Inny obiekt Ułamek, który chcemy pomnożyć przez bieżący
     * @param[out] Ułamek Nowy obiekt Ułamek
     * */
    public Ulamek razy(Ulamek x) {
        return new Ulamek(this.licznik*x.licznik,this.mianownik*x.mianownik);
    }
    /**\brief Funkcja służąca do reprezentacji obiektu ułamka w formie dziesiętnej
     * @param[out] double Reprezentacja dziesiętna ułamka
     * */
    public double rozwDziesietne() {
        double miano = (double)this.mianownik;
        return this.licznik / miano;
    }
    /**\brief Funkcja służąca do odwrócenia licznika z mianownikiem w obrębie obiektu*/
    public void odwroc() {
        int bufor;
        bufor = this.licznik;
        this.licznik = this.mianownik;
        this.mianownik = bufor;
    }
    /**\brief Funkcja pomocnicza służąca do obliczenia NWD
     * @param[out] a NWD aktualnego obiektu Ułamek */
    private int NWD() {
        int a = this.licznik;
        int b = this.mianownik;

        while (a!=b) {
            if (a>b) {
                a-=b;
            }
            else {
                b-=a;
            }
        }
        return a;
    }
    /**\brief   Funkcja służąca do skrócenia ułamka.
     * \details Skraca ułamek modyfikując parametry — licznik oraz mianownik bieżącego obiektu Ulamek
     *          wykorzystuje funkcję pomocniczą NWD(). */
    public void skroc() {
        int dzielnik;
        dzielnik = NWD();
        this.licznik = this.licznik/dzielnik;
        this.mianownik = this.mianownik/dzielnik;
    }
    /**\brief Funkcja służy do reprezentacji ułamka opisywanego przez klasę w formie tekstowej (przyjaznej dla użytkownika).
     * @param[out]  String      Zwraca String ułamka w formie licznika i mianownika oddzielonych ukośnikiem.
     * */
    public String str() {
        return this.licznik+"/"+this.mianownik;
    }
    /**\brief Funkcja służąca do opisu obiektu w formie String
     * @param[out]  String      Zwraca opis obiektu
     * */
    public String toString() {
        //Funkcja toString() służy do reprezentacji obiektu Ulamek w formie tekstowej (bardziej opisowej).
        return "klasa: "+getClass().getName()+", licznik: "+this.licznik+", mianownik: "+this.mianownik;
    }
    /** \page Strona wewnątrz ułamka
     * <h1>Opis pewnych funkcjonalności</h1>
     * <p>Lorem ipsum</p>
     * */

}
