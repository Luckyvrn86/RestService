package org.ssemchenko.restservice.util;

public class SqlString {
    public static final String FIND_BY_ID = "select * from ? where id = ?";
    public static final String DELETE_BY_ID = "delete from ? where id = ?";
    public static final String FIND_ALL = "select * from ?";
    public static final String SAVE = "insert into ?";
}
