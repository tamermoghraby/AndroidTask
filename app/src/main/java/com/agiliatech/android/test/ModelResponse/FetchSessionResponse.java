package com.agiliatech.android.test.ModelResponse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class FetchSessionResponse implements Serializable {

    int total_size;
    int total_pages;
    int current_page;
    int current_page_size;
    List<Session> list;

    public FetchSessionResponse(int total_size, int total_pages, int current_page, int current_page_size, List<Session> list) {
        this.total_size = total_size;
        this.total_pages = total_pages;
        this.current_page = current_page;
        this.current_page_size = current_page_size;
        this.list = list;
    }

    public int getTotal_size() {
        return total_size;
    }

    public void setTotal_size(int total_size) {
        this.total_size = total_size;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getCurrent_page_size() {
        return current_page_size;
    }

    public void setCurrent_page_size(int current_page_size) {
        this.current_page_size = current_page_size;
    }

    public List<Session> getSessionList() {

        return list;
    }

    public void setSessionList(List<Session> list) {
        this.list = list;
    }

}
