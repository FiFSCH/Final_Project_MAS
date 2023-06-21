package GUI.View;

import GUI.View.ListCellRenderers.ClientsListCellRenderer;
import Model.Person.Person;

import javax.swing.*;

public class ClientsListWindow {
    /**
     * Auto-generated class by IntelliJ's swing UI builder. It includes swing components used in the view and their getters.
     * */
    private JPanel ClientsPanel;
    private JList ClientsList;

    public JPanel getClientsPanel() {
        return ClientsPanel;
    }

    public JList getClientsList() {
        return ClientsList;
    }

    /**
     * Auto-generated method by IntelliJ's swing UI builder.
     * This method becomes available after selecting the "custom create" option in the gui creator.
     * By using this method we are able to specify a custom ListCellRenderer which will affect the look of the list.
     * We have to explicitly create new JList object in order to be able to use the methods of this object (avoid null pointer exception).
     * */
    private void createUIComponents() {
        ClientsList = new JList<Person>();
        ClientsList.setCellRenderer(new ClientsListCellRenderer());
    }
}
