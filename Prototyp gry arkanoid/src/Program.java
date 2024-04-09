import javax.swing.*;

public class Program
{

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Plansza p;
            int rozmiarX = 310; //Trzeba wziąć korektę na 17 pikseli ????
            int rozmiarY = 280;

            JFrame jf=new JFrame();

            p=new Plansza();
            jf.add(p);


            jf.setTitle("Arkanoid");
            jf.setSize(rozmiarX,rozmiarY);

            jf.setResizable(false);
            // Blokuję zmianę rozszerzania okienka, ponieważ kulka mogła uciec poza planszę, gdy rozszerzyliśmy, a następnie
            // wróciliśmy do mniejszego rozmiaru.
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setVisible(true);

            p.setRozmiarX(); //Dopiero teraz możemy ustawić rozmiar parametru





        });
    }

}