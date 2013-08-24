package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the instantiation of the Consumer class.
 * @author isim
 *
 */
public class ConsumerInitTest {

  @Test
  public void canCreateConsumerTest(){
    try{
      Consumer consumer = new Consumer();
      Assert.assertNotNull(consumer);
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
  
  @Test
  public void canConnectConsumerToQueueTest(){
    try{
      Consumer consumer = new Consumer();
      Assert.assertTrue(consumer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
  
  @Test
  public void canDisconnectConsumerFromQueueTest(){
    try{
      Consumer consumer = new Consumer();
      consumer.disconnect();
      Assert.assertTrue(!consumer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Consumer.");
    }
  }
}
