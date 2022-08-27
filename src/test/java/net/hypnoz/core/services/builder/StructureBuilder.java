package net.hypnoz.core.services.builder;

import net.hypnoz.core.dto.StructuresDto;

import java.util.Collections;
import java.util.List;

public class StructureBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static StructuresDto getDto() {
        return  StructuresDto.builder()
                .id(1L)
                .raisonSocial("Hypnoz")
                .sigle("HPS")
                .description("Hypnoz Senegal")
                .bilanSocail("")
                .build();

    }
}
