package Rent;

import BookEdition.*;
import Utilities.*;
import Person.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BookCopy implements Serializable {
    private double price;
    private BookEdition bookEdition;


    private BookCopy(double price, BookEdition bookEdition) {
        setPrice(price);
        this.bookEdition = bookEdition;
    }

    public static BookCopy createBookCopy(BookEdition bookEdition, double price) throws SharedObjectException {
        if (bookEdition == null)
            throw new IllegalArgumentException("Empty book edition!");
        BookCopy bookCopy = new BookCopy(price, bookEdition);
        if (BookEdition.getGlobalBookCopies().contains(bookCopy))
            throw new SharedObjectException();
        bookEdition.addBookCopy(bookCopy);
        return bookCopy;
    }


    public void deleteBookCopy() {
        if (bookEdition == null)
            return;
        bookEdition.removeBookCopy(this);
        bookEdition = null;
    }

    public BookEdition getBookEdition() {
        return bookEdition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Too low price!");
        this.price = price;
    }


    private Set<Rent> rentedBy = new HashSet<>();

    public Set<Rent> getRentedBy() {
        return Collections.unmodifiableSet(rentedBy);
    }

    public void addRentedBy(Rent client) throws IllegalAccessException {
        if (client == null)
            throw new IllegalArgumentException("Empty object!");
        if (client.getBookCopy() != this)
            throw new IllegalArgumentException("Not related!");
        if (this.rentedBy.contains(client))
            return;
        this.rentedBy.add(client);
        client.addBookCopy(this);
    }

    public void removeRentedBy(Rent client) throws IllegalAccessException {
        if (client == null)
            throw new IllegalArgumentException("Empty object!");
        if (!this.rentedBy.contains(client))
            return;
        this.rentedBy.remove(client);
        client.remove();
    }

    public Rent rentBook(Person client, LocalDate dateStart) throws IllegalAccessException {
        return new Rent(dateStart, this, client);
    }


    @Override
    public String toString() {
        String toString = "BookCopy{" +
                "price=" + price +
                ", bookEdition=" + (bookEdition != null ? bookEdition.getIsbn13() : "") +
                ", rentedBy=";
        for (Rent rent : rentedBy)
            toString += " " + rent.getClient().getFirstName() + " " + rent.getClient().getLastname() + ", ";
        toString += '}';
        return toString;
    }
}
