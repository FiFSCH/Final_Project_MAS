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
    private MainController mainController;

    private BookRentDetailsWindow bookRentDetailsWindow;

    public BookRentWindowController(MainController mainController) {
        this.mainController = mainController;
        this.bookRentDetailsWindow = new BookRentDetailsWindow();
        this.bookRentDetailsWindow.getAuthorsList().setModel(new DefaultListModel<Person>());
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
}
