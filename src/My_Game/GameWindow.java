package My_Game;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    private final GamePanel marioGamePanel;

    public GameWindow () {

        this.marioGamePanel = new GamePanel();

        registerWindowListener();
        createMenu();

        add(marioGamePanel);                        //Mit der Anweisung in Zeile 16 fügen wir die Spielfläche (das GamePanel-Objekt) dem Spielfenster hinzu.
        pack();                                     // Die folgenden Anweisungen lassen das Spielfenster auf dem Bildschirm in der bevorzugten Abmessung anzeigen

        setTitle("Mario");
        setLocation(10,10);
        setResizable(false);

        setVisible(true);
    }


    private void registerWindowListener(){
        addWindowListener(new WindowAdapter() {
            //@Override
            public void WindowClosing(WindowEvent e) {
                System.exit(0);
            }
            //@Override
            public void WindowDeactivated(WindowEvent e){
                marioGamePanel.pauseGame();
            }
            //@Override
            public void WindowActivated(WindowEvent e){
                marioGamePanel.continueGame();
            }
            });
    }

    private void addFileMenuItems(JMenu fileMenu){

        JMenuItem quitItem = new JMenuItem("Pause");
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                marioGamePanel.pauseGame();
            }
        });
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenu prefMenu = new JMenu("Preferences");

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(prefMenu);

        addFileMenuItems(fileMenu);
        addGameMenuItems(gameMenu);
        addPrefMenuItems(prefMenu);
    }

    private void addGameMenuItems(JMenu gameMenu) {
        JMenuItem pauseItem = new JMenuItem("Pause");
        gameMenu.add(pauseItem);
        pauseItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.pauseGame();
            }
        });

        JMenuItem continueItem = new JMenuItem("Continue");
        gameMenu.add(continueItem);
        continueItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.continueGame();
            }
        });

        gameMenu.addSeparator();
        JMenuItem restartItem = new JMenuItem("Restart");
        gameMenu.add(restartItem);
        restartItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.restartGame();
            }
        });
    }

    private void addPrefMenuItems(JMenu prefMenu) {

        JMenu submenu = new JMenu("Choose Background");
        prefMenu.add(submenu);

        JMenuItem menuItem = new JMenuItem("Stage 1");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.setBackgroundImage(0);
                repaint();
            }
        });

        menuItem = new JMenuItem("Stage 2");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.setBackgroundImage(1);
                repaint();
            }
        });

        menuItem = new JMenuItem("Stage 3");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marioGamePanel.setBackgroundImage(2);
                repaint();
            }
        });
    }
}
