package org.wang.protocal;

import lombok.Getter;

/**
 * @author wangjiabao
 */
@Getter
public enum MessageType {




    ;
    private final byte type;

    MessageType(byte type) {
        this.type = type;
    }
}