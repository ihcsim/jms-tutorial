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
  public void canCreateConsumer() throws IOException{
    Consumer consumer = new Consumer();
    Assert.assertNotNull(consumer);
  }
  
  @Test
  public void producerCanSendMessage() throws IOException{
    Producer producer = new Producer();
    try{
      producer.sendMessage();
    } catch(IOException e){
      Assert.fail("Producer failed to send message to queue.");
    }
  }

  @Test
  public void consumerCanReceiveMessage() throws IOException, InterruptedException{
    Producer producer = new Producer();
    producer.sendMessage();
    Consumer consumer = new Consumer();
    consumer.receiveMessage();
    Assert.assertEquals("Hello World", consumer.receiveMessage());
  }
}

