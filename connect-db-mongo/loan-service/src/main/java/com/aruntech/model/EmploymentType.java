package com.aruntech.model;

public enum EmploymentType {
    GOVT("govt"), PRIVATE("private"), MNC("mnc"), DOCTOR("doctor"),
    CA("ca"), CS("cs"), ARCHITECT("architect");
    private String name;

    EmploymentType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
