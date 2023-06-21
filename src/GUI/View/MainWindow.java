package GUI.View;

import javax.swing.*;

public class MainWindow extends JFrame {
    /**
     * Class which is responsible for setting up the main window of the application.
     * Apart from the basic configuration of size, close operation, etc. It also includes setting up a Menu,
     * with a bar and a single option "Display Clients" which is available after pressing the menu button at the bar.
     * LocationRelativeTo is set to null just to make the window display at the middle of the screen.
     * */
    private JMenuItem menuItem;
    public MainWindow(){
        setTitle("Reservation management");
        setSize(400, 420);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenu jmenu = new JMenu("Options");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(jmenu);
        menuItem = new JMenuItem("Display Clients");
        jmenu.add(menuItem);
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
