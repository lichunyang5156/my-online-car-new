package com.example.dailyTestServer.NIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class OSFileIO {

    static byte[] data = "123456789\n".getBytes();
    static String path =  "/Users/cherry/data/out.txt";

    public static void main(String[] args) throws Exception {
        testRandomAccessFileWrite();
    }

    public static void testRandomAccessFileWrite() throws  Exception {
        //=========================随机写=================
        //创建随机读写文件，参数：文件路径，读写标识
//        File file = new File(path);
//        FileOutputStream out = new FileOutputStream(file);
//        FileChannel channel = out.getChannel();
//        MappedByteBuffer map1 = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);


        /*RandomAccessFile raf = new RandomAccessFile(path, "rw");
        //普通写
        raf.write("hello mashibing\n".getBytes());
        raf.write("hello seanzhou\n".getBytes());
        System.out.println("write------------");
        System.in.read();
        //设置偏移量，即使文件写入从指定位置写入内容
        raf.seek(4);
        //在“hell”位置后开始写入“ooxx”
        raf.write("ooxx".getBytes());

        System.out.println("seek---------");
        System.in.read();*/
        //=======================================堆外映射写===============
        //Channel
//        FileChannel rafchannel = raf.getChannel();
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        FileChannel channel = out.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);

        //mmap  堆外  和文件映射的   byte  not  objtect
        //只有文件的channel有map方法，其他channel没有map方法。因为文件被称为“块设备”，就是可以来回自由寻址，可以读取前一块或者后一块
        //只有文件的才会做内存映射，就是将文件的块和内存的pageCache映射依赖
        //map方法会进行系统调用mmap，然后在堆外生成一块和文件映射的Bytebuffer
//        MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        //IO模型
        map.put("@@@LCY".getBytes());  //不是系统调用，  但是因为这个map是和内存映射的，所以数据会到达内核的pagecache
        //曾经我们是需要out.write()  这样的系统调用，才能让程序的data 进入内核的pagecache
        //换言之曾经必须有用户态内核态切换。
        //但是，mmap的内存映射，依然是内核的pagecache体系所约束的！！！——换言之，丢数据
        //你可以去github上找一些 其他C程序员写的jni扩展库，使用linux内核的Direct IO
        //直接IO是忽略linux的pagecache
        //是把pagecache  交给了程序自己开辟一个字节数组当作pagecache，动用代码逻辑来维护一致性/dirty。。。一系列复杂问题

        System.out.println("map--put--------");
        System.in.read();

//        map.force(); //  flush



//        raf.seek(0);
        //ByteBuffer+文件IO
        ByteBuffer buffer = ByteBuffer.allocate(8192);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        int read = channel.read(buffer);   //buffer.put()
//        int read = rafchannel.read(buffer);   //buffer.put()
        System.out.println(buffer);
        buffer.flip();
        System.out.println(buffer);

        for (int i = 0; i < buffer.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char)buffer.get(i)));
        }


    }

}
