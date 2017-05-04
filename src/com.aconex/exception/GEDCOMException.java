/**
 * Created by meghanaharidev
 */

package com.aconex.exception;

/**
 * Custom Exception for GEDCOM specific errors
 */
public class GEDCOMException extends Exception{
    public GEDCOMException()
    {
        super();
    }

    public GEDCOMException(String message)
    {
        super(message);
    }

    public GEDCOMException(Throwable object)
    {
        super(object);
    }

    public GEDCOMException(String message, Throwable object)
    {
        super(message, object);
    }
}
