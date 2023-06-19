package GUI.View;

import GUI.View.ListCellRenderers.ClientsListCellRenderer;
import Model.Person.Person;

import javax.swing.*;

public class ClientsListWindow {
    private JPanel ClientsPanel;
    private JList ClientsList;

    public JPanel getClientsPanel() {
        return ClientsPanel;
    }

    public JList getClientsList() {
        return ClientsList;
    }

    private void createUIComponents() {
        ClientsList = new JList<Person>();
        ClientsList.setCellRenderer(new ClientsListCellRenderer());
    }
}
