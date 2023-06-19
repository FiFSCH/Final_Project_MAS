package GUI.View;

import GUI.View.ListCellRenderers.AuthorsListCellRenderer;
import Model.Person.Person;

import javax.swing.*;

public class BookRentDetailsWindow {
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

    private void createUIComponents() {
        AuthorsList = new JList<Person>();
        AuthorsList.setCellRenderer(new AuthorsListCellRenderer());
    }
}
