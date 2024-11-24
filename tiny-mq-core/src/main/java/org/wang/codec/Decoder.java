package org.wang.codec;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.wang.enums.MessageType;
import org.wang.protocal.MessageHeader;
import org.wang.request.PublishRequest;
import org.wang.serialization.Serialization;
import org.wang.serialization.SerializationFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.wang.protocal.MessageHeader.*;

/**
 * @author wangjiabao
 */
public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < HEADER_LEN) {
            return;
        }

        // mark readIndex，如果发现收到的消息体长度小于请求头里的数据长度的时候，就 reset readIndex
        in.markReaderIndex();

        char magic = in.readChar();
        byte version = in.readByte();
        byte type = in.readByte();
        CharSequence requestId = in.readCharSequence(REQUEST_ID_LEN, StandardCharsets.UTF_8);
        int msgLen = in.readInt();

        if (in.readableBytes() < msgLen) {
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[msgLen];
        in.readBytes(data);

        MessageHeader header = builder()
                .magic(magic)
                .version(version)
                .type(type)
                .requestId((String) requestId)
                .msgLen(msgLen)
                .build();

        // todo
        Serialization serialization = SerializationFactory.getSerialization();
        MessageType msgType = MessageType.findByType(type);
        switch (msgType) {
            case PUBLISH:
                // 生产消息
                PublishRequest request = serialization.deserialize(data, PublishRequest.class);

            case ACK:
            default:
                // todo
        }
    }
}
