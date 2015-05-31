package com.rohitkhadgi27.myapplication;

/**
 * Created by rohit on 5/25/15.
 */
public class GS {

    int id;
    String title = null;
    String detail = null;


    public GS() {

    }

    public GS(int id, String title, String detail) {
        super();
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public GS(String title, String detail) {
        super();
        this.title = title;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
