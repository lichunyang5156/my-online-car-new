package com.yang.patternDesignServer.CircleDependentTest;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CircularTest {
    // 保存提前暴露的对象，也就是半成品的对象
    private final static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println(getBean(CircularTest1.class).getCircularTest2());
        System.out.println(getBean(CircularTest2.class).getCircularTest1());
    }

    private static <T> T getBean(Class<T> beanClass) throws Exception {
        //1.获取类对象对应的名称
        String beanName = beanClass.getSimpleName().toLowerCase();
        // 2.根据名称去 singletonObjects 中查看是否有半成品的对象
        if (singletonObjects.containsKey(beanName)) {
            return (T) singletonObjects.get(beanName);
        }
        //1、获取实例对象
        Object obj = beanClass.newInstance();
        // 还没有完整的创建完这个对象就把这个对象存储在了 singletonObjects中
        singletonObjects.put(beanName,obj);
        //2、完成属性填充
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        //遍历处理
        for (Field field : declaredFields) {
            field.setAccessible(true);//针对private属性修饰
            //获取成员变量对应的类对象
            Class<?> fieldClass = field.getType();
            // 获取对应的 beanName
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            //给成员变量赋值
            field.set(obj, singletonObjects.containsKey(fieldBeanName) ?
                    singletonObjects.get(fieldBeanName) : getBean(fieldClass));
            field.setAccessible(false);
        }

        return (T) obj;
    }
}
    class CircularTest1 {
        public CircularTest1() {
        }

        private CircularTest2 circularTest2;

        public CircularTest2 getCircularTest2() {
            return circularTest2;
        }

        public void setCircularTest2(CircularTest2 circularTest2) {
            this.circularTest2 = circularTest2;
        }
    }

    class CircularTest2 {
        public CircularTest2() {
        }

        private CircularTest1 circularTest1;

        public CircularTest1 getCircularTest1() {
            return circularTest1;
        }

        public void setCircularTest1(CircularTest1 circularTest1) {
            this.circularTest1 = circularTest1;
        }
    }

