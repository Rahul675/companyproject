package com.example.companyproject1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class submitcontent {
    private String caption = "OK";
    private String timeStamp = "1643977974796";
    private List<Values> checklist = new ArrayList<>();
    private String did = "0001";
    private String distance = "";
    private String Emp_id = "48398473";
    private String event = "submit";
    private String fakeGpsMessage = "";
    private String geolocation = "28.6112289,77.3612274";
    private String locationId = "";
    private String M_Id = "678";
    private String mappingId = "0";
    private String mobiledatetime = "2022-02-04 06:03:58 PM";

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Values> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<Values> checklist) {
        this.checklist = checklist;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.Emp_id = emp_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFakeGpsMessage() {
        return fakeGpsMessage;
    }

    public void setFakeGpsMessage(String fakeGpsMessage) {
        this.fakeGpsMessage = fakeGpsMessage;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getM_Id() {
        return M_Id;
    }

    public void setM_Id(String m_Id) {
        this.M_Id = m_Id;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getMobiledatetime() {
        return mobiledatetime;
    }

    public void setMobiledatetime(String mobiledatetime) {
        this.mobiledatetime = mobiledatetime;
    }

    @Override
    public String toString() {
        return "[{" +
                "caption='" + caption + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", checklist=" + checklist +
                ", did='" + did + '\'' +
                ", distance='" + distance + '\'' +
                ", Emp_id='" + Emp_id + '\'' +
                ", event='" + event + '\'' +
                ", fakeGpsMessage='" + fakeGpsMessage + '\'' +
                ", geolocation='" + geolocation + '\'' +
                ", locationId='" + locationId + '\'' +
                ", M_Id='" + M_Id + '\'' +
                ", mappingId='" + mappingId + '\'' +
                ", mobiledatetime='" + mobiledatetime + '\'' +
                "}]";
    }

//    public submitcontent(String caption, String timeStamp, String checklist, String did, String distance, String emp_id, String event, String fakeGpsMessage, String geolocation, String locationId, String m_Id, String mappingId, String mobiledatetime) {
//        this.caption = caption;
//        this.timeStamp = timeStamp;
//        this.checklist = checklist;
//        this.did = did;
//        this.distance = distance;
//        this.Emp_id = emp_id;
//        this.event = event;
//        this.fakeGpsMessage = fakeGpsMessage;
//        this.geolocation = geolocation;
//        this.locationId = locationId;
//        this.M_Id = m_Id;
//        this.mappingId = mappingId;
//        this.mobiledatetime = mobiledatetime;
//    }
}

