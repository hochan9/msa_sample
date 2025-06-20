/*
 * Created by Hochan Son on 2025. 6. 20.
 * As part of
 *
 * Copyright (C)  () - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Backend Team <hc.son9@google.com>, 2025. 6. 20.
 */

package com.sample.message.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * create on 2025. 6. 20. create by IntelliJ IDEA. create by IntelliJ IDEA.
 *
 * <p>메시지 channel. </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @since 1.0
 */
@RequiredArgsConstructor
@Getter
public enum MessageChannel {
  DEFAULT("default"),;

  private final String name;

}
