package Model.BookEdition;

import java.time.LocalDate;

public class AudioBook extends BookEdition {
    private double length;
    private FileFormatAudio fileFormat;

    public AudioBook(LocalDate dateOfPublishing, ISBN13 isbn13, String language, double length, FileFormatAudio fileFormat) throws Exception {
        super(dateOfPublishing, isbn13, language);
        setLength(length);
        setFileFormat(fileFormat);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length <= 0)
            throw new IllegalArgumentException("Zero or negative length of audiobook!");
        this.length = length;
    }

    public FileFormatAudio getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(FileFormatAudio fileFormat) {
        if (fileFormat == null)
            throw new IllegalArgumentException("Empty file format for audioBook!");
        this.fileFormat = fileFormat;
    }
}
