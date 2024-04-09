import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

class SilnikKulki extends Thread
{
    Kulka a;
    Belka b;
    Kafelek[] kafelki;
    int wynikDocelowy;
    boolean gameover = false;
    Plansza p;

    SilnikKulki(Kulka a, Belka b, Kafelek[] kafelki, Plansza p)
    {
        this.a=a;
        this.b=b;
        this.kafelki=kafelki;
        this.p=p;
        this.wynikDocelowy= kafelki.length;

        start();
    }

    int przeszukajListe(Kafelek[] kafelki, Kafelek szukany) {
        //Funkcja pomocnicza dla listy kafelków
        //Odnajduje indeks danego obiektu i pozwala go wykorzystać
        for (int index = 0; index < kafelki.length; index++) {
            if (kafelki[index] == szukany) {
                return index;
            }
        }
        return -1;
    }

    public Boolean kolidujeZBelka() {
        //Sprawdzamy, czy w następnym kroku jest kolizja belki z kulką
//        return this.b.intersects(a.x+a.dx,a.y+a.dy,a.width,a.height);
        return this.b.intersects(a.x,a.y,a.width,a.height);
    }

    public int kolidujeZObiektem() {
        //Sprawdzamy, czy w następnym kroku jest kolizja z kafelkiem, -1 brak kolizji
        for (Kafelek kafelek: kafelki){
            if (kafelek!= null && kafelek.intersects(a.x+a.dx,a.y+a.dy,a.width,a.height)){
                return przeszukajListe(kafelki,kafelek);
            }
        }
        return -1;
    }

    public void run() {
        try {
            while(!gameover){ //Gramy, dopóki nie przegraliśmy, duh
                if (a.getMinY() > 120) { //Od tej wysokości możemy wykrywać belkę, a nie przeszukiwać listę
                    if (!kolidujeZBelka()) {
                        a.nextKrok();
                    } else {
                        double ax1 = a.getMinX();
                        double ax2 = a.getMaxX();

                        double ay1 = a.getMinY();
                        double ay2 = a.getMaxY();

                        double bx1 = b.getMinX();
                        double bx2 = b.getMaxX();

                        double by1 = b.getMinY();
                        double by2 = b.getMaxY();

                        // o ------------>
                        // |             x
                        // |
                        // |
                        // |
                        // |
                        // V y


                        if (ay2 < by1 + 2 && ay2 >= by1 - 2) { //Uderzenie od góry
                            //Dodana funkcja zmieniająca kierunek
//                            System.out.println("1");

                            double middle = a.getMinX()+a.getWidth()/2;
                            if (middle > b.getMinX() && middle < (b.getMinX()+b.getWidth()/3)){
                                //System.out.println(a.dx+" "+a.dy); //z prawej dx ujemne dy dodatnie
                                if (a.dx < 0 && a.dy > 0) {
                                    a.dy = -a.dy;
                                } else {
                                    a.dy = -a.dy;
                                    a.dx = -a.dx;
                                }
                            } else if (middle > (b.getMinX()+b.getWidth()/3) && middle < (b.getMinX()+2*b.getWidth()/3)) {
                                a.dy = -a.dy;
                            } else {
                                //System.out.println(a.dx+" "+a.dy);
                                if (a.dx > 0 && a.dy > 0) {
                                    a.dy = -a.dy;
                                } else {
                                    a.dy = -a.dy;
                                    a.dx = -a.dx;
                                }
                            }
                        } else if (ax2 < bx1 + 2 && ax2 >= bx1) { //Uderzenie lewego boku
//                            System.out.println("2");
                            a.dx = -a.dx;
                        } else if (ax1 > bx2 - 2 && ax1 <= bx2) { //Uderzenie prawego boku
//                            System.out.println("3");
                            a.dx = -a.dx;

                        } else if (ay1 > by2 - 2 && ay1 <= by2 + 2) { //Uderzenie w dół (dla belki bez sensu)
//                            System.out.println("else");
                            a.dy = -a.dy;
                        }
                        a.nextKrok();
                    }
                } else {
                    int przypadek = kolidujeZObiektem();
                    if (przypadek==-1) {

                        a.nextKrok();

                    } else {

                        //Następuje zbicie kafelka

                        double ax1 = a.getMinX();
                        double ax2 = a.getMaxX();

                        double ay1 = a.getMinY();
                        double ay2 = a.getMaxY();

                        double bx1 = kafelki[przypadek].getMinX();
                        double bx2 = kafelki[przypadek].getMaxX();

                        double by1 = kafelki[przypadek].getMinY();
                        double by2 = kafelki[przypadek].getMaxY();

                        // o ------------>
                        // |             x
                        // |
                        // |
                        // |
                        // |
                        // V y


                        if (ay2 < by1 + 2 && ay2 >= by1 - 2) { //Uderzenie od góry

//                            System.out.println("1");
                            a.dy = -a.dy;
                        } else if (ax2 < bx1 + 2 && ax2 >= bx1) { //Uderzenie lewego boku
//                            System.out.println("2");
                            a.dx = -a.dx;
                        } else if (ax1 > bx2 - 2 && ax1 <= bx2) { //Uderzenie prawego boku
//                            System.out.println("3");
                            a.dx = -a.dx;

                        } else if (ay1 > by2 - 2 && ay1 <= by2 + 2) { //Uderzenie w dół
//                            System.out.println("else");
                            a.dy = -a.dy;
                        }
                        a.nextKrok();
                        kafelki[przypadek]=null; //Zerujemy obiekt kafelka
                        p.wynik+=1;

                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/mouseClick.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        } catch (Exception ex) {
                            System.out.println("Błąd dzwięku");
                            //ex.printStackTrace();
                        }
                    }
                }

                if (p.wynik==wynikDocelowy) {
                    //Wygrywamy, jeżeli wynik pokrywa się z ilością utworzonych kafelków
                    a.dx=0;
                    a.dy=0;
                    b.dx=0;
                    p.koniecGry();
                }
//                System.out.println(p.wynik);

                b.moveBelka();
                //Przeniosłem tutaj ruch belki, aby silnik odpowiadał i za belkę i za kulkę
                //Eliminuje to problem teleportacji belki w miejsce kulki i błąd kolizji.
                sleep(5); //15 default
            }
        }
        catch(InterruptedException e){
            System.out.println("Błąd");
        }
    }
}