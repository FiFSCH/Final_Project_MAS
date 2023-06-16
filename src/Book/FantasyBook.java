package Book;

import java.util.EnumSet;

public class FantasyBook extends Book {
    private FantasyType fantasyType;

    public FantasyBook(String title, TargetAudience targetAudience, ChildStage childStage, Boolean isRequiredReading, EnumSet<Warnings> warnings, FantasyType fantasyType) throws IllegalAccessException {
        super(title, targetAudience, childStage, isRequiredReading, warnings);
        this.fantasyType = fantasyType;
    }

    public FantasyType getFantasyType() {
        return fantasyType;
    }

    public void setFantasyType(FantasyType fantasyType) {
        if (fantasyType == null)
            throw new IllegalArgumentException("Empty fantasy type!");
        this.fantasyType = fantasyType;
    }
}
