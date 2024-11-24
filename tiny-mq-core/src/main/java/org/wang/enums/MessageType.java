package org.wang.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author wangjiabao
 */
@Getter
public enum MessageType {

    /**
     * 发布消息
     */
    PUBLISH((byte)1),
    /**
     * ACK
     */
    ACK((byte)2),


    ;
    private final byte type;

    MessageType(byte type) {
        this.type = type;
    }

    public static MessageType findByType(byte type) {
        return Arrays.stream(MessageType.values()).filter(item -> item.getType() == type).findFirst().orElse(null);
    }
}
