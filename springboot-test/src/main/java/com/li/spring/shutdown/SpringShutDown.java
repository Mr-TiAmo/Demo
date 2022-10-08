package com.li.spring.shutdown;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-10-08 16:55
 **/
public class SpringShutDown {
    /**
     spring 优雅停机原理  Runtine Shutdown Hook -> Context:destory() -> DisposableBean:destroy()
     !!! Bean的生命周期销毁：ContextClosedEvent、@PreDestroy、DisposableBean !!!
     @PostConstruct -> initializingBean -> init-method -> ContextStartedEvent -> spring启动完毕
     关闭spring -> ContextCloseEvent -> @PreDestroy -> DisposableBean ->destroy-method


     registerShutdownHook 添加一个监听器，监听执行kill命令
     在AbstractApplicationContext类的destroy方法
     publishEvent 发布一个关闭时间
     lifecycleProcessor.onClose 调用生命周期处理器
     destroyBeans  销毁所有的bean， 实现了DisposableBean 类型的 Bean对象， 通过实现destroy()自定义如何释放资源
     closeBeanFactory 关闭bean工厂
     onClose  调用子类的close函数
     */

    public void springShutDown() {
        // destroy()
//        synchronized(this.startupShutdownMonitor) {
//            this.doClose();
//            if (this.shutdownHook != null) {
//                try {
//                    Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
//                } catch (IllegalStateException var4) {
//                }
//            }
//
//        }

        // doClose()
//        try {
//            this.publishEvent((ApplicationEvent)(new ContextClosedEvent(this)));
//        } catch (Throwable var3) {
//            this.logger.warn("Exception thrown from ApplicationListener handling ContextClosedEvent", var3);
//        }
//
//        if (this.lifecycleProcessor != null) {
//            try {
//                this.lifecycleProcessor.onClose();
//            } catch (Throwable var2) {
//                this.logger.warn("Exception thrown from LifecycleProcessor on context close", var2);
//            }
//        }
//
//        this.destroyBeans();
//        this.closeBeanFactory();
//        this.onClose();
//        this.active.set(false);
    }

    /**
     *
     Spring Boot > 2.3.0：内置容器
     Springboot2.3.0 之后默认完成了优雅停机
     启用正常停机
     可以通过在应用程序配置文件中设置两个属性来进行：

     # 开启优雅停机
     server.shutdown=graceful
     spring.lifecycle.timeout-per-shutdown-phase=30s
     server.shutdown 属性可以支持的值有两种：

     immediate 这是默认值，配置后服务器立即关闭，无优雅停机逻辑。
     graceful 开启优雅停机功能，并遵守 spring.lifecycle.timeout-per-shutdown-phase 属性中给出的超时来作为服务端等待的最大时间。
     spring.lifecycle.timeout-per-shutdown-phase 服务端等待最大超时时间，采用java.time.Duration格式的值，默认30s。
     当我们使用了如上配置开启了优雅停机功能，当我们通过SIGTERM信号关闭 Spring Boot 应用时：
     此时如果应用中没有正在进行的请求，应用程序将会直接关闭，而无需等待超时时间结束后才关闭。
     此时如果应用中有正在处理的请求，则应用程序将等待超时时间结束后才会关闭。如果应用在超时时间之后仍然有未处理完的请求，应用程序将抛出异常并继续强制关闭。



     Springboot2.3.0 之前需要自己实现优雅停机

     */

    public void springBootShutDown() {
//        @Bean
//        public ConfigurableServletWebServerFactory tomcatCustomizer() {
//            TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//            factory.addConnectorCustomizers(gracefulShutdown());
//            return factory;
//        }
//
//        @Bean
//        public GracefulShutdown gracefulShutdown() {
//            return new GracefulShutdown();
//        }
//
//        private static class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
//            private static final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);
//            private volatile Connector connector;
//
//            @Override
//            public void customize(Connector connector) {
//                this.connector = connector;
//            }
//
//            @Override
//            public void onApplicationEvent(ContextClosedEvent event) {
//                this.connector.pause();
//                Executor executor = this.connector.getProtocolHandler().getExecutor();
//                if (executor instanceof ThreadPoolExecutor) {
//                    try {
//                        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//                        threadPoolExecutor.shutdown();
//                        if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
//                            log.warn("Tomcat thread pool did not shut down gracefully within 30 seconds. Proceeding with forceful shutdown");
//                        }
//                    } catch (InterruptedException ex) {
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
    }

    /**
     *
     自定义 优雅关闭， 执行顺序：contextCloseEvent > disposableBean.destroy() > @PreDestroy
     1.实现ApplicationListener接口，实现contextClosedEvent事件
     2.实现DisposableBean接口，实现destroy()
     //3. 使用@PreDestroy注解

     */
    public void customerShutDown() {
        // 1.实现ApplicationListener接口，实现contextClosedEvent事件
//        public class APIService implements ApplicationListener<ContextClosedEvent>
//        {
//            @Override
//            public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
//                //Do shutdown work.
//            }
//        }

        // 2.实现DisposableBean接口，实现destroy()
//        public class DefaultDataStore implements DisposableBean {
//
//            private final ExecutorService executorService = new ThreadPoolExecutor(
//                    　　　　　　OSUtil.getAvailableProcessors(), OSUtil.getAvailableProcessors() + 1, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(200), new DefaultThreadFactory("UploadVideo"));
//
//            @Override
//            public void destroy() throws Exception {
//                log.info("准备优雅停止应用使用 DisposableBean");
//                executorService.shutdown();
//            }
//        }

        //3. 使用@PreDestroy注解
//        public class DefaultDataStore {
//
//            private final ExecutorService executorService = new ThreadPoolExecutor(
//                    　　　　　　OSUtil.getAvailableProcessors(), OSUtil.getAvailableProcessors() + 1, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(200), new DefaultThreadFactory("UploadVideo"));
//
//            @PreDestroy
//            public void shutdown() {
//                log.info("准备优雅停止应用 @PreDestroy");
//                executorService.shutdown();
//            }
//        }
    }


    /**
     *
     1.收到kill 9 进程退出信号，spring容器会触发容器销毁事件
     2.provider端会取消注册服务元数据信息
     3.consumer端会收到最新地址列表(不包含准备停机的地址)
     4.dubbo协议会发送readonly事件报文通知consumer服务不可用
     5.服务端等待已经执行的任务结束并拒绝新任务执行

     既然注册中心已经通知了最新服务列表，为什么还要再发送readonly
     报文呢？这里主要考虑到注册中心推送服务有网络延迟，以及客户端计算服务列表可能占用一
     些时间。Dubbo协议发送readonly时间报文时，consumer端会设置响应的provider为不可用状
     态，下次负载均衡就不会调用下线的机器。
     Dubbo 2.6.3以后修复了优雅停机的一些bug,在以前的版本中没有做到完全优雅停机的原
     因是Spring注册了 JVM停止的钩子，Dubbo也注册了 JVM停止的钩子，这种场景下两个并发
     执行的线程可能引用已经销毁的资源，导致优雅停机失去了意义。比如，Dubbo正在执行的任
     务需要引用Spring中的Bean,但这时Spring钩子函数已经关闭了 Spring的上下文状态，导致
     访问任何Spring资源都会报错
     */
    public void dubboShutDown() {

        /**
         * 2.6.3 之前入口， AbstractConfig的静态代码块中
         */


//        static {
//            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//                public void run() {
//                    if (logger.isInfoEnabled()) {
//                        logger.info("Run shutdown hook now.");
//                    }
//                    ProtocolConfig.destroyAll();
//                }
//            }, "DubboShutdownHook"));
//        }

        /**
         1.destroyed.compareAndSet 防止并发调用
         2.AbstractRegistryFactory.destroyAll() 首先注销注册中心
         以zk为例，dubbo将会删除其对应服务节点，然后取消订阅，由于zk节点信息变更，zk服务端将会通知dubbo消费者下线该服务节点，最后再关闭服务于zk的连接
         通过注册中心，Dubbo 可以及时通知消费者下线服务，新的请求也不再发往下线的节点，也就解决上面提到的第一个问题：新的请求不能再发往正在停机的 Dubbo 服务提供者
         但是这里还是存在一些弊端，由于网络的隔离，ZK 服务端与 Dubbo 连接可能存在一定延迟，ZK 通知可能不能在第一时间通知消费端。考虑到这种情况，在注销注册中心之后，加入等待进制
         3.protocol.destroy() 再注销 Protocol
         Dubbo 默认使用 Netty 作为其底层的通讯框架，分为 Server 与 Client。Server 用于接收其他消费者 Client 发出的请求。
         上面源码中首先关闭 Server ，停止接收新的请求，然后再关闭 Client。这样做就降低服务被消费者调用的可能性
         */

//        public static void destroyAll() {
//            if (!destroyed.compareAndSet(false, true)) {
//                return;
//            }
//            AbstractRegistryFactory.destroyAll();
//            ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
//            for (String protocolName : loader.getLoadedExtensions()) {
//                try {
//                    Protocol protocol = loader.getLoadedExtension(protocolName);
//                    if (protocol != null) {
//                        protocol.destroy();
//                    }
//                } catch (Throwable t) {
//                    logger.warn(t.getMessage(), t);
//                }
//            }
//        }





        /**  ---------------------------------------------------------------------------------------*/

        /**
         * 2.6.3 之后，通过SpringExtensionFactory，兼容spring优雅停机，并取消dubbo自己的unregister()
         * 当 Spring 框架开始初始化之后，将会触发 SpringExtensionFactory 逻辑，之后将会注销 AbstractConfig 注册 ShutdownHook，
         * 然后增加 ShutdownHookListener。这样就完美解决上面『双 hook』 问题
         */

//        public class SpringExtensionFactory implements ExtensionFactory {
//            private static final Logger logger = LoggerFactory.getLogger(SpringExtensionFactory.class);
//            private static final Set<ApplicationContext> CONTEXTS = new ConcurrentHashSet();
//
//            public SpringExtensionFactory() {
//            }
//
//            public static void addApplicationContext(ApplicationContext context) {
//                CONTEXTS.add(context);
//                if (context instanceof ConfigurableApplicationContext) {
//                    ((ConfigurableApplicationContext) context).registerShutdownHook();
//                    DubboShutdownHook.getDubboShutdownHook().unregister();
//                }
//
//            }
//        }
    }
}
