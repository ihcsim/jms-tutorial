package isim.orion.jms.ptp.single;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the instantiation of the Consumer class.
 * @author isim
 *
 */
public class InitConsumerTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  private Consumer consumer;
  
  @Before
  public void setUp(){
    consumer = new Consumer(QUEUE_NAME, DEFAULT_HOST);
  }

  @Test
  public void canCreateConsumerTest(){
    Assert.assertNotNull(consumer);
  }
  
  @Test
  public void canCreateMultipleConsumersTest() {
    Consumer consumer1 = new Consumer(QUEUE_NAME, DEFAULT_HOST);
    Assert.assertTrue(consumer1.isConnected());
    
    Consumer consumer2 = new Consumer(QUEUE_NAME, DEFAULT_HOST);
    Assert.assertTrue(consumer2.isConnected());
    
    Consumer consumer3 = new Consumer(QUEUE_NAME, DEFAULT_HOST);
    Assert.assertTrue(consumer3.isConnected());
    
    Consumer consumer4 = new Consumer(QUEUE_NAME, DEFAULT_HOST);
    Assert.assertTrue(consumer4.isConnected());
  }
  
  @Test
  public void canConnectConsumerToQueueTest(){
    Assert.assertTrue(consumer.isConnected());
  }
  
  @Test
  public void canDisconnectConsumerFromQueueTest(){
    consumer.disconnect();
    Assert.assertTrue(!consumer.isConnected());
  }
}
