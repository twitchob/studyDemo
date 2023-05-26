package nettyLongConn;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 9:54
 */
@AllArgsConstructor
public class NettyClientInitializer extends ChannelInitializer {


    private NettyClient nettyClient;


    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //心跳检测
        pipeline.addLast(new IdleStateHandler(0, 30, 0, TimeUnit.SECONDS));
        //字符串编码（默认编码）
        pipeline.addLast("encoder", new StringEncoder());
        //字符串解码（重写类，如不需要可使用默认）
        pipeline.addLast(new TCPBaseMessageDecoder());
        //客户端的逻辑
        pipeline.addLast("handler", new NettyClientHandler(nettyClient));

    }
}
