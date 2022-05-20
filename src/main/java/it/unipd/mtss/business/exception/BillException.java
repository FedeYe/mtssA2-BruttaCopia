////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business.exception;

public class BillException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BillException(String msg) {
        super(msg);
    }
}
