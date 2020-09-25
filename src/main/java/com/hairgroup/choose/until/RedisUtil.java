package com.hairgroup.choose.until;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created on 2020/9/18
 *
 * @author Yue Wu
 */
public class RedisUtil {

    protected static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    //实现单例
    private RedisUtil() {}

    //Redis服务器IP
    private final static String ADDR = "182.92.208.18";

    //Redis的端口号
    private final static int PORT = 6379;

    //访问密码
    private final static String AUTH = "Wuyue88@";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private final static int MAX_TOTAL = 300;

    //最小空闲连接数, 默认0
    private final static int MIN_IDLE=0;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    //最大空闲连接数, 默认8个
    private final static int MAX_IDLE = 8;

    //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private final static int MAX_WAIT = -1;

    private final static int TIMEOUT = 10000;

    //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
    private final static boolean BLOCK_WHEN_EXHAUSTED = false;

    //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
    private final static String EVICTION_POLICY_CLASSNAME="org.apache.commons.pool2.impl.DefaultEvictionPolicy";

    //是否启用pool的jmx管理功能, 默认true
    private final static boolean JMX_ENABLED=true;

    //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
    private final static String JMX_NAME_PREFIX="pool";

    //是否启用后进先出, 默认true
    private final static boolean LIFO=true;

    //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
    private final static long MIN_EVICTABLE_IDLE_TIME_MILLIS=180000L;

    //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
    private final static long SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS=1800000L;

    //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
    private final static int NUM_TESTS_PER_EVICYION_RUN=3;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    //在获取连接的时候检查有效性, 默认false
    private final static boolean TEST_ON_BORROW = false;

    //在空闲时检查有效性, 默认false
    private final static boolean TEST_WHILEIDLE=false;

    //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
    private final static long TIME_BERWEEN_EVICTION_RUNS_MILLIS=-1;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setBlockWhenExhausted(BLOCK_WHEN_EXHAUSTED);
            config.setEvictionPolicyClassName(EVICTION_POLICY_CLASSNAME);
            config.setJmxEnabled(JMX_ENABLED);
            config.setJmxNamePrefix(JMX_NAME_PREFIX);
            config.setLifo(LIFO);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
            config.setMinIdle(MIN_IDLE);
            config.setNumTestsPerEvictionRun(NUM_TESTS_PER_EVICYION_RUN);
            config.setSoftMinEvictableIdleTimeMillis(SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setTestWhileIdle(TEST_WHILEIDLE);
            config.setTimeBetweenEvictionRunsMillis(TIME_BERWEEN_EVICTION_RUNS_MILLIS);

            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                logger.info("=>获取Jedis");
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            logger.info("=>释放Jedis");
            jedis.close();
        }
    }
}
