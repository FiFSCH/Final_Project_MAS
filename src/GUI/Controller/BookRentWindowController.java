package GUI.Controller;

import GUI.View.MainWindow;
import Model.Book.Book;
import Model.BookEdition.BookEdition;
import GUI.View.BookRentDetailsWindow;
import Model.Rent.Rent;
import Model.Person.Person;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Set;

public class BookRentWindowController {
    private MainController mainController;
    private BookRentDetailsWindow bookRentDetailsWindow;
    private Rent rent;

    public BookRentWindowController(MainController mainController) {
        this.mainController = mainController;
        this.bookRentDetailsWindow = new BookRentDetailsWindow();
        this.bookRentDetailsWindow.getAuthorsList().setModel(new DefaultListModel<Person>());
        buttonHandler();
    }

    public MainController getMainController() {
        return mainController;
    }

    public BookRentDetailsWindow getBookRentDetailsWindow() {
        return bookRentDetailsWindow;
    }

    public void getBookAndRentDetails(Rent rent) {
        bookRentDetailsWindow.getCancelReservationButton().setEnabled(true);
        if (rent == null)
            return;
        this.rent = rent;
        BookEdition edition = rent.getBookCopy().getBookEdition();
        Book book = edition.getBook();
        Set<Person> authors = book.getAuthors();
        bookRentDetailsWindow.getRentStartField().setText(rent.getDateStart().toString());
        bookRentDetailsWindow.getRentEndField().setText(rent.getDateEnd().toString());
        bookRentDetailsWindow.getISBNField().setText(edition.getIsbn13().toString());
        bookRentDetailsWindow.getTitleFiled().setText(book.getTitle());
        DefaultListModel<Person> defaultListModel = (DefaultListModel<Person>) bookRentDetailsWindow.getAuthorsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(authors);
        if (rent.getDateStart().isBefore(LocalDate.now()) || rent.getDateStart().isEqual(LocalDate.now()))
            bookRentDetailsWindow.getCancelReservationButton().setEnabled(false);
    }
    private void buttonHandler(){
        bookRentDetailsWindow.getCancelReservationButton().addActionListener(action ->{
            int choice = JOptionPane.showConfirmDialog(null,"Are you sure?", "Cancel reservation",JOptionPane.YES_NO_OPTION);
            if(choice == 0) { //choice == means that the user pressed Yes.
                rent.removeRent();
                JOptionPane.showMessageDialog(null, "Rent successfully cancelled.", "Rent cancelation - success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
}
