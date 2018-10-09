### JMS简介

        JMS即java消息队列（Java Message Service）应用程序接口，是一个Java平台中关于面向消息中间件（MOM）的API，用于在两个应用程序之
    间，或分布式系统中发送消息，进行异步通信。


#### JMS的特点
    
    1.Asynchronous：JMS本身是一个异步的消息服务，客户端获取请求时，无需发送请求，消息会自动的发送给客户端。
    
    2.Reliable：JMS消息只会发送一次，可以有效的避免（只是避免，某些糟糕的情况下可能会发生）重复发送问题。
    
#### JMS消息模型

    JMS具有两种通信模式：
        1、Point-to-Point Messaging Domain （点对点）
        2、Publish/Subscribe Messaging Domain （发布/订阅模式）
        
        
##### 点对点通信模型

    1.定义：
            在点对点通信模型中，应用程序由消息队列、发送方和接受方组成，每个消息都被发送到一个特定的队列，接收者从队列中获取消息。队列保
        留着消息，直到他们被消费或超时。。
    
    2.特点：
        a.每个消息只有一个消费者。
        b.发送者和接受者在时间上没有约束，不管接收者是否存在或者是否接收消息，发送者都可以向队列中发送消息。
        c.不管发送方是否在发送消息，接收方都可以从消息队列中去到消息。
        d.接收方在接收完消息之后，需要向消息队列应答成功。
        

##### 发布/订阅模式

    1.定义：
            在发布/订阅消息模型中，发布者发布一个消息，该消息通过topic传递给所有的客户端。该模式下，发布者与订阅者都是匿名的，即发布者
        与订阅者都不知道对方是谁。并且可以动态的发布与订阅Topic。Topic主要用于保存和传递消息，且会一直保存消息直到消息被传递给客户端。
    
    2.特点：
        a.一个消息可以有多个接受方。
        b.发布者与订阅者具有时间约束，针对某个主题（Topic）的订阅者，它必须创建一个订阅者之后，才能消费发布者的消息，而且为了消费消息，
        订阅者必须保持运行的状态。
        c.为了缓和这样严格的时间相关性，JMS允许订阅者创建一个可持久化的订阅。这样，即使订阅者没有被激活（运行），它也能接收到发布者的消
        息。
        
    3.消息的订阅类型：
        对于一个Topic而言，它的订阅者的订阅类型分为两种：Durable Subscribers（持久订阅） 和 NonDurable Subscribers（非持久订阅）。
    若采用持久订阅，当订阅者在订阅之后由于某种原因而处于Inactive状态，同时发布者发布了一条消息，此时Broker会为订阅者保存消息，当订阅者
    恢复至Active状态时仍能够接收到消息。因此采用持久订阅可以防止丢失消息。
    
    以ActiveMq为例：
        // 若采用持久订阅，需要指定clientID，activemq正是通过clientID和订户名称来区分消费者的。
        // 因此不同消费者需要指定不同的clientID，若指定两个相同的clientID，则第二个会报错。
        connection.setClientID(clientID);
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("testMqPS");
        //非持久订阅者
        MessageConsumer subscriber = session.createConsumer(topic);
        //持久订阅者
        MessageConsumer subscriber = session.createDurableSubscriber(topic, clientID);
        
        
#### JMS接收消息

    1.Synchronous：在同步消费信息模式模式中，订阅者/接收方通过调用 receive（）方法来接收消息。在receive（）方法中，消息未到达或者到达
    指定时间之前，线程会阻塞，直到有消息可用。
    
        MessageConsumer consumer = session.createConsumer(queue);
        while(true) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(name + "接收消息，内容为：" + message.getText());
        }
    
    2.Asynchronous：使用异步方式接收消息的话，消息订阅者需注册一个消息监听者，类似于事件监听器，只要消息到达，JMS服务提供者会通过调用监
    听器的onMessage()递送消息。
    
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage msg = (TextMessage) message;
                try {
                    System.out.println(name + "接收消息，内容为：" + msg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    
    
#### JMS的编程模型

1.基本组成模块
    
    .1 管理对象（Administered objects）-连接工厂（Connection Factories）和目的地（Destination）
    .2 连接对象（Connections）
    .3 会话（Sessions）
    .4 消息生产者（Message Producers）
    .5 消息消费者（Message Consumers）
    .6 消息监听者（Message Listeners）

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    