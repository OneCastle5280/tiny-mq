package org.wang.protocal;

import lombok.Builder;
import lombok.Data;

/**
 * @author wangjiabao
 */
@Data
@Builder
public class MessageHeader {

    public static final int HEADER_LEN = 12;
    public static final int REQUEST_ID_LEN = 32;

    /**
     * 魔数
     */
    private char magic;
    /**
     * 版本
     */
    private byte version;
    /**
     * 消息类型 {@link MessageType}
     */
    private byte type;
    /**
     * 消息 id
     */
    private String requestId;
    /**
     * 消息长度
     */
    private int msgLen;

}
