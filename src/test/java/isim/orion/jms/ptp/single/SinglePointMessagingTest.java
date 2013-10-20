package isim.orion.jms.ptp.single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SinglePointMessagingTest {
  
  private static final String DEFAULT_HOST = "localhost";
  private final static String QUEUE_NAME = "test-queue";
  
  private Producer producer;
  private Consumer consumer;
  
  @Before
  public void setUp(){
    producer = new Producer(QUEUE_NAME, DEFAULT_HOST);
    consumer = new Consumer(QUEUE_NAME, DEFAULT_HOST);
  }
  
  @After
  public void tearDown(){
    producer.disconnect();
    consumer.disconnect();
  }
  
  @Test
  public void testProducer_CanSendMessage(){
    try{
      String message = "Hello Queue";
      producer.send(message);
    } catch(RuntimeException e){
      Assert.fail("Unable to send message. " + e.getMessage());
    }
  }
  
  @Test
  public void testProducer_CanSendMultipleMessage(){
    try{
      List<String> messages = generateMultipleFakeMessages();
      producer.send(messages);
    } catch(RuntimeException e){
      Assert.fail("Unable to send multiple messages");
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testProducer_CantSendNullObject() throws IOException{
    String message = null;
    producer.send(message);
  }
  
  @Test
  public void testConsumer_CanReceiveShortString() {
    String message = "Hello Queue";
    producer.send(message);
    Assert.assertEquals(message, consumer.receive().get(0));
  }
  
  @Test
  public void testConsumer_CanReceiveEmptyString(){
    String message = "";
    producer.send(message);
    Assert.assertEquals("", consumer.receive().get(0));
  }
  
  public void testConsumer_CanReceiveMultipleMessages(){
    List<String> expectedMessages = generateMultipleFakeMessages();
    producer.send(expectedMessages);
    List<String> actualMessages = consumer.receive();
  }
  
  private List<String> generateMultipleFakeMessages() {
    List<String> messages = new ArrayList<String>();
    messages.add("Message 1");
    messages.add("Message 2");
    messages.add("Message 3");
    messages.add("Message 4");
    messages.add("Message 5");
    messages.add("Message 6");
    return messages;
  }
}

