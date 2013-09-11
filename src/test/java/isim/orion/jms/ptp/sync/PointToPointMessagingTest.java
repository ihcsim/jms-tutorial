package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

public class PointToPointMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  @Test
  public void producerCanSendMessageTest(){
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

  @Test
  public void consumerCanReceiveMessageTest_ShortString() {
    try{
      String message = "Hello Queue";
      
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      producer.sendMessage(message);
      
      Consumer consumer = new Consumer();
      Assert.assertEquals(message, consumer.receiveMessage());
      
      producer.disconnect();
      consumer.disconnect();
    } catch(Exception e){
      Assert.fail("Consumer failed to send message to queue.");
    }
  }
  
  @Test
  public void consumerCanReceiveMessageTest_EmptyString(){
    try{
      String message = "";
      
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      producer.sendMessage(message);
      
      Consumer consumer = new Consumer();
      Assert.assertEquals("", consumer.receiveMessage());
      
      producer.disconnect();
      consumer.disconnect();
    } catch(Exception e){
      Assert.fail("Consumer failed to send message to queue.");
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void consumerCantSendNullObjectTest() throws IOException{
    String message = null;

    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(channel, QUEUE_NAME);
    producer.sendMessage(message);
    producer.disconnect();
  }
}

