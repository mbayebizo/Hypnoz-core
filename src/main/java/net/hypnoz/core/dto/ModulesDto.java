package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;

@ApiModel("Module Dto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ModulesDto extends AbstractDto<Long> {
    @Serial
     static final long serialVersionUID = -6226646823763427686L;
     Long id;
     String code;
     String libCode;
     String libDesc;
     String version;
     String versionDate;
     String url;
     String iconClass;
     String active;
     int standart;
     int ordre;
     StructuresDto structures;

    
}