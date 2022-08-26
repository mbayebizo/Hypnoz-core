package net.hypnoz.core.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class FonctionsDto implements Serializable {
     @Serial
     static final long serialVersionUID=1L;
     String fCode;
     String fLibCode;
     String fLibDesc;
     String FType;
     String fUrl;
     String fIconClass;
     String fActions;
     String fApplication;
     String fModule;
     String fActive;
     int fOrdre;
     boolean FUsed;
}
