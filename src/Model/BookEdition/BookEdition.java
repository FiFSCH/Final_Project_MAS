package Model.BookEdition;

import Utilities.*;
import Model.Rent.BookCopy;
import Model.Book.Book;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BookEdition extends ObjectPlus implements Serializable {
    private static Set<BookCopy> allBookCopies = new HashSet<>();
    private Set<BookCopy> bookCopies = new HashSet<>();
    private LocalDate dateOfPublishing;
    private ISBN13 isbn13;
    private String language;

    public BookEdition(LocalDate dateOfPublishing, ISBN13 isbn13, String language) throws Exception {
        setDateOfPublishing(dateOfPublishing);
        setIsbn13(isbn13);
        setLanguage(language);
    }

    //region getters and setters
    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(LocalDate dateOfPublishing) {
        if (dateOfPublishing == null)
            throw new IllegalArgumentException("Empty date of publishing");
        if (dateOfPublishing.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date from the future!");
        this.dateOfPublishing = dateOfPublishing;
    }

    public ISBN13 getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(ISBN13 isbn13) throws Exception {
        if (isbn13 == null)
            throw new IllegalArgumentException("Empty ISBN!");
        for (ObjectPlus edition : ObjectPlus.getExtent(this.getClass())) {
            if (((BookEdition) edition).isbn13 == isbn13)
                throw new Exception("ISBN13 must be unique!");
        }
        this.isbn13 = isbn13;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (language == null || language.isBlank())
            throw new IllegalArgumentException("Empty language string!");
        this.language = language;
    }
//endregion
    //region composition

    public void addBookCopy(BookCopy bookCopy) throws SharedObjectException {
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty book copy!");
        if (!bookCopies.contains(bookCopy)) {
            if (allBookCopies.contains(bookCopy))
                throw new SharedObjectException();
        }
        bookCopies.add(bookCopy);
        allBookCopies.add(bookCopy);
    }

    public void removeWholeEdition() {
        allBookCopies.removeAll(bookCopies);
        bookCopies.forEach(book -> book.deleteBookCopy());
        bookCopies = new HashSet<>();
        this.removeFromExtent();
    }

    public void removeBookCopy(BookCopy bookCopy) {
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty book!");
        if (!bookCopies.contains(bookCopy) && !allBookCopies.contains(bookCopy))
            return;
        bookCopies.remove(bookCopy);
        allBookCopies.remove(bookCopy);
        bookCopy.deleteBookCopy();
    }

    public Set<BookCopy> getBookCopies() {
        return Collections.unmodifiableSet(bookCopies);
    }

    public static Set<BookCopy> getGlobalBookCopies() {
        return Collections.unmodifiableSet(allBookCopies);
    }

    //endregion
    //region book
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) throws IllegalAccessException {
        if (book == null)
            throw new IllegalArgumentException("Empty book!");
        if (this.book == book)
            return;
        this.book = book;
        book.addEdition(this);
    }

    public void removeBook(Book book) throws IllegalAccessException {
        if (this.book == null)
            return;
        if (this.book != book)
            return;
        this.book = null;
        book.removeEdition(this);
    }


//endregion

    @Override
    public String toString() {
        return "Model.BookEdition{" +
                "bookCopies=" + bookCopies +
                ", dateOfPublishing=" + dateOfPublishing +
                ", isbn13=" + isbn13 +
                ", language='" + language + '\'' +
                ", book=" + book +
                '}';
    }
}
