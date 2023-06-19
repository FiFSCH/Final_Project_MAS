package GUI.View;

import javax.swing.*;

import GUI.View.ListCellRenderers.RentsListCellRenderer;
import Model.Rent.Rent;

public class ClientDetailsWindow {
    private JPanel clientRents;
    private JList Rents;
    private JPanel ClientDetails;
    private JTextField nameField;
    private JTextField dobField;
    private JTextField membSinceField;

    public JPanel getClientRentsPanel() {
        return clientRents;
    }

    public JList getRentsList() {
        return Rents;
    }

    public JPanel getClientDetailsPanel() {
        return ClientDetails;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getDobField() {
        return dobField;
    }

    public JTextField getMembSinceField() {
        return membSinceField;
    }

    private void createUIComponents() {
        Rents = new JList<Rent>();
        Rents.setCellRenderer(new RentsListCellRenderer());
    }
}
