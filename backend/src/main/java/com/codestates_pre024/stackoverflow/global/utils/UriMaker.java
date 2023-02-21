package com.codestates_pre024.stackoverflow.global.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriMaker {
    private UriMaker() { }
    public static URI getUri(String url, Long resourceId) {
        String path = url+ "/" + resourceId;
        return UriComponentsBuilder.fromPath(path).build().toUri();
    }
}
