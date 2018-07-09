package My_Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel{                                   // Wir geben den Namen unserer Java Klasse vor. Mit dem Schlüsselwort extends nach dem Klassennamen legen wir fest, dass sich unsere GamePanel-Klasse von der Klasse JPanel der Swing-Bibliothek ableiten soll. Dies ist sehr wichtig, da wir auf diese Weise alle Eigenschaften und Fähigkeiten der JPanel-Klasse erben, bspw. ist es dadurch möglich in dem Programmfenster zu zeichnen.

    private Missile testMissileOne;
    private Missile testMissileTwo;

    public static final String IMAGE_DIR = "images/";

    private final Dimension prefSize = new Dimension(1180, 780);

    private ImageIcon backgroundImage;
    private final String[] backgroundImages =  new String [] {"images.png","maxresdefault.jpg","stage_classicmario.png"};



    private boolean gameOver =  false;
    private int collectedCoins = 0;

    private Timer t;


    public GamePanel(){
        setFocusable(true);                                                //Im parameterlosen Konstruktor, Zeile 34 bis 40, unserer GamePanel-Klasse legen wir die bevorzugte Größe der Spielfläche fest und stellen sicher, dass sie den Benutzerfokus erhalten kann
        setPreferredSize(prefSize);

        initGame();
        startGame();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {                           //Mit den beiden Methoden isGameOver() und setGameOver() in den Zeilen 43 bis 49 können wir den Spielzustand abfragen
        this.gameOver = gameOver;                                         // bzw. den Spielzustand auf den gewünschten boolean-Wert setzen. Wir werden diese Methoden später benötigen, wenn wir die Spiellogik implementieren
    }

    private void initGame(){                                              //Nun kommen wir zu einer sehr wichtigen Methode unserer GamePanel-Klasse, der initGame() Methode, die wir in den Zeilen 51 bis 61 definieren.
        setBackgroundImage(0);                                            // In dieser Methode initialisieren wir unser Java Spiel. Wir setzen das Hintergrundbild, erzeugen die Spielobjekte und den Timer.
        createGameObjects();

        t =  new Timer(20, new ActionListener() {                       //Mit dem Timer werden wir unser Java Spiel steuern.
                                                                            // Für das neu erzeugte Timer-Objekt registrieren wir mit Hilfe einer anonymen inneren Klasse einen ActionListener mit
            public void actionPerformed(ActionEvent actionEvent) {         // der überschriebenen Methode actionPerformed(). Diese Methode wird immer dann automatisch aufgerufen, wenn der Timer um 20 ms weitergelaufen ist.
                doOnTick();                                                 //In dieser Methode rufen wir unsere eigene Methode doOnTick() auf, die wir weiter unten im Quellcode definieren werden.
            }                                                               //Sie ist dafür zuständig, den Spielzustand zu berechnen und das Zeichnen der Spielobjekte zu veranlassen.
        });
    }

    private void createGameObjects(){
        testMissileOne = new Missile(new Coordinate(200,100), 9, Math.toRadians(45), 5);
        testMissileTwo = new Missile(new Coordinate(200,609), 9, Math.toRadians(-45), 5);
    }

    private void initPlayer(){
        //TODO
    }

    public void setBackgroundImage(int imageNumber){                        // Die Methode setBackgroundImage() nutzen wir, um das Hintergrundbild für unser Java Spiel zu laden.
        String imagePath = IMAGE_DIR + backgroundImages[imageNumber];       // Dazu bauen wir zuerst den relativen Pfad zu einem unserer eingefügten Bilder zusammen.
        URL imageURL = getClass().getResource(imagePath);                   // Anschließend lassen wir uns ein URL-Objekt erstellen, welches den absoluten Pfad zur Bilddatei enthält.
        backgroundImage = new ImageIcon(imageURL);                          // Mit diesem URL-Objekt als Argument erstellen wir dann unser Hintergrundbild in Form eines ImageIcon-Objekts.
    }

    private void startGame(){
        t.start();                                                           // t = Timer Objekt
    }

    public void pauseGame(){
        t.stop();
    }

    public void continueGame(){
        if (!isGameOver()){
            t.start();
        }
    }

    public void restartGame(){
        collectedCoins = 0;
        setGameOver(false);
        createGameObjects();
        startGame();
    }

    private void endGame(){
        setGameOver(true);
        pauseGame();
    }

    private void doOnTick(){
        collectedCoins++;
        if(collectedCoins > 2015 ) endGame();

        testMissileOne.makeMove();
        testMissileTwo.makeMove();
        if (testMissileOne.touches(testMissileTwo)) endGame();

        repaint();
    }
                                                                                                      //Mit der Anweisung in Zeile 105 lassen wir die Spielfläche neu zeichnen.
    public void paintComponent(Graphics g){                                                         //Durch den Aufruf der geerbten repaint() Methode, wird auch die Callback-Methode paintComponent() aufgerufen.
        super.paintComponent(g);                                                                    //Diese Callback-Methode haben wir in den Zeilen 109 bis 126 überschrieben und lassen durch sie unsere Spielgrafik darstellen.

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backgroundImage.paintIcon(null, g, 0, 0);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        g.setColor(Color.BLUE);
        g.drawString("Coins collected: " + collectedCoins, 22, prefSize.height-5);

        if (isGameOver()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", prefSize.width/2 - 130, prefSize.height/5);
        }

        testMissileOne.paintMe(g);
        testMissileTwo.paintMe(g);
    }
}