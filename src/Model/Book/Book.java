package Model.Book;

import Model.BookEdition.BookEdition;
import Utilities.ObjectPlus;
import Model.Person.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public abstract class Book extends ObjectPlus implements Serializable {
    /**
     * Abstract superclass for disjoint inheritance of book genre aspect.
     * "Target audience" aspect of the multi-aspect inheritance was flattened and moved to this class.
     */
    private String title;
    /**
     * Discriminator for targetAudience aspect of multi-aspect inheritance.
     */
    private TargetAudience targetAudience;
    private Set<Person> authors = new HashSet<>();

    //Book for children
    private ChildStage childStage;
    private Boolean isRequiredReading; //Boolean object because this attribute is optional.
    //Book for adults
    private EnumSet<Warnings> warnings;

    public Book(String title, TargetAudience targetAudience, ChildStage childStage, Boolean isRequiredReading, EnumSet<Warnings> warnings) throws IllegalAccessException {
        super();
        setTitle(title);
        setTargetAudience(targetAudience);
        //Validation of target audience aspect
        if (targetAudience == TargetAudience.CHILD_BOOK) {
            setChildStage(childStage);
            setRequiredReading(isRequiredReading);
            return;
        }
        if (targetAudience == TargetAudience.ADULT_BOOK)
            setWarnings(warnings);
    }

    //region getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Empty title!");
        this.title = title;
    }

    public TargetAudience getTargetAudience() {
        return targetAudience;
    }

    /**
     * Target audience of the book cannot be changed.
     */
    private void setTargetAudience(TargetAudience targetAudience) {
        if (targetAudience == null)
            throw new IllegalArgumentException("Empty target audience!");
        this.targetAudience = targetAudience;
    }

    public ChildStage getChildStage() throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.CHILD_BOOK)
            throw new IllegalAccessException("This book is not for children!");
        return childStage;
    }

    public void setChildStage(ChildStage childStage) throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.CHILD_BOOK)
            throw new IllegalAccessException("This book is not for children!");
        this.childStage = childStage;
    }

    public Boolean getRequiredReading() throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.CHILD_BOOK)
            throw new IllegalAccessException("This book is not for children!");
        return isRequiredReading;
    }

    public void setRequiredReading(Boolean requiredReading) throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.CHILD_BOOK)
            throw new IllegalAccessException("This book is not for children!");
        if (requiredReading == null)
            throw new IllegalArgumentException("Null value for required reading!");
        //nulls are only allowed during object's creation in case a book is targeted into adults.
        isRequiredReading = requiredReading;
    }

    public Set<Warnings> getWarnings() throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.ADULT_BOOK)
            throw new IllegalAccessException("This book is not for adults!");
        return Collections.unmodifiableSet(warnings);
    }

    public void setWarnings(EnumSet<Warnings> warnings) throws IllegalAccessException {
        if (this.targetAudience != TargetAudience.ADULT_BOOK)
            throw new IllegalAccessException("This book is not for adults!");
        this.warnings = warnings;
    }
    //endregion

    //region association author
    public void addAuthor(Person author) throws IllegalAccessException {
        if (author == null)
            throw new IllegalArgumentException("Empty Author!");
        if (!author.getPersonTypes().contains(PersonType.AUTHOR))
            throw new IllegalArgumentException("This person is not an author");
        if (authors.contains(author))
            return;
        authors.add(author);
        author.addBook(this);
    }

    public void removeAuthor(Person author) throws IllegalAccessException {
        if (author == null)
            throw new IllegalArgumentException("Empty author!");
        if (!author.getPersonTypes().contains(PersonType.AUTHOR))
            throw new IllegalArgumentException("This person is not an author");
        if (!authors.contains(author))
            return;
        authors.remove(author);
        author.removeBook(this);
    }

    public Set<Person> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }
    //endregion

    //region edition
    private Set<BookEdition> editions = new HashSet<>();

    public Set<BookEdition> getEdition() {
        return Collections.unmodifiableSet(editions);
    }

    public void addEdition(BookEdition edition) throws IllegalAccessException {
        if (edition == null)
            throw new IllegalAccessException("Empty edition!");
        if (editions.contains(edition))
            return;
        editions.add(edition);
        edition.setBook(this);
    }

    public void removeEdition(BookEdition edition) throws IllegalAccessException {
        if (edition == null)
            throw new IllegalAccessException("Empty edition!");
        if (!editions.contains(edition))
            return;
        editions.remove(edition);
        edition.removeBook(null);
    }
//endregion

    @Override
    public String toString() {
        String toString = "Model.Book{" +
                "title='" + title + '\'' +
                ", targetAudience=" + targetAudience +
                ", childStage=" + childStage +
                ", isRequiredReading=" + isRequiredReading +
                ", warnings=" + warnings +
                ", authors=";
        try {
            for (Person author : authors)
                toString += " " + author.getFirstName() + " " + author.getLastname() + (author.getPseudonym() == null || author.getPseudonym().isBlank() ? "" : author.getPseudonym());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        toString += " , editions=";
        for (BookEdition edition : editions)
            toString += " " + edition.getIsbn13() + ", ";
        return toString + '}';
    }
}
