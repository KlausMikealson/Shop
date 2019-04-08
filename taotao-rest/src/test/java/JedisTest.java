import java.util.HashSet;
import java.util.Set;
import com.taotao.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    //测试单机版
    @Test
    public void testJedisSingle()
    {
        Jedis jedis = new Jedis("192.168.223.128", 6379);
        jedis.set("test", "hello jedis");
        String str = jedis.get("test");
        System.out.println(str);
        jedis.close();
    }

    //使用连接池
    @Test
    public void testJedisPool()
    {
        JedisPool jedisPool = new JedisPool("192.168.223.128",6379);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("test");
        System.out.println(str);
        jedis.close();
        jedisPool.close();
    }

    //集群版
    @Test
    public void testJedisCluster()
    {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.223.128", 7001));
        nodes.add(new HostAndPort("192.168.223.128", 7002));
        nodes.add(new HostAndPort("192.168.223.128", 7003));
        nodes.add(new HostAndPort("192.168.223.128", 7004));
        nodes.add(new HostAndPort("192.168.223.128", 7005));
        nodes.add(new HostAndPort("192.168.223.128", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name", "zhanghui");
        String name = jedisCluster.get("name");
        System.out.println(name);

        jedisCluster.close();
    }

    @Test
    public void testJedisClientSpring()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("client1", "1000");
        String string = jedisClient.get("client1");
        System.out.println(string);
    }
}