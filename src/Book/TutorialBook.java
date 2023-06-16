package Book;

import java.util.EnumSet;

public class TutorialBook extends Book{
    private TutorialType tutorialType;

    public TutorialBook(String title, TargetAudience targetAudience, ChildStage childStage, Boolean isRequiredReading, EnumSet<Warnings> warnings, TutorialType tutorialType) throws IllegalAccessException {
        super(title, targetAudience, childStage, isRequiredReading, warnings);
        setTutorialType(tutorialType);
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }

    public void setTutorialType(TutorialType tutorialType) {
        if(tutorialType == null)
            throw new IllegalArgumentException("Empty tutorial type!");
        this.tutorialType = tutorialType;
    }
}
