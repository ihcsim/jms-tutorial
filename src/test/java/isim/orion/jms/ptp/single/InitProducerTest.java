package isim.orion.jms.ptp.single;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the instantiation of the Producer class.
 * @author isim
 *
 */
public class InitProducerTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  private Producer producer;
  
  @Before
  public void setUp(){
    producer = new Producer(QUEUE_NAME, DEFAULT_HOST);
  }
  
  @Test
  public void canConnectProducerToQueueTest(){
    Assert.assertTrue(producer.isConnected());
  }
  
  @Test
  public void canDisconnectProducerFromQueueTest(){
    producer.disconnect();
    Assert.assertTrue(!producer.isConnected());
  }
}
