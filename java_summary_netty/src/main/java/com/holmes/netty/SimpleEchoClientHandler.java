package com.holmes.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class SimpleEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 当到指定服务器的连接建立成功之后，发送一条消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8));
    }

    /**
     * 接收消息时调用此方法，当消息分多次接收，此方法也会调用多次
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        System.out.println("Echo Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
//        throw new Exception();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught run");
        cause.printStackTrace();
        ctx.close();
    }
}
