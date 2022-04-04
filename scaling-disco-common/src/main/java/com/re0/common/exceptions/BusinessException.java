package com.re0.common.exceptions;


import com.re0.common.result.ResultCode;

/**
 * The type Business exception.
 *
 * @author fangxi created by 2022-04-04
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4868842805385777275L;

    private int code;

    /**
     * Instantiates a new Business exception.
     */
    public BusinessException() {
    }

    /**
     * Instantiates a new Business exception.
     */
    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }


    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     */
    public BusinessException(String message) {
        super(message);
        this.code = ResultCode.FAILURE.getCode();
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param code    the code
     * @param message the message
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }
}
