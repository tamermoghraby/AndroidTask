package com.agiliatech.android.test.ModelResponse;

import java.io.Serializable;
import java.lang.*;

public class Session implements Serializable, Comparable<Session> {

    long start_time_ms = 0;
    long end_time_ms;
    String service_name = "";
    int client_id;
    String client_name = "";
    int client_gender;
    String client_pic = "";
    int session_number;
    int sessions_count;
    String session_place = "";

    public Session(long start_time_ms, long end_time_ms, String service_name,
                   int client_id, String client_name, int client_gender,
                   String client_pic, int session_number, int sessions_count,
                   String session_place) {

        this.start_time_ms = start_time_ms;
        this.end_time_ms = end_time_ms;
        this.service_name = service_name;
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_gender = client_gender;
        this.client_pic = client_pic;
        this.session_number = session_number;
        this.sessions_count = sessions_count;
        this.session_place = session_place;
    }

    public long getStart_time_ms() {
        return start_time_ms;
    }

    public void setStart_time_ms(Long start_time_ms) {
        this.start_time_ms = start_time_ms;
    }

    public long getEnd_time_ms() {
        return end_time_ms;
    }

    public void setEnd_time_ms(Long end_time_ms) {
        this.end_time_ms = end_time_ms;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public int getClient_gender() {
        return client_gender;
    }

    public void setClient_gender(int client_gender) {
        this.client_gender = client_gender;
    }

    public String getClient_pic() {
        return client_pic;
    }

    public void setClient_pic(String client_pic) {
        this.client_pic = client_pic;
    }

    public int getSession_number() {
        return session_number;
    }

    public void setSession_number(int session_number) {
        this.session_number = session_number;
    }

    public int getSessions_count() {
        return sessions_count;
    }

    public void setSessions_count(int session_count) {
        this.sessions_count = session_count;
    }

    public String getSession_place() {
        return session_place;
    }

    public void setSession_place(String session_place) {
        this.session_place = session_place;
    }


    @Override
    public int compareTo(Session session) {
        int compareTime = (int) ((Session) session).getStart_time_ms();

        return (int) (this.getStart_time_ms() - compareTime);
    }



}
