package com.kafka.alertAndRecommendation;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
@WebService(endpointInterface="com.kafka.alertAndRecommendation.AlertAndRecommendationService")
@Service
public class AlertAndRecommendationServiceImpl implements AlertAndRecommendationService {

    private String phAlertMessage;
    private String humidityAlertMessage;
    private String temperatureAlertMessage;
    private List<String> alerts;

    private String phRecommendationMessage;
    private String humidityRecommendationMessage;
    private String temperatureRecommendationMessage;
    private List<String> recommendations;

    /*
    *La source des informations sur lesquelles nous avons basé pour les valeurs des trois facteurs de production de tomates:
    * https://www.yara.fr/fertilisation/solutions-pour-cultures/tomate/principes-agronomiques-tomates/
    */

    @KafkaListener(topics="phAlert",groupId="groupId")
    public String getPhAlertAndRecommendation(String data) {
        /*
        """
        Le pH optimal du sol se situe entre 6,0 et 6,5.
        Les tomates sont toutefois cultivées sur des sols dont le pH est compris entre 5,0 à 7,5.
        Lorsque le pH est inférieur à 5,5, la disponibilité en magnésium et en molybdène diminue.
        Lorsqu'il est supérieur à 6,5, les niveaux de zinc, de manganèse et de fer deviennent insuffisants.
        """
        */
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=5.5) && (value<=6.5)){
            this.phAlertMessage="!!!- Le pH optimal du sol pour la production des tomates";
            this.phRecommendationMessage="-> La valeur de Ph est très bonne !";
        }else if(value<5.5){
            this.phAlertMessage="!!!- Valeur ph trop basse: la disponibilité en magnésium et en molybdène diminue";
            this.phRecommendationMessage="-> Ph est trop basse:\n\ta: Ajoutez des amendements alcalins tels que de la chaux agricole pour élever le pH du sol.\n\t" +
                    "b :Utilisez des engrais à libération lente pour éviter l'acidification excessive du sol.";
        }else{
            this.phAlertMessage="!!!- Valeur ph trop élevée: les niveaux de zinc, de manganèse et de fer deviennent insuffisants";
            this.phRecommendationMessage="-> Ph est trop élevée:\n\t1- Ajoutez des amendements acides tels que du soufre élémentaire ou des engrais acidifiants pour abaisser le pH du sol.\n\t" +
                    "2- Utilisez des engrais spécifiques qui favorisent l'acidité du sol, comme les engrais à base d'azote ammoniacal.";
        }
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(phAlertMessage+ "\n\"\"\"\n" +phRecommendationMessage);
        return phAlertMessage+ "\n\"\"\"\n" +phRecommendationMessage;
    }

    @KafkaListener(topics="humidityAlert",groupId="newGId")
    public String getHumidityAlertAndRecommendation(String data) {
        /*
        """
        L'humidité relative optimale des cultures sous serre varie de 60 à 80 %.
        """
        */
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=60.0) && (value<=80.0)){
            this.humidityAlertMessage="!!!- Humidité relative optimale pour la production des tomates";
            this.humidityRecommendationMessage="-> La valeur de l'humidité est très bonne !";
        }else if(value<60.0){
            this.humidityAlertMessage="!!!- Humidité trop basse et peut entraîner un stress hydrique.";
            this.humidityRecommendationMessage="-> Humidité trop basse:\n\ta: Utilisez des pulvérisateurs ou des brumisateurs pour augmenter l'humidité autour des plantes.\n\t" +
                    "b: Placez des récipients d'eau près des plantes pour augmenter l'humidité par évaporation.";
        }else{
            this.humidityAlertMessage="!!!- Humidité trop élevée et peut favoriser le développement de maladies fongiques";
            this.humidityRecommendationMessage="-> Humidité trop élevée:\n\t1- Assurez-vous d'une bonne circulation de l'air en utilisant des ventilateurs ou des déshumidificateurs.\n\t" +
                    "2- Évitez les arrosages excessifs et assurez-vous que le sol a une bonne capacité de drainage.";
        }
        System.out.println(humidityAlertMessage+ "\n\"\"\"\n" +humidityRecommendationMessage);
        return humidityAlertMessage+ "\n\"\"\"\n" +humidityRecommendationMessage;
    }

    @KafkaListener(topics="temperatureAlert",groupId="newId")
    public String getTemperatureAlertAndRecommendation(String data) {
        /*
        """
        La plage de température optimale pour la culture des tomates est comprise entre 18 et 27 °C.
        Les températures supérieures à 27 °C restreignent la formation des fleurs
        """
        */
        Double value=0.0;
        try{
            value=Double.valueOf(data.replace(',','.'));
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        if ( (value>=18.0) && (value<=27.0)){
            this.temperatureAlertMessage="!!!- Température optimale pour la culture des tomates";
            this.temperatureRecommendationMessage="-> La valeur de la température est très bonne! ";
        }else if(value>27.0){
            this.temperatureAlertMessage="!!!- Température supérieure à 27 °C restreigne la formation des fleurs.";
            this.temperatureRecommendationMessage="-> La valeur de la températureest est trop élevée:\n\t1- Utilisez des ombrages ou des écrans pour réduire l'exposition directe au soleil pendant les heures les plus chaudes de la journée.\n\t" +
                    "2- Augmentez la ventilation et la circulation de l'air autour des plantes en utilisant des ventilateurs ou des systèmes de ventilation.\n\t" +
                    "3- Privilégiez l'irrigation par goutte à goutte ou l'irrigation au pied des plantes pour éviter l'évaporation excessive.";
        }else{
            this.temperatureAlertMessage="!!!- Témperature trop élevée et peut réduit la nouaison et retarde la maturité";
            this.temperatureRecommendationMessage="-> La valeur de la températureest trop basse:\n\ta: Utilisez des tunnels ou des serres pour créer un environnement protégé et augmenter la température.\n\t" +
                    "b: Utilisez des couvertures flottantes ou des tissus thermiques pour protéger les plantes des températures froides la nuit.\n\t" +
                    "c: Utilisez des systèmes de chauffage appropriés pour maintenir une température optimale dans les serres.";
        }
        System.out.println(temperatureAlertMessage+ "\n\"\"\"\n" +temperatureRecommendationMessage);
        return temperatureAlertMessage+ "\n\"\"\"\n" +temperatureRecommendationMessage;
    }

    @Override
    public List<String> getAlerts(){
        alerts=new ArrayList<>();
        alerts.add(phAlertMessage);
        alerts.add(humidityAlertMessage);
        alerts.add(temperatureAlertMessage);
        return alerts;
    }

    @Override
    public List<String> getRecommendations(){
        recommendations=new ArrayList<>();
        recommendations.add(phRecommendationMessage);
        recommendations.add(humidityRecommendationMessage);
        recommendations.add(temperatureRecommendationMessage);
        return recommendations;
    }

}
