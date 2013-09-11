package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

public class PointToPointMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  @Test
  public void testProducer_CanSendMessage(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      
      String message = "Hello Queue";
      producer.sendMessage(message);
      producer.disconnect();
    } catch(IOException e){
      Assert.fail("Producer failed to send message to queue.");
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testProducer_CantSendNullObject() throws IOException{
    String message = null;

    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
    producer.sendMessage(message);
    producer.disconnect();
  }

  @Test
  public void testConsumer_CanReceiveShortString() {
    try{
      String message = "Hello Queue";
      
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      producer.sendMessage(message);
      
      Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
      Assert.assertEquals(message, consumer.receiveMessage());
      
      producer.disconnect();
      consumer.disconnect();
    } catch(Exception e){
      Assert.fail("Consumer failed to send message to queue: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  @Test
  public void testConsumer_CanReceiveEmptyString(){
    try{
      String message = "";
      
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      producer.sendMessage(message);
      
      Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
      Assert.assertEquals("", consumer.receiveMessage());
      
      producer.disconnect();
      consumer.disconnect();
    } catch(Exception e){
      Assert.fail("Consumer failed to send message to queue: " + e.getMessage());
    }
  }
}

