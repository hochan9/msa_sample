/*
 * Created by Hochan Son on 2025. 6. 23.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 23.
 */

package com.sample.config;

import com.sample.message.entity.MessageChannel;
import com.sample.publisher.RedisMessagePublisher;
import com.sample.subscriber.MessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * create on 2025. 6. 23. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>Redis 관련. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class RedisConfig {

  /**
   * 구독 관련 Container.
   *
   * @param connectionFactory 커넥션 정보
   * @param subscriber        구독 정보
   * @return 응답.
   */
  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
      MessageSubscriber subscriber) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(subscriber, new ChannelTopic(MessageChannel.DEFAULT.getName()));
    return container;
  }

  /**
   * 레디스 설정 정보.
   *
   * @param connectionFactory 커넥션정보
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }
}
