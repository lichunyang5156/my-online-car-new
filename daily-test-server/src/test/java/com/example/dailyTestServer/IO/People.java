package com.example.dailyTestServer.IO;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private static final long serialVersionUID = 1L;

    private static String  name="LCY";

    private transient String password;

    private Integer age;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                "password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
