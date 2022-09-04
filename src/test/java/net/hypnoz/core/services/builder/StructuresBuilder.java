package net.hypnoz.core.services.builder;

import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.dto.pojo.StructureInitPojo;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

public class StructuresBuilder {
    public static List<String> getIds(){
        return Collections.singletonList("1");
    }

    public static StructuresDto getDto(){
        return StructuresDto.builder()
                .id(1L)
                .build();
    }
    public static ResponseEntity<StructuresDto> getStructureInitPojo(){
        return ResponseEntity.ok(StructuresDto.builder()
                .sigle("libeleTest")
                .raisonSocial("TesObject Rasocc hjskdqkddq")
                .build());
    }

    public static StructureInitPojo getStrPojo() {
        return StructureInitPojo.builder()
                .sigle("libeleTest")
                .raisonSocial("TesObject Rasocc hjskdqkddq")
                .build();
    }
}
