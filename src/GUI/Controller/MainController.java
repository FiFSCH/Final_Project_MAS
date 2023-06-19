package GUI.Controller;

import GUI.View.MainWindow;
import Person.Person;
import Rent.Rent;

public class MainController{
    private MainWindow mainWindowView;
    private ClientsWindowController clientsWindowController;
    private ClientDetailsWindowController clientDetailsWindowController;
    private BookRentWindowController bookRentWindowController;

    public MainController() throws ClassNotFoundException {
        this.mainWindowView = new MainWindow();
        menuItemHandler();
        this.clientsWindowController = new ClientsWindowController(this);
        this.clientDetailsWindowController = new ClientDetailsWindowController(this);
        this.bookRentWindowController = new BookRentWindowController(this);
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
        clientDetailsWindowController.clientDetails(client);
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(clientDetailsWindowController.getClientDetailsWindow().getClientRentsPanel());
        mainWindowView.revalidate();
        mainWindowView.getContentPane().repaint();
    }
    public void displayBookRentDetails(Rent rent){
        mainWindowView.getContentPane().removeAll();
        //TODO get details (rent and book)
        mainWindowView.getContentPane().add(bookRentWindowController.getBookRentDetailsWindow().getMainPanel());
        mainWindowView.revalidate();
        mainWindowView.getContentPane().repaint();
    }
}
