package com.tuanmhoang.elk.constant;

public class AppConstants {

    private AppConstants(){}

    public static final String ADVANCED_QUERY ="{\n"
        + "  \"query\": {\n"
        + "    \"bool\": {\n"
        + "      \"must\": [\n"
        + "        {\n"
        + "          \"match\": {\n"
        + "            \"title\": \"%s\"\n"
        + "          }\n"
        + "        },\n"
        + "        {\n"
        + "          \"range\": {\n"
        + "            \"dateTime\": {\n"
        + "              \"gte\": \"%s\"\n"
        + "            }\n"
        + "          }\n"
        + "        }\n"
        + "      ]\n"
        + "    }\n"
        + "  }\n"
        + "}";
}
