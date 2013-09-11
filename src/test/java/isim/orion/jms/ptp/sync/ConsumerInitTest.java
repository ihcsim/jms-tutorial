package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

/**
 * Test the instantiation of the Consumer class.
 * @author isim
 *
 */
public class ConsumerInitTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";

  @Test
  public void canCreateConsumerTest(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Consumer consumer = new Consumer(channel, QUEUE_NAME);
      Assert.assertNotNull(consumer);
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
  
  @Test
  public void canConnectConsumerToQueueTest(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Consumer consumer = new Consumer(channel, QUEUE_NAME);
      Assert.assertTrue(consumer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
  
  @Test
  public void canDisconnectConsumerFromQueueTest(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Consumer consumer = new Consumer(channel, QUEUE_NAME);
      consumer.disconnect();
      Assert.assertTrue(!consumer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
}
