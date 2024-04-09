import java.io.*;
import java.net.*;

public class Klient_simpleks
{
    public static final int PORT=50007;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException
    {
        //nawiazanie polaczenia z serwerem
        Socket sock;
        try {
            sock=new Socket(HOST,PORT);
            System.out.println("Nawiazalem polaczenie: "+sock);
        }
        catch (UnknownHostException | ConnectException e) {
            //Ćw. 5.3
            System.out.println("Połączenie zostało przerwane");
            return;
        }


        //tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu
        BufferedReader klaw;
        klaw=new BufferedReader(new InputStreamReader(System.in));

        BufferedReader inp;
        inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));

        PrintWriter outp;
        outp=new PrintWriter(sock.getOutputStream());

        //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia
        String strOut,strIn;
        boolean x=true;
        while (x) {


            System.out.print("<Wysylamy:> ");
            strOut=klaw.readLine();

            outp.println(strOut);
            outp.flush();

            try {
                strIn=inp.readLine();
                // Sprawdzam czy serwer nie zwrócił kodu zamknięcia
                if (strIn.equalsIgnoreCase("koniec")) {
                    System.out.println("Koniec połączenia");
                    outp.println("koniec");
                    outp.flush();
                    x=false;

                }
                else {
                    System.out.println("<Nadeszlo:> " + strIn);
                }
            }
            catch (SocketException e) {
                System.out.println("Wystąpił problem z socketem");
            }


        }


        //zamykanie polaczenia
        klaw.close();
        outp.close();
        sock.close();
    }
}