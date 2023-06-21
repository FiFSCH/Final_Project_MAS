package GUI.View.ListCellRenderers;

import Model.Person.Person;

import javax.swing.*;
import java.awt.*;

public class ClientsListCellRenderer extends JLabel implements ListCellRenderer<Person> {

    /**
     * Class responsible for adjusting the look of the clients JList in the clients window.
     * setOpaque(true) - makes sure that every pixel of the list gets painted.
     * setText() - changes the value displayed on the list from person's toString() to it's first and last name.
     * By checking if the field is selected we are able to set the way a selected item displays.
     * In this case if it is selected, it's background and foreground are set to match the default selected item look.
     * */
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
