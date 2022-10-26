package Domain.Exceptions;

public class EmptyStackException extends Exception {
    public EmptyStackException() {};
    public EmptyStackException(String msg) {super(msg);}
}
