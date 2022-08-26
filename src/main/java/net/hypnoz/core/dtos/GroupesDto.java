package net.hypnoz.core.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupesDto implements Serializable {
    @Serial
    static final long serialVersionUID=1L;
    Long grpId;
    String grpCode;
    String grpLibelle;
    StructuresDto structuredto;
}
