package com.example.dailyTestServer.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //获取class
        //方式一
        People people=new People();
        Class aClass = people.getClass();
        System.out.println(aClass.getSimpleName());
        //方式二
        Class<?> people1 = Class.forName("people");
        System.out.println(people1.getSimpleName());

        //方式三
        Class peopleClass = People.class;
        System.out.println(peopleClass.getSimpleName());
        //根据class获取对象
        //方式一
        People peopleObject = (People) people1.newInstance();
        System.out.println(peopleObject.getAge());
        peopleObject.setAge(1);

        //方式二
        Constructor declaredConstructor = peopleClass.getDeclaredConstructor(String.class, Integer.class);
        People object = (People) declaredConstructor.newInstance("张三", 2);
        object.setName("李四");
    }
}
