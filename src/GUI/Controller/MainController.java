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
           //TODO: make it go back to the customers list if we are in some other window (or other strange behaviour)
           displayClients();
        });
    }

    public void displayClients(){
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(clientsWindowController.getListOfClientsWindow().getClientsPanel());
        mainWindowView.revalidate();
    }
    public void displayClientDetails(Person client) {
        mainWindowView.getContentPane().removeAll();
        clientDetailsWindowController.clientDetails(client);
        mainWindowView.getContentPane().add(clientDetailsWindowController.getClientDetailsWindow().getClientRentsPanel());
        mainWindowView.revalidate();
    }
    //TODO: REMOVE LATER IF NEEDED
//    public void displaysManager(JPanel display) {
//        mainWindowView.getContentPane().removeAll();
//        mainWindowView.getContentPane().add(display);
//        mainWindowView.revalidate();
//    }
}
