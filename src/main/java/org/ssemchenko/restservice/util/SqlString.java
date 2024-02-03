package org.ssemchenko.restservice.util;

public class SqlString {
    public static final String FIND_BY_ID = "select * from %s where id = ?";
    public static final String DELETE_BY_ID = "delete from %s where id = ?";
}
