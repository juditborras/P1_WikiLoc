package org.milaifontanals.wikiloc.exception;

/**
 *
 * @author JUDIT
 */

public class WikilocModelException extends RuntimeException{
    
    public WikilocModelException(String message) {
        super(message);
    }

    public WikilocModelException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
