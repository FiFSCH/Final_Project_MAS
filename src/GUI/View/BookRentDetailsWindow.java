package GUI.View;

import GUI.View.ListCellRenderers.AuthorsListCellRenderer;
import Model.Person.Person;

import javax.swing.*;

public class BookRentDetailsWindow {
    /**
     * Auto-generated class by IntelliJ's swing UI builder. It includes swing components used in the view and their getters.
     * */
    private JPanel MainPanel;
    private JPanel Rent;
    private JTextField TitleFiled;
    private JTextField ISBNField;
    private JTextField rentStartField;
    private JTextField rentEndField;
    private JButton cancelReservationButton;
    private JList AuthorsList;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JPanel getRent() {
        return Rent;
    }

    public JTextField getTitleFiled() {
        return TitleFiled;
    }

    public JTextField getISBNField() {
        return ISBNField;
    }

    public JTextField getRentStartField() {
        return rentStartField;
    }

    public JTextField getRentEndField() {
        return rentEndField;
    }

    public JButton getCancelReservationButton() {
        return cancelReservationButton;
    }

    public JList getAuthorsList() {
        return AuthorsList;
    }
    /**
     * Auto-generated method by IntelliJ's swing UI builder.
     * This method becomes available after selecting the "custom create" option in the gui creator.
     * By using this method we are able to specify a custom ListCellRenderer which will affect the look of the list.
     * We have to explicitly create new JList object in order to be able to use the methods of this object (avoid null pointer exception).
     * */
    private void createUIComponents() {
        AuthorsList = new JList<Person>();
        AuthorsList.setCellRenderer(new AuthorsListCellRenderer());
    }
}
