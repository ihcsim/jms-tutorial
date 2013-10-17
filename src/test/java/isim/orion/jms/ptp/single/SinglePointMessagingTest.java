package isim.orion.jms.ptp.single;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

public class SinglePointMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  @Test
  public void testProducer_CanSendMessage(){
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
      
    String message = "Hello Queue";
    producer.sendSingleMessage(message);
    producer.disconnect();
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testProducer_CantSendNullObject() throws IOException{
    String message = null;

    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
    producer.sendSingleMessage(message);
    producer.disconnect();
  }

  @Test
  public void testConsumer_CanReceiveShortString() {
    String message = "Hello Queue";
    
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
    producer.sendSingleMessage(message);
    
    Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
    Assert.assertEquals(message, consumer.receiveSingleMessage());
    
    producer.disconnect();
    consumer.disconnect();
  }
  
  @Test
  public void testConsumer_CanReceiveEmptyString(){
    String message = "";
    
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
    producer.sendSingleMessage(message);
    
    Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
    Assert.assertEquals("", consumer.receiveSingleMessage());
    
    producer.disconnect();
    consumer.disconnect();
  }
}

