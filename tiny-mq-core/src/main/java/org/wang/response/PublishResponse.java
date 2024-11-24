package org.wang.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangjiabao
 */
@Data
@Accessors(chain = true)
public class PublishResponse {

    /**
     * Broker 成功收到之后返回的消息 Id
     */
    private String messageId;
}
