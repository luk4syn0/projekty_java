import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

//Proste wykorzystanie dwuwątkowości do obsługi serwera komunikatora.
//Wykorzystałem jedynie jeden dodatkowy wątek poza głównym do obsługi podstawowych
//zadań, które powinien wykonywać serwer lub klient.
//Obsługa wysyłania wiadomości spoczywa na głównym procesie, natomiast odbiór
//oraz wypisywanie wiadomości na ekranie obsługiwane jest na drugim wątku.
//Konsekwencją tego pomysłu jest konieczność kliknięcia przycisku po
//zamknięciu komunikacji po drugiej stronie, ponieważ główny wątek oczekuje na input z klawiatury.
//Myślę, że nie jest to zbytnim problemem.

class Odbior_serwer extends Thread {
        Socket sock;
        BufferedReader sockReader;

        String strIN;

        public Odbior_serwer(Socket sock) throws IOException {
            this.sock = sock;
            this.sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }

        public void run() {

            while (!sock.isClosed()) {
                try {
                    strIN = sockReader.readLine();
                    if (strIN != "null") {
                        if (strIN.equalsIgnoreCase("koniec")) {
                            System.out.println("Koniec połączenia, naciśnij dowolny klawisz aby zamknąć serwer");
                            //zamkniecie polaczenia
                            try {
                                sockReader.close();
                                sock.close();
                            } catch (IOException e) {
                                System.out.println("Nie udalo sie zamknac socketu");
                            }
                        } else {

                            System.out.println("["+ LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond()+"]"+"<Klient:> " + strIN);
                        }
                    }

                } catch (IOException e) {
                    if (!sock.isClosed()) {
                        System.out.println("Nie udało sie odczytac socketu.");
                    }

                }


            }

        }
    }

    public class Serwer_dupleks
    {
        public static final int PORT=50007;

        public static void main(String[] args) throws IOException
        {
            //tworzenie gniazda serwerowego
            ServerSocket serv;
            serv=new ServerSocket(PORT);

            //oczekiwanie na polaczenie i tworzenie gniazda sieciowego
            System.out.println("Nasluchuje: "+serv);
            Socket sock;
            sock=serv.accept();
            System.out.println("Jest polaczenie: "+sock);

            //tworzenie watka odbierajacego
            new Odbior_serwer(sock).start();

            //tworzenie zmiennych dla wysylania danych do klienta
            BufferedReader klaw;
            klaw=new BufferedReader(new InputStreamReader(System.in));

            PrintWriter outp;
            outp=new PrintWriter(sock.getOutputStream());
            String strOUT;

            while (!sock.isClosed()) {
//                System.out.print("<Wysylamy:> ");

                try {
                    strOUT = klaw.readLine();
                    outp.println(strOUT);
                    outp.flush();
                    if (!sock.isClosed()) {
                        if (strOUT.equalsIgnoreCase("koniec")) {
                            sock.close();
                        } else {
                            System.out.println("["+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond()+"]"+"<JA:> " + strOUT);
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Problem IO");
                }


        }



            //zamykanie serwera
            System.out.println("Zamykam");
            serv.close();
            klaw.close();
            outp.close();
            try {
                sock.close();
            } catch (IOException e) {
                System.out.println("Nie udalo sie zamknac end");
            }
        }
    }
