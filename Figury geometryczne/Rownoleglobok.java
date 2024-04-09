public class Rownoleglobok extends Figura{

    // Dla uproszczenia użyjemy równoległoboku, którego koniec podstawy (bokA) pokrywa się z początkiem
    // 'sufitu' (również bokA) i na tej podstawie wyliczymy sobie bokB, aby zbytnio nie komplikować
    // programu pod kątem sprawdzania, czy równoległobok o takich parametrach może istnieć.
    double bokA;
    double bokB;
    double wysokosc;

    Rownoleglobok(double bokA, double wysokosc) {
        this.bokA=bokA;
        this.wysokosc=wysokosc;
        this.bokB= Math.pow(Math.pow(bokA,2)+Math.pow(wysokosc,2),0.5); // Ze wzoru Pitagorasa
    }

    double pole()
    {
        return bokA*wysokosc;
    }

    double obwod()
    {
        return bokA*2+bokB*2;
    }

    public String toString()
    {
        return "Równoległobok o podstawie "+bokA+", ścianie bocznej "+bokB+" oraz wysokości "+wysokosc;
    }

}
