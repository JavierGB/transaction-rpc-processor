package com.santander.processor.event;

public class RawEvent {

    private String id;
    private String type;

    public RawEvent() {
    }

    public RawEvent(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RawEvent{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
