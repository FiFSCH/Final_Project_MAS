package GUI.Controller;

import GUI.View.ClientsListWindow;
import Person.*;
import Utilities.ObjectPlus;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsWindowController {
    private ClientsListWindow clientsListWindow;
    private List<Person> clients;
    private MainController mainController;

    public ClientsWindowController(MainController mainController) throws ClassNotFoundException {
        this.mainController = mainController;
        this.clientsListWindow = new ClientsListWindow();
        clientsListWindow.getClientsList().setModel(new DefaultListModel<Person>());
        displayClients();
        selectHandler();

    }
    private void getClients() throws ClassNotFoundException {
        clients = new ArrayList<>();
        for (ObjectPlus client : ObjectPlus.getExtent(Person.class)){
            if(((Person) client).getPersonTypes().contains(PersonType.CLIENT))
                clients.add((Person) client);
        }
    }
    private void displayClients() throws ClassNotFoundException{
        getClients();
        DefaultListModel<Person> defaultListModel = (DefaultListModel<Person>) clientsListWindow.getClientsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(clients);
    }

    private void selectHandler(){
        clientsListWindow.getClientsList().addListSelectionListener(list ->{
            if(!list.getValueIsAdjusting()){
                Person selectedClient = (Person) clientsListWindow.getClientsList().getSelectedValue();
                mainController.displayClientDetails(selectedClient);
            }
        });
    }
    public ClientsListWindow getListOfClientsWindow() {
        return clientsListWindow;
    }
}
