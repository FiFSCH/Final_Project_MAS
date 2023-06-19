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
    private MainController mainController;

    public ClientDetailsWindowController(MainController mainController) {
        this.mainController = mainController;
        this.clientDetailsWindow = new ClientDetailsWindow();
        this.clientDetailsWindow.getRentsList().setModel(new DefaultListModel<Rent>());
        selectHandler();
    }

    private void selectHandler(){
        clientDetailsWindow.getRentsList().addListSelectionListener(list ->{
            if(!list.getValueIsAdjusting()){
                Rent rent = (Rent) clientDetailsWindow.getRentsList().getSelectedValue();
                mainController.displayBookRentDetails(rent);
            }
        });
    }

    public ClientDetailsWindow getClientDetailsWindow() {
        return clientDetailsWindow;
    }
    public void clientDetails(Person client){
        getRents(client);
        DefaultListModel<Rent> defaultListModel = (DefaultListModel<Rent>) clientDetailsWindow.getRentsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(rents);
        clientDetailsWindow.getDobField().setText(client.getDateOfBirth().toString());
        clientDetailsWindow.getNameField().setText(client.getFirstName() + " " + client.getLastname());
        clientDetailsWindow.getMembSinceField().setText(client.getMemberFrom().toString());
    }
    private void getRents(Person client){
        rents = new HashSet<>();
        rents.addAll(client.getRentedBooks());
    }
}
