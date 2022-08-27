package net.hypnoz.core.services.builder;

import net.hypnoz.core.dto.FonctionsDto;

import java.util.Collections;
import java.util.List;

public class FonctionsBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FonctionsDto getDto() {
        return FonctionsDto.builder().build();

    }
}