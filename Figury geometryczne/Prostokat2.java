class Prostokat2 extends Figura
{
    double dlugosc;
    double szerokosc;

    Prostokat2(double dlugosc,double szerokosc)
    {
        this.dlugosc=dlugosc;
        this.szerokosc=szerokosc;
    }

    double pole()
    {
        return dlugosc*szerokosc;
    }

    double obwod()
    {
        return 2*dlugosc+2*szerokosc;
    }

    public String toString()
    {
        return "prostokat o wym. "+dlugosc+" na "+szerokosc;
    }
}