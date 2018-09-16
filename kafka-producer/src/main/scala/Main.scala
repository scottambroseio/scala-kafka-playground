import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Main extends App {
  println("Starting Producer")

  val props = new Properties
  
  props.put("bootstrap.servers", "rpi3a:9092,rpi3b:9092,rpi3c:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[Nothing, String](props)

  val message = new ProducerRecord("scala", "hello world")

  println("Sending message")

  producer.send(message)

  println("Exiting")

  producer.close()
}
