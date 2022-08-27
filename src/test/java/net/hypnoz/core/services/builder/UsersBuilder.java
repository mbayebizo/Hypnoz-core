package net.hypnoz.core.services.builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class UsersBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static UsersDto getDto() {
        UsersDto dto = new UsersDto();
        dto.setId("1");
        return dto;
    }
}