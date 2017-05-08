package com.distributed.system.common.web.listener;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import org.jboss.netty.channel.ChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;

/**
 * Created by cyc on 2017/5/5.
 */
public class MyContextListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(MyContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //先释放dubbo所占用的资源
        ProtocolConfig.destroyAll();
        //用反射释放NettyClient所占用的资源, 以避免不能优雅shutdown的问题
        releaseNettyClientExternalResources();
    }

    private void releaseNettyClientExternalResources() {
        try {
            Field field = NettyClient.class.getDeclaredField("channelFactory");
            field.setAccessible(true);
            ChannelFactory channelFactory = (ChannelFactory) field.get(NettyClient.class);
            channelFactory.releaseExternalResources();
            field.setAccessible(false);
            logger.debug("Release NettyClient's external resources");
        } catch (Exception e) {
            logger.error("Release NettyClient's external resources error", e);
        }
    }
}
