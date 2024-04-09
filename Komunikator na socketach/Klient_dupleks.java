import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

class Odbior_klient extends Thread {
    Socket sock;
    BufferedReader sockReader;

    String strIN;

    public Odbior_klient(Socket sock) throws IOException {
        this.sock = sock;
        this.sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }

    public void run() {
        while (!sock.isClosed()) {
            try {
                strIN = sockReader.readLine();
                if (strIN != "null") {
                    if (strIN.equalsIgnoreCase("koniec")) {
                        System.out.println("Koniec połączenia, nacisnij dowolny klawisz aby zamknąć klient");
                        //zamkniecie polaczenia
                        try {
                            sockReader.close();
                            sock.close();
                        } catch (IOException e) {
                            System.out.println("Nie udalo sie zamknac socketu.");
                        }

                    } else {
                        System.out.println("["+LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond()+"]"+"<Serwer:> " + strIN);
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


public class Klient_dupleks {
    public static final int PORT = 50007;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        //nawiazanie polaczenia z serwerem

        Socket sock;
        try {
            sock = new Socket(HOST, PORT);
            System.out.println("Nawiazalem polaczenie: " + sock);
        } catch (IOException e) {
            System.out.println("Nie nawiązałem połączenia. Uruchom serwer i spróbuj jeszcze raz.");
            System.exit(1);
            return;
        }

        //tworzenie watka odbierajacego
        new Odbior_klient(sock).start();

        //tworzenie zmiennych dla wysylania danych do klienta
        BufferedReader klaw;
        klaw = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter outp;
        outp = new PrintWriter(sock.getOutputStream());
        String strOUT;

        while (!sock.isClosed()) {
//            System.out.print("<Wysylamy:> ");

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
        System.out.println("Zamykam");
        klaw.close();
        outp.close();
    }
}



