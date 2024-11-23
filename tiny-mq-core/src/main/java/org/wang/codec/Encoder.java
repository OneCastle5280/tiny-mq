package org.wang.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.wang.protocal.MessageHeader;
import org.wang.protocal.MessageProtocal;
import org.wang.serialization.SerializationFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjiabao
 */
public class Encoder<T> extends MessageToByteEncoder<MessageProtocal<T>> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocal<T> tMessageProtocal, ByteBuf byteBuf) throws Exception {
        MessageHeader header = tMessageProtocal.getHeader();

        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getType());
        byteBuf.writeCharSequence(header.getRequestId(), StandardCharsets.UTF_8);

        // serialization
        byte[] data = SerializationFactory.getSerialization().serialize(tMessageProtocal.getBody());
        byteBuf.writeInt(data.length);

        byteBuf.writeBytes(data);
    }
}
