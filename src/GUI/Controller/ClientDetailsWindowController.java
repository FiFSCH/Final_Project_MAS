package GUI.Controller;

import Person.Person;
import GUI.View.ClientDetailsWindow;
import Rent.Rent;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDetailsWindowController {
    private ClientDetailsWindow clientDetailsWindow;
    private Set<Rent> rents;

    public ClientDetailsWindowController() {
        this.clientDetailsWindow = new ClientDetailsWindow();
        clientDetailsWindow.getRentsList().setModel(new DefaultListModel<Rent>());
    }

    public ClientDetailsWindow getClientDetailsWindow() {
        return clientDetailsWindow;
    }
    public void clientDetails(Person client){
        getRents(client);
        DefaultListModel<Rent> defaultListModel = (DefaultListModel<Rent>) clientDetailsWindow.getRentsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(rents);
        System.out.println(client);
        clientDetailsWindow.getDobField().setText(client.getDateOfBirth().toString());
        clientDetailsWindow.getNameField().setText(client.getFirstName() + " " + client.getLastname());
        clientDetailsWindow.getMembSinceField().setText(client.getMemberFrom().toString());
    }
    private void getRents(Person client){
        rents = new HashSet<>();
        rents.addAll(client.getRentedBooks());
    }
}
