package org.wang.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangjiabao
 */
@Data
@Accessors(chain = true)
public class PublishRequest {

    /**
     * 发布到哪个 Topic 上
     */
    private String topic;

    /**
     * 需要发布到哪个 Partition 上
     */
    private Integer partition;

    /**
     * 实际消息
     */
    private byte[] message;
}
