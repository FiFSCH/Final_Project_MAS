package GUI.View.ListCellRenderers;

import Model.Rent.Rent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RentsListCellRenderer extends JLabel implements ListCellRenderer<Rent> {
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
