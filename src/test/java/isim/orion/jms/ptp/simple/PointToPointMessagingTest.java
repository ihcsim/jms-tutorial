package isim.orion.jms.ptp.simple;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class PointToPointMessagingTest {

  @Test
  public void canCreateProducer() throws IOException {
    Producer producer = new Producer();
    Assert.assertNotNull(producer);
  }
  
  @Test
  public void canCreateConsumer(){
    Consumer consumer = new Consumer();
    Assert.assertNotNull(consumer);
  }
  
  @Test
  public void producerCanSendMessage() throws IOException{
    Producer producer = new Producer();
    Assert.assertTrue(producer.sendMessage());
  }

  @Test
  public void consumerCanReceiveMessage() throws IOException, InterruptedException{
    Consumer consumer = new Consumer();
    Assert.assertEquals("Hello World", consumer.receiveMessage());
  }
}

