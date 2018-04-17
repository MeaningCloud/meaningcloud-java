package com.meaningcloud;

import java.util.ArrayList;
import java.util.List;

public class Response {

    public class Status {
        public String code;
        public String msg;
        public String credits;
        public String remaining_credits;
    }

    public Status status;

    public static <T> List<T> list(List<T> l) {
        if (l == null) return new ArrayList<T>();
        return l;
    }
}
