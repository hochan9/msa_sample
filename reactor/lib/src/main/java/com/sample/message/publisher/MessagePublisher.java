/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.message.publisher;

import com.sample.message.dto.MessageDto;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 발행관련 인터페이스. </p>
 *
 * @author Hochan Son
 * @since 1.0
 */
public interface MessagePublisher {

  /**
   * 메시지 발행.
   *
   * @param message 발행할 dto
   */
  void publish(MessageDto message);
}
