package nettyLongConn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 9:57
 */
public class TCPBaseMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //不做处理，直接传递给下一个handler
        list.add(byteBuf);

    }
}
