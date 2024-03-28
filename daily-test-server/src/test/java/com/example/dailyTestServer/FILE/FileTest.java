package com.example.dailyTestServer.FILE;

import java.io.File;
import java.io.IOException;

public class FileTest {


        //这是一个main方法，是程序的入口：
        public static void main(String[] args) throws IOException {
            //将文件封装为一个File类的对象：
            File f = new File("d:\\test.txt");
            File f1 = new File("d:\\test.txt");
            File f2 = new File("d:/test.txt");
            //File.separator属性帮我们获取当前操作系统的路径拼接符号
            //在windows，dos下，系统默认用“\”作为路径分隔符 ，在unix，url中，使用“/”作为路径分隔符。
            File f3 = new File("d:"+File.separator+"test.txt");//建议使用这种

            //常用方法：
            System.out.println("文件是否可读："+f.canRead());
            System.out.println("文件是否可写："+f.canWrite());
            System.out.println("文件的名字："+f.getName());
            System.out.println("上级目录："+f.getParent());
            System.out.println("是否是一个目录："+f.isDirectory());
            System.out.println("是否是一个文件："+f.isFile());
            System.out.println("是否隐藏："+f.isHidden());
            System.out.println("文件的大小："+f.length());
            System.out.println("是否存在："+f.exists());
        /*if(f.exists()){//如果文件存在，将文件删除操作
            f.delete();
        }else{//如果不存在，就创建这个文件
            f.createNewFile();
        }*/
            System.out.println(f == f1);//比较两个对象的地址
            System.out.println(f.equals(f1));//比较两个对象对应的文件的路径

            //跟路径相关的：
            System.out.println("绝对路径："+f.getAbsolutePath());
            System.out.println("相对路径："+f.getPath());
            System.out.println("toString:"+f.toString());

            System.out.println("----------------------");
            File f5 = new File("demo.txt");
            if(!f5.exists()){
                f5.createNewFile();
            }
            //绝对路径指的就是：真实的一个精准的，完整的路径
            System.out.println("绝对路径："+f5.getAbsolutePath());
            //相对路径：有一个参照物，相对这个参照物的路径。
            //在main方法中，相对位置指的就是：D:\IDEA_workspace\TestJavaSE
            //在junit的测试方法中，相对路径指的就是模块位置
            System.out.println("相对路径："+f5.getPath());
            //toString的效果永远是  相对路径
            System.out.println("toString:"+f5.toString());
            File f6 = new File("a/b/c/demo.txt");
            if(!f5.exists()){
                f5.createNewFile();
            }
            System.out.println("绝对路径："+f6.getAbsolutePath());
            System.out.println("相对路径："+f6.getPath());
        }

}
