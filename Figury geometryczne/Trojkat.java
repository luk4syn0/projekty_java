import java.lang.Math;
class TrojkatRowRam extends Figura {
    double podstawa;
    double wysokosc;
    double ramie;

    TrojkatRowRam(double podstawa, double wysokosc) {
        this.podstawa=podstawa;
        this.wysokosc=wysokosc;
        this.ramie=Math.pow(Math.pow(0.5*podstawa,2)+Math.pow(wysokosc,2),0.5); // Ze wzoru Pitagorasa
    }
    double pole()
    {
        return podstawa*wysokosc*0.5;
    }

    double obwod()
    {
        return podstawa+ramie*2;
    }

    public String toString()
    {
        return "Trojkat równoramienny o podstawie "+podstawa+", wysokości "+wysokosc+" oraz ramionach o długości "+ramie;
    }
}
