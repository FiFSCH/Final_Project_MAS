package GUI.Controller;

import GUI.View.MainWindow;
import Person.Person;


public class MainController{
    private MainWindow mainWindowView;
    private ClientsWindowController clientsWindowController;
    private ClientDetailsWindowController clientDetailsWindowController = new ClientDetailsWindowController();

    public MainController() throws ClassNotFoundException {
        this.mainWindowView = new MainWindow();
        menuItemHandler();
        clientsWindowController = new ClientsWindowController(this);
    }

    public void displayGUI() {
        mainWindowView.setVisible(true);
    }

    public void menuItemHandler() {
        mainWindowView.getMenuItem().addActionListener(action -> {
           displayClients();
        });
    }

    public void displayClients(){
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(clientsWindowController.getListOfClientsWindow().getClientsPanel());
        mainWindowView.revalidate();
        mainWindowView.getContentPane().repaint();
    }
    public void displayClientDetails(Person client) {
        mainWindowView.getContentPane().removeAll();
        clientDetailsWindowController.clientDetails(client);
        mainWindowView.getContentPane().add(clientDetailsWindowController.getClientDetailsWindow().getClientRentsPanel());
        mainWindowView.revalidate();
        mainWindowView.getContentPane().repaint();
    }
}
