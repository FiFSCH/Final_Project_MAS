package Person;

import Utilities.ObjectPlus;
import Book.Book;
import Rent.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Person extends ObjectPlus implements Serializable {

    private String firstName, lastname, email;
    private LocalDate dateOfBirth;
    private EnumSet<PersonType> personTypes;

    //author
    private Set<Book> books = new HashSet<Book>();
    private String pseudonym;

    //client
    private static final int minimalRequiredAge = 14;
    private LocalDate memberFrom, memberTo;

    public Person(String firstName, String lastname, String email, LocalDate dateOfBirth, EnumSet<PersonType> personTypes,
                  String pseudonym, LocalDate memberFrom, LocalDate memberTo) throws IllegalAccessException {
        super();
        setFirstName(firstName);
        setLastname(lastname);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setPersonTypes(personTypes);

        if (personTypes.contains(PersonType.AUTHOR)) {
            books = new HashSet<>();
            setPseudonym(pseudonym);
        }

        if (personTypes.contains(PersonType.CLIENT)) {
            validateMinimalAge();
            setMemberFrom(memberFrom);
            setMemberTo(memberTo);
        }
    }

    public Set<PersonType> getPersonTypes() {
        return Collections.unmodifiableSet(personTypes);
    }

    private void setPersonTypes(EnumSet<PersonType> personTypes) {
        if (personTypes == null || personTypes.isEmpty())
            throw new IllegalArgumentException("Null person role!");
        this.personTypes = personTypes;
    }

    //region dynamic
    public void changeRoleToAuthor(String pseudonym) throws IllegalAccessException {
        if (this.personTypes.contains(PersonType.AUTHOR))
            throw new RuntimeException("This person is already an author!");
        this.personTypes.add(PersonType.AUTHOR);
        this.personTypes.remove(PersonType.CLIENT);
        books = new HashSet<>();
        this.memberFrom = null;
        this.memberTo = null;
        setPseudonym(pseudonym);
    }

    public void changeRoleToClient(LocalDate memberSince, LocalDate memberTo) throws IllegalAccessException {
        if (this.personTypes.contains(PersonType.CLIENT))
            throw new RuntimeException("This person is already a client!");
        List<Book> tmp = new ArrayList<>(books); //Temporary list created in order to avoid concurrent modification exception.
        //By copying the array in this way, the references are also passed to this array (objects are not cloned).
        //Every operation performed on objects of this array is reflected on the original object and original list.
        tmp.forEach(book -> {
            try {
                book.removeAuthor(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        this.personTypes.add(PersonType.CLIENT);
        this.personTypes.remove(PersonType.AUTHOR);
        this.pseudonym = null;
        this.books = null;
        setMemberFrom(memberSince);
        setMemberTo(memberTo);
    }
    //endregion

    //region Person getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("Empty name!");
        if (!firstName.matches("[A-Za-z]+"))
            throw new IllegalArgumentException("Name can't contain numbers or special characters!");
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.isBlank())
            throw new IllegalArgumentException("Empty lastname!");
        if (!lastname.matches("[A-Za-z]+"))
            throw new IllegalArgumentException("Lastname can't contain numbers or special characters!");
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null){
            this.email = null;
            return;
        }
        if (email.isBlank())
            throw new IllegalArgumentException("Empty email!");
        if (!email.matches("\\S+@\\S+\\.\\S+")) //Match any non-whitespace character once or more times, @, any non-whitespace character once or more times, . (dot), any non-whitespace character once or more times
            throw new IllegalArgumentException("Wrong email format!");
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null)
            throw new IllegalArgumentException("Empty date!");
        if (dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date from the future!");
        this.dateOfBirth = dateOfBirth;
    }
    //endregion

    //region author


    public String getPseudonym() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.AUTHOR))
            throw new IllegalAccessException("This person is not an author!");
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.AUTHOR))
            throw new IllegalAccessException("This person is not an author!");
        if (pseudonym == null) {
            this.pseudonym = null;
            return;
        }
        if (pseudonym.isBlank())
            throw new IllegalArgumentException("Empty pseudonym");
        this.pseudonym = pseudonym;
    }

    public Set<Book> getBooks() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.AUTHOR))
            throw new IllegalAccessException("This person is not an author!");
        return Collections.unmodifiableSet(this.books);
    }

    public void addBook(Book book) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.AUTHOR))
            throw new IllegalAccessException("This person is not an author!");
        if (book == null)
            throw new IllegalArgumentException("Empty book!");
        if (books.contains(book))
            return;
        books.add(book);
        book.addAuthor(this);
    }

    public void removeBook(Book book) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.AUTHOR))
            throw new IllegalAccessException("This person is not an author!");
        if (book == null)
            throw new IllegalArgumentException("Empty book!");
        if (!books.contains(book))
            return;
        books.remove(book);
        book.removeAuthor(this);
    }
    //endregion

    //region client
    private Set<Rent> rentedBooks = new HashSet<>();

    public Set<Rent> getRentedBooks() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        return Collections.unmodifiableSet(rentedBooks);
    }

    public void addRentedBook(Rent bookCopy) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty relation!");
        if (bookCopy.getClient() != this)
            throw new IllegalArgumentException("Not related!");
        if (rentedBooks.contains(bookCopy))
            return;
        rentedBooks.add(bookCopy);
        bookCopy.addClient(this);
    }

    public void removeRentedBook(Rent bookCopy) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        if (bookCopy == null)
            throw new IllegalArgumentException("Empty relation!");
        if (!rentedBooks.contains(bookCopy))
            return;
        this.rentedBooks.remove(bookCopy);
        bookCopy.remove();
    }

    public Rent rentBook(BookCopy book, LocalDate dateStart) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        return new Rent(dateStart, book, this);
    }

    public LocalDate getMemberTo() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        return memberTo;
    }


    public void setMemberTo(LocalDate memberTo) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        if (memberTo == null) {
            this.memberTo = null;
            return;
        }
        if (memberTo.isBefore(memberFrom))
            throw new IllegalArgumentException("Membership ending must be after membership beginning!");
        this.memberTo = memberTo;
    }

    public int getAge() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
    }

    public LocalDate getMemberFrom() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        return memberFrom;
    }

    public void setMemberFrom(LocalDate memberFrom) throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        if (memberFrom == null)
            throw new IllegalArgumentException("Empty date!");
        if (memberFrom.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date from the future!");
        this.memberFrom = memberFrom;
    }

    public void validateMinimalAge() throws IllegalAccessException {
        if (!personTypes.contains(PersonType.CLIENT))
            throw new IllegalAccessException("This person is not a client!");
        if (this.getAge() < Person.minimalRequiredAge)
            throw new IllegalArgumentException("This person is too young to become a client!");
    }
    //endregion


    @Override
    public String toString() {
        String toString =  "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", personTypes=" + personTypes +
                ", pseudonym='" + pseudonym + '\'' +
                ", memberFrom=" + memberFrom +
                ", memberTo=" + memberTo +
                ", rentedBooks=";
        for(Rent rent : rentedBooks)
            toString += " " + rent.getBookCopy().getBookEdition().getIsbn13() + ", ";
        toString += "authorOf=";
        for(Book book : books)
            toString += " " + book.getTitle() + ", ";
        toString += '}';
        return toString;
    }
}
