package com.example.springbootrediscache;


import com.example.springbootrediscache.models.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringBootRedisCacheApplication {

	@Value( "${spring.redis.host}")
	private String redisHost;

	@Value( "${spring.redis.port}")
	private int redisPort;

	@Value( "${spring.redis.password}")
	private String redisPassword;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		//RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);

		// RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-10698.c256.us-east-1-2.ec2.cloud.redislabs.com", 10698);

		// final String redisPassword = "gc8fMWDVhTk6hKY7aWHZsSq4ELht5Kst";


		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
		redisStandaloneConfiguration.setPassword(redisPassword);

		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheApplication.class, args);
	}

}
