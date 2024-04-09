import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Kalk implements ActionListener {
    JTextField t1;
    JTextField tdebug;
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    JButton bplus, brow,bdiv,bmult,bdel,bminus,bdot,bac,bpow,bpercent,bsqr,blast,back;

    double x, buf, var2;
    boolean done;
    int opType, numerLoguBack,numerLogu=0;

    boolean debug = true; //pomocnicze okienko plus aktualizacja informacji o aktywnej operacji (opType)



    public void przelicz() {

         /*
         Funkcja obsługująca obliczenia na podstawie aktywnego
         typu operacji ustawianego przez wybór operacji za
         pomocą przycisków w menu kalkulatora.

            |  Lista przypadków |
            |1: dodawanie       |
            |2: odejmowanie     |
            |3: mnożenie        |
            |4: dzielenie       |
            |5: potęgowanie     |
            |6: pierwiastek_kw  |
            |7: procent?        |
          */
        if (t1.getText().length()>0) {
            switch (opType) {

                case 1: //Dodawanie

                    x = Double.parseDouble(t1.getText());
                    var2 = x;
                    x = buf + x;
                    t1.setText(Double.toString(x));
                    System.out.println(opType);

                    //Przywracamy typ operacji na domyślny - 0
                    //Korzystają z tego instrukcje warunkowe danych przycisków
                    opType=0;

                    //Aktualizacja pomocniczego okienka określającego typ operacji, jaką aktualnie wykonujemy
                    if (debug) {
                        updateDebugText();
                    }

                    //Uznajemy operację liczenia za ukończoną
                    done=true;

                    //Zapisujemy do pliku wszystkie zmienne, jakie się
                    //pojawiły w obliczeniu.
                    logujDoPliku(numerLogu,buf,"+",var2, t1.getText());

                    //Zwiększamy numerLogu o 1, aby zachować kolejność numeracji
                    numerLogu+=1;

                    //Przypisujemy pomocniczej zmiennej numerLogu
                    //Potrzebne do operacji cofania działań.
                    numerLoguBack = numerLogu;

                    //Przywracamy bufor do domyślnej wartości
                    buf=0.0;

                    t1.requestFocus();
                    break;

                case 2: //Odejmowanie

                    x = Double.parseDouble(t1.getText());
                    var2 = x;
                    x = buf - x;
                    t1.setText(Double.toString(x));
                    System.out.println(opType);
                    opType=0;
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"-",var2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;

                case 3: //Mnożenie

                    x = Double.parseDouble(t1.getText());
                    var2 = x;
                    x = buf * x;
                    t1.setText(Double.toString(x));
                    System.out.println(opType);
                    opType=0;
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"*",var2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;

                case 4: //Dzielenie

                    x = Double.parseDouble(t1.getText());
                    var2 = x;

                    //Obsługa przypadku dzielenia przez zero
                    if (x!=0.0) {
                        x = buf / x;
                        t1.setText(Double.toString(x));
                        System.out.println(opType);
                        opType=0;
                    } else {
                        t1.setText("DIVISION BY ZERO"); //Wypisujemy w okienku wiadomość
                        buf = 0.0;
                        opType = 0;
                    }
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"/",var2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;

                case 5: //Podniesienie do potęgi

                    x = Double.parseDouble(t1.getText());
                    var2 = x;
                    x = Math.pow(buf,x);
                    t1.setText(Double.toString(x));
                    System.out.println(opType);
                    opType=0;
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"^",var2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;

                case 6: //Pierwiastek kwadratowy

                    //Obsługa wyjątku, gdy chcemy obliczyć pierwiastek z liczby ujemnej
                    if (buf<0) {
                        t1.setText("ERROR");
                        buf = 0.0;
                        opType = 0;
                    } else {
                        x = Math.pow(buf, (double) 1 / 2);
                        t1.setText(Double.toString(x));
                        System.out.println(opType);
                        opType = 0;
                    }
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"^",(double) 1/2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;
                case 7:

                    //Procent - implementacja
                    //Podajemy liczbę - np. 63
                    //Klikamy procent i podajemy kolejną liczbę
                    //np. 100, więc chcemy 63% z liczby 100.

                    x = Double.parseDouble(t1.getText());
                    var2 = x;
                    x = x * (buf/100);
                    t1.setText(Double.toString(x));
                    System.out.println(opType);
                    opType=0;
                    if (debug) {
                        updateDebugText();
                    }
                    done=true;
                    logujDoPliku(numerLogu,buf,"%",var2, t1.getText());
                    numerLogu+=1;
                    numerLoguBack = numerLogu;
                    buf=0.0;
                    t1.requestFocus();
                    break;
                default:
                    System.out.println("Podaj typ operacji");

            }

        }

//        x = Double.parseDouble(t1.getText());
//        x = buf + x;
//        t1.setText(Double.toString(x));
//        t1.requestFocus();
    }

    public void updateDebugText() {
        //funkcja pomocnicza do debug-u
        tdebug.setText(""+opType);
    }

    private static void logujDoPliku(int numerLogu, double var1, String operator, double var2, String wynik) {
        //Funkcja przyjmująca odpowiednie parametry, które zostają zapisane do pliku w formie logów kalkulatora
        String logMessage = ("Log "+ numerLogu +": (Liczba1="+var1+", Liczba2="+var2+", Operator="+operator+", Wynik="+wynik+")");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logi.txt", true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String odczytajWynik(int numerLogu) {
        //Odczytanie wyniku z zapisanych logów dla funkcji cofnięcia ostatniego działania.
        try {
            String logToRead = Files.lines(Paths.get("logi.txt"))
                    .filter(line -> line.startsWith("Log " + numerLogu + ":"))
                    .findFirst()
                    .orElse("Log not found");

            if (!logToRead.equals("Log not found")) {
                // Parsowanie pola wyniku z linii logu
                int start = logToRead.indexOf("Wynik=") + "Wynik=".length();
                int end = logToRead.indexOf(")", start);
                if (end == -1) {
                    end = logToRead.length() - 1;
                }
                System.out.println(start+" "+end);
                System.out.println(logToRead.substring(start, end));
                return logToRead.substring(start, end);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void wyczyscLogi() {
        //Funkcja czyszcząca plik z logami. Wywoływana w trakcie inicjalizacji kalkulatora
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logi.txt"))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //Obsługa wciśnięć klawiszy

            //Gdy otrzymaliśmy wynik, a chcemy obliczyć cos nowego bez klikania opcji wyczyszczenia
            if (done) {
                t1.setText("");
            }
            char keyChar = e.getKeyChar();
            //Sprawdzamy naciśnięty klawisz pod kątem wybrania cyfry
            if (Character.isDigit(keyChar)) {
                t1.setText(t1.getText() + keyChar);
                done=false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();

        if ((t1.getText().equals("DIVISION BY ZERO")) || (t1.getText().equals("ERROR"))) {
            t1.setText("");
        }

        if ((target == b1) || (target == b2) || (target == b3)
                || (target == b4) || (target == b5) || (target == b6)
                || (target == b7) || (target == b8) || (target == b9)
                || (target == b0)) {

            if (done) {
                t1.setText("");
                done=false;
            }
            t1.setText(t1.getText() + ((JButton) target).getText());
            t1.requestFocus();

        } else if (target == bplus) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty())) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if ((opType==0) && (buf==0.0)){
                opType=1;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else if (buf == 0.0) {
                opType=1;
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == bminus) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty())) { //jeżeli chcemy zacząć od liczby ujemnej
                t1.setText("-");
            } else if ((opType==0) && (buf==0.0)) {
                opType=2;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else if (opType!=2) { //przycisk minus nie zmienia typu operacji, jest zapisywany i rozpatrywany
                if (!t1.getText().contains("-")) {
                    t1.setText("-");
                }
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == bmult) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty())) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if ((opType==0) && (buf==0.0)){
                opType=3;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else if (buf == 0.0) {
                opType=3;
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == bdiv) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty())) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if ((opType==0) && (buf==0.0)){
                opType=4;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else if (buf == 0.0) {
                opType=4;
            } else {
                przelicz();
            }
        } else if (target == bpow) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty() || (t1.getText().equals("-")))) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if (opType==0){
                opType=5;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == bsqr) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty() || (t1.getText().equals("-")))) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if (opType==0){
                opType=6;
                buf = Double.parseDouble(t1.getText());
                przelicz();
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == bpercent) {
            done=false; //ustawiamy wskaźnik wykonania na false i oczekujemy wprowadzenia liczb
            if ((buf == 0.0) && (t1.getText().isEmpty() || (t1.getText().equals("-")))) { //ochrona przed błędami i dalszym sprawdzaniem
            } else if (opType==0){
                opType=7;
                buf = Double.parseDouble(t1.getText());
                t1.setText("");
                t1.requestFocus();
            } else {
                przelicz();
            }
            if (debug) {
                updateDebugText();
            }
        } else if (target == brow || target == t1) {
            przelicz();

        } else if (target == blast) {
            //Podmiana wyświetlanej liczby na ostatni wynik z pamięci
            t1.setText(Double.toString(x));
            done=true; //Przywracamy stan programu na taki jak po otrzymaniu wyniku

        } else if (target == back) {
            //Cofnięcie się wynikiem o jedno działanie
            if (numerLoguBack>0) {

                t1.setText(odczytajWynik(numerLoguBack-1));
                numerLoguBack-=1;
            } else {
                t1.setText("ERROR");
            }
            done=true; //Przywracamy stan programu na taki jak po otrzymaniu wyniku

        } else if (target == bac) { //done
            buf = 0.0;
            opType=0;
            t1.setText("");
            t1.requestFocus();
            if (debug) {
                updateDebugText();
            }
        } else if (target == bdel) { //done
            if (t1.getText().length()>0) {
            t1.setText(t1.getText().substring(0, t1.getText().length()-1));
            done=false;
            t1.requestFocus();
            }
            else {
                System.out.println("Brak znakow do usuniecia");
            }
        } else if (target == bdot) { //done
            if (t1.getText().contains(".")) {
                System.out.println("Nie mozna wykonac tej operacji");
            }
            else {
                t1.setText(t1.getText() + ((JButton) target).getText());
                t1.requestFocus();
            }

        }
    }

    void init() {
        //try
        //{
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //}
        //catch(Exception e){}

        JFrame f = new JFrame();
        Container c = f.getContentPane();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        c.setLayout(gbl);


        t1 = new JTextField(15);
        t1.addActionListener(this);
        t1.addKeyListener(new MyKeyListener());
        t1.setHorizontalAlignment(JTextField.RIGHT);
        t1.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.ipadx = 0;
        gbc.ipady = 5;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(t1, gbc);
        c.add(t1);


        b1 = new JButton("1");
        b1.addActionListener(this);
        b1.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b1, gbc);
        c.add(b1);

        b2 = new JButton("2");
        b2.addActionListener(this);
        b2.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b2, gbc);
        c.add(b2);

        b3 = new JButton("3");
        b3.addActionListener(this);
        b3.setFocusable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b3, gbc);
        c.add(b3);

        b4 = new JButton("4");
        b4.addActionListener(this);
        b4.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b4, gbc);
        c.add(b4);

        b5 = new JButton("5");
        b5.addActionListener(this);
        b5.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b5, gbc);
        c.add(b5);

        b6 = new JButton("6");
        b6.addActionListener(this);
        b6.setFocusable(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b6, gbc);
        c.add(b6);

        b7 = new JButton("7");
        b7.addActionListener(this);
        b7.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b7, gbc);
        c.add(b7);

        b8 = new JButton("8");
        b8.addActionListener(this);
        b8.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b8, gbc);
        c.add(b8);

        b9 = new JButton("9");
        b9.addActionListener(this);
        b9.setFocusable(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 0);
        gbl.setConstraints(b9, gbc);
        c.add(b9);


        bplus = new JButton("+");
        bplus.addActionListener(this);
        bplus.setFocusable(false);
        bplus.setToolTipText("dodawanie");
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bplus, gbc);
        c.add(bplus);

        bminus = new JButton("-");
        bminus.addActionListener(this);
        bminus.setFocusable(false);
        bminus.setToolTipText("odejmowanie");
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bminus, gbc);
        c.add(bminus);

        bmult = new JButton("*");
        bmult.addActionListener(this);
        bmult.setFocusable(false);
        bmult.setToolTipText("mnożenie");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bmult, gbc);
        c.add(bmult);

        bdiv = new JButton("/");
        bdiv.addActionListener(this);
        bdiv.setFocusable(false);
        bdiv.setToolTipText("dzielenie");
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bdiv, gbc);
        c.add(bdiv);

        bdel = new JButton("DEL");
        bdel.addActionListener(this);
        bdel.setFocusable(false);
        bdel.setToolTipText("kasacja 1 znaku");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bdel, gbc);
        c.add(bdel);

        bac = new JButton("AC");
        bac.addActionListener(this);
        bac.setFocusable(false);
        bac.setToolTipText("kasacja");
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 0, 5);
        gbl.setConstraints(bac, gbc);
        c.add(bac);

        b0 = new JButton("0");
        b0.addActionListener(this);
        b0.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbl.setConstraints(b0, gbc);
        c.add(b0);


        bdot = new JButton(".");
        bdot.addActionListener(this);
        bdot.setFocusable(false);
        bdot.setToolTipText("kropka dziesietna");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbl.setConstraints(bdot, gbc);
        c.add(bdot);

        bpow = new JButton("^y");
        bpow.addActionListener(this);
        bpow.setFocusable(false);
        bpow.setToolTipText("podnieś do potęgi y");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbl.setConstraints(bpow, gbc);
        c.add(bpow);

        bsqr = new JButton("sqr");
        bsqr.addActionListener(this);
        bsqr.setFocusable(false);
        bsqr.setToolTipText("pierwiastek kwadratowy");
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbl.setConstraints(bsqr, gbc);
        c.add(bsqr);

        bpercent = new JButton("%");
        bpercent.addActionListener(this);
        bpercent.setFocusable(false);
        bpercent.setToolTipText("procent");
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbl.setConstraints(bpercent, gbc);
        c.add(bpercent);

        blast = new JButton("L");
        blast.addActionListener(this);
        blast.setFocusable(false);
        blast.setToolTipText("ostatni wynik z pamięci");
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbl.setConstraints(blast, gbc);
        c.add(blast);

        back = new JButton("1B");
        back.addActionListener(this);
        back.setFocusable(false);
        back.setToolTipText("1 operacja wstecz");
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbl.setConstraints(back, gbc);
        c.add(back);

        brow = new JButton("=");
        brow.addActionListener(this);
        brow.setFocusable(false);
        brow.setToolTipText("wykonaj działanie");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.ipadx = 30;
        gbc.ipady = 0;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbl.setConstraints(brow, gbc);
        c.add(brow);

        if (debug) {
            tdebug = new JTextField(15);
            tdebug.addActionListener(this);
            tdebug.setHorizontalAlignment(JTextField.RIGHT);
            tdebug.setEditable(false);
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 2;
            gbc.ipadx = 0;
            gbc.ipady = 5;
            gbc.insets = new Insets(5, 5, 0, 5);
            gbl.setConstraints(tdebug, gbc);
            c.add(tdebug);
        }


        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Kalkulator");
        f.setVisible(true);
        wyczyscLogi();
    }

    public static void main(String[] args) {
        //do wersji 1.4
        //new Kalk().init();

        //od wersji 1.5
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Kalk().init();
            }
        });
    }
}