package Domain.Exceptions;

public class EmptyStackException extends CustomException {
    public EmptyStackException() {};
    public EmptyStackException(String msg) {super(msg);}
}
