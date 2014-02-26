package com.flipkart.automobile.exceptions;

import com.flipkart.automobile.core.Locator;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElementNotFoundException extends RuntimeException {

    private String page;
    private Locator locator;

    public ElementNotFoundException(String page, Locator locator)
    {
        this.locator = locator;
        this.page = page;
    }

    @Override
    public String toString()
    {
        return page + " does not contain the element \"" + locator.key + "\" identified by locator: " + locator.value;
    }
}
