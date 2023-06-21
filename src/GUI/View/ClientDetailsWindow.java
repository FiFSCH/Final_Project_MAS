package GUI.View;

import javax.swing.*;

import GUI.View.ListCellRenderers.RentsListCellRenderer;
import Model.Rent.Rent;

public class ClientDetailsWindow {
    /**
     * Auto-generated class by IntelliJ's swing UI builder. It includes swing components used in the view and their getters.
     * */
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

    /**
     * Auto-generated method by IntelliJ's swing UI builder.
     * This method becomes available after selecting the "custom create" option in the gui creator.
     * By using this method we are able to specify a custom ListCellRenderer which will affect the look of the list.
     * We have to explicitly create new JList object in order to be able to use the methods of this object (avoid null pointer exception).
     * */
    private void createUIComponents() {
        Rents = new JList<Rent>();
        Rents.setCellRenderer(new RentsListCellRenderer());
    }
}
