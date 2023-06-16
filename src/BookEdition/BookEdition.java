package BookEdition;

import Utilities.ObjectPlus;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class BookEdition extends ObjectPlus implements Serializable {
    //TODO composition
    private LocalDate dateOfPublishing;
    private ISBN13 isbn13;
    private String language;

    public BookEdition(LocalDate dateOfPublishing, ISBN13 isbn13, String language) throws Exception{
        setDateOfPublishing(dateOfPublishing);
        setIsbn13(isbn13);
        setLanguage(language);
    }

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
            if(((BookEdition) edition).isbn13 == isbn13)
                throw new Exception("ISBN13 must be unique!");
        }
            this.isbn13 = isbn13;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if(language == null || language.isBlank())
            throw new IllegalArgumentException("Empty language string!");
        this.language = language;
    }

    @Override
    public String toString() {
        return "BookEdition{" +
                "dateOfPublishing=" + dateOfPublishing +
                ", isbn13=" + isbn13 +
                ", language='" + language + '\'' +
                '}';
    }
}
