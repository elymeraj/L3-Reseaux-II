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

## Généralité - Côté Client

Dans le modèle client/serveur, le client initie la communication en envoyant des requêtes au serveur. Il est souvent créé lorsque le besoin apparaît, soit par une personne soit au démarrage du système. Le client doit connaître l'adresse du serveur à interroger, ce qui peut être configuré dans un fichier ou saisi manuellement lors de l'exécution.

## Généralité - Côté Serveur

Le serveur fonctionne de manière permanente, souvent sous forme de démon (processus créé au démarrage du système), ou est lancé à la demande par un processus écouteur. Lorsqu'une demande arrive, le serveur peut la traiter directement ou la déléguer à un processus fils ou un thread, gérant ainsi plusieurs requêtes simultanément sans surcharger la mémoire.

## TCP et UDP

Le protocole TCP offre un service de communication connecté, assurant la fiabilité par la détection et la correction des pertes, la gestion des duplications et le contrôle de flux, garantissant ainsi la livraison des données dans l'ordre. En revanche, le protocole UDP fonctionne en mode non connecté, permettant la communication avec un ou plusieurs destinataires sans garantir la fiabilité ni l'ordre des messages, ce qui le rend plus rapide mais moins sûr pour certaines applications.

## Fiabilité de TCP (1)

La fiabilité de TCP repose sur la numérotation des octets lors de l'expédition, où chaque bloc d'octets est assigné à un numéro de séquence (seq) et un numéro d'acquittement (ack) correspondants, initialisés à l'établissement de la connexion. Lors de la réception, les numéros sont vérifiés : un numéro reçu supérieur au numéro attendu indique une perte, un inférieur indique une duplication, et un égal indique une transmission correcte. Pour corriger les erreurs, TCP ignore les duplications et retransmet les segments perdus pour garantir une transmission fiable.

## Fiabilité de TCP (2)

La gestion de la fiabilité en TCP distingue les paquets perdus des paquets retardés. TCP peut acquitter plusieurs segments en une fois pour optimiser la bande passante et utilise des temporisations (timers) pour détecter les pertes. Un timer est créé à chaque envoi, et si l'acquittement n'arrive pas avant la fin du timer, le segment est considéré comme perdu. Le calcul du timer doit être précis : trop court, il provoque de nombreuses retransmissions inutiles; trop long, il ralentit la détection des pertes. Le timer est recalculé périodiquement, basé sur le temps de trajet moyen (RTT) et sa variance.

## Contrôle de Flux en TCP

Le contrôle de flux en TCP empêche l'envoi de plus de données que le récepteur peut gérer. Chaque entité TCP utilise une file d'attente pour stocker les octets reçus. Si cette file est pleine, l'expéditeur doit cesser d'envoyer des données pour éviter la perte de segments et la consommation inutile de ressources. Pour gérer ce flux, chaque PDU (unité de données de protocole) contient la taille de la fenêtre TCP (tcp window size), indiquant le nombre d'octets que le récepteur peut encore accepter.

## Connexion TCP/IP

Pour identifier un processus dans une connexion TCP/IP, trois informations sont nécessaires : l'adresse IP, le protocole utilisé (TCP ou UDP) et le numéro de port. Ces informations permettent d'établir des connexions spécifiques entre les applications, par exemple, le port 25 pour SMTP (email), le port 80 pour HTTP (web) et le port 79 pour Finger (utilitaire de recherche d'informations sur les utilisateurs). Les connexions peuvent être filaires (ethernet) ou non filaires (WiFi).

## Contrôle de Congestion

Le contrôle de congestion en TCP optimise l'utilisation de la bande passante sans saturer le réseau. Des algorithmes comme New Reno, Vegas et Westwood+ augmentent progressivement le nombre de segments envoyés. En cas de perte, détectée via les numéros de séquence et d'acquittement, le nombre de segments est réduit, puis augmenté à nouveau progressivement pour équilibrer efficacité et stabilité du réseau.

## Couche Réseau - Généralités

La couche réseau gère le transfert de paquets entre différents réseaux. Elle utilise des adresses uniques pour identifier les systèmes et calcule les routes pour acheminer les paquets de la source à la destination. Les principales fonctions incluent le routage et la création de tables de routage.

**IPv4** utilise des adresses **32 bits**, permettant environ **4,2 milliards d'adresses uniques**. Chaque carte réseau a une adresse IP, représentée par quatre nombres de 0 à 255 (ex: **193.55.95.26**). Le mode de connexion est **non connecté**.

Une adresse IPv4 se compose d'une **partie réseau** et d'une **partie hôte**. Le masque de sous-réseau, donné avec l'adresse IP, détermine ces parties. Par exemple, pour l'adresse **193.55.95.32** avec le masque **255.255.255.0**, la partie réseau est **193.55.95.0**.

Chaque réseau IPv4 a deux adresses réservées : l'**adresse réseau** (tous les bits hôtes à 0) et l'**adresse de diffusion** (tous les bits hôtes à 1). Les réseaux peuvent être classés en **classes A, B et C**, avec une évolution vers l'adressage classless (**CIDR**) pour une utilisation plus efficace des adresses IP.

Les adresses IPv4 sont divisées en classes par défaut : 
- **Classe A** (masque **255.0.0.0**)
- **Classe B** (masque **255.255.0.0**)
- **Classe C** (masque **255.255.255.0**).

Par exemple, une adresse de classe C, comme **200.10.15.0**, peut contenir jusqu'à **254 machines**.

Les sous-réseaux permettent de diviser un réseau de classe A, B ou C en segments plus petits, en modifiant le masque de sous-réseau. Par exemple, pour un réseau de classe C avec un masque par défaut de **255.255.255.0**, on peut créer quatre sous-réseaux avec un masque de **255.255.255.192**. Cela donne deux bits supplémentaires pour les sous-réseaux, permettant quatre sous-réseaux, chacun avec **62 hôtes** (puisque deux adresses sont réservées pour l'adresse réseau et l'adresse de diffusion).

### Architecture IPv4

Chaque réseau possède une adresse unique. Pour créer des sous-réseaux, on augmente le masque de réseau. Par exemple, pour une adresse de classe C, un masque de **255.255.255.128** permet deux sous-réseaux, chacun avec **126 hôtes**.

## Les Sous-Réseaux

Pour calculer le masque d'un sous-réseau, on augmente le masque réseau d'un nombre de bits correspondant à une puissance de 2. Par exemple, pour un réseau de classe C, un masque de **255.255.255.128** permet deux sous-réseaux, chacun pouvant accueillir **126 hôtes**. De même, un masque de **255.255.255.192** permet quatre sous-réseaux avec **62 hôtes** chacun. Pour un réseau de classe C avec une adresse de réseau **200.10.15.0**, les sous-réseaux seraient :

- **200.10.15.0** avec un masque de **255.255.255.192**
- **200.10.15.64**
- **200.10.15.128**
- **200.10.15.192**

Pour calculer un sous-réseau, on commence par déterminer le nombre de sous-réseaux nécessaires et trouver la puissance de 2 correspondante. Par exemple, pour un réseau de classe B avec l'adresse **156.10.0.0** et nécessitant **20 sous-réseaux**, on utilise **5 bits supplémentaires** (2^5 = 32). Le masque devient **255.255.248.0**. En faisant varier les bits du sous-réseau, on obtient les adresses des sous-réseaux :

- **156.10.0.0**
- **156.10.8.0**
- **156.10.16.0**
- ... jusqu'à **156.10.248.0**

## VLSM

Le Variable Length Subnet Mask (VLSM) permet une utilisation plus efficace des adresses IP en subdivisant un sous-réseau en segments encore plus petits. Par exemple, pour un réseau **200.1.1.0/24** nécessitant 7 sous-réseaux de différentes tailles, on peut diviser en plusieurs blocs, comme **200.1.1.0/27** pour Perth (supportant **30 hôtes**), en laissant les autres blocs libres pour d'autres sous-réseaux futurs.

Le VLSM divise les réseaux en sous-réseaux de tailles variées selon les besoins spécifiques. Pour le réseau **200.1.1.0/24** nécessitant 7 sous-réseaux de tailles différentes (60, 28, 12, 12, 2, 2, 2 hôtes), on trie les sous-réseaux du plus grand au plus petit et attribue les adresses :

- **200.1.1.0/26** pour Perth (60 hôtes)
- **200.1.1.64/27** pour Kuala Lumpur (30 hôtes)
- **200.1.1.96/28** pour Sydney (14 hôtes)
- **200.1.1.112/28** pour Singapore (14 hôtes)
- **200.1.1.128/30** pour petits sous-réseaux restants (2 hôtes chacun).

En continuant avec VLSM, après avoir attribué **200.1.1.0/26** à Perth, **200.1.1.64/27** à Kuala Lumpur, et ainsi de suite, les petits sous-réseaux restants comme **200.1.1.128/30** peuvent être utilisés pour les plus petites configurations, comme **200.1.1.132/30** et **200.1.1.136/30**, chacun supportant deux hôtes. Cela optimise l'utilisation des adresses IP en minimisant les gaspillages.

## Résumé de Route

La technique de résumé de route, ou agrégation, permet de combiner plusieurs sous-réseaux en une seule route pour simplifier les tables de routage. Par exemple, les adresses **172.16.64.0**, **172.16.65.0**, **172.16.66.0** et **172.16.67.0** peuvent être résumées en **172.16.64.0/22**. Cette agrégation utilise un masque de sous-réseau commun qui couvre toutes les adresses incluses.

Pour les adresses **172.16.68.0**, **172.16.69.0**, **172.16.70.0** et **172.16.71.0**, on peut les résumer de la même manière. En examinant les bits communs, on trouve qu'elles peuvent être résumées en **172.16.68.0/22**.

**Exemple 1 :**

Adresses : **172.16.68.0**, **172.16.69.0**, **172.16.70.0**, **172.16.71.0**  
Bits communs : **10101100.00010000.010001xx**  
On annonce alors le réseau : **172.16.68.0/22**

**Exemple 2 :**

Adresses : **172.16.72.0**, **172.16.73.0**, **172.16.74.0**, **172.16.75.0**  
Bits communs : **10101100.00010000.010010xx**  
On annonce alors le réseau : **172.16.72.0/22**

## IPv4 (6)

Les adresses IP privées, définies par la RFC 1918, sont utilisées pour les réseaux internes et ne sont pas routables sur Internet. Elles incluent trois plages principales :

- **10.0.0.0 à 10.255.255.255** (masque **255.0.0.0**)
- **172.16.0.0 à 172.31.255.255** (masque **255.255.0.0**)
- **192.168.0.0 à 192.168.255.255** (masque **255.255.255.0**)

Ces adresses permettent de conserver des adresses IPv4 publiques en réduisant la demande, bien que les dispositifs utilisant ces adresses doivent utiliser la traduction d'adresses réseau (NAT) pour accéder à Internet.

## Problèmes des Adresses IPv4

L'épuisement des adresses IPv4 est dû à la croissance rapide d'Internet, ce qui entraîne une pénurie d'adresses disponibles. De plus, les tables de routage deviennent gigantesques et certaines fonctionnalités nécessaires ne sont pas disponibles. Pour résoudre ces problèmes, la migration vers IPv6 est nécessaire. **IPv6** utilise des adresses sur **128 bits**, offrant un espace d'adressage beaucoup plus grand et intégrant des fonctionnalités comme la sécurité (chiffrement et authentification), le routage source, la gestion du temps réel et l'autoconfiguration.

## IPv6

IPv6 introduit différents types d'adresses : **unicast** pour un destinataire unique, **multicast** pour un groupe de machines, **anycast** pour la machine la plus proche appartenant à un groupe, et il n'y a plus d'adresses broadcast. Cela permet une plus grande flexibilité et efficacité dans la gestion des adresses et des transmissions.

## Adressage IPv6

Les adresses IPv6 sont représentées en notation hexadécimale, regroupées par 4 chiffres séparés par des deux-points, par exemple : **FEDC:E323:A65A:95F5:63D4:08BB:76F5:A234**. Les sous-réseaux disparaissent au profit de la taille du préfixe indiquée par ‘/’, par exemple : **2F45:EE34:C23E::/48**. Les zéros peuvent être omis pour simplifier l’écriture, par exemple : **2001:0:0:0:0:342D:342F:FF45** peut être écrit **2001::342D:342F:FF45**.

L'adresse **unicast** utilise **64 bits** pour le réseau et **64 bits** pour l'hôte. Les premiers **48 bits** sont pour le préfixe global de routage, gérés par des entités comme IANA et RIPE-NCC, et les **16 bits** suivants pour le réseau d'entreprise. Les **64 bits** restants identifient l'interface de l'hôte, souvent dérivés de l'adresse MAC, ce qui simplifie l’autoconfiguration.

Les adresses **multicast IPv6** comprennent un indicateur (8 bits), un drapeau (4 bits), une visibilité (4 bits), et un identifiant de groupe (112 bits). Par exemple, **FF01::1** désigne le multicast pour les machines sur le même médium (node local), et **FF05::2** désigne les routeurs sur le même site (site-local). Les différents niveaux de visibilité (**node-local, link-local, site-local, organization-local, global**) définissent la portée de l'adresse multicast.



