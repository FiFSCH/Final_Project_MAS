package BookEdition;

import java.time.LocalDate;

public class PaperBook extends BookEdition{
    private boolean hasHardCover;

    public PaperBook(LocalDate dateOfPublishing, ISBN13 isbn13, String language, boolean hasHardCover) throws Exception {
        super(dateOfPublishing, isbn13, language);
        this.hasHardCover = hasHardCover;
    }

    public boolean isHasHardCover() {
        return hasHardCover;
    }

    public void setHasHardCover(boolean hasHardCover) {
        this.hasHardCover = hasHardCover;
    }
}
