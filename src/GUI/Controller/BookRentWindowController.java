package GUI.Controller;

import Model.Book.Book;
import Model.BookEdition.BookEdition;
import GUI.View.BookRentDetailsWindow;
import Model.Rent.Rent;
import Model.Person.Person;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Set;

public class BookRentWindowController {

    /**
     * Window reference in order to be able to access the elements of this window through getters.
     */
    private BookRentDetailsWindow bookRentDetailsWindow;
    private Rent rent;

    public BookRentWindowController() {
        this.bookRentDetailsWindow = new BookRentDetailsWindow();
        /*Allows us to easily manage and display objects (instead of plain text) of class person in the list.
         Because of using the DefaultListModel the data structure responsible for list elements display and management will be automatically managed.*/
        this.bookRentDetailsWindow.getAuthorsList().setModel(new DefaultListModel<Person>());
        buttonHandler();
    }

    public BookRentDetailsWindow getBookRentDetailsWindow() {
        return bookRentDetailsWindow;
    }

    /**
     * Method responsible for obtaining the details of the objects and setting the values into appropriate component of the window.
     */
    public void getBookAndRentDetails(Rent rent) {
        if (rent == null) //To avoid possible null passing
            return;
        this.rent = rent; //To Enable other functions to use the rent object passed to this function.
        BookEdition edition = rent.getBookCopy().getBookEdition();
        Book book = edition.getBook();
        Set<Person> authors = book.getAuthors();
        //Setting the read-only values for the view
        bookRentDetailsWindow.getRentStartField().setText(rent.getDateStart().toString());
        bookRentDetailsWindow.getRentEndField().setText(rent.getDateEnd().toString());
        bookRentDetailsWindow.getISBNField().setText(edition.getIsbn13().toString());
        bookRentDetailsWindow.getTitleFiled().setText(book.getTitle());
        //Making sure that the objects displayed on the list are up-to-date and relevant to the object that we previously selected.
        DefaultListModel<Person> defaultListModel = (DefaultListModel<Person>) bookRentDetailsWindow.getAuthorsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(authors);
        //Disabling the "Cancel reservation" button if the rent has already started.
        if (rent.getDateStart().isBefore(LocalDate.now()) || rent.getDateStart().isEqual(LocalDate.now()))
            bookRentDetailsWindow.getCancelReservationButton().setEnabled(false);
        else
            bookRentDetailsWindow.getCancelReservationButton().setEnabled(true);
    }

    /**
     * Event handler for the "Cancel reservation" button.
     * After pressing the button, system displays the JOptionPane allowing the user to confirm the decision.
     * If the user confirms his action the removeRent method of the rent object is invoked which is responsible for removing the association in every related entity.
     * After this operation a dialog box is displayed. It informs the user about successful removal.
     */
    private void buttonHandler() {
        bookRentDetailsWindow.getCancelReservationButton().addActionListener(action -> {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Cancel reservation", JOptionPane.YES_NO_OPTION);
            if (choice == 0) { //choice == 0 - means that the user pressed Yes.
                rent.removeRent();
                JOptionPane.showMessageDialog(null, "Rent successfully cancelled.", "Rent cancellation - success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
}
