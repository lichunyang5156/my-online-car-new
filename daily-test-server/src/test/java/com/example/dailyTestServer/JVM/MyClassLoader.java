package com.example.dailyTestServer.JVM;

public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        super()
        return super.findClass(name);
    }
}
