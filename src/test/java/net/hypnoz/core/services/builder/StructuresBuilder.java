package net.hypnoz.core.services.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.hypnoz.core.dto.StructuresDto;

import java.util.Collections;
import java.util.List;

public class StructuresBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static StructuresDto getDto() {
        StructuresDto dto = new StructuresDto();
        dto.setId(Long.valueOf("1"));
        dto.setRaisonSocial("Hypnoz tech");

        return dto;
    }
}