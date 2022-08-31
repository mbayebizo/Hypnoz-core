package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;

@ApiModel("Fonction Dto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class FonctionsDto extends AbstractDto<Long> {
    @Serial
    static final long serialVersionUID = 6553296754537262814L;
    Long id;
    String code;
    String libCode;
    String libDesc;
    String type;
    String url;
    String iconClass;
    String actions;
    String application;
    String module;
    String active;
    int ordre;
    boolean used;


}