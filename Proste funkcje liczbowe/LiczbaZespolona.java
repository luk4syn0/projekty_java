import static java.lang.Math.abs; //< Klasa Math robi matematykę

/**
 * \brief       Klasa opisuje podstawowe własności liczb zespolonych.
 * \details     pozwala na wykonanie operacji takich jak: dodanie, odjęcie czy pomnożenie
 *              liczb zespolonych
 * \author      Łukasz Łuszczyk
 * \version     01.2024 build 31
 * \date        16.01.2024
 * \warning     Zostało wykonane minimum funkcjonalności oraz brakuje rozsądnych zabezpieczeń
 * \copyright   GNU Public License.*/
public class LiczbaZespolona {
    int rzeczywista; //< Część rzeczywista liczby zespolonej
    int urojona; //< Część urojona liczby zespolonej
    LiczbaZespolona(int rzeczywista, int urojona) {
        this.rzeczywista = rzeczywista;
        this.urojona = urojona;

    }
    /**\brief Getter parametru rzeczywista
     * @param[out] rzeczywista Aktualna liczba rzeczywista
     * */
    public int getRzeczywista() {
        return rzeczywista;
    }
    /**\brief Setter parametru rzeczywista
     * @param[in] rzeczywista Nowa liczba rzeczywista
     * */
    public void setRzeczywista(int rzeczywista) {
        this.rzeczywista = rzeczywista;
    }
    /**\brief Getter parametru urojona
     * @param[out] urojona Aktualna liczba urojona
     * */
    public int getUrojona() {
        return urojona;
    }
    /**\brief Setter parametru urojona
     * @param[in] urojona Nowa liczba urojona
     * */
    public void setUrojona(int urojona) {
        this.urojona = urojona;
    }

    /**\brief Funkcja służąca do dodania dwóch liczb zespolonych.
     * @param[in] x Obiekt LiczbaZespolona
     * @param[out] LiczbaZespolona nowy obiekt LiczbaZespolona */
    public LiczbaZespolona plus(LiczbaZespolona x) {

        int nowaUrojona,nowaRzeczywista;
        nowaRzeczywista=this.rzeczywista+x.rzeczywista;
        nowaUrojona=this.urojona+x.urojona;
        return new LiczbaZespolona(nowaRzeczywista,nowaUrojona);
    }
    /**\brief Funkcja służąca do odjęcia dwóch liczb zespolonych.
     * @param[in] x Obiekt LiczbaZespolona
     * @param[out] LiczbaZespolona nowy obiekt LiczbaZespolona */
    public LiczbaZespolona minus(LiczbaZespolona x) {
        /*Funkcja minus() służąca do odjęcia od siebie dwóch liczb zespolonych
         * zwraca nowy obiekt LiczbaZespolona */
        int nowaUrojona,nowaRzeczywista;
        nowaRzeczywista=this.rzeczywista-x.rzeczywista;
        nowaUrojona=this.urojona-x.urojona;
        return new LiczbaZespolona(nowaRzeczywista,nowaUrojona);
    }
    /**\brief Funkcja służąca do pomnożenia dwóch liczb zespolonych.
     * @param[in] x Obiekt LiczbaZespolona
     * @param[out] LiczbaZespolona nowy obiekt LiczbaZespolona */
    public LiczbaZespolona razy(LiczbaZespolona x) {
        /*Funkcja razy() służąca do pomnożenia dwóch liczb zespolonych
        * zwraca nowy obiekt LiczbaZespolona */
        int nowaUrojona, nowaRzeczywista;

        //(a+bi)*(c+di)=a*c+a*d*i+c*b*i+bi*di=ac+adi+cbi+bdi^2=ac+(ad+cb)*i+bd*-1
        //Przy mnożeniu wykorzystujemy fakt, że i^2 = -1
        //nowaRzeczywista = ac+bd*-1
        //nowaUrojona = ad+cb

        nowaRzeczywista=this.rzeczywista*x.rzeczywista+(this.urojona*x.urojona*-1);
        nowaUrojona=this.rzeczywista*x.urojona+this.urojona*x.rzeczywista;
        return new LiczbaZespolona(nowaRzeczywista,nowaUrojona);
    }
    /**\brief Funkcja służąca do opisu obiektu w formie String
     * @param[out]  String      Zwraca opis obiektu
     * */
    public String toString() {
        if (this.urojona >= 0) {
            return this.rzeczywista+" + "+this.urojona+"i";
        } else {
            return this.rzeczywista+" - "+abs(this.urojona)+"i";
        }

    }

}
