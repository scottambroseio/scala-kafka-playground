import java.util.{Collections, Properties}
import scala.collection.JavaConversions._

import org.apache.kafka.clients.consumer.KafkaConsumer

object Main extends App {
  println("Starting consumer")

  val props = new Properties

  props.put("bootstrap.servers", "rpi3a:9092,rpi3b:9092,rpi3c:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "rpi3")

  val consumer = new KafkaConsumer[Nothing, String](props)

  println("Subscribing to topic \"Scala\"")

  consumer.subscribe(Collections.singletonList("scala"))

  println("Starting poll loop")

  while (true) {
    val records = consumer.poll(100)
    
    for (record <- records.iterator()) println(record.value())
  }
}
