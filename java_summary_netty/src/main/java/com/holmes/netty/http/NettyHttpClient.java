package com.holmes.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyHttpClient {

    private String host;

    private int port;

    public NettyHttpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpClientChannelInitializer());

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(this.host, this.port)).sync();
            if (future.isSuccess()) {
                System.out.println("success connect to server");
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NettyHttpClient("127.0.0.1", 9090).start();
    }
}
