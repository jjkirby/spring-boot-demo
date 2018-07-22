package io.swagger.api;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  Handles Boilerplate Not Founds
 *
 */


public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
