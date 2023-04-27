# Illustration de l'architecture Hexagonale avec Quarkus

Ce projet est l'illustration de l'article [Architecture Hexagonale : Comment créer des applications polyvalentes](https://lixtec.fr/architecture-hexagonale-hexagonal-architecture)

## Synthèse de l'architecture Hexagonale

L’Architecture Hexagonale, également connue sous le nom de Ports et Adapters, suit un modèle architectural dans lequel les entrées des utilisateurs ou des systèmes externes arrivent dans l’Application via un Port grâce à un Adaptateur, et où les sorties de l’Application sont envoyées via un Port à un Adaptateur. Cela crée une couche d’abstraction qui protège le coeur de l’application et l’isole des outils et technologies externes – qui peuvent être considérés comme étant sans importance pour l’application.

## Quels sont les principaux composants de l’architecture Hexagonale?

### Ports
Nous pouvons considérer un Port comme un point d’entrée agnostique en termes de technologie, il détermine l’interface qui servira aux acteurs externes à communiquer avec l’application, peu importe qui ou quoi implémentera ladite interface. Tout comme un port USB sert à communiquer à plusieurs types de périphériques avec un ordinateur tant qu’ils disposent d’un adaptateur USB. Les Ports servent également à l’application pour communiquer avec des systèmes ou services externes, tels que des bases de données, des brokers de messages, d’autres applications, etc.

### Adaptateurs – Adapters
Un Adaptateur initiera l’interaction avec l’Application via un Port, en utilisant une technologie spécifique. Par exemple, un contrôleur REST représenterait un adaptateur qui permet à un client de communiquer avec l’Application. Il peut y avoir autant d’Adaptateurs pour un seul Port que nécessaire, sans que cela représente un risque pour les Ports ou l’Application elle-même.


### Application
L’Application est le cœur du système, elle contient les Services d’Application qui orchestrent la fonctionnalité ou les cas d’utilisation. Elle est représentée par un hexagone qui reçoit des commandes ou des requêtes des Ports, et envoie des demandes à d’autres acteurs externes, comme les bases de données, via les Ports également.

## Packaging

<pre><span>hexagonal<br>|<br>| - account<br>    | - adapter<br>    |    | - driving<br>    |    |    | - api<br>    |    |    |    | - AccountApi<br>    |    |    |    | - AccountResource<br>    |    |    | - web<br>    |    |         | - AccountController<br>    |    | - driven<br>    |    |    | - persistence<br>    |              | - AccountRepository<br>    | - application<br>    |    | - port<br>    |    |    | - driving<br>    |    |    |    | - SendMoneyUseCase<br>    |    |    | - driven<br>    |    |         | - LoadAccountPort<br>    |    |         | - UpdateAccountStatePort<br>    |    | - service<br>    |              | - AccountServiceImpl<br>    | - domain<br>    |    | - model<br/>    |              | - Account<br>    |              | - Transaction<br></span></pre>

## Répartition en projet

### Projet API
<pre><span>hexagonal<br>|<br>| - account<br>    | - adapter<br>    |    | - driving<br>    |    |    | - api<br>    |    |    |    | - AccountApi<br>    | - application<br>    |    | - port<br>    |    |    | - driving<br>    |    |    |    | - SendMoneyUseCase<br>    |    |    | - driven<br>    |    |         | - LoadAccountPort<br>    |    |         | - UpdateAccountStatePort<br>    | - domain<br>         | - model<br/>              | - Account<br>              | - Transaction<br></span></pre>

### Projet CORE
<pre><span>hexagonal<br>|<br>| - account<br>    | - adapter<br>    |    | - driving<br>    |    |    | - api<br>    |    |    |    | - AccountResource<br>    |    |    | - web<br>    |    |         | - AccountController<br>    |    | - driven<br>    |    |    | - persistence<br>    |              | - AccountRepository<br>    | - application<br>    |    | - service<br>    |              | - AccountServiceImpl</span></pre>
