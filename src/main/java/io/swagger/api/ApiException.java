package io.swagger.api;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  Handles API Exception Calls
 *
 */


public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
