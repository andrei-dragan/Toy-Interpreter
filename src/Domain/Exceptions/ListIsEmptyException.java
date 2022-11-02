package Domain.Exceptions;

public class ListIsEmptyException extends CustomException {
    public ListIsEmptyException() {};
    public ListIsEmptyException(String msg) {super(msg);}
}
