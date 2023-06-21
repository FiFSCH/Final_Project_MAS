package GUI.View.ListCellRenderers;

import Model.Rent.Rent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RentsListCellRenderer extends JLabel implements ListCellRenderer<Rent> {
    /**
     * Class responsible for adjusting the look of the clients JList in the client details + rents window.
     * setOpaque(true) - makes sure that every pixel of the list gets painted.
     * setText() - changes the value displayed on the list from rent's toString() to title of the rented book.
     * By checking if the field is selected we are able to set the way a selected item displays.
     * In this case if it is selected, it's background and foreground are set to match the default selected item look.
     * If the rent has already started then it's font color is changed to red.
     * */
    @Override
    public Component getListCellRendererComponent(JList<? extends Rent> list, Rent value, int index, boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        String bookTitle = (value.getBookCopy() == null ? null : value.getBookCopy().getBookEdition().getBook().getTitle());
        setText(bookTitle);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        if(value.getDateStart().isBefore(LocalDate.now()) || value.getDateStart().isEqual(LocalDate.now()))
            setForeground(Color.RED);
        return this;
    }
}
