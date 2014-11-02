package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StringRedisTemplate redisTemplate(@Value("${dev.redis.host}") String redisHost)
	{
		System.out.println("!!!!! Redis Host is " + redisHost);
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisHost);
//		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.afterPropertiesSet();
		return new StringRedisTemplate(jedisConnectionFactory);
	}

}
