package GUI.View.ListCellRenderers;

import Person.Person;

import javax.swing.*;
import java.awt.*;

public class ClientsListCellRenderer extends JLabel implements ListCellRenderer<Person> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        setText(value.getFirstName() + " " + value.getLastname());
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        return this;
    }
}
