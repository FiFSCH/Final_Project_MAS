package GUI.View.ListCellRenderers;

import Model.Person.Person;

import javax.swing.*;
import java.awt.*;

public class AuthorsListCellRenderer extends JLabel implements ListCellRenderer<Person> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        setText(value.getFirstName() + " " + value.getLastname());
        setBackground(list.getBackground());
        list.setEnabled(false);
        return this;
    }
}