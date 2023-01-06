package com.example.companyproject1;

import android.content.Context;

import java.util.ArrayList;

public class component_class {

    Context context;
    ArrayList<String> checkpointid = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> value = new ArrayList<>();
    ArrayList<String> typeId = new ArrayList<>();
    ArrayList<String> mandatory  = new ArrayList<>();
    ArrayList<String> editable = new ArrayList<>();
    ArrayList<String> correct = new ArrayList<>();
    ArrayList<String> size = new ArrayList<>();
    ArrayList<String> Score = new ArrayList<>();
    ArrayList<String> language = new ArrayList<>();
    ArrayList<String> Active = new ArrayList<>();
    ArrayList<String> Is_Dept = new ArrayList<>();
    ArrayList<String> Logic = new ArrayList<>();
    ArrayList<String> isGeofence = new ArrayList<>();
    ArrayList<String> action = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();

    public component_class(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<String> getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(ArrayList<String> checkpointid) {
        this.checkpointid = checkpointid;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }

    public ArrayList<String> getTypeId() {
        return typeId;
    }

    public void setTypeId(ArrayList<String> typeId) {
        this.typeId = typeId;
    }

    public ArrayList<String> getMandatory() {
        return mandatory;
    }

    public void setMandatory(ArrayList<String> mandatory) {
        this.mandatory = mandatory;
    }

    public ArrayList<String> getEditable() {
        return editable;
    }

    public void setEditable(ArrayList<String> editable) {
        this.editable = editable;
    }

    public ArrayList<String> getCorrect() {
        return correct;
    }

    public void setCorrect(ArrayList<String> correct) {
        this.correct = correct;
    }

    public ArrayList<String> getSize() {
        return size;
    }

    public void setSize(ArrayList<String> size) {
        this.size = size;
    }

    public ArrayList<String> getScore() {
        return Score;
    }

    public void setScore(ArrayList<String> score) {
        Score = score;
    }

    public ArrayList<String> getLanguage() {
        return language;
    }

    public void setLanguage(ArrayList<String> language) {
        this.language = language;
    }

    public ArrayList<String> getActive() {
        return Active;
    }

    public void setActive(ArrayList<String> active) {
        Active = active;
    }

    public ArrayList<String> getIs_Dept() {
        return Is_Dept;
    }

    public void setIs_Dept(ArrayList<String> is_Dept) {
        Is_Dept = is_Dept;
    }

    public ArrayList<String> getLogic() {
        return Logic;
    }

    public void setLogic(ArrayList<String> logic) {
        Logic = logic;
    }

    public ArrayList<String> getIsGeofence() {
        return isGeofence;
    }

    public void setIsGeofence(ArrayList<String> isGeofence) {
        this.isGeofence = isGeofence;
    }

    public ArrayList<String> getAction() {
        return action;
    }

    public void setAction(ArrayList<String> action) {
        this.action = action;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }
}
