package Book;

import Utilities.ObjectPlus;
import Person.*;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public abstract class Book extends ObjectPlus {
    private String title;
    private TargetAudience targetAudience;
    private Set<Person> authors = new HashSet<>();

    //Book for children
    private ChildStage childStage;
    private Boolean isRequiredReading; //Boolean object because this attribute is optional.
    //Book for adults
    private EnumSet<Warnings> warnings;

    public Book(String title, TargetAudience targetAudience, ChildStage childStage, Boolean isRequiredReading, EnumSet<Warnings> warnings) {
        this.title = title;
        this.targetAudience = targetAudience;
        this.childStage = childStage;
        this.isRequiredReading = isRequiredReading;
        this.warnings = warnings;
    }

    //region getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TargetAudience getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(TargetAudience targetAudience) {
        this.targetAudience = targetAudience;
    }

    public ChildStage getChildStage() {
        return childStage;
    }

    public void setChildStage(ChildStage childStage) {
        this.childStage = childStage;
    }

    public Boolean getRequiredReading() {
        return isRequiredReading;
    }

    public void setRequiredReading(Boolean requiredReading) {
        isRequiredReading = requiredReading;
    }

    public EnumSet<Warnings> getWarnings() {
        return warnings;
    }

    public void setWarnings(EnumSet<Warnings> warnings) {
        this.warnings = warnings;
    }
    //endregion

    public void addAuthor(Person author) {
        if (author == null)
            throw new IllegalArgumentException("Empty Author!");
        if(!author.getPersonTypes().contains(PersonType.AUTHOR))
            throw new IllegalArgumentException("This person is not an author");
        if (authors.contains(author))
            return;
        authors.add(author);
        author.addBook(this);
    }

    public void removeAuthor(Person author) {
        if (author == null)
            throw new IllegalArgumentException("Empty author!");
        if(!author.getPersonTypes().contains(PersonType.AUTHOR))
            throw new IllegalArgumentException("This person is not an author");
        if (!authors.contains(author))
            return;
        authors.remove(author);
        author.removeBook(this);
    }

    public Set<Person> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }
}
