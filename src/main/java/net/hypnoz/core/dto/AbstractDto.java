package net.hypnoz.core.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDto<E> implements Serializable {
     @Serial
     static final long serialVersionUID = -5163634117644252198L;
     LocalDateTime createAt;
     LocalDateTime lastModifiedAt;
     String createdBy;
     String lastModifiedBy;


}