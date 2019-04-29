package com.xuhh.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
    jedis的测试
 */
public class JedisDemo1 {

    @Test
    public void  demo01(){
        // 1.设置ip地址和端口
        Jedis jedis  = new Jedis("127.0.0.1",6379);
        // 2.保存数据
        jedis.set("name","xuhh");
        //3.获取数据
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();

    }
    @Test
    public void demo02(){
        //获取连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲连接数
        config.setMaxIdle(10);
        //获得连接池
        JedisPool jedisPool = new JedisPool(config,"127.0.0.1",6379);
        //获得核心对象
        Jedis jedis = null;
        try {
            jedis =jedisPool.getResource();
            jedis.set("name","liuyu");
            System.out.println(jedis.get("name"));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedisPool!=null)
            jedisPool.close();
            if(jedis!=null)
                jedis.close();
        }

    }
}
