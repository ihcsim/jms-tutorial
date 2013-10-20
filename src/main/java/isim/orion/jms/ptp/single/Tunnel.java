package isim.orion.jms.ptp.single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Tunnel {
  
  private Channel rabbitMQChannel;
  private String queue;
  
  public static Tunnel newInstance(String queue, String host){
    return new Tunnel(queue, host);
  }
  
  private Tunnel(String queue, String host){
    rabbitMQChannel = ChannelFactory.open(queue, host);
    this.queue = queue;
  }

  public void publish(String message) {
    try {
      if(message == null)
        throw new IllegalArgumentException();
      rabbitMQChannel.basicPublish("", queue, null, message.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isOpen() {
    return rabbitMQChannel.isOpen();
  }
  
  public void purgeQueue(){
    try {
      rabbitMQChannel.queuePurge(queue);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void close() {
    try {
      rabbitMQChannel.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public List<String> receive() {
    List<String> messages = new ArrayList<String>();
    try{
      // callback to buffer the messages
      QueueingConsumer queueConsumer = new QueueingConsumer(rabbitMQChannel);
      rabbitMQChannel.basicConsume(queue, true, queueConsumer);
        
      // consumer remains in suspend until message arrives
      QueueingConsumer.Delivery delivery = queueConsumer.nextDelivery();
      messages.add(new String(delivery.getBody()));
      return messages;
    } catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  public List<String> receive(int timeout) {
    List<String> messages = new ArrayList<String>();
    try{
      QueueingConsumer queueConsumer = new QueueingConsumer(rabbitMQChannel);
      rabbitMQChannel.basicConsume(queue, true, queueConsumer);
      QueueingConsumer.Delivery delivery = null;
      while(true){
        delivery = queueConsumer.nextDelivery(timeout);
        if(delivery == null)
          break;
        messages.add(new String(delivery.getBody()));
      } 
      return messages;
    } catch(Exception e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public void disconnect() {
    try{
      rabbitMQChannel.queuePurge(queue);
      rabbitMQChannel.close();
    } catch(IOException e){
      throw new RuntimeException(e);
    }
  }
}
