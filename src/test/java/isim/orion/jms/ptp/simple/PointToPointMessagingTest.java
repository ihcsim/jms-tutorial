package isim.orion.jms.ptp.simple;

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
      while(consumer.isConnected()) {
        String messageReceived = consumer.receiveMessage();
        Assert.assertEquals("Hello Queue", messageReceived);
        consumer.disconnect();
      }
    } catch(InterruptedException e){
      Assert.fail("Consumer failed to receive messages from queue.");
    }
  }

  @Test
  public void canCreateProducerTest(){
    try{
      Producer producer = new Producer();
      Assert.assertNotNull(producer);
    } catch(IOException e){
      Assert.fail("Failed to create Producer.");
    }
  }
  
  @Test
  public void canConnectProducerToQueueTest(){
    try{
      Producer producer = new Producer();
      Assert.assertTrue(producer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Producer.");
    }
  }
  
  public void canDisconnectProducerFromQueueTest(){
    try{
      Producer producer = new Producer();
      producer.disconnect();
      Assert.assertTrue(!producer.isConnected());
    } catch(IOException e){
      Assert.fail("Failed to create Producer.");
    }
  }
  
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

