package Model.BookEdition;

import java.time.LocalDate;

public class EBook extends BookEdition {
    private FileFormatEbook fileFormat;

    public EBook(LocalDate dateOfPublishing, ISBN13 isbn13, String language, FileFormatEbook fileFormat) throws Exception {
        super(dateOfPublishing, isbn13, language);
        setFileFormat(fileFormat);
    }

    public FileFormatEbook getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(FileFormatEbook fileFormat) {
        if (fileFormat == null)
            throw new IllegalArgumentException("Empty file format!");
        this.fileFormat = fileFormat;
    }
}
