package com.santander.processor.event;

public class Document {

    private String identifier;
    private String type;

    public Document(String identifier, String type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Document{" +
                "identifier='" + identifier + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
