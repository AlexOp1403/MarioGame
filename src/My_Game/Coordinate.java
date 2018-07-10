package My_Game;

public class Coordinate {
                                                    //Mit Hilfe der zwei Variablen x und y kann ein Punkt im zweidimensionalen Raum definiert werden.
    private double y;                               // Über die Get– und Set-Methoden wird der Wert der jeweiligen Variable abgefragt oder gesetzt.
    private double x;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}