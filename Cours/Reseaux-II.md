# Résumé du Cours de Réseaux II

## Introduction

Ce document résume les principaux concepts et notions abordés dans le cours de Réseaux II.

## Couches de l'OSI

### Couches Hautes

1. **Application**: Échanges de données d'application selon l'application.
2. **Présentation**: Mise en forme des données pour la transmission.
3. **Session**: Synchronisation de la communication entre processus.
4. **Transport**: Transfert de blocs d'octets entre processus.
5. **Réseau**: Transfert de blocs d'octets entre systèmes distants.
6. **Liaison de données**: Transfert fiable de blocs d'octets entre systèmes sur le même réseau physique.
7. **Physique**: Transfert physique de bits entre systèmes raccordés au même médium.

#### Sous-couches de Liasion de données:
1. **LLC (Logical Link Control):*** Couche de transition entre la couche MAC et la couche 3.
2. **MAC (Medium accessControl):*** Transfert de bits entre systèmes raccordés au même support physique , avec contrôle du droit d'émission.
### Couches TCP/IP

1. **Application**: Échanges de données d'application, mise en forme et synchronisation.
2. **Transport**: Transfert de blocs d'octets entre processus.
3. **Internet**: Transfert de blocs d'octets entre systèmes distants.
4. **Accès Réseau**: Transfert fiable de blocs d'octets entre systèmes sur le même réseau et transmission physique.

## Détails des Couches OSI

### Couche 1 : Physique

La couche physique gère la transmission de bits sous forme de signaux physiques à travers des connecteurs et des câbles, spécifiant les connecteurs et les tensions. Elle utilise des équipements comme les répéteurs, les concentrateurs (HUB), et les modems. Elle définit la bande passante, mesurée en bits par seconde, bien que le débit réel soit souvent inférieur au débit théorique en raison de facteurs comme la topologie du réseau et le nombre d’utilisateurs.

### Couche 2 : Liaison de Données

La couche liaison de données assure le transfert fiable de blocs d'octets entre systèmes sur le même réseau en utilisant des trames. Elle détecte et corrige les erreurs, régule le flux, et garantit l'ordre de transmission. Les commutateurs (Switch) et les cartes réseau (NIC) sont utilisés pour ces fonctions, connectant des médias comme le cuivre, la fibre optique et les réseaux sans fil.

### Couche 3 : Réseau

La couche réseau transfère des blocs d'octets entre réseaux différents via des routeurs, sous forme de paquets. Elle s'occupe du calcul des routes, du contrôle de flux, et de la gestion des adresses logiques. Cette couche permet l'interconnexion de réseaux divers en assurant que les paquets atteignent leur destination finale.

### Couche 4 : Transport

La couche transport assure la transmission de blocs d'octets entre processus via des segments, contrôlant les pertes, duplications et l'ordre des données. Le protocole TCP offre un service connecté et fiable, tandis que l'UDP fournit un service non connecté, rapide mais moins fiable. Elle gère également le flux de données pour éviter la congestion.

### Couche 5 : Session

La couche session organise et synchronise les échanges entre processus, établissant, gérant et terminant les sessions de communication. Elle définit des points de reprise pour maintenir une communication continue et ordonnée entre les applications.

### Couche 6 : Présentation

La couche présentation met en forme les données pour qu'elles soient interprétées correctement par les applications, s'occupant du codage, de la conversion, de la compression et du chiffrement. Elle assure la compatibilité entre systèmes différents et la sécurité des données transmises.

### Couche 7 : Application

La couche application fournit des services réseau aux applications utilisateur, gérant les échanges de données et offrant des interfaces pour des services comme le Web, FTP, et la messagerie. C'est l'interface principale pour les utilisateurs, facilitant diverses tâches réseau et applications.
