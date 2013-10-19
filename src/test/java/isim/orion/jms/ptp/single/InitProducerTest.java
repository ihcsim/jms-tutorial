package isim.orion.jms.ptp.single;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the instantiation of the Producer class.
 * @author isim
 *
 */
public class InitProducerTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  @Test
  public void canConnectProducerToQueueTest(){
    Tunnel tunnel = Tunnel.newInstance(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(tunnel, QUEUE_NAME);
    Assert.assertTrue(producer.isConnected());
  }
  
  @Test
  public void canDisconnectProducerFromQueueTest(){
    Tunnel tunnel = Tunnel.newInstance(QUEUE_NAME, DEFAULT_HOST);
    Producer producer = new Producer(tunnel, QUEUE_NAME);
    producer.disconnect();
    Assert.assertTrue(!producer.isConnected());
  }
}
