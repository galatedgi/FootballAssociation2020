package Exceptions;

public class notFoundException extends Exception{

    public notFoundException() {
    }

    @Override
    public String toString() {
        return "404 Not Found";
    }
}
