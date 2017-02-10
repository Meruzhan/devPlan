package exception;

import org.springframework.stereotype.Component;

/**
 * Created by meruzhan.gasparyan on 06-Feb-17.
 */
public class ConcurrentUserNameException extends Exception {

    public ConcurrentUserNameException(String message) {
        super(message);
    }
}
