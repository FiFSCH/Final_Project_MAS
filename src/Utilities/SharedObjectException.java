package Utilities;

public class SharedObjectException extends Exception{

    public SharedObjectException() {
        super("This object is already a part of another object!");
    }
}