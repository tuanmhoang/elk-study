package com.tuanmhoang.elk.constant;

public class AppConstants {

    private AppConstants(){}

    public static final String YYYY_MM_DD = "uuuu-MM-dd";

    public static final String QUERY_SEARCH = "{\"bool\":{\"must\":[{\"match\":{\"title\":\"Java\"}},{\"range\":{\"dateTime\":{\"gte\":\"2022-01-02\"}}}]}}";
}
