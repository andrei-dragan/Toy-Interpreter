package Domain.Exceptions;

public class StackOverflowException extends CustomException {
    public StackOverflowException() {};
    public StackOverflowException(String msg) {super(msg);}
}
