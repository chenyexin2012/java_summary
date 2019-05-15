package com.holmes.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.Iterator;
import java.util.Map;

public class ClientResponseHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "netty/test/getUser?id=1");
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

        ctx.channel().writeAndFlush(request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object object) throws Exception {

        if(object instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) object;

            HttpHeaders headers = response.headers();
            Iterator<Map.Entry<String, String>> entryIterator = headers.iteratorAsString();
            while (entryIterator.hasNext()) {
                Map.Entry<String, String> entry = entryIterator.next();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            ByteBuf buf = response.content();
            System.out.println(new String(buf.toString(CharsetUtil.UTF_8)));
        }
    }
}
