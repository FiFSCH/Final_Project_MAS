import BookEdition.BookEdition;
import BookEdition.ISBN13;
import Person.*;
import Book.*;
import BookEdition.*;
import Rent.*;
import Utilities.ObjectPlus;

import java.time.LocalDate;
import java.time.Month;
import java.util.EnumSet;


public class Main {
    public static void main(String[] args) throws Exception {
        ISBN13.writeCodesAndCountriesIntoMap();
        Person test = new Person("First", "Name", "email@mas.com", LocalDate.of(2000, Month.JANUARY, 1), EnumSet.of(PersonType.AUTHOR, PersonType.CLIENT), null, LocalDate.now(), null);
        Book book = new TutorialBook("Title1", TargetAudience.ADULT_BOOK, null, null, EnumSet.of(Warnings.DRUGS), TutorialType.COOKING);
        book.addAuthor(test);
        System.out.println(test);
        System.out.println(book);
        test.removeBook(book);
        System.out.println(test);
        System.out.println(book);
        //UNIQUE ISBN CHECK
        ISBN13 isbn13 = new ISBN13(83, "8129", "243");
        BookEdition edition = new EBook(LocalDate.now(), isbn13, "PL", FileFormatEbook.EPUB);
        System.out.println(edition);
        //Composition check
        BookCopy bc = BookCopy.createBookCopy(edition, 15);
        System.out.println(bc);
        //System.out.println(edition);
        System.out.println(ObjectPlus.getExtent(edition.getClass()));
        Rent rent = test.rentBook(bc, LocalDate.now());
        System.out.println("===================================================================");
        Rent rent2 = new Rent(LocalDate.now(),bc,test);
        bc.removeRentedBy(rent2);
        System.out.println(rent2);
        test.removeRentedBook(rent);
        System.out.println(test);
        System.out.println(rent);
        System.out.println(bc);
        System.out.println("/////////////////////////////////////////////////");
        book.addEdition(edition);

    }
}