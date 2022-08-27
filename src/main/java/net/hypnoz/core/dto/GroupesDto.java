package net.hypnoz.core.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.hypnoz.core.models.Structures;

import java.io.Serial;

@ApiModel("Groupe Dto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GroupesDto extends AbstractDto<Long> {
    @Serial
    static final long serialVersionUID = 3001109767306041516L;
    Long id;
    String code;
    String libelle;
    Structures structures;
}