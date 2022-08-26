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
public class ApplicationsDto implements Serializable {
    @Serial
    static final long serialVersionUID=1L;
   String appCde;
   String appLibCode;
   String appLibDesc;
   String appUrl;
   String appIconClass;
   String appModule;
   String appActive;
   int appOrdre;
}
