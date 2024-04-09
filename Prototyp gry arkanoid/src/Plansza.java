import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Plansza extends JPanel implements MouseMotionListener
{
    Belka b;
    Kulka a;
    SilnikKulki s;
    int rozmiarX;

    Kafelek[] kafelki;
    int wynik;
    BufferedImage tlo;
    boolean poczatek = true;

    Plansza()
    {
        super();
        addMouseMotionListener(this);
        b=new Belka(100,0);
        a=new Kulka(this,155,200,0,0);
        this.tlo=null;
//        this.wynik = 40; //Debug dla wygranej
        wczytajObraz();
        kafelki = new Kafelek[42];
        tworzKafelki();


        s=new SilnikKulki(a,b, kafelki, this);
    }

    // 12 odleglosci od krawedzi
    // 5 kafelkow w poziomie po 52 piksele szerokosci

    void wczytajObraz(){
        try {
            this.tlo = ImageIO.read(new File("src/tlo.jpg"));
        } catch (IOException e) {
            System.out.println("Błąd tła");
        }
    }
    void tworzKafelki()
    {
        int szerokosc = 36;
        int wysokosc = 12;
        int xPocz = 10;

        for (int i = 0; i < 7; i++) {
            kafelki[i] = new Kafelek(xPocz, 12, szerokosc, wysokosc);
            xPocz+=40;
        }
        xPocz= 10;
        for (int i = 7; i < 14; i++) {
            kafelki[i] = new Kafelek(xPocz, 28, szerokosc, wysokosc);
            xPocz+=40;
        }
        xPocz= 10;
        for (int i = 14; i < 21; i++) {
            kafelki[i] = new Kafelek(xPocz, 44, szerokosc, wysokosc);
            xPocz+=40;
        }
        xPocz= 10;
        for (int i = 21; i < 28; i++) {
            kafelki[i] = new Kafelek(xPocz, 60, szerokosc, wysokosc);
            xPocz+=40;
        }
        xPocz= 10;
        for (int i = 28; i < 35; i++) {
            kafelki[i] = new Kafelek(xPocz, 76, szerokosc, wysokosc);
            xPocz+=40;
        }
        xPocz= 10;
        for (int i = 35; i < 42; i++) {
            kafelki[i] = new Kafelek(xPocz, 92, szerokosc, wysokosc);
            xPocz+=40;
        }
        //kafelki[0] = new Kafelek(10, y, szerokosc, wysokosc);
        //kafelki[1] = new Kafelek(50, y, szerokosc, wysokosc);
        //kafelki[2] = new Kafelek(90, y, szerokosc, wysokosc);
        //kafelki[3] = new Kafelek(130, y, szerokosc, wysokosc);
        //kafelki[4] = new Kafelek(170, y, szerokosc, wysokosc);
        //kafelki[5] = new Kafelek(210, y, szerokosc, wysokosc);
        //kafelki[6] = new Kafelek(250, y, szerokosc, wysokosc);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d=(Graphics2D)g;
        g2d.drawImage(tlo, 0, 0, null);

        Font font = new Font("TimesRoman", Font.PLAIN, 16);
        g2d.setFont(font);

        if (poczatek){
            FontMetrics metryki = getFontMetrics(font);
            int x = getX() + (getWidth() - metryki.stringWidth("PRZECIĄGNIJ MYSZĄ")) / 2;
            int y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
            g2d.setColor(Color.WHITE);
            g2d.drawString("PRZECIĄGNIJ MYSZĄ",x,y);

            x = getX() + (getWidth() - metryki.stringWidth("ABY ROZPOCZĄĆ")) / 2;
            y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
            g2d.drawString("ABY ROZPOCZĄĆ",x,y+20);
            return;
        }

        if (s.gameover){

            //FontMetrics snippet z https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
            FontMetrics metryki = getFontMetrics(font);
//            int x = getX() + (getWidth() - metryki.stringWidth("GAME OVER")) / 2;
//            int y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();

            if (wynik != 42)
            {
                int x = getX() + (getWidth() - metryki.stringWidth("GAME OVER")) / 2;
                int y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
                g2d.setColor(Color.red);
                g2d.drawString("GAME OVER",x,y-20);
            } else {
                int x = getX() + (getWidth() - metryki.stringWidth("WYGRAŁEŚ!")) / 2;
                int y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
                g2d.setColor(Color.GREEN);
                g2d.drawString("WYGRAŁEŚ!",x,y-20);
            }

            metryki = getFontMetrics(font);
            int x = getX() + (getWidth() - metryki.stringWidth("Zdobyłeś "+wynik+" punktów")) / 2;
            int y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
            g2d.drawString("Zdobyłeś "+wynik+" punktów", x, y);

            x = getX() + (getWidth() - metryki.stringWidth("PRZECIĄGNIJ BY ZRESTARTOWAĆ")) / 2;
            y = (getY() + (getHeight() - metryki.getHeight()) / 2) + metryki.getAscent();
            g2d.drawString("PRZECIĄGNIJ BY ZRESTARTOWAĆ",x,y+100);
            return;
        }
        g2d.setColor(Color.white);
        g2d.fill(a);
        g2d.setColor(Color.cyan);
        g2d.fill(b);
        g2d.setColor(Color.white);
        g2d.drawString("Wynik: ", 200, 235);
        g2d.drawString(String.valueOf(wynik), 260, 235);

        for (int i = 0; i < 42; i++) {
            if (kafelki[i]!=null) {
                g2d.fill(kafelki[i]);
            }

        }
    }

    void setRozmiarX(){
        this.rozmiarX=getWidth();
//        System.out.println(getWidth()+" "+getHeight());
    }

    void koniecGry(){
        a.dx=0;
        a.dy=0;
        b.dx=0;
        s.gameover=true;
        repaint();

    }

    public void mouseMoved(MouseEvent e)
    {
//        System.out.println(e.getX());
        int pozycjaMyszy = e.getX();
        int szerokoscBelki = (int)b.width;
        // Wykroczenie w prawo
        //            System.out.println("else");
        if (pozycjaMyszy < szerokoscBelki/2) // Wykroczenie w lewo
        {
            b.setXDest(0);

        } else b.setXDest(Math.min(pozycjaMyszy - szerokoscBelki / 2, rozmiarX - szerokoscBelki));

//        b.setX(e.getX()-50);

    }

    public void mouseDragged(MouseEvent e)
    {
        if (poczatek) {
            poczatek=false;
            a.dx=1;
            a.dy=-1;
            b.dx=1.25;
        }
        if (s.gameover) {
            b=null;
            a=null;
            tlo=null;
            s=null;
            kafelki=null;
            wynik=0;
            System.gc();


            b=new Belka(100,1);
            a=new Kulka(this,155,200,1,-1);
            this.tlo=null;
//            this.wynik = 37; //Debug dla wygranej
            wczytajObraz();
            kafelki = new Kafelek[42];
            tworzKafelki();
            s=new SilnikKulki(a,b, kafelki, this);
        }
    }
}