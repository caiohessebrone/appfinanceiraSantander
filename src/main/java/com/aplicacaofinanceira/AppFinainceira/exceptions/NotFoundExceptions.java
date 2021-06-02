package com.aplicacaofinanceira.AppFinainceira.exceptions;

import com.aplicacaofinanceira.AppFinainceira.utils.MessageUtils;

import java.util.function.Supplier;

public class NotFoundExceptions extends RuntimeException {

    public NotFoundExceptions(){
        super(MessageUtils.NOT_RECORDS_FOUND);
    }
}
