package net.hypnoz.core.utils.RequesteResponsheandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum RequestErrorEnum {
    ERROR_SIGLE(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(),"sigle.error" ,"sigle.error.description" ),
    ERROR_RAISON_SOCIAL(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), "raison.social.error", "raison.social.error.description");
    HttpStatus httpStatus;
    String errorCode;
    String errorMessage;
    String errorDescription;

    RequestErrorEnum(HttpStatus httpStatus, String errorCode, String errorMessage, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public static  RequestErrorEnum loadEnumByErrorCode(String code){
        return Arrays.stream(RequestErrorEnum.values()).filter(e->e.getErrorCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}
