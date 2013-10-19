package isim.orion.jms.ptp.single;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;

public class SinglePointMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  private Producer producer;
  
  @Before
  public void setUp(){
    producer = new Producer(QUEUE_NAME, DEFAULT_HOST);
  }
  
  @After
  public void tearDown(){
    producer.disconnect();
  }
  
  @Test
  public void testProducer_CanSendMessage(){
    try{
      String message = "Hello Queue";
      producer.send(message);
    } catch(RuntimeException e){
      Assert.fail("Unable to send message. " + e.getMessage());
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testProducer_CantSendNullObject() throws IOException{
    String message = null;
    producer.send(message);
  }

  @Test
  public void testConsumer_CanReceiveShortString() {
    String message = "Hello Queue";
    
    producer.send(message);
    
    Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
    Assert.assertEquals(message, consumer.receiveSingleMessage());
    
    consumer.disconnect();
  }
  
  @Test
  public void testConsumer_CanReceiveEmptyString(){
    String message = "";
    
    producer.send(message);
    
    Channel consumerChannel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(consumerChannel, QUEUE_NAME);
    Assert.assertEquals("", consumer.receiveSingleMessage());
    
    consumer.disconnect();
  }
}

