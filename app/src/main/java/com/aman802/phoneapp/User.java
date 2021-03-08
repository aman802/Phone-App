package com.aman802.phoneapp;

public class User {
    private String id;
    private String name;
    private String displayName;
    private String number;
    private int type = -1;
    private long callDate = -1;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.displayName = name;
    }

    public User(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.displayName = name;
        this.number = number;
    }

    public User(String id, String name, String number, int type, long callDate) {
        this.id = id;
        this.name = name;
        this.displayName = name;
        this.number = number;
        this.type = type;
        this.callDate = callDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public long getCallDate() {
        return callDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCallDate(long callDate) {
        this.callDate = callDate;
    }

    public boolean equals(User user2) {
        if (!number.isEmpty() && !user2.getNumber().isEmpty()) {
            if (!number.equals(user2.getNumber())) return false;
        }

        if (!name.isEmpty() && !user2.getName().isEmpty()) {
            if (!name.equals(user2.getName())) return false;
        }

        if (type != -1 && user2.getType() != -1) {
            if (type != user2.getType()) return false;
        }

        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!id.isEmpty())  stringBuilder.append("ID: ").append(id).append(" ");
        if (!name.isEmpty()) stringBuilder.append("Name: ").append(name).append(" ");
        if (!number.isEmpty()) stringBuilder.append("Number: ").append(number).append(" ");
        if (type != -1) stringBuilder.append("Type: ").append(type).append(" ");

        return stringBuilder.toString();
    }
}
