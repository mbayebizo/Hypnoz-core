package net.hypnoz.core.services.builder;

import net.hypnoz.core.dto.UsersDto;

import java.util.Collections;
import java.util.List;

public class UsersBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static UsersDto getDto() {
        return UsersDto.builder().build();
    }
}