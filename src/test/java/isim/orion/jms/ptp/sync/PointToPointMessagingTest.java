package isim.orion.jms.ptp.sync;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class PointToPointMessagingTest {
  
  @Test
  public void producerCanSendMessageTest(){
    try{
      String message = "Hello Queue";
      Producer producer = new Producer();
      producer.sendMessage(message);
    } catch(IOException e){
      Assert.fail("Producer failed to send message to queue.");
    }
  }

  @Test
  public void consumerCanReceiveMessageTest() throws IOException{
    try{
      String message = "Hello Queue";
      Producer producer = new Producer();
      producer.sendMessage(message);
      
      Consumer consumer = new Consumer();
      Assert.assertEquals("Hello Queue", consumer.receiveMessage());
    } catch(InterruptedException e){
      Assert.fail("Consumer failed to receive messages from queue.");
    }
  }
}

