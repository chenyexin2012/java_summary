package com.holmes.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.Iterator;
import java.util.Map;

public class ServerRequestHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {

        if (object instanceof FullHttpRequest) {

            FullHttpRequest request = (FullHttpRequest) object;

            System.out.println("url: " + request.uri());
            HttpHeaders headers = request.headers();
            Iterator<Map.Entry<String, String>> entryIterator = headers.iteratorAsString();
            while (entryIterator.hasNext()) {
                Map.Entry<String, String> entry = entryIterator.next();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            ByteBuf buf = request.content();
            System.out.println(new String(buf.toString(CharsetUtil.UTF_8)));

            ByteBuf byteBuf = Unpooled.copiedBuffer("<body><p>Hello World!</p></body>".toCharArray(), CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.content().writeBytes(byteBuf);

            channelHandlerContext.channel().writeAndFlush(response);
        }
    }
}
