package GUI.View;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JMenuItem menuItem;
    public MainWindow(){
        setTitle("Reservation management");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenu jmenu = new JMenu("Options");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(jmenu);
        menuItem = new JMenuItem("Display Clients");
        jmenu.add(menuItem);
        setJMenuBar(menuBar);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
