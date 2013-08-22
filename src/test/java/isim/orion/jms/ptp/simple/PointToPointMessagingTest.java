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
  public void producerCanSendMessage(){
    try{
      String message = "Hello Queue";
      Producer producer = new Producer();
      producer.sendMessage(message);
    } catch(IOException e){
      Assert.fail("Producer failed to send message to queue.");
    }
  }

  @Test
  public void consumerCanReceiveMessage() throws IOException, InterruptedException{
    String message = "Hello Queue";
    Producer producer = new Producer();
    producer.sendMessage(message);
    Consumer consumer = new Consumer();
    consumer.receiveMessage();
    Assert.assertEquals("Hello World", consumer.lastReceivedMessage());
    Assert.assertEquals(1, consumer.numReceivedMessages());
  }
}

