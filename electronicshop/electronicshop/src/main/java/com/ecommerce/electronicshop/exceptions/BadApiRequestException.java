package com.ecommerce.electronicshop.exceptions;

public class BadApiRequestException extends RuntimeException
{
    public BadApiRequestException(String message)
    {
      super(message);

    }
    public BadApiRequestException()
    {
        super("Bad request!");
    }





}
