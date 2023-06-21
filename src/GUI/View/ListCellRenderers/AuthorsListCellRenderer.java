package GUI.View.ListCellRenderers;

import Model.Person.Person;

import javax.swing.*;
import java.awt.*;

public class AuthorsListCellRenderer extends JLabel implements ListCellRenderer<Person> {

    /**
     * Class responsible for adjusting the look of the authors JList in the book and rent details window.
     * setOpaque(true) - makes sure that every pixel of the list gets painted.
     * setText() - changes the value displayed on the list from person's toString() to it's first and last name.
     * setBackground(list.getBackground()) - set's the background of the list to the default list background.
     * setEnabled(false) - disables the selection feature of the list, thus making it read-only.
     * */

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        setText(value.getFirstName() + " " + value.getLastname());
        setBackground(list.getBackground());
        list.setEnabled(false);
        return this;
    }
}