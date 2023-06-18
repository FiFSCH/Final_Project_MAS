package GUI.Controller;

import GUI.View.MainWindow;

import javax.swing.*;

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
           //TODO: make it go back to the customers list if we are in some other window
           displayClients();
        });
    }

    public void displayClients(){
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(clientsWindowController.getListOfClientsWindow().getClientsPanel());
        mainWindowView.revalidate();
    }
    public void displayClientDetails(){
        mainWindowView.getContentPane().removeAll();
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
