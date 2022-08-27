package net.hypnoz.core.services.builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class FonctionsBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FonctionsDto getDto() {
        FonctionsDto dto = new FonctionsDto();
        dto.setId("1");
        return dto;
    }
}