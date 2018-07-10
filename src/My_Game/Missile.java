package My_Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

public class Missile extends GameObject {                                                               //Die Missile-Klasse erweitert die abstrakte Klasse GameObject( Superklasse ).

    private int range = 100;

    public Missile (Coordinate position, double size, double movingAngle, double movingDistance) {             //Mit dem Konstruktor in den Zeilen 13 bis 18 setzen wir die Werte der geerbten Membervariablen.
        super(position, size, size/3);                                                                  // Dazu rufen wir auch den Konstruktor der Superklasse mit der Anweisung in Zeile 14 auf.

        setMovingAngle(movingAngle);
        setMovingDistance(movingDistance);
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public void makeMove() {                                                                            //Mit dem Quellcode in den Zeilen 29 bis 32 überschreiben wir die makeMove() Methode der Superklasse GameObject.
        if (range > 0) super.makeMove();                                                               // Solange der range-Wert größer als 0 ist, rufen wir die makeMove() Methode der Superklasse, mit der Anweisung super.makeMove(), auf.
        range--;                                                                                      // Dadurch wird das Geschoss-Spielobjekt einen Schritt in die Bewegungsrichtung fortbewegt.
    }

    @Override
    public void paintMe(java.awt.Graphics g) {                                                        //Die Aufgabe der paintMe() Methode ist das Zeichnen des Spielobjekts, hier eines Geschosses.

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        AffineTransform transform = new AffineTransform();                                          //Zuerst erzeugen wir in den Zeilen 41 bis 43 ein Rechteck als Form unseres Geschosses.
        RoundRectangle2D missileShape = new RoundRectangle2D.Double(getObjectPosition().getX(),        // Dabei greifen wir auf die Membervariablen der Missile-Klasse zu und lesen die aktuelle Position und Ausmaße des Spielobjekts aus.
                getObjectPosition().getY(),                                                         // Anschließend rotieren wir die Affine Abbildung um den Bewegungswinkel des Spielobjekts.
                getWidth(), getHeight(), 3, 3);                                             // Als Ankerpunkt übergeben wir die Koordinaten des Mittelpunkts unseres Spielobjekts.

        transform.rotate(getMovingAngle(),missileShape.getCenterX(), missileShape.getCenterY());
        Shape transformedMissileShape = transform.createTransformedShape(missileShape);             //Nun erzeugen wir in Zeile 46 die transformierte Form unseres Spielobjekts mit Hilfe der Methode createTransformedShape(),
                                                                                                    // die wir über das AffineTransform-Objekt aufrufen. Als Ergebnis erhalten wir die Form des Spielobjekts gedreht um den Bewegungswinkel.
        g2d.fill(transformedMissileShape);                                                          //Jetzt müssen wir die transformierte Form nur noch zeichnen. Dies führen wir in Zeile 48 durch und erhalten somit unser transformierten Spielobjekt.

    }

}