package com.tuanmhoang.elk.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
@Getter
@AllArgsConstructor
public enum Action {
    CREATE("_create"),
    SEARCH("_search");

    private String type;
}
