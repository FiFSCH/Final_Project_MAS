package Book;

import java.util.EnumSet;

public class ScientificBook extends Book {
    private ScientificField scientificField;

    public ScientificBook(String title, TargetAudience targetAudience, ChildStage childStage, Boolean isRequiredReading, EnumSet<Warnings> warnings, ScientificField scientificField) throws IllegalAccessException {
        super(title, targetAudience, childStage, isRequiredReading, warnings);
        setScientificField(scientificField);
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        if (scientificField == null)
            throw new IllegalArgumentException("Empty scientific field!");
        this.scientificField = scientificField;
    }
}
