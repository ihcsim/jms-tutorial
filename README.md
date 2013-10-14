This tutorial project contains source and test codes using [RabbitMQ](http://www.rabbitmq.com/) libraries to demonstrate key [JMS](http://docs.oracle.com/javaee/6/tutorial/doc/bncdq.html) concepts. The basic idea is to use a producer to publish some messages to a queue to be consumed by some consumers. All of my examples are derived from the original [RabbitMQ tutorial page](http://www.rabbitmq.com/getstarted.html). I added unit tests and refactor the codes to help with my own understanding of the subject.

All of these examples are tested using RabbitMQ 3.1.5 on Ubuntu 12.

Here are a number of useful references:
* [RabbitMQ download and installation instructions](http://www.rabbitmq.com/download.html)
* [RabbitMQ tutorials](http://www.rabbitmq.com/getstarted.html)
* [RabbitMQ Management Plugin installation instructions](http://www.rabbitmq.com/management.html)

Tips:
* To help clear any messages linger in the queue, access the Queue using the RabbitMQ Management console, and clear the queue by using the 'Get Messages' section with the 'Requeue' option set to 'No'.
