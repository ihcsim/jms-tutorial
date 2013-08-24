package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.rabbitmq.client.Channel;

/**
 * Test the instantiation of the Producer class.
 * @author isim
 *
 */
public class ProducerInitTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  @Test
  public void canConnectProducerToQueueTest(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      Assert.assertTrue(producer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Producer.");
    }
  }
  
  @Test
  public void canDisconnectProducerFromQueueTest(){
    try{
      Channel channel = ChannelFactory.open(QUEUE_NAME, DEFAULT_HOST);
      Producer producer = new Producer(channel, QUEUE_NAME);
      producer.disconnect();
      Assert.assertTrue(!producer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Producer.");
    }
  }
}
