package com.codestates_pre024.stackoverflow.global.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.List;

@NoArgsConstructor
@Getter
public class MultiResponse<T> {
    private String code;
    private String message;
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponse(HttpStatus status, String message, @Nullable List<T> data, Page page) {
        this.code = String.valueOf(status.value());
        this.message = message;
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
