package GUI.Controller;

import Person.Person;
import GUI.View.ClientDetailsWindow;

public class ClientDetailsWindowController {
    private ClientDetailsWindow clientDetailsWindow;

    public ClientDetailsWindowController() {
        this.clientDetailsWindow = new ClientDetailsWindow();
    }

    public ClientDetailsWindow getClientDetailsWindow() {
        return clientDetailsWindow;
    }
    public void clientDetails(Person client){
        //TODO: RENTS LIST
        System.out.println(client);
        clientDetailsWindow.getDobField().setText(client.getDateOfBirth().toString());
        clientDetailsWindow.getNameField().setText(client.getFirstName() + " " + client.getLastname());
        clientDetailsWindow.getMembSinceField().setText(client.getMemberFrom().toString());
    }
}
