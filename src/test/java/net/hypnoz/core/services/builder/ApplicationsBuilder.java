package net.hypnoz.core.services.builder;

import cn.hutool.db.Page;
import net.hypnoz.core.dto.ApplicationsDto;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

public class ApplicationsBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }


    public static ApplicationsDto getDto() {
        return  ApplicationsDto.builder()
                .id(1L)
                .ordre(1)
                .url("localhost")
                .libDesc("description")
                .module("M0")
                .iconClass("class")
                .build();
    }
}