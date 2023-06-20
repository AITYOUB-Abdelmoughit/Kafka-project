package com.kafka.mysensor;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface = "com.kafka.mysensor.Sensor")
@Service
public class TemperatureSensor implements Sensor {
    public final String topicName;
    public double temperatureValue;
    public final String sensorType;
    public final String alertTopic;
    public final String unit;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public Date date;

    public TemperatureSensor(KafkaTemplate<String, String> kafkaTemplate) {
        this.topicName = "temperature";
        this.sensorType = "TEMPERATURE";
        this.alertTopic="temperatureAlert";
        this.unit = "ºC";
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public String getData() {
        return this.toString();
    }

    @Override
    public String getSensorType() {
        return sensorType;
    }
    @Override
    public String toString() {
        return "{Date: " + this.date + ", Sensor type: " + this.sensorType + ", Value: " + String.format("%.2f",this.temperatureValue) + ", Unit: " +this.unit+ "}";
    }
    public void generateData(){
        //generate a random temperature value between 20 and 40 ºC
        this.temperatureValue=Math.random()*20+20;
        this.date=new Date();
    }

    @Bean
    public void captureData() {
        //generating the data from the sensor
        generateData();
        kafkaTemplate.send(topicName,toString());
        kafkaTemplate.send(alertTopic,String.format("%.2f",this.temperatureValue));
        synchronized (Sensor.SHARED_TOPIC){
            kafkaTemplate.send(Sensor.SHARED_TOPIC,toString());
        }
    }
}
