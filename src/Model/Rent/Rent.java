package Model.Rent;

import Model.Person.Person;
import Utilities.ObjectPlus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Rent extends ObjectPlus implements Serializable {
    /**
     * Association table between Person (client) and bookCopy.
     * This is a bag association, thus no validation of repetitions.
     * */
    private LocalDate dateStart, dateEnd;
    private BookCopy bookCopy;
    private Person client;
    private static final int baseRentPeriod = 30;

    public Rent(LocalDate dateStart, BookCopy bookCopy, Person client) throws IllegalAccessException {
        super();
        setBookCopy(bookCopy);
        setClient(client);
        setDateStart(dateStart);
        setDateEnd();
        addBookCopy(bookCopy);
        addClient(client);
    }


    public LocalDate getDateStart() {
        return dateStart;
    }

    private void setBookCopy(BookCopy bookCopy) {
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty book!");
        this.bookCopy = bookCopy;
    }

    private void setClient(Person client) {
        if (client == null)
            throw new IllegalArgumentException("Empty client!");
        this.client = client;
    }

    private void setDateStart(LocalDate dateStart) {
        if (dateStart == null)
            throw new IllegalArgumentException("Empty date!");
        if (this.bookCopy.getRentedBy().size() != 0) {
            //If this book is already rented then the start date is automatically assigned based on the end date of the current rent
            LocalDate startDate = this.bookCopy.getRentedBy().stream().max(Comparator.comparing(date -> date.getDateEnd())).get().dateEnd;
            this.dateStart = startDate.plusDays(1); //we cen rent the book one day after it was returned
            return;
        }
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    private void setDateEnd() {
        //I'm not validating the start date here because I invoke this method after setting it in the constructor, thus it is already validated by the setDateStart
        this.dateEnd = this.dateStart.plusDays(baseRentPeriod);
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void addBookCopy(BookCopy bookCopy) throws IllegalAccessException {
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty book!");
        client.addRentedBook(this);
    }

    public Person getClient() {
        return client;
    }

    public void addClient(Person client) throws IllegalAccessException {
        if (client == null)
            throw new IllegalArgumentException("Empty client!");
        bookCopy.addRentedBy(this);
    }

    @Override
    public String toString() {
        return "Model.Rent{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", bookCopy=" + (bookCopy != null ? bookCopy : null) +
                ", client=" + (client != null ? client.getFirstName() + " " + client.getLastname() : null) +
                '}';
    }

    public void removeRent() {
        this.removeFromExtent();
        if (this.client == null)
            return;
        this.client.removeRentedBook(this);
        if (this.bookCopy == null)
            return;
        this.bookCopy.removeRentedBy(this);
        this.client = null;
        this.bookCopy = null;
        //In case the book was returned earlier
        this.dateEnd = LocalDate.now();
    }
}
