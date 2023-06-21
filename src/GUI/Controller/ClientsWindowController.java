package GUI.Controller;

import GUI.View.ClientsListWindow;
import Model.Person.*;
import Utilities.ObjectPlus;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsWindowController {
    /**
     * Window reference in order to be able to access the elements of this window through getters.
     */
    private ClientsListWindow clientsListWindow;
    /**
     * List of all clients in the system (People with a client role assigned to them)
     */
    private List<Person> clients;
    /**
     * Main controller reference in order to be able to use the methods of this object.
     * I decided not to make those methods static to make sure that I'll be invoking them on the very same object every time.
     */
    private MainController mainController;

    public ClientsWindowController(MainController mainController) throws ClassNotFoundException {
        this.mainController = mainController;
        this.clientsListWindow = new ClientsListWindow();
        /*Allows us to easily manage and display objects (instead of plain text) of class Person in the list.
         Because of using the DefaultListModel the data structure responsible for list elements display and management will be automatically managed.*/
        clientsListWindow.getClientsList().setModel(new DefaultListModel<Person>());
        displayClients();
        selectHandler();
    }

    /**
     * Method responsible for finding all objects of class Person with Client role assigned to them.
     */
    private void getClients() throws ClassNotFoundException {
        clients = new ArrayList<>();
        for (ObjectPlus client : ObjectPlus.getExtent(Person.class)) {
            //Casting from class object to person in order to be able to access methods of class person on this object
            if (((Person) client).getPersonTypes().contains(PersonType.CLIENT))
                clients.add((Person) client);
        }
    }

    /**
     * Method responsible for inserting the clients into the list
     */
    private void displayClients() throws ClassNotFoundException {
        getClients();
        //Making sure that the objects displayed on the list are up-to-date and relevant to the object that we previously selected.
        DefaultListModel<Person> defaultListModel = (DefaultListModel<Person>) clientsListWindow.getClientsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(clients);
    }

    /**
     * Event handler for the selected value from the clients list.
     * When the selection process ends (getValueIsAdjusting is responsible for verifying if the selection is a part of events chain),
     * then the currently selected client is retrieved from the list and is passed to the main controller method,
     * which is responsible for displaying the details of the client and list of rents associated with this client.
     */
    private void selectHandler() {
        clientsListWindow.getClientsList().addListSelectionListener(list -> {
            if (!list.getValueIsAdjusting()) {
                Person selectedClient = (Person) clientsListWindow.getClientsList().getSelectedValue();
                mainController.displayClientDetails(selectedClient);
            }
        });
    }

    public ClientsListWindow getListOfClientsWindow() {
        return clientsListWindow;
    }
}
