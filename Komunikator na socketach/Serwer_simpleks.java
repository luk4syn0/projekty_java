import java.io.*;
import java.net.*;

public class Serwer_simpleks
{
    public static final int PORT=50007;

    public static void main(String[] args) throws IOException, InterruptedException {
        //tworzenie gniazda serwerowego
        ServerSocket serv;
        serv=new ServerSocket(PORT);

        //oczekiwanie na polaczenie i tworzenie gniazda sieciowego
        System.out.println("Nasluchuje: "+serv);
        Socket sock;
        sock=serv.accept();
        System.out.println("Jest polaczenie: "+sock);

        //tworzenie strumienia danych pobieranych z gniazda sieciowego

        BufferedReader klaw;
        klaw=new BufferedReader(new InputStreamReader(System.in));

        BufferedReader inp;
        inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));

        PrintWriter outp;
        outp=new PrintWriter(sock.getOutputStream());

        //komunikacja - czytanie danych ze strumienia

        String strIn, strOut;
        boolean x = true;
        while (x)
        {
            try {
                strIn=inp.readLine();
                if (strIn.equalsIgnoreCase("koniec")) {
                    System.out.println("Koniec połączenia");
                    outp.println("koniec");
                    outp.flush();
                    x=false;
                }
                else {
                    if (strIn != "null") {
                        System.out.println("<Nadeszlo:> " + strIn);

                        System.out.print("<Wysylamy:> ");
                        strOut=klaw.readLine();

                        outp.println(strOut);
                        outp.flush();
                    }
                }

            }
            //Ćw. 5.4
            catch (SocketException e) {
                System.out.println("Wystąpił problem z socketem");
            }

        }




        //zamykanie polaczenia
        inp.close();
        klaw.close();
        sock.close();
        serv.close();



    }
}