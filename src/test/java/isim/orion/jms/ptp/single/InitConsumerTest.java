package isim.orion.jms.ptp.single;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

/**
 * Test the instantiation of the Consumer class.
 * @author isim
 *
 */
public class InitConsumerTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";

  @Test
  public void canCreateConsumerTest(){
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(channel, QUEUE_NAME);
    Assert.assertNotNull(consumer);
  }
  
  @Test
  public void canCreateMultipleConsumersTest() {
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer1 = new Consumer(channel, QUEUE_NAME);
    Assert.assertTrue(consumer1.isConnected());
    
    Consumer consumer2 = new Consumer(channel, QUEUE_NAME);
    Assert.assertTrue(consumer2.isConnected());
    
    Consumer consumer3 = new Consumer(channel, QUEUE_NAME);
    Assert.assertTrue(consumer3.isConnected());
    
    Consumer consumer4 = new Consumer(channel, QUEUE_NAME);
    Assert.assertTrue(consumer4.isConnected());
  }
  
  @Test
  public void canConnectConsumerToQueueTest(){
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(channel, QUEUE_NAME);
    Assert.assertTrue(consumer.isConnected());
  }
  
  @Test
  public void canDisconnectConsumerFromQueueTest(){
    Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
    Consumer consumer = new Consumer(channel, QUEUE_NAME);
    consumer.disconnect();
    Assert.assertTrue(!consumer.isConnected());
  }
}
