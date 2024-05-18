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

# Chapitre II

Ce chapitre traite du transfert de données sur un réseau local en se concentrant sur la couche 2, qui comprend la couche MAC (Medium Access Control) et la couche LLC (Logical Link Control). Il aborde également les codes détecteurs et correcteurs d’erreurs, les protocoles filaires comme Ethernet et les protocoles non filaires tels que **802.15**, **802.16** et **802.11**.

## La Couche MAC - Généralité (1)

La couche **MAC (Medium Access Control)** permet la transmission de bits entre systèmes raccordés au même médium, tel que les câbles en paire torsadée, la fibre optique, ou les ondes radio. Les équipements typiques incluent les **switches**, les **hubs** et les **points d’accès**. La couche MAC utilise différents protocoles comme **CSMA/CD** pour Ethernet, et des bus ou jetons pour d'autres types de réseaux.

## Couche 2 - Généralité

La couche 2 gère le transfert fiable de données entre systèmes sur un même réseau local. Elle s'occupe de la numérotation des trames, de la détection et de la correction des erreurs, et de la régulation du trafic en utilisant des acquittements. Chaque système sur le réseau est identifié de manière unique par une **adresse MAC**, permettant de trouver facilement le destinataire des trames envoyées.

## L’Adresse MAC

L'adresse **MAC (Media Access Control)** est une identification unique au niveau de la couche 2, composée de **6 octets**. Les trois premiers octets représentent l'**Organizationally Unique Identifier (OUI)**, attribué à l'entreprise, et les trois octets suivants sont un numéro unique pour l’entreprise. Certaines adresses MAC peuvent être de groupe ou de broadcast. Cette adresse permet d’identifier de manière unique un ordinateur sur un réseau local. Pour vérifier une adresse MAC sous Windows, utilisez la commande **ipconfig /all** dans l'invite de commande.

## La Couche MAC - Généralité (2)

Chaque système sur le réseau a une entité MAC qui reçoit les demandes d'émission, décide quand émettre et écoute les transmissions pour décider de recevoir ou non. Si un système est connecté à deux supports différents, il contient une entité MAC pour chaque médium et une entité utilisateur de couches MAC liée à chaque médium. La communication entre deux couches MAC est possible uniquement si le système est raccordé aux deux supports, souvent en utilisant un routeur qui opère au niveau 3.

## Le Traitement des Erreurs (1)

Le traitement des erreurs est crucial pour maintenir l'intégrité des données transmises. Le taux d'erreur (**Te**) est défini comme le nombre de bits erronés divisé par le nombre de bits transmis pendant une période d'observation, généralement compris entre **10^-9** et **10^-3**. Les erreurs se produisent souvent en rafale. Le principe de base consiste à utiliser un vocabulaire commun entre l'émetteur et le récepteur, où l'émetteur n'envoie que des mots valides. Si le récepteur reçoit un mot non valide, il détecte une ou plusieurs erreurs.

### Stratégie de Traitement d'une Erreur

Lorsqu'une erreur est détectée, plusieurs stratégies peuvent être adoptées. Si les erreurs sont acceptables, aucune correction n'est tentée. Pour les erreurs inacceptables, elles peuvent être signalées ou non. Si une erreur est détectée, une procédure de détection et de correction est mise en œuvre. Si la correction échoue, l'erreur est signalée; si elle réussit, l'erreur est corrigée. Le pourcentage d'erreurs détectées et corrigées est mesuré pour évaluer l'efficacité de ces procédures.

## Le Traitement des Erreurs (2)

Pour gérer les erreurs, on peut utiliser des techniques comme l'écho, la répétition et la redondance. Typiquement, on envoie **m bits** de données et **r bits** de redondance, formant un message de longueur **m+r**. Il existe deux stratégies : utiliser suffisamment de redondance pour permettre au récepteur de reconstituer les données (**codes correcteurs d'erreurs**) ou utiliser juste assez de redondance pour détecter les erreurs et demander une retransmission (**codes détecteurs d'erreurs**).

### Distance de Hamming

La **distance de Hamming** est un critère pour évaluer le pouvoir détecteur et correcteur d'un code. Elle est définie comme le nombre de positions avec des valeurs distinctes entre deux mots. Par exemple, la distance de Hamming entre **110011** et **101010** est **3**. La distance de Hamming d'un code est le minimum des distances entre tous les mots du code. Par exemple, pour les mots **{110, 101, 011}**, la distance est **2**.

### Pouvoir Détecteur d'un Code

Une erreur d'ordre **k** se produit lorsqu'un mot émis diffère par **k bits** du mot reçu. Pour détecter une erreur d'ordre **1**, la distance de Hamming du code doit être de **2**, ce qui empêche une erreur simple de changer un mot du code en un autre mot du code. Pour détecter une erreur d'ordre **k**, la distance de Hamming doit être de **k+1**.

### Pouvoir Correcteur d'un Code

Pour corriger une erreur d'ordre **1**, une distance de Hamming de **2** n'est pas suffisante, il faut une distance de **3**. Si la distance de Hamming est **2**, une erreur simple peut produire un mot se situant exactement à mi-chemin entre deux mots du code, rendant la correction impossible. Pour corriger une erreur d'ordre **k**, il faut une distance de Hamming de **2k+1**.

### Code Détecteur d'Erreurs (1)

Les codes de contrôle de parité sont utilisés pour détecter les erreurs. Le code **VCR (Vertical Redundancy Check)** ajoute un bit de parité pour que le nombre de 1 dans les données envoyées soit pair, offrant une distance de Hamming de **2**, ce qui permet de détecter une erreur. Le code **LCR (Longitudinal Redundancy Check)** combine parité verticale et horizontale, offrant une distance de Hamming de **3**, permettant de détecter et corriger des erreurs simples.

### Code Détecteur d'Erreurs (2)

Les codes polynomiaux, comme le **CRC (Cyclic Redundancy Check)** et le **FCS (Frame Control Check)**, utilisent des polynômes à coefficients binaires pour représenter des séquences de bits. Le polynôme générateur (**G(x)**) détermine le code, et le message (**M(x)**) est encodé en ajoutant des bits de redondance. Par exemple, pour un message M(x) et un générateur G(x) comme **G(x)=x^16 + x^12 + x^5 + 1 (CRC-CCITT)**, le message est multiplié par **x^r** et divisé par **G(x)** pour obtenir le reste **R(x)**, formant le message encodé **N(x) = M(x) * x^r + R(x)**.

### Code Détecteur d'Erreurs (3)

Pour décoder, on divise le message reçu par le polynôme générateur **G(x)** et vérifie si le reste est nul. Si c'est le cas, aucune erreur n'a été détectée. Les opérations mathématiques en base 2 sont utilisées pour l'addition et la soustraction, où **1 + 1** et **1 - 1** donnent **0**, et **0 + 1** et **0 - 1** donnent **1**.

### Exemple

Pour illustrer, considérons **G(x) = x^2 + 1** (représenté par **101** en binaire) et un message à envoyer **M(x) = 1100** (x^3 + x^2). On multiplie M(x) par **x^2** pour obtenir **x^5 + x^4** (110000 en binaire). On divise **110000** par **101** :

`110000 ÷ 101 = quotient 110, reste 11`

Le message envoyé est donc **110011**. Pour vérifier, on divise **110011** par **101** :

`110011 ÷ 101 = quotient 110, reste 00 (pas d'erreur)`

Le reste de **00** indique que le message est correct et n'a pas subi d'erreurs pendant la transmission.
### Protocole de Niveau 2

#### IEEE 802.3, Ethernet - II

Le protocole **Ethernet**, inventé par Xerox PARC en 1973, utilise des connecteurs **RJ45** avec 8 broches pour les connexions électriques. Pour des débits de **10/100 Mb/s**, les broches **1-2** et **3-6** sont utilisées, tandis que les débits de **1 Gb/s** utilisent toutes les 8 broches. Les câbles peuvent être droits ou croisés, bien que le décroisement puisse maintenant se faire au niveau logiciel grâce à la détection de la polarité du signal. La topologie Ethernet est passée de bus à maillage, avec une méthode d'accès par compétition utilisant **CSMA/CD (Carrier Sense Multiple Access/Collision Detection)**.

#### IEEE 802.3, Ethernet - II (Topologie et Méthode d'Accès)

La méthode d'accès en bus implique que les dispositifs écoutent le médium et émettent lorsque celui-ci est libre. En cas de collision, une réémission est tentée après un délai. Avec une topologie maillée, le **full-duplex** permet d'éviter les collisions, ce qui améliore les performances. Les débits disponibles vont de **10 Mb/s** à **10 Gb/s**.

#### IEEE 802.3, Ethernet - II (Trame)

Les trames Ethernet contiennent plusieurs champs : la synchronisation, l'adresse **MAC** du destinataire, l'adresse **MAC** de l'expéditeur, le type de trame, les données et un champ de contrôle (**FCS**) pour la détection d'erreurs. En half-duplex, les hubs sont utilisés, tandis que le full-duplex utilise des switches (commutateurs).

#### Exemple Ethernet - II

Une trame Ethernet - II est représentée en hexadécimal, par exemple :

```
AA AA AA AA AA AA AA AB 08 00 2b 91 b0 d0 00 e0
b1 45 12 06 08 00 45 00 00 32 0e f1 00 00 39 06 5e
65 c1 37 5f 01 c1 36 33 01 0f 85 00 15 3f b5 1b 97 1f
24 de b9 50 18 40 00 c6 fa 00 00 55 53 45 52 20 6d
63 76 0d 0a 36 A2 5D 24
```

Les champs incluent l'adresse **MAC** du destinataire, l'adresse **MAC** de l'expéditeur, le type de trame, les données, et le champ de contrôle **FCS**, utilisé pour vérifier l'intégrité des données transmises.

### LAN

Un **LAN (Local Area Network)** est un ensemble d'équipements reliés par des switches, hubs ou points d'accès. Les débits peuvent varier de **10 Mb/s** à **1 Gb/s** ou plus. Un **switch** permet une connexion point à point en full-duplex et apprend les adresses MAC pour acheminer les trames correctement. Il permet également de sécuriser le réseau en limitant les trames aux liens destinataires spécifiques, bien qu'il puisse être vulnérable à la saturation de la table des adresses MAC ou au vol d'adresses MAC. Des mesures de sécurité, comme la sécurisation des ports du switch, sont donc nécessaires.

### Un Switch

Un **switch** permet la connexion point à point en mode **full-duplex**, ce qui élimine les collisions et améliore les performances réseau. Il apprend les adresses **MAC** des dispositifs connectés et utilise une **table d'adresses MAC** pour acheminer les trames uniquement vers le destinataire approprié. Cela réduit le trafic inutile et augmente la sécurité du réseau, car les données ne sont transmises que sur le lien destinataire. Cependant, la table d'adresses MAC peut se saturer, et il existe un risque de vol d'adresses MAC. Pour éviter ces problèmes, les ports du switch peuvent être sécurisés.

### Protocole de Niveau 2 Non Filaire

#### Généralités

Les protocoles de niveau 2 non filaires permettent la communication sans utiliser de câbles. Plusieurs normes existent pour répondre à différentes applications, comme **IrDA** pour les communications infrarouges, et diverses normes hertziennes telles que **GSM**, **GPRS**, **UMTS**, **LTE**, et **5G**. Les normes **IEEE 802.11 (Wi-Fi)**, **802.15 (Bluetooth)**, et **802.16 (WiMAX)** couvrent les communications à courte, moyenne et longue portée.

### Fréquences

Les fréquences utilisées pour les communications varient en fonction des applications :

- **VLF (Very Low Frequencies)** : navigation maritime, sonar.
- **LF (Low Frequencies)** : aéronautique, radio grandes ondes.
- **MF (Medium Frequencies)** : SOS, radio OM.
- **HF (High Frequencies)** : ISM, radio-diffusion sur onde courte.
- **VHF (Very High Frequencies)** : radio numérique, FM, communications satellite LEO, trafic aérien.
- **UHF (Ultra High Frequencies)** : télévision, téléphonie, GPS, communications satellite.
- **SHF (Super High Frequencies)** : micro-ondes, communications satellite.
- **EHF (Extremely High Frequencies)** : recherche spatiale.
- **Bande ISM** : Industriel, Scientifique et Médical.


