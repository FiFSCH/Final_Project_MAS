package GUI.Controller;

import GUI.View.BookRentDetailsWindow;

public class BookRentWindowController {
    private MainController mainController;

    private BookRentDetailsWindow bookRentDetailsWindow;


    public BookRentWindowController(MainController mainController) {
        this.mainController = mainController;
        this.bookRentDetailsWindow = new BookRentDetailsWindow();
    }

    public MainController getMainController() {
        return mainController;
    }

    public BookRentDetailsWindow getBookRentDetailsWindow() {
        return bookRentDetailsWindow;
    }
}
