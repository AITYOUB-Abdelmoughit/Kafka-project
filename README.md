# Système distribué pour une agriculture intelligente et traitement de l'information
## Introduction générale
L’agriculture est l’activité humaine la plus ancienne et la plus importante et est encore
pratiquée aujourd’hui. En raison de sa grande importance en termes de sécurité alimentaire,
d’autant plus que l’agriculture est la principale source d’alimentation, qu’elle soit végétale ou
animale. Ils sont très importants dans tous les domaines de la vie et leur importance ne peut
être négligée. Par conséquent, ce domaine est devenu l’objet d’études, pour tenter de résoudre
les problèmes qui peuvent y être exposés.
Comme les systèmes intelligents dans l’agriculture sont devenus la solution idéale pour
résoudre les problèmes auxquels est confrontée l’agriculture traditionnelle, grâce à l’utilisation
de divers capteurs et appareils intelligents basés sur l’Internet des objets (IOT), dont les mesures
sont caractérisées par des données précises telles que la température du sol, l’humidité et le PH.
Ceci permettra une meilleure aide à la décision en agriculture, notamment pour optimiser l’eau
d’irrigation, l’usage des intrants, et la planification de travaux agricoles.
En résumé, la mise en place d’un système distribué pour une agriculture intelligente peut
aider à améliorer la productivité agricole, à réduire les coûts et les pertes, à améliorer la qualité
des produits, tout en préservant l’environnement et en assurant la durabilité de l’agriculture.
<br>
<br>

##  Les détails de la culture de la Tomate:
Les conditions environnementales idéales pour la croissance de la tomate:
- Température : Les tomates préfèrent des températures comprises entre 18°C et 29°C pendant
la journée, et entre 15°C et 21°C pendant la nuit.
- Soleil : Les tomates ont besoin d’au moins 6 à 8 heures d’ensoleillement direct par jour
pour une croissance optimale.
- Humidité du sol : Le sol doit être constamment humide, mais pas détrempé. L’objectif est
d’atteindre un niveau d’humidité d’environ 75
- Niveau de pH : Les tomates préfèrent un sol légèrement acide, avec un pH compris entre
6,0 et 6,8.
- Nutriments : Les tomates ont besoin de niveaux adéquats d’azote, de phosphore et de
potassium pour une croissance saine. Pensez à incorporer un engrais équilibré pour assurer une
bonne disponibilité des nutriments.
<br>
<br>

## Architecture du Projet:
>Notre architecture repose sur un modèle de producteur-consommateur. Les services web
agissent comme des producteurs, collectant et envoyant les données des capteurs dans le système.
Ces données sont alors consommées par deux entités distinctes. La première, le système d’alerte,
surveille les données en temps réel pour déceler toute anomalie ou tout problème potentiel.
La deuxième, le système de recommandation, analyse les données pour fournir des conseils
pratiques sur la gestion des cultures et du bétail. Cette architecture nous permet d’assurer une
gestion efficace et précise de l’agriculture intelligente:<br>
<img width="233" alt="image" src="https://github.com/AITYOUB-Abdelmoughit/Kafka-project/assets/94485789/c0d0ac36-f071-4f5c-a60a-a3dd2eea6e7b">
<br>
<br>

## Les technologies utilisées dans ce projet:
+ ###  Apache Kafka:
> Apache Kafka est une plateforme de streaming de données open source, développée par
la Fondation Apache. Elle fournit une architecture de messagerie distribuée pour la gestion de
flux de données en temps réel:<br>
<img width="186" alt="image" src="https://github.com/AITYOUB-Abdelmoughit/Kafka-project/assets/94485789/745713e8-0905-45c1-9d4e-8bcc0d7269ef">
<br>
<br>
+ ### Spring boot:
>Spring Boot est un framework Java puissant et polyvalent, largement utilisé pour le
développement d’applications autonomes et prêtes à l’emploi. Grâce à sa configuration automatique intelligente, il permet de réduire considérablement le temps et les efforts nécessaires pour démarrer un projet:<br>
<img width="276" alt="image" src="https://github.com/AITYOUB-Abdelmoughit/Kafka-project/assets/94485789/f33fe36f-d732-4c0e-8a12-97fc5cf23dd9">
<br>
<br>
+ ### SOAP UI:
>SOAP UI est un outil graphique polyvalent utilisé pour tester des Services Web. Il peut être
utilisé en tant qu’application autonome ou intégré dans des environnements de développement
tels que Intellij, NetBeans et Maven. L’une des grandes forces de SOAP UI est sa compatibilité
avec toutes les plateformes de développement:<br>
<img width="269" alt="image" src="https://github.com/AITYOUB-Abdelmoughit/Kafka-project/assets/94485789/98530f12-7c11-4c2b-bd5e-94e1c8ac1795">
