package GUI.Controller;

import GUI.View.MainWindow;
import Model.Person.Person;
import Model.Rent.Rent;

public class MainController {
    /**
     * Window reference in order to be able to access the elements of this window.
     */
    private MainWindow mainWindowView;
    /**
     * Controllers references in order to be able to access methods from them.
     */
    private ClientsWindowController clientsWindowController;
    private ClientDetailsWindowController clientDetailsWindowController;
    private BookRentWindowController bookRentWindowController;

    public MainController() throws ClassNotFoundException {
        this.mainWindowView = new MainWindow();
        menuItemHandler();
        this.clientsWindowController = new ClientsWindowController(this);
        this.clientDetailsWindowController = new ClientDetailsWindowController(this);
        this.bookRentWindowController = new BookRentWindowController();
    }

    /**
     * Method that is responsible for making the window visible in order to display the GUI.
     */
    public void displayGUI() {
        mainWindowView.setVisible(true);
    }

    /**
     * Event handler for selecting the option from the menu item in the menu bar of the main window.
     * It invokes the "DisplayClients" method, which is responsible for displaying the list of clients.
     */
    public void menuItemHandler() {
        mainWindowView.getMenuItem().addActionListener(action -> displayClients());
    }

    /**
     * Method responsible for displaying list of clients (Objects of class person with Client role assigned to them)
     * 1. Clear selection is done in order to unselect previously selected client from the list,
     *    in order to be able to freely select any client from the list after coming back to it from other view.
     * 2. getContentPane() is responsible for obtaining the content layer from the frame. All objects are displayed on it.
     * 3. getContentPane().removeALl() - removes all objects from the content layer of the frame.
     *     It is done in order to be sure that the view will not hold any elements coming from the previous views.
     * 4. getContentPane().add(clientsWindowController.getListOfClientsWindow().getClientsPanel()
     *    Adds the panel containing the list of clients into the content pane of the main window
     * 5. revalidate() is used to trigger the layout accommodation process in order to adjust the UI to the changes.
     * 6. repaint() is used in order to force refreshing the component on the screen,
     *     to make sure that its visual representation will reflect all the changes made in view.
     */
    public void displayClients() {
        clientsWindowController.getListOfClientsWindow().getClientsList().clearSelection();
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

    public void displayBookRentDetails(Rent rent) {
        bookRentWindowController.getBookAndRentDetails(rent);
        mainWindowView.getContentPane().removeAll();
        mainWindowView.getContentPane().add(bookRentWindowController.getBookRentDetailsWindow().getMainPanel());
        mainWindowView.revalidate();
        mainWindowView.getContentPane().repaint();
    }
}
