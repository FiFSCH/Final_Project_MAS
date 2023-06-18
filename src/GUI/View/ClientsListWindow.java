package GUI.View;

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
        ClientsList = new JList();
        ClientsList.setCellRenderer(new ClientsListCellRenderer());
    }
}
