package GUI.View;

import javax.swing.*;

public class BookRentDetailsWindow {
    private JPanel MainPanel;
    private JPanel Rent;
    private JTextField Title;
    private JTextField Author;
    private JTextField ISBN;
    private JTextField rentStart;
    private JTextField rentEnd;
    private JButton cancelReservationButton;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JPanel getRent() {
        return Rent;
    }

    public JTextField getTitle() {
        return Title;
    }

    public JTextField getAuthor() {
        return Author;
    }

    public JTextField getISBN() {
        return ISBN;
    }

    public JTextField getRentStart() {
        return rentStart;
    }

    public JTextField getRentEnd() {
        return rentEnd;
    }

    public JButton getCancelReservationButton() {
        return cancelReservationButton;
    }
}
