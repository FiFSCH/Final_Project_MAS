import Model.Book.*;
import Model.BookEdition.*;
import GUI.Controller.MainController;
import Model.Person.Person;
import Model.Person.PersonType;
import Model.Rent.BookCopy;
import Model.Rent.Rent;
import Utilities.ObjectPlus;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.EnumSet;


public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Reading the counties and their codes which are used in ISBN.
         * */
        ISBN13.writeCodesAndCountriesIntoMap();
        File extents = new File("Extents.txt");
        /**
         * Reading the objects saved to the file.
         * */
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(extents))) {
            ObjectPlus.loadExtents(ois);
        }
        /**
         * Making sure that the UI code is separate, swing specific thread.
         * Done for thread safety purposes.
         * */
        SwingUtilities.invokeLater(() -> {
            MainController mainController;
            try {
                mainController = new MainController();
                mainController.displayGUI();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        /**
         * Because GUI was running on another thread, the saving operation could be executed in some other point in time,
         * and would not reflect changes made through GUI.
         * By using the code below, we make sure that this operation will be executed at the very end of the program's execution.
         * */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Extents.txt"))) {
                ObjectPlus.saveExtents(oos);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        //region Sample data
        //Seeding the data in the most elegant way possible.
//        Person client1 = new Person("Jan", "Nowak", "Jan.Nowak@email.com", LocalDate.of(2001, Month.FEBRUARY, 14), EnumSet.of(PersonType.CLIENT), null, LocalDate.of(2020, Month.APRIL, 12), null);
//        Person client2 = new Person("Maciej", "Kowalski", "Maciej.Kowalski@email.com", LocalDate.of(2000, Month.JULY, 1), EnumSet.of(PersonType.CLIENT), null, LocalDate.of(2021, Month.DECEMBER, 12), null);
//        Person client3 = new Person("Andrzej", "Cegla", "Andrzej.Cegła@email.com", LocalDate.of(1988, Month.NOVEMBER, 11), EnumSet.of(PersonType.CLIENT), null, LocalDate.of(2018, Month.NOVEMBER, 9), null);
//        Person dmitrij = new Person("Dmitrij", "Gluchowski", "Głuchowski@email.com", LocalDate.of(1979, Month.JUNE, 12), EnumSet.of(PersonType.AUTHOR), null, null, null);
//        Person stephen = new Person("Stephen", "King", "King@email.com", LocalDate.of(1947, Month.SEPTEMBER, 21), EnumSet.of(PersonType.AUTHOR), null, null, null);
//        Person george = new Person("George", "Orwell", null, LocalDate.of(1903, Month.JUNE, 25), EnumSet.of(PersonType.AUTHOR), null, null, null);
//        Book metro2033 = new FantasyBook("Metro 2033", TargetAudience.ADULT_BOOK, null, null, EnumSet.of(Warnings.VIOLENCE, Warnings.FEAR, Warnings.BAD_LANGUAGE), FantasyType.SCIENCE);
//        Book TO = new FantasyBook("TO", TargetAudience.ADULT_BOOK, null, null, EnumSet.of(Warnings.VIOLENCE, Warnings.FEAR, Warnings.BAD_LANGUAGE), FantasyType.DARK);
//        Book rok1984 = new FantasyBook("Rok 1984", TargetAudience.ADULT_BOOK, null, null, EnumSet.of(Warnings.BAD_LANGUAGE), FantasyType.SCIENCE);
//        metro2033.addAuthor(dmitrij);
//        TO.addAuthor(stephen);
//        rok1984.addAuthor(george);
//        BookEdition metroEditionPaper = new PaperBook(LocalDate.of(2018, Month.OCTOBER, 17), new ISBN13(83, "6607", "130"), "Polish", true);
//        BookEdition metroEditionEbook = new EBook(LocalDate.of(2018, Month.OCTOBER, 17), new ISBN13(83, "6608", "130"), "Polish", FileFormatEbook.EPUB);
//        BookEdition TOEdition = new PaperBook(LocalDate.of(2019, Month.AUGUST, 1), new ISBN13(83, "8125", "702"), "Polish", true);
//        BookEdition rok1984Edition = new PaperBook(LocalDate.of(1953, Month.JANUARY, 1), new ISBN13(83, "8188", "433"), "Polish", false);
//        TO.addEdition(TOEdition);
//        metro2033.addEdition(metroEditionEbook);
//        metro2033.addEdition(metroEditionPaper);
//        rok1984.addEdition(rok1984Edition);
//        BookCopy metroPaper = BookCopy.createBookCopy(metroEditionPaper, 49.50);
//        BookCopy metroPaper2 = BookCopy.createBookCopy(metroEditionPaper, 49.50);
//        BookCopy metroEbook = BookCopy.createBookCopy(metroEditionEbook, 30);
//        BookCopy TOPaper = BookCopy.createBookCopy(TOEdition, 49.99);
//        BookCopy TOPaper2 = BookCopy.createBookCopy(TOEdition, 49.99);
//        BookCopy rokPaper = BookCopy.createBookCopy(rok1984Edition, 20.99);
//        BookCopy rokPaper2 = BookCopy.createBookCopy(rok1984Edition, 20.99);
//        Rent rent1 = new Rent(LocalDate.of(2023, Month.JUNE, 18), metroPaper, client1);
//        Rent rent2 = new Rent(LocalDate.of(2023, Month.JULY, 15), TOPaper2, client1);
//        Rent rent3 = new Rent(LocalDate.of(2023, Month.AUGUST, 13), TOPaper2, client2);
//        Rent rent4 = new Rent(LocalDate.of(2022, Month.JANUARY, 4), rokPaper, client2);
//        Rent rent5 = new Rent(LocalDate.of(2023, Month.NOVEMBER, 9), rokPaper2, client3);
//        Rent rent6 = new Rent(LocalDate.of(2023, Month.JULY, 25), metroEbook, client3);
        //endregion
    }
}