package net.hypnoz.core.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ModulesDto implements Serializable {
     @Serial
     static final long serialVersionUID=1L;
     String mCode;
     String mLibCode;
     String mLibDesc;
     String mVersion;
     String mVersionDate;
     String mUrl;
     String mIconClass;
     String mActive;
     int mOrdre;
     StructuresDto structureDto;
}
