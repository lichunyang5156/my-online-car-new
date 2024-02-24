package com.yang.patternDesignServer.template;

public abstract class AbstractDBHandler {

    public final void executeDBOperate(){
        //加载驱动
        this.loadDriver();
        //创建连接
        this.createConnect();
        //构建SQL
        this.buildSQL();
        //执行SQL
        this.executeSQL();
        //关闭连接
        this.closeConnection();
    }

    private void loadDriver(){
        System.out.println("加载驱动……");
    }

    private void createConnect(){
        System.out.println("开始创建数据库连接");
    }

    protected abstract void buildSQL();

    private void executeSQL(){
        System.out.println("执行SQL");
    }

    private void closeConnection(){
        System.out.println("关闭连接");
    }
}
