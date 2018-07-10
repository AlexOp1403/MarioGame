package My_Game;

public abstract class GameObject {                                                      //Durch das Schlüsselwort abstract legen wir fest, dass die GameObject-Klasse eine abstrakte Klasse sein wird, von der keine Instanzen erzeugt werden können.

    private Coordinate objectPosition;                                                  // Mit der GameObject-Klasse möchten wir die Grundlage für alle Spielobjekte in unserem Java Spiel legen.
    private double height;                                                              // In ihr definieren wir alle Basiseigenschaften und -fähigkeiten von Spielobjekten.
    private double movingAngle;                                                         // Die speziellen Eigenschaften und Fähigkeiten, wie das Aussehen oder Bewegungsart, sollen dann in abgeleiteten Klassen nachträglich definiert werden.
    private double width;
    private double movingDistance;

    public GameObject(Coordinate objectPosition, double width, double height) {
        this.objectPosition = objectPosition;
        this.width = width;
        this.height = height;
        movingAngle = 0;
        movingDistance = 0;
    }

    public Coordinate getObjectPosition() {
        return objectPosition;
    }

    public void setObjectPosition(Coordinate objectPosition) {
        this.objectPosition = objectPosition;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMovingAngle() {
        return movingAngle;
    }

    public void setMovingAngle(double movingAngle) {
        this.movingAngle = movingAngle;
    }

    public double getMovingDistance() {
        return movingDistance;
    }

    public void setMovingDistance(double movingDistance) {
        this.movingDistance = movingDistance;
    }

    public boolean isLeftOf(GameObject that) {
        return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
    }

    public boolean isAbove(GameObject that) {
        return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
    }

    public boolean touches(GameObject that) {
        if(this.isLeftOf(that)) return false;
        if(that.isLeftOf(this)) return false;
        if(this.isAbove(that))  return false;
        if(that.isAbove(this))  return false;

        return true;
    }

    public static Coordinate polarToCartesianCoordinates(double angle) {

        // X-Achse zeigt nach Osten, Y-Achse zeigt nach Süden beim Zeichnen
        double x = Math.cos(angle);
        double y = Math.sin(angle);

        return new Coordinate(x, y);
    }

    public void moveGameObject() {

        Coordinate direction = polarToCartesianCoordinates(movingAngle);

        objectPosition.setX(objectPosition.getX() + direction.getX() * movingDistance);
        objectPosition.setY(objectPosition.getY() + direction.getY() * movingDistance);
    }

    public void makeMove() {
        moveGameObject();
    }

    protected abstract void paintMe(java.awt.Graphics g);

}