package com.example.dailyTestServer.Redis.PublishAndSubscribe;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubscribeTest {

    public static void main(String[] args) {
        // 创建发布者和订阅者的实例
        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber();

        // 创建并启动订阅线程
        Thread subscriberThread = new Thread(() -> {
            try {
                Jedis jedis = new Jedis("10.8.0.3",6379);
                jedis.subscribe(subscriber, "channel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        subscriberThread.start();

        // 发布消息
        try (Jedis jedis = new Jedis("10.8.0.3",6379)) {
            publisher.publishMessage(jedis, "channel", "Hello, subscribers!");
            Thread.sleep(1000); // 等待1秒钟，以确保订阅者接收到消息
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 停止订阅线程
        subscriber.unsubscribe();
        try {
            subscriberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    // 发布者
    static class Publisher {
        void publishMessage(Jedis jedis, String channel, String message) {
            jedis.publish(channel, message);
        }
    }

    // 订阅者
    static class Subscriber extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println("Received message: " + message);
        }
    }
}
