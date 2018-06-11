package com.selfapps.dok.network;

public class NetworkConstants {
    private static final String IMAGE = "domek/images/";
    private static final String REST = "services/rest/";
    private static final String POI = "poi/";
    private static final String ARTICLE = "article/";
    private static final String BOOK = "book/";
    private static final String DIRECTORY = "directory/";
    private static final String HISTORY = "history/";
    private static final String PERSON = "person/";
    private static final String ROUTE = "route/";

    public static final String BASE_URL = "http://52.15.243.17:8080/";
    public static final String IMAGE_URL = BASE_URL + IMAGE;
    public static final String JSON_URL = REST +"{fileName}";
    public static final String POI_URL =  REST + POI;
    public static final String PERSON_URL = REST + PERSON;
    public static final String ARTICLE_URL = BASE_URL + REST + ARTICLE;
    public static final String BOOK_URL = BASE_URL + REST + BOOK;
    public static final String DIRECTORY_URL = BASE_URL + REST + DIRECTORY;
    public static final String HISTORY_URL = BASE_URL + REST + HISTORY;
    public static final String ROUTE_URL =  REST + ROUTE;


}
