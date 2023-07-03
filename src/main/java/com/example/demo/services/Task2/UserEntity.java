package com.example.demo.services.Task2;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true)
public class UserEntity {
    @DataField(pos = 1)
    private int id;
    @DataField(pos = 2)
    private String name;
    @DataField(pos = 3)
    private short age;

    public UserEntity() {
    }

    public UserEntity(int id, String name, short age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }
}
