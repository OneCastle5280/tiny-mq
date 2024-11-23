package org.wang.protocal;


import lombok.Builder;
import lombok.Data;

/**
 * @author wangjiabao
 */
@Data
@Builder
public class MessageProtocal<T> {

    /**
     * 消息头
     */
    private MessageHeader header;
    /**
     * 消息体
     */
    private T body;
}
