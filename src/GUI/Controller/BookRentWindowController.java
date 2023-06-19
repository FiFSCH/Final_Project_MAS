package GUI.Controller;

import Book.Book;
import BookEdition.BookEdition;
import GUI.View.BookRentDetailsWindow;
import Rent.Rent;
import Person.Person;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Set;

public class BookRentWindowController {
    private MainController mainController;

    private BookRentDetailsWindow bookRentDetailsWindow;
    private Set<Person> authors;
    private BookEdition edition;
    private Book book;

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
        if(rent == null)
            return;
        edition = rent.getBookCopy().getBookEdition();
        book = edition.getBook();
        authors = book.getAuthors();
        bookRentDetailsWindow.getRentStartField().setText(rent.getDateStart().toString());
        bookRentDetailsWindow.getRentEndField().setText(rent.getDateEnd().toString());
        bookRentDetailsWindow.getISBNField().setText(edition.getIsbn13().toString());
        bookRentDetailsWindow.getTitleFiled().setText(book.getTitle());
        DefaultListModel<Person> defaultListModel = (DefaultListModel<Person>) bookRentDetailsWindow.getAuthorsList().getModel();
        defaultListModel.removeAllElements();
        defaultListModel.addAll(authors);
        if(rent.getDateStart().isAfter(LocalDate.now()) || rent.getDateStart().isEqual(LocalDate.now()))
            bookRentDetailsWindow.getCancelReservationButton().setEnabled(false);
    }
}
