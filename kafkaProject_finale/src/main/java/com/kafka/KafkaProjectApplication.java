package com.kafka;

import com.kafka.mysensor.HumiditySensor;
import com.kafka.mysensor.PhSensor;
import com.kafka.mysensor.Sensor;
import com.kafka.mysensor.TemperatureSensor;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.kafka")
public class KafkaProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaProjectApplication.class, args);

		// Get the KafkaTemplate bean from the application context
		KafkaTemplate<String, String> kafkaTemplate = context.getBean(KafkaTemplate.class);

		// Create instances of each sensor
		TemperatureSensor temperatureSensor = new TemperatureSensor(kafkaTemplate);
		PhSensor phSensor = new PhSensor(kafkaTemplate);
		HumiditySensor humiditySensor = new HumiditySensor(kafkaTemplate);

		//starting the sensor and generate the data
		while(true){
			phSensor.captureData();
			humiditySensor.captureData();
			temperatureSensor.captureData();
			try {
				//sleeping the thread for 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

	}
}



