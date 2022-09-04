package net.hypnoz.core.utils.exceptions;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.utils.RequesteResponsheandler.RequestErrorEnum;

import java.io.Serial;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseException extends RuntimeException{
    @Serial
    static final long serialVersionUID= -3515390014108856326L;

    public ResponseException(RequestErrorEnum requestErrorEnum) {
        super(requestErrorEnum.getErrorDescription());
    }

    public ResponseException(RequestErrorEnum requestErrorEnum, Throwable e){
        super(requestErrorEnum.getErrorDescription(),e);
    }
    public ResponseException(String error){
        super(error);
    }
}
