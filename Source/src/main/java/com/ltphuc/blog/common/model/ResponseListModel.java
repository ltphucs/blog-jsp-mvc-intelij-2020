package com.ltphuc.blog.common.model;

import java.util.List;

public class ResponseListModel<T> extends ResponseModel {
    public List<T> getList() {
        return list;
    }

    public ResponseListModel(List<T> list) {
        this.list = list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private  List<T> list;

    public ResponseListModel(Object object,boolean status, String message, Exception exception, List<T> list) {
        super(object, status, message, exception);
        this.list = list;
    }
    public ResponseListModel(String message, boolean status, Exception exception, List<T> list) {
        super(null, status, message, exception);
        this.list = list;
    }

    public ResponseListModel() {
        super();
    }
}
