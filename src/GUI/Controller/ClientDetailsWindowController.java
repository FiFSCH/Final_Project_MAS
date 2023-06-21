package GUI.Controller;

import Model.Person.Person;
import GUI.View.ClientDetailsWindow;
import Model.Rent.Rent;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDetailsWindowController {
    /**
     * Window reference in order to be able to access the elements of this window through getters.
     */
    private ClientDetailsWindow clientDetailsWindow;
    /**
     * Rents of currently selected client.
     */
    private Set<Rent> rents;
    /**
     * Main controller reference in order to be able to use the methods of this object.
     * I decided not to make those methods static to make sure that I'll be invoking them on the very same object every time.
     */
    private MainController mainController;

    public ClientDetailsWindowController(MainController mainController) {
        this.mainController = mainController;
        this.clientDetailsWindow = new ClientDetailsWindow();
        /*Allows us to easily manage and display objects (instead of plain text) of class Rent in the list.
         Because of using the DefaultListModel the data structure responsible for list elements display and management will be automatically managed.*/
        this.clientDetailsWindow.getRentsList().setModel(new DefaultListModel<Rent>());
        selectHandler();
    }

    /**
     * Event handler for the selected value from the rents list.
     * When the selection process ends (getValueIsAdjusting is responsible for verifying if the selection is a part of events chain),
     * then the currently selected rent is retrieved from the list and is passed to the main controller method,
     * which is responsible for displaying the details of the rent and the rented book.
     */
    private void selectHandler() {
        clientDetailsWindow.getRentsList().addListSelectionListener(list -> {
            if (!list.getValueIsAdjusting()) {
                Rent rent = (Rent) clientDetailsWindow.getRentsList().getSelectedValue();
                mainController.displayBookRentDetails(rent);
            }
        });
    }

    public ClientDetailsWindow getClientDetailsWindow() {
        return clientDetailsWindow;
    }

    public void clientDetails(Person client) {
        if (client == null) //To avoid possible null passing
            return;
        getRents(client);
        //Making sure that the objects displayed on the list are up-to-date and relevant to the object that we previously selected.
        DefaultListModel<Rent> defaultListModel = (DefaultListModel<Rent>) clientDetailsWindow.getRentsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(rents);
        //Setting the read-only values for the view
        clientDetailsWindow.getDobField().setText(client.getDateOfBirth().toString());
        clientDetailsWindow.getNameField().setText(client.getFirstName() + " " + client.getLastname());
        clientDetailsWindow.getMembSinceField().setText(client.getMemberFrom().toString());
    }

    /**
     * Method responsible for getting all the rents,
     * that are currently associated with the given client.
     */
    private void getRents(Person client) {
        rents = new HashSet<>();
        rents.addAll(client.getRentedBooks());
    }
}
