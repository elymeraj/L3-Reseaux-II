# Résumé du Cours de Réseaux II

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

## Fiabilité de TCP 

La fiabilité du protocole TCP est assurée par plusieurs mécanismes. Lors de l'expédition, les octets de l'utilisateur sont numérotés par séquence dans des PDU-TCP, permettant à l'entité-TCP d'initialiser et de calculer les numéros de séquence (seq) et d'acquittement (ack). À la réception, les numéros sont vérifiés pour détecter des pertes, des duplications ou des réceptions correctes. Les duplications sont ignorées et les pertes entraînent la retransmission des PDU. Pour différencier un paquet perdu d'un paquet en retard, TCP utilise des temporisations (timers). Un acquittement (ack) avant l'expiration du timer indique que le segment a été reçu; sinon, le segment est considéré comme perdu. Le timer est recalculé périodiquement en fonction du délai moyen et de la variance pour optimiser les retransmissions. Le contrôle de flux prévient l'envoi de plus de données que le récepteur peut gérer, en utilisant le champ "window size" pour indiquer la capacité de réception​

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

L'adressage IPv6 utilise des adresses de 128 bits, écrites en hexadécimal et séparées par des deux-points, par exemple, FEDC:E323:A65A:95F5:63D4:08BB:76F5:A234. Les sous-réseaux disparaissent, remplacés par des tailles de préfixe (ex. /48). Les adresses unicast sont constituées de 64 bits pour le réseau (dont 48 bits pour le préfixe global et 16 bits pour le réseau d'entreprise) et de 64 bits pour l'hôte. L'adresse commence souvent par 2001 et intègre l'adresse MAC modifiée. Les adresses multicast incluent un indicateur, un drapeau, une visibilité, et un identifiant de groupe (ex. FF01::1 pour le node local et les machines)​

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

# Chapitre 3 : Wi-Fi

### La Norme 802.11

La norme **802.11** définit les couches 1 et 2 pour les liaisons sans fil utilisant des ondes électromagnétiques. La couche physique inclut les codages **DSSS**, **FHSS** et **IrDA**, tandis que la couche de liaison de données comprend la couche **LLC** et la couche **MAC**. Cette norme initiale permettait des débits de **1 ou 2 Mb/s** avec un accès au médium par compétition via la méthode **CSMA/CA (Collision Avoidance)**.

### Wi-Fi

Wi-Fi est l'évolution de la norme 802.11, avec différentes versions offrant des débits et des fonctionnalités améliorées :

- **802.11a (WiFi 2)** : 54 Mb/s, 8 canaux à 5 GHz.
- **802.11b (WiFi 1)** : 11 Mb/s, portée de 300 m, 3 canaux à 2,4 GHz.
- **802.11c** : Établissement de pont pour 802.11d.
- **802.11d** : Règles internationales pour le transport des données.
- **802.11e** : Définition de la qualité de service (QoS).
- **802.11f** : Interopérabilité pour l'itinérance (roaming).
- **802.11g (WiFi 3)** : 54 Mb/s, portée de 300 m, compatible avec 802.11b.
- **802.11i (WPA2)** : Amélioration de la sécurité pour les normes a, b, et g.
- **802.11j** : Norme pour la communauté japonaise.
- **802.11n (WiFi 4)** : Débit max de 300 Mb/s, 2,4 ou 5 GHz.
- **802.11ac (WiFi 5)** : Débit de 1 Gb/s, 5 GHz.
- **802.11ax (WiFi 6)** : Débit de 10 Gb/s, bande de 1 à 7 GHz.

### Topologies

Les réseaux Wi-Fi peuvent être configurés de deux façons principales :

- **Mode Infrastructure (ou hotspot)** : Ce mode est le plus courant et nécessite au moins un point d'accès (AP) ainsi que des postes sans fil. L'ensemble de services de base (**BSS**) est identifié par un **BSSID**, souvent abrégé en **SSID (Service Set Identifier)**.
- **Mode Ad-Hoc** : Dans ce mode, il n'y a pas de point d'accès; seuls des postes sans fil communiquent entre eux. L'ensemble de services de base indépendant (**IBSS**) est identifié par un **SSID**. Tous les dispositifs doivent pouvoir se voir directement ou via un poste configuré comme routeur.

### Architecture en Couches

L'architecture Wi-Fi est divisée en plusieurs couches :
- **Couche Physique** : Comprend le **FHSS (Frequency Hopping Spread Spectrum)**, le **DSSS (Direct Sequence Spread Spectrum)**, et l'**IrDA (Infrarouge)**. Utilise des procédures telles que **PLCP (Physical Layer Convergence Procedure)** et **PMD (Physical Medium Dependent)**.
- **Couche de Liaison de Données** : Comprend la couche **LLC (Logical Link Control)** et la couche **MAC (Medium Access Control)**.

### Physique - FHSS

Le **FHSS (Frequency Hopping Spread Spectrum)** divise la bande de fréquence en 79 canaux de 1 MHz chacun. La transmission se fait en sautant de canal en canal selon une séquence prédéfinie connue de toutes les stations, avec 78 combinaisons possibles. Chaque canal est utilisé pendant 400 ms avant de passer au suivant. La bande de fréquence utilisée est de 2,4 GHz à 2,4835 GHz.

### Physique - DSSS

Le **DSSS (Direct Sequence Spread Spectrum)** divise la bande de fréquence en 14 canaux de 22 MHz chacun, mais ces canaux se chevauchent, donc seuls les canaux 1, 6 et 11 sont généralement utilisés pour éviter les interférences. Pour réduire les collisions, le DSSS utilise une technique appelée "chipping", où chaque bit est représenté par une séquence de bits (séquence Barker de 11 bits), ce qui augmente la redondance et la résistance aux erreurs.

### Physique - OFDM

L'**OFDM (Orthogonal Frequency Division Multiplexing)** divise un canal principal en plusieurs sous-canaux utilisés en parallèle, chacun avec une modulation différente. Utilisé principalement pour les normes 802.11a et 802.11ac, l'OFDM permet une utilisation efficace des fréquences 2,4 GHz et 5 GHz. Par exemple, la norme 802.11a utilise 8 canaux de 20 MHz entre 5,15 GHz et 5,35 GHz, tandis que la norme 802.11ac utilise 8 canaux de 80 MHz entre 5,17 GHz et 5,83 GHz. La norme 802.11ax (WiFi 6) utilise l'**OFDMA (Orthogonal Frequency Division Multiple Access)**, également utilisé dans la 5G.

### Couche de Liaison de Données

La couche de liaison de données pour le Wi-Fi est similaire à celle de l'Ethernet. Elle contrôle l'accès au support, gère les erreurs par **CRC (Cyclic Redundancy Check)**, effectue la fragmentation et le réassemblage des trames, et gère l'énergie et la mobilité. Il existe deux méthodes d'accès pour les normes 802.11a, b, et g :
- **DCF (Distributed Coordination Function)** : Utilisé pour les données asynchrones, avec des collisions possibles.
- **PCF (Point Coordination Function)** : Utilisé pour les données synchrones, sans collisions.

### Distributed Coordination Function (DCF)

Le **DCF** est basé sur l'accès **CSMA/CA (Carrier Sense Multiple Access with Collision Avoidance)**. Pour émettre, une station écoute le support (ondes) et, si le support est libre pendant un temps donné (**DIFS, Distributed Inter Frame Space**), elle envoie une trame **RTS (Ready To Send)** contenant des informations sur le volume de données et la vitesse de transmission. Si un **CTS (Clear To Send)** est reçu, la station envoie les données, puis attend un **ACK (acknowledgement)** pour chaque trame. Si une station veut émettre, elle doit attendre que le support soit libre, en utilisant un **vecteur d'allocation réseau (NAV, Network Allocation Vector)** pour éviter les collisions.

### Distributed Coordination Function - Exemple

Le DCF suit un processus de dialogue structuré :
1. La station source envoie une trame **RTS**.
2. La station destination envoie une trame **CTS**.
3. La station source envoie les données.
4. La station destination envoie un **ACK**.

Ce processus utilise différents espaces inter-trames (IFS) pour prioriser les trames : **SIFS (Short Inter Frame Space)**, **PIFS (Priority Inter Frame Space)**, **DIFS (Distributed Inter Frame Space)**, et **EIFS (Extended Inter Frame Space)**. Le backoff est un temps d'attente aléatoire pour éviter que toutes les stations n'émettent en même temps après une collision.

### Les Trames WiFi (1)

Il existe trois types de trames WiFi :
- **Trames de données** : Transportent les données utilisateur.
- **Trames de contrôle** : Incluent les trames RTS, CTS et ACK pour gérer l'accès au support.
- **Trames de gestion** : Utilisées pour les processus de gestion du réseau comme l'association et l'authentification.

Les trames WiFi ont une structure comprenant un en-tête **PLCP (Physical Layer Convergence Procedure)**, un préambule, des données **MAC**, **LLC**, **DATA**, et un champ **CRC** pour le contrôle d'erreur. Le préambule et le PLCP varient en fonction de l'interface physique utilisée (**FHSS**, **DSSS**, **IrDA**, **OFDM**, **OFDMA**).

### Les Trames WiFi (2)

Les trames de données WiFi contiennent plusieurs champs importants :

- **Contrôle de trame (2 octets)** : Indique la version, le type et le sous-type de la trame, ainsi que d'autres indicateurs comme "To DS" (vers le système de distribution), "From DS" (provenant du système de distribution), "More Fragments" (indique des fragments supplémentaires), "Retry" (réessai), "Power Management" (gestion de l'énergie), "More Data" (plus de données à envoyer), "WEP" (trame chiffrée), et "Order" (trame ordonnée).
- **Durée/ID (2 octets)** : Identifiant pour des trames de contrôle de sondage ou durée pour calculer le **NAV (Network Allocation Vector)**.
- **Adresses** : Quatre adresses de 6 octets chacune pour **DA (Destination Adresse)**, **SA (Source Adresse)**, **RA (Receiver Adresse)**, **TA (Transmitter Adresse)**, et **BSSID (Basic Service Set Identifier)**.
- **Contrôle de séquence (2 octets)** : Numérotation des trames pour réassembler les fragments.
- **Corps de la trame** : Contient les données, pouvant aller jusqu'à **2312 octets**.
- **CRC (4 octets)** : Code de redondance cyclique pour la vérification des erreurs.

### Les Trames WiFi (3)

Les trames de contrôle comprennent les trames **RTS, CTS et ACK** :

- **Trame RTS (Request to Send)** : Contient le contrôle de trame, la durée, l'adresse du récepteur (RA), l'adresse du transmetteur (TA), et le **FCS (Frame Check Sequence)**.
- **Trame CTS (Clear to Send)** : Contient le contrôle de trame, la durée, l'adresse du récepteur (RA), et le FCS.
- **Trame ACK (Acknowledgement)** : Contient le contrôle de trame, la durée, l'adresse du récepteur (RA), et le FCS.

### La Sécurité (1)

Les réseaux Wi-Fi sont sujets à diverses attaques :

- **Écoute passive ou active** : Permet l'interception de données, souvent indétectable.
- **Intrusion réseau** : Peut être réalisée par des employés ou via des virus.
- **Brouillage radio** : Facile à détecter mais très efficace.
- **Dénis de service** : Empêche l'accès légitime au réseau.
- **Attaque Man In the Middle** : Via **ARP spoofing** ou **ARP poisoning**.

### La Sécurité (2)

Pour contrer ces attaques, plusieurs mesures peuvent être mises en place :

- **Limiter la puissance d'émission** : Pour éviter d'arroser les environs.
- **Désactiver les services d'administration** : Fermer les ports et changer les mots de passe par défaut.
- **Changer le SSID par défaut** : Bien que le SSID soit généralement visible dans les trames d'association.
- **Désactiver le broadcast du SSID** : Mais cela peut être contourné.
- **Filtrer les adresses MAC** : Utiliser des **ACL (Access Control Lists)**, bien que cela puisse être contourné par le spoofing d'adresses MAC.
- **Chiffrer les données** : Utiliser un cryptage robuste comme **WPA2** pour protéger les communications.

### La Sécurité (3)

Le cryptage est essentiel pour sécuriser les réseaux Wi-Fi. Le **WEP (Wired Equivalent Privacy)** utilise une clé de 40 bits ou 104 bits, combinée avec un vecteur d'initialisation (IV) de 24 bits pour chiffrer les données avec l'algorithme RC4. Cependant, WEP présente des faiblesses importantes, notamment la faible sécurité des IV, qui sont incrémentés de 1 à chaque envoi, et peuvent être facilement craqués en quelques minutes. Par conséquent, si WEP est utilisé, il est recommandé d'ajouter des couches de sécurité supplémentaires comme **SSL**, **IPsec**, ou **SSH**.

### La Sécurité (4)

La norme **802.11i**, introduite en 2003, améliore la sécurité avec **WPA (Wi-Fi Protected Access)** et **WPA2**. Elle repose sur la norme **802.1x** pour l'authentification, impliquant trois acteurs : le client (**supplicant**), le point d'accès (**NAS, Network Access Server**), et le serveur d'authentification (**RADIUS**). Le chiffrement WPA utilise le **TKIP (Temporal Key Integrity Protocol)**, qui change fréquemment les clés pour éviter le craquage. **WPA2**, basé sur le protocole **CCMP (Counter-Mode/CBC-Mac Protocol)** et utilisant le chiffrement **AES**, offre une sécurité encore meilleure. **WPA3**, introduit en 2018, renforce la sécurité avec des clés plus longues et une négociation initiale plus sécurisée.

### Le Roaming

Le roaming permet à un appareil sans fil de rester connecté tout en se déplaçant entre différents points d'accès. Le protocole **IAPP (Inter-Access Point Protocol)** de la norme 802.11F utilise 4 ou 5 trames pour changer de point d'accès, mais il présente des problèmes de sécurité et de latence. La norme **802.11r (Fast Basic Service Set Transition)** améliore ce processus en mettant en cache les clés de chiffrement dans les points d'accès, ce qui réduit la latence lors de l'authentification et permet des transitions plus rapides et plus sécurisées entre les points d'accès.

## Mobilité dans les Réseaux Sans Fils

## Les Réseaux Mobiles Ad Hoc (1)

Un réseau mobile ad hoc (**MANET**, Mobile Ad Hoc NETwork) est constitué d'unités mobiles qui se déplacent librement et communiquent entre elles sans infrastructure préexistante ou administration centralisée. Chaque nœud du réseau peut agir comme un routeur pour transmettre les données à d'autres nœuds. Un MANET peut être modélisé par un graphe **G = (V,E)**, où **V** représente les nœuds et **E** les connexions entre eux. L'objectif est de permettre la communication entre des nœuds distants en passant par un ou plusieurs intermédiaires. Les applications incluent les situations d'urgence, la sécurité routière et les activités en plein air.

## Les Réseaux Mobiles Ad Hoc (2)

Les MANETs ont des caractéristiques spécifiques :

- **Topologie dynamique** : Les nœuds peuvent se déplacer, entraînant des changements constants de la topologie du réseau.
- **Bande passante limitée** : Partagée entre les nœuds, ce qui limite le débit.
- **Contraintes énergétiques** : Les dispositifs mobiles dépendent de batteries.
- **Absence d'infrastructure** : Pas de points d'accès fixes ou de routeurs centralisés.
- **Sécurisation des données** : Problèmes de sécurité physique comme l'inondation ou la dispersion des signaux.

## Communication dans les Réseaux MANET

Les objectifs principaux pour la communication dans les MANETs sont :

- **Minimiser la charge réseau** : Éviter les boucles et la concentration du trafic en un point.
- **Effectuer des communications multi-points fiables** : Assurer une route optimale pour la transmission des données.
- **Optimiser le temps de latence** : Les protocoles doivent gérer efficacement les délais de transmission.

## Classification des Protocoles MANET

Les protocoles MANET peuvent être classés en trois catégories :

- **Proactifs (Table Driven)** : Maintiennent des tables de routage à jour, comme **DSDV, OLSR, et FSR**.
- **Réactifs (On Demand Driven)** : Créent des routes à la demande, comme **DSR et AODV**.
- **Hybrides** : Combinent des aspects des deux catégories précédentes, comme **ZRP et TORA**.

## OLSR (1)

**OLSR (Optimized Link State Routing)** est un protocole proactif basé sur un algorithme d'état de lien optimisé. Il utilise des relais multipoints (**MPR, MultiPoint Relay**) pour réduire le nombre de transmissions nécessaires pour couvrir tous les voisins de second niveau. OLSR est standardisé par l'IETF sous les **RFC 3626 et 7181**. Il maintient plusieurs tables : la table de voisinage, la table de topologie du réseau, et la table de routage.

## OLSR (2)

La découverte du voisinage dans OLSR se fait via l'envoi périodique de trames **HELLO**. Les types de liens sont :

- **Lien symétrique** : Fiable et bidirectionnel.
- **Lien asymétrique** : Non bidirectionnel.
- **Lien MPR** : Relais multipoint.
- **Lien perdu** : Plusieurs trames HELLO sans réponse.

Un lien est considéré comme fiable s'il est symétrique, établi après quatre échanges de trames HELLO.

## OLSR (3)

Les **relais multipoints (MPR)** minimisent le nombre de transmissions nécessaires pour diffuser une information à tous les voisins de second niveau. Chaque nœud envoie sa table de voisinage à tous ses voisins, permettant à chaque nœud de connaître les stations à deux sauts de distance.

## OLSR (4)

La table de routage dans OLSR est créée de manière similaire à un algorithme d'état de lien, en utilisant les tables de voisinage et de topologie. Cela permet de maintenir une table de routage complète en permanence, mais nécessite des trames de contrôle fréquentes, ce qui peut poser des problèmes énergétiques pour certains nœuds.

## AODV (1)

Le protocole **AODV (Ad Hoc On-Demand Distance Vector)** est un protocole de routage réactif basé sur l'algorithme de Bellman-Ford, mais optimisé pour éviter les boucles. Il est standardisé par l'IETF sous la **RFC 3561**. AODV prend en charge le routage unicast et multicast, et est principalement conçu pour les liens symétriques. Il a deux fonctions principales : la découverte des routes et la maintenance des routes.

## AODV (2)

La table de routage dans AODV contient plusieurs informations essentielles :

- Adresse IP de destination
- Nombre de sauts nécessaires pour atteindre la destination
- Adresse IP du prochain saut
- Numéro de séquence correspondant à la destination
- Temps d'expiration de l'entrée dans la table
- Liste des précurseurs

Une destination est ajoutée à la table de routage uniquement lorsque la station veut envoyer un message à cette destination. Lorsque le temps d'expiration arrive à zéro, la destination est supprimée de la table.

## AODV (3)

La découverte de route commence par la diffusion d'un paquet **ROUTE REQUEST (RREQ)** contenant l'adresse de la destination, l'adresse de la source, un numéro de broadcast unique, et des numéros de séquence. Si aucune réponse n'est reçue avant la fin du **NET_TRAVERSAL_TIME**, le numéro de broadcast est incrémenté, et une nouvelle requête est diffusée.

## AODV (4)

Lorsqu'une **RREQ** est reçue :

- Si la station est la destination :
  - Vérification si le (numéro de RREQ, ID de la source) n'a pas été déjà reçu.
  - Mise à jour de la table de routage avec le chemin vers le destinataire, le numéro de séquence, et le temps d'expiration.
  - Envoi d'une **Route Reply (RREP)** en unicast.
- Si la station est un nœud intermédiaire :
  - Vérification si le (numéro de RREQ, ID de la source) n'a pas été déjà reçu.
  - Si aucune route n'est disponible vers la destination :
    - Mise à jour de la table de routage pour le retour.
    - Diffusion de la demande en ajoutant un saut.
  - Si une route est disponible vers la destination :
    - Vérification que le numéro de séquence de la route trouvée est supérieur à celui de la RREQ.
    - Envoi d'une **Route Reply** en unicast avec le nombre de sauts nécessaire pour atteindre la destination.
    - Mise à jour de la liste des prédécesseurs pour cette destination.

## AODV (5)

AODV construit la table de routage uniquement lorsque cela est nécessaire, ce qui élimine le besoin de trafic de contrôle constant. Cependant, cela peut poser des problèmes pour les protocoles utilisant des timers (comme TCP) en raison des délais dans la découverte de route. AODV gère bien l'énergie, car il ne maintient pas de tables de routage complètes en permanence, réduisant ainsi la consommation d'énergie.

## Bilan

- **OLSR** : Proactif, maintient des tables de routage complètes en permanence, efficace pour les réseaux avec des changements de topologie fréquents, mais consomme plus d'énergie.
- **AODV** : Réactif, découvre les routes à la demande, réduit le trafic de contrôle, économe en énergie, mais peut avoir des délais de découverte de route.

En résumé, OLSR et AODV ont chacun leurs avantages et inconvénients, et le choix entre les deux dépend des besoins spécifiques du réseau et des applications. OLSR est plus adapté aux réseaux avec des communications fréquentes et des topologies changeantes, tandis qu'AODV convient mieux aux réseaux avec des communications sporadiques et un besoin de conservation d'énergie.

# Chapitre 4 : DHCP et IPv6

### DHCPv4 : Définitions

Le protocole **DHCP (Dynamic Host Configuration Protocol)**, défini par la **RFC 2131**, succède à BOOTP et utilise la couche transport UDP avec les ports 67 (client vers serveur) et 68 (serveur vers client). Il permet la récupération automatique d'une adresse IP, d'un masque de réseau, de l'adresse de la passerelle, de l'adresse DNS et du nom de domaine. Un serveur DHCP dispose d'un "pool" d'adresses à distribuer et utilise des baux pour la durée de location des adresses IP.

### DHCP : Fonctionnement

Le processus DHCP se déroule en quatre étapes :
1. **DHCPDISCOVER** : Le client envoie un broadcast pour demander une adresse IP.
2. **DHCPOFFER** : Le serveur répond en unicast avec une offre spécifiant les paramètres réseau (après avoir vérifié que l'adresse proposée n'est pas déjà utilisée).
3. **DHCPREQUEST** : Le client accepte l'offre en envoyant un broadcast.
4. **DHCPACK** : Le serveur acquitte en unicast l'accord du client.

Pour libérer une adresse IP, le client envoie un **DHCPRELEASE**. DHCPv4 est utilisable uniquement sur le réseau LAN et est abandonné pour IPv6, remplacé par **ICMPv6**.

### Adresse Unicast IPv6

Les adresses unicast IPv6 représentent 1/8 de l'espace total d'adressage avec le préfixe **2000::/3** (les trois premiers bits étant 001). Les adresses sont divisées en **48 bits** pour le réseau public, **16 bits** pour le réseau d'entreprise, et **64 bits** pour l'interface, qui doit être unique sur le réseau.

### Adresse Lien Local IPv6

Les adresses lien local IPv6 sont valides uniquement sur un lien et ne traversent pas les routeurs. Elles sont automatiquement attribuées à chaque interface et utilisent le préfixe **FE80::/10**, généralement étendu à /64. Ces adresses permettent de découvrir les voisins sur le réseau local.

### Gestion des Adresses IPv6

L'attribution des adresses IPv6 peut se faire de manière manuelle ou via auto-configuration :
- **Auto-configuration Stateless** : Le périphérique récupère automatiquement le préfixe du site et ajoute la partie hôte via **EUI-64** ou un nombre aléatoire.
- **SLAAC avec DHCPv6 Stateless** : Utilisé pour récupérer des informations comme DNS et nom de domaine.
- **Mode Stateful** : Récupération de l'adresse via **DHCPv6** (ports 546 et 547), bien que rarement utilisé.

Le processus DHCPv6 implique quatre transactions :
1. Le client envoie un paquet **Solicit** à l'adresse **ff02::1:2** (port 547).
2. Le serveur DHCPv6 répond par **Advertise** via l'adresse lien local (port 546).
3. Le client renvoie une **Request** à l'adresse **ff02::1:2**.
4. Le serveur DHCPv6 finit par **Reply** via l'adresse lien local.

### Auto-configuration Stateless

La configuration stateless commence par l'attribution d'une adresse lien local (**FE80::**). Le périphérique vérifie l'unicité de cette adresse en envoyant une sollicitation de voisin **ICMPv6** (type 135) à l'adresse multicast **ff02::1**. Si aucune réponse n'est reçue après 1 seconde, l'adresse est considérée comme valide. Ensuite, une sollicitation de routeur **ICMPv6** (type 133) est envoyée à l'adresse multicast **ff02::2**. Le routeur répond avec une annonce de routeur (type 134) contenant le préfixe du réseau. Par défaut, les routeurs envoient des annonces de routeur régulièrement (toutes les 200 secondes pour Cisco).


### DNS

Le **DNS (Domain Name System)** convertit les noms de domaine en adresses IP et vice versa, facilitant la mémorisation des adresses par les utilisateurs. Chaque adresse IP et chaque nom de domaine doit être unique dans le monde entier.

### DNS - Généralité

Les objectifs du DNS incluent la création d'un espace de noms mondial, cohérent et indépendant des protocoles et systèmes de communication sous-jacents, ainsi que la gestion décentralisée des informations. Les avantages du DNS incluent son caractère distribué et décentralisé, mais il peut poser des problèmes de certification et de cohérence en raison de l'utilisation de caches pour mémoriser les résolutions précédentes.

### Espace des Noms (1)

L'espace des noms DNS est arborescent, avec la racine représentée par un point ("."). Sous la racine se trouvent les domaines de niveau supérieur (**TLD**), suivis des domaines et sous-domaines (jusqu'à 127 niveaux), et enfin le nom d'hôte de la machine. La taille maximale d'un domaine est de 63 caractères, et chaque nom de domaine complet (**FQDN**) inclut le nom d'hôte, le domaine et le TLD, par exemple : www.isima.fr.

### Espace des Noms (2)

Les TLD peuvent être de deux types :
- **gTLD (Generic Top Level Domains)** : Réservés à des secteurs d'activité spécifiques, comme com, edu, mil, gov, int, net, biz, aero, etc.
- **ccTLD (Country Code Top Level Domains)** : Réservés aux pays, comme fr, us, be, nl, tv, zw, etc.

### Les Serveurs de Nom

Les serveurs de nom (**Name Server, NS**) implémentent le DNS, généralement via le logiciel **BIND (Berkley Internet Name Daemon)** sur le port UDP 53. Ils répondent aux requêtes concernant les ressources de leur zone et éventuellement aux requêtes concernant d'autres zones (données mises en cache). Typiquement, il existe un serveur de nom primaire (**SOA, Start of Authority**) et des serveurs secondaires (copies/sauvegardes du serveur primaire sur un autre site).

### Fonctionnement du DNS

Lorsqu'un serveur DNS reçoit une requête, il répond directement si l'information est dans ses tables ou son cache. Sinon, il peut soit interroger un serveur DNS successif (mode récursif), soit fournir l'adresse IP du serveur à interroger (mode itératif). Les serveurs de noms racine (**root name servers**) sont au sommet de la hiérarchie DNS, et il en existe 13, nommés de **a.root-servers.net** à **m.root-servers.net**.

### Pour Finir

Un serveur DNS est toujours référencé par son adresse IP. Il existe des serveurs DNS privés et publics, chacun ayant des rôles et des niveaux de sécurité différents.

# Chapitre 7 : Protocole HTTP

### Protocole HTTP

Le protocole **HTTP (HyperText Transfer Protocol)**, défini par les **RFC 1945** et **2616**, est utilisé pour le rapatriement de documents et la soumission de formulaires. Fonctionnant au-dessus de TCP, HTTP utilise une **URI (Uniform Resource Identifier)** pour accéder aux ressources. Les versions **HTTP 1.0** et **1.1** sont couramment utilisées, avec **HTTP/2** défini par la **RFC 7540** et **HTTP/3** en cours de déploiement (**RFC 9114**).

### Méthode HTTP (1)

Quelques méthodes HTTP courantes incluent :
- **GET** : Récupération d'un document.
- **POST** : Envoi de données à l'URL spécifiée.
- **HEAD** : Récupération des informations d'en-tête du document.
- **PUT** : Enregistrement d'un document.
- **DELETE** : Suppression d'un fichier.
- **OPTIONS** : Vérification des options disponibles sur le serveur.
- **CONNECT** : Connexion à un proxy.
- **TRACE** : Récupération du message reçu par le serveur.

### Requête HTTP

Une requête HTTP comprend :
- Une ligne de requête contenant la méthode, l'URL, et la version du protocole utilisé.
- Des champs d'en-tête fournissant des informations supplémentaires sous forme de paires clé-valeur.
- Une ligne blanche.
- Le corps de la requête (si nécessaire).

Exemple :
```
GET /index.html HTTP/1.1\r\n
Host: www.isima.fr\r\n
Accept: text/html, application/xhtml+xml\r\n
Accept-Encoding: gzip, deflate\r\n
Connection: keep-alive\r\n
\r\n
```

### Réponse HTTP

Une réponse HTTP comprend :
- Une ligne de statut contenant la version du protocole utilisé, le code de statut, et la signification du code.
- Des champs d'en-tête fournissant des informations supplémentaires sous forme de paires clé-valeur.
- Une ligne blanche.
- Le corps de la réponse.

Exemple :
```
HTTP/1.1 200 OK\r\n
Date: Tue, 22 Apr 2014 09:48:55 GMT\r\n
Server: Apache/2.4.4 (Win32) PHP/5.4.14\r\n
Last-Modified: Wed, 15 Feb 2014 15:57:14 GMT\r\n
Content-Length: 125\r\n
Keep-Alive: timeout=5, max=99\r\n
Connection: Keep-Alive\r\n
Content-Type: text/html\r\n
\r\n

<html>\r\n
<head><title>page essai</title></head>\r\n
<body><H3>Vive le reseau</H3></body>\r\n
</html>
```
### Quelques En-Têtes (1)
***Content-length :*** Longueur des données.
***Content-type :*** Type MIME des données.
***Connection :*** Type de connexion (keep-alive ou close).
***Host :*** Hôte virtuel demandé (obligatoire à partir de HTTP 1.1).
***Accept :*** Type MIME autorisé.
***Accept-encoding :*** Méthode de compression autorisée.
***Cookie :*** Cookie mémorisé par le client.
***User-agent :*** Nom et version du logiciel client.
***If-modified-since :*** Renvoie le document seulement s'il a été modifié.
***Date :*** Date de la réponse.
***Server :*** Nom et version du logiciel serveur.
***Last-modified :*** Date de dernière modification.
***Content-language :*** Langue utilisée pour le document.
***Expires :*** Date d'expiration du document.
***Etag :*** Numéro de version du document.
***Set-cookie :*** Pour créer un cookie.
### Codes de Réponse
***100 à 199 :*** Informationnel.
***200 à 299 :*** Succès de la requête (200 : OK, 201 : Created, 202 : Accepted).
***300 à 399 :*** Redirection (301 : Moved Permanently, 302 : Found).
***400 à 499 :*** Erreur due au client (400 : Bad Request, 401 : Unauthorized, 403 : Forbidden, 404 : Not Found).
***500 à 599 :*** Erreur due au serveur (500 : Internal Error, 501 : Not Implemented, 502 : Bad Gateway).

## Chiffrement : Principes

Le chiffrement transforme des données lisibles (message M) en données illisibles (chiffrées) via un algorithme de chiffrement (E) et une clé de chiffrement (k). Le déchiffrement utilise un algorithme de déchiffrement (D) et une clé de déchiffrement (k'). Un bon cryptosystème doit satisfaire les propriétés suivantes :

\[ D_{k'}(E_k(M)) = M \]

où k et k' sont associés.
- Le secret repose sur les clés plutôt que sur les algorithmes, qui peuvent être publics.
- Calculer k' à partir de M et C doit être très difficile.

## Chiffrement : Cryptosystèmes

Il existe deux principaux types de chiffrement :

- **Symétrique** : Une même clé (k = k') est utilisée pour le chiffrement et le déchiffrement. Il est économique mais pose des problèmes de gestion des clés.
- **Asymétrique** : Utilise deux clés différentes (k ≠ k'), une pour le chiffrement et une pour le déchiffrement. Il est peu économique mais résout le problème de la gestion des clés.

Les algorithmes courants incluent **DES**, **Triple-DES**, **IDEA**, **AES**, **DH**, **RSA**, **MD5**, et **SHA-256**.

## Chiffrement : Asymétrique

Le chiffrement asymétrique repose sur trois éléments essentiels :

- **Clé privée** : Connue uniquement de son propriétaire.
- **Clé publique** : Distribuée à tout le monde pour chiffrer les messages.
- **Certificat** : Délivré par une autorité de certification (PKI) pour garantir que la clé publique appartient bien au propriétaire annoncé.

## Certificat

Un certificat est un fichier électronique qui contient :

- La clé publique d'un individu ou d'une entité.
- Les détails de cet individu ou entité (nom, prénom, nom de domaine).
- La signature d'un tiers de confiance pour garantir l'authenticité.
- D'autres informations comme l'usage de la clé, les dates de validité, et les informations de révocation.

## HTTPS (1)

**HTTPS (HTTP sécurisé)** combine HTTP avec **SSL (Secure Sockets Layer)** pour sécuriser les communications. Il utilise le port **443** et des certificats de type **X.509**, qui peuvent être signés par une autorité de certification ou auto-signés. HTTPS garantit la confidentialité et l'intégrité des données, et est largement utilisé pour les transactions bancaires, financières, et les achats en ligne.

## HTTPS (2)

L'établissement d'une connexion HTTPS se fait en plusieurs étapes :

1. Le client se connecte au serveur et demande une session sécurisée.
2. Le serveur envoie son certificat au client.
3. Le client vérifie le certificat et génère une clé de session symétrique.
4. Le client chiffre la clé de session avec la clé publique du serveur et l'envoie au serveur.
5. Le serveur déchiffre la clé de session avec sa clé privée.
6. Les communications suivantes sont chiffrées avec la clé de session.

## NTP - Généralité

Le **NTP (Network Time Protocol)** synchronise les horloges des ordinateurs avec des serveurs de temps sur Internet pour maintenir un temps universel cohérent. Cela est crucial pour des applications comme la compilation séparée et la synchronisation des processus. Les serveurs NTP sont organisés en strates :

- **Strate 1** : Serveurs synchronisés sur l'heure UTC.
- **Strate 2** : Serveurs synchronisés avec les serveurs de strate 1.
- **Strate 3** : Serveurs synchronisés avec les serveurs de strate 2.
- **Strate 4** : Ordinateurs locaux synchronisés avec les serveurs de strate 3.

## NTP - Principe

NTP utilise une variable d'ajustement pour synchroniser l'heure des machines avec l'heure réelle, calculée en fonction des horloges des serveurs de temps. Les horloges sont organisées en couches (strates), et la norme limite le nombre de couches à 15.

## NTP - Synchronisation (1)

La synchronisation NTP implique de connaître l'heure actuelle de la machine et l'heure réelle, calculée à partir des différentes horloges récupérées. Cette synchronisation utilise un filtre et une pondération pour ajuster l'heure réelle.

## NTP - Synchronisation (2)

NTP code le temps sur 64 bits avec un début de codage fixé au 1er janvier 1900, valide jusqu'en 2036. La trame NTP inclut :

- La version du protocole.
- Le mode d'échange (client/serveur, symétrique, multicast).
- Trois horodatages : Originate, Receive, et Transmit.

Pour calculer le délai de transmission, il faut connaître le délai aller-retour du paquet et supposer que le temps aller est égal au temps retour.

## Exemple de Calcul de Délai

Pour trouver le délai de transmission :

1. Le client envoie un message avec un horodatage **Originate**.
2. Le serveur répond avec **Receive** et **Transmit** timestamps.
3. Le client calcule le délai en utilisant les différences entre ces horodatages et l'heure actuelle de la machine.

# Chapitre 5 : Routage et VLAN

### Table de Routage

Chaque équipement de niveau 3, comme un PC ou un routeur, dispose d'une table de routage. Cette table contient quatre informations principales : l'adresse réseau distante, le masque du réseau distant, l'adresse IP du saut suivant pour atteindre le réseau distant, et l'interface de sortie. La table de routage ne fournit que l'adresse IP du prochain système sur la route vers la destination finale, opérant selon le principe hop-by-hop.

### Fonctionnement de la Table de Routage

Pour déterminer la route d'un paquet, on compare l'adresse de destination avec les entrées de la table de routage. Pour chaque ligne, un "ET" binaire est effectué entre l'adresse de destination et le masque. Si le résultat correspond à l'adresse réseau distante, l'adresse IP du prochain saut est utilisée pour le paquet. Si aucune correspondance n'est trouvée, le paquet est abandonné. La commande `netstat -r` ou `ip route` permet de visualiser la table de routage.

### Utilisation du Routage (1)

Lors de l'envoi d'un paquet, la trame se compose des données, des adresses MAC source et destination, du type de trame (IP), et des adresses IP source et destination. Si le saut suivant n'est pas directement connecté, on utilise **ARP (Address Resolution Protocol)** pour obtenir l'adresse MAC correspondante à l'adresse IP du saut suivant.

### ARP (1)

**ARP** permet d'obtenir la correspondance entre une adresse IP et une adresse MAC. Lorsqu'un dispositif A souhaite envoyer un message à B et connaît son adresse IP mais pas son adresse MAC, il envoie un broadcast ARP demandant l'adresse MAC associée à l'adresse IP de B. Tous les dispositifs reçoivent ce message, mais seule la station B répond en unicast avec son adresse MAC. La station A stocke cette information dans sa table ARP pour les communications futures.

### ARP (2)

Chaque dispositif maintient une table ARP avec les correspondances IP-MAC, consultable via `arp -a`. Si l'adresse MAC n'est pas trouvée dans la table, une requête ARP est diffusée sur le réseau.

### ARP - Généralité

**ARP**, défini par la RFC 826, permet de mapper les adresses IP aux adresses MAC. Il fonctionne via des requêtes ARP en broadcast et des réponses ARP en unicast. Les correspondances sont stockées dans une table dynamique, visible avec `arp -a`. **ARP gratuit (gratuitous ARP)** permet d'annoncer les correspondances à tous les dispositifs.

### ARP - Format

Une trame ARP contient :
- **Hardware type** : 01 pour Ethernet
- **Protocol type** : 0x0800 pour IP
- **Hardware address length** : 06 pour Ethernet
- **Protocol Address length** : 04 pour IPv4, 16 pour IPv6
- **Operation** : 1 pour une requête, 2 pour une réponse

### ARP Spoofing (1)

L'**ARP spoofing** est une attaque où un attaquant envoie des messages ARP trompeurs sur un réseau local. Par exemple, un attaquant peut prétendre avoir l'adresse IP de 10.0.0.3 avec une adresse MAC différente pour rediriger le trafic vers lui.

### ARP Spoofing (2)

En **ARP spoofing**, l'attaquant envoie de fausses informations ARP pour s'insérer dans la communication entre deux dispositifs, créant une attaque de type **Man in the Middle**. Par exemple, il peut prétendre être à la fois 10.0.0.1 et 10.0.0.2 pour intercepter le trafic.

### Mac Flooding

Le **Mac flooding** attaque un switch en inondant sa table MAC avec de fausses adresses, le forçant à passer en mode hub où il diffuse le trafic à tous les ports, compromettant ainsi la sécurité du réseau.

### Contre-Mesure

Pour contrer les attaques ARP :
- Utiliser des entrées ARP statiques (`arp -s`).
- Bloquer les ARP gratuits avec des pare-feu.
- Utiliser des systèmes de détection d'intrusion (IDS).
- Mettre en place la protection **ARP dynamique (DAI)** en corrélation avec le DHCP.
- Configurer la sécurité des ports sur les switches (Port-security).

### Utilisation du Routage (2 bis)

Certaines protocoles, comme ICMP, ne peuvent attendre la réponse à une requête ARP. Par exemple, pour le **ping (ICMP)**, Windows fait 5 essais, Cisco 4, et Linux peut continuer indéfiniment jusqu'à obtenir une réponse.

### VLAN (1)

Un **VLAN (Virtual LAN)** permet de segmenter un réseau physique en réseaux logiques distincts. Cela améliore la sécurité et réduit les domaines de broadcast. Les VLANs facilitent également la mobilité des utilisateurs sans nécessiter de changement d'adresse IP et permettent de regrouper des utilisateurs dispersés géographiquement.

### VLAN (2)

Un VLAN de bout en bout regroupe les utilisateurs selon leur groupe de travail ou fonction, indépendamment de leur localisation physique. Chaque VLAN partage des besoins de sécurité communs et facilite la mobilité des utilisateurs sur le campus, tout en maintenant leur appartenance au même VLAN.

### VLAN (3)

Il existe trois types de VLAN :
- **VLAN basé sur le port** : Simple à mettre en œuvre, affecte les ports de switch à un VLAN.
- **VLAN basé sur l'adresse MAC** : Nécessite plus de mémoire, affecte les adresses MAC à un VLAN.
- **VLAN basé sur le protocole** : Pour les protocoles routables uniquement.

### VLAN (4)

Un VLAN nécessite une adresse réseau unique de couche 3 pour permettre aux routeurs de transférer les paquets entre les VLANs. Chaque VLAN est traité comme un réseau distinct.

### VLAN (5)

Pour réduire le nombre de liaisons nécessaires entre les équipements actifs, la notion de **trunking** (lien agrégé) est utilisée. Deux normes courantes sont **ISL (Inter-Switch Link)** de Cisco et **IEEE 802.1Q**, qui est une norme ouverte pour l'étiquetage des trames VLAN.

### VLAN (6)

Une trame 802.1Q sur Ethernet contient un en-tête tag pour l'identification VLAN. Le **VLAN Trunking Protocol (VTP)** facilite la gestion des VLANs sur les liens agrégés, avec trois états possibles pour un commutateur : **serveur**, **client**, et **transparent**.

### STP (1)

Le **Spanning Tree Protocol (STP)** évite les boucles dans les réseaux en créant un arbre de chemins unique. Il maintient des liens redondants pour augmenter la fiabilité, tout en empêchant les tempêtes de broadcast. Les messages **BPDU (Bridge Protocol Data Unit)** sont échangés entre les switches pour établir la topologie.

### Algorithme STP (1)

STP commence par l'élection d'un **root bridge** basé sur un numéro unique (priorité + adresse MAC). Le **root port** est le port avec le coût le plus bas pour atteindre le root bridge. Les **designated ports** sont choisis pour chaque segment réseau, représentant le chemin le plus rapide vers le root bridge.

### Algorithme STP (2)

Les ports non désignés ou root sont bloqués pour éviter les boucles. Les états de port dans STP sont :
- **Blocking** : Ne transmet aucune trame, reçoit les BPDU.
- **Listening** : Ne transmet aucune trame, passe les BPDU, apprend la topologie.
- **Learning** : Ne transmet aucune trame, passe les BPDU, élection et nommage des ports.
- **Forwarding** : Transmet les trames et passe les BPDU.
- **Disabled** : Aucune émission, aucun BPDU.

# Chapitre 6 : Téléphonie Mobile

## Téléphonie Fixe

Les liaisons vers les commutateurs locaux et CAA sont analogiques ou numériques, avec de la fibre optique reliant les différents équipements. Depuis 2019, la commercialisation des lignes RTC a cessé, avec un passage progressif à la VoIP.

## Correspondances Bande/Fréquence

Les bandes de fréquences disponibles en téléphonie actuellement sont utilisées pour différentes générations de réseaux mobiles et leurs services associés.

## Générations de Réseaux Mobiles

- **1G** : Téléphonie analogique (1980-1995) utilisant FDMA.
- **2G** : Téléphonie et SMS (à partir de 1991) utilisant TDMA avec GSM.
- **2.5G** : Téléphonie, SMS, et accès IP jusqu'à 100 kb/s avec GPRS et EDGE.
- **3G** : Téléphonie, SMS, et accès IP jusqu'à 1 Mb/s avec UMTS utilisant CDMA.
- **3.9G** : Téléphonie, SMS, et accès IP jusqu'à 10 Mb/s avec HSDPA et nouvelle modulation.
- **4G** : Accès IP jusqu'à 100 Mb/s avec LTE utilisant OFDM.
- **5G** : Accès IP jusqu'à 1 Gb/s avec OFDM et MIMO.

## Comparaison des Technologies Mobiles

Les débits varient selon les générations :
- **GSM** : 9,6 Kb/s
- **GPRS** : 115 Kb/s
- **EDGE** : 171 Kb/s
- **UMTS** : 384 Kb/s à 2 Mb/s
- **HSPA** : 100 Mb/s
- **LTE** : 750 Mb/s
- **5G** : 1 Gb/s ou plus

## Les Réseaux Cellulaires

Les téléphones mobiles utilisent différentes fréquences allouées par l'opérateur, réparties en cellules pour éviter les interférences entre elles. Les cellules peuvent être de différentes tailles (macro-cellule, micro-cellule). Le passage d'une cellule à l'autre est géré par le handover, et l'utilisation d'un opérateur étranger est appelée roaming.

## Identification de l’Usager

Les cartes SIM, et maintenant les USIM, contiennent l'**IMSI (International Mobile Subscriber Identity)** composé de trois éléments : **MCC (Mobile Country Code)**, **MNC (Mobile Network Code)**, et **MSIN (Mobile Subscriber Identification Number)**. L'IMSI est un numéro unique qui caractérise un usager.

## Le Réseau GSM

Le **GSM (Global System for Mobile communications)** a été adopté en 1986 et standardisé en 1987 par 13 pays européens. Il fonctionne principalement sur la bande des 900 MHz et s'étend à 1800 MHz et 1900 MHz pour DCS. Le GSM utilise la commutation de circuit et nécessite une carte SIM pour fonctionner.

## Architecture du Réseau GSM (1)

Le **Gateway Mobile Service Center (GMSC)** relie le réseau GSM aux réseaux RTC, RNIS, et PABX.

## Architecture du Réseau GSM (2)

Les stations de base (**BTS**) assurent la transmission radio entre les téléphones mobiles et le réseau de l'opérateur. Chaque BTS représente une cellule et peut gérer jusqu'à 12 TRX (Transmission/Reception Units). Pour améliorer la capacité en ville, la puissance des BTS est réduite, augmentant ainsi le nombre de BTS nécessaires et les coûts associés.

## Architecture du Réseau GSM (3)

Les types de BTS incluent :
- **BTS rayonnantes** : Pour les grandes zones.
- **BTS ciblées** : Pour les zones à forte densité d'abonnés.
- **Micro BTS** : Pour les lieux très peuplés.

Les antennes peuvent être omnidirectionnelles, bidirectionnelles, ou tri-sectorielles.

## Architecture du Réseau GSM (4)

Chaque BTS est connectée à un **BSC (Base Station Controller)** qui joue le rôle de concentrateur, généralement via des liaisons filaires comme la fibre optique.

## Le GSM : L'Interface Radio (1)

Les bandes de fréquences utilisées pour le GSM sont :
- **Voie montante** : 890-915 MHz pour GSM et 1710-1785 MHz pour DCS 1800.
- **Voie descendante** : 935-960 MHz pour GSM et 1805-1880 MHz pour DCS 1800.

Chaque porteuse utilise une bande de fréquence de 200 kHz, avec 125 fréquences disponibles.

## Le GSM : L'Interface Radio (2)

Le partage en temps **TDMA (Time Division Multiple Access)** divise chaque porteuse en 8 intervalles de temps (slots), permettant ainsi 8 communications simultanées par porteuse. Chaque slot dure 0,577 ms, et une trame complète dure 4,616 ms.

## GSM (Mise sous Tension)

La mise sous tension d'un mobile GSM se fait en trois étapes :
1. **Sélection de PLMN (Public Land Mobile Network)** : Identification du réseau mobile préféré.
2. **Sélection de la cellule** : Recherche et classement des cellules candidates par signal de réception.
3. **Inscription sur le réseau** : Contact avec le HLR (Home Location Register), EIR (Equipment Identity Register), et AuC (Authentication Center).

## GSM : Établissement d'un Appel (1)

Lorsqu'un mobile initie un appel, la BTS transmet le numéro appelé au MSC (Mobile Switching Center) via un BSC. Le MSC vérifie les caractéristiques de l'abonné dans les registres HLR et VLR (Visitor Location Register) avant d'autoriser l'allocation d'un canal par la BTS pour transporter la voix et établir la connexion vers le destinataire.

## GSM : Établissement d'un Appel (2)

Pour recevoir un appel, le MSC vérifie auprès du HLR les autorisations et les références du MSC du VLR où se trouve le mobile. Le MSC destinataire établit une liaison avec les BSC et BTS correspondants pour acheminer la communication.

## GSM : Le Handover

Le handover permet à un mobile de passer d'une cellule à une autre en maintenant la communication. Le mobile se connecte toujours à l'antenne avec le signal le moins atténué, connu grâce au BCCH (Broadcast Control Channel). En cas de signal faible, le mobile écoute les cellules voisines et envoie les résultats à la BTS, qui décide du handover.

## GPRS : General Packet Radio Service

Le **GPRS**, une extension du GSM, introduit la transmission en mode paquet, permettant des débits jusqu'à 171 Kbit/s. L'**EDGE (Enhanced Data for Global Evolution)** améliore le GPRS en permettant des transferts de données à haut débit.

## Architecture du Réseau GPRS

Les stations de base évoluent avec des mises à jour logicielles. Le BSC est doublé par un **PCU (Packet Controller Unit)**, et des commutateurs de paquets **SGSN (Serving GPRS Support Node)** et **GGSN (Gateway GPRS Support Node)** sont ajoutés pour gérer les enregistrements, authentifications, et connexions aux réseaux IP.

## UMTS

Le **UMTS (Universal Mobile Telecommunication System)** est un système de troisième génération (3G) avec des débits allant jusqu'à 2 Mb/s en mode statique et 384 Kbits/s en mobilité. Il utilise des bandes de fréquences de 1900-1980 MHz et 2010-2170 MHz et des modes d'accès **FDD (Frequency Division Duplex)** et **TDD (Time Division Duplex)**.

## Architecture UMTS

Le réseau UMTS conserve le cœur du réseau GPRS, mais toutes les stations de base et contrôleurs doivent être mis à jour (**NodeB** remplace **BTS** et **RNC** remplace **BSC**). Le réseau **UTRAN (UMTS Terrestrial Radio Access Network)** est ainsi formé.

## Inscription auprès d’un Réseau

Un mobile UMTS s'inscrit auprès du réseau en choisissant un PLMN et une cellule. Pour l'inscription au domaine **PS (Packet Switched)**, le mobile communique avec le SGSN via le RNC. Le SGSN vérifie les informations dans le HLR et le fichier EIR avant d'inscrire le mobile et de lui attribuer une adresse IP via le GGSN.

## UMTS : Handover (1)

L'UMTS utilise plusieurs canaux pour améliorer la communication :
- **Softer Handover** : Le mobile utilise plusieurs canaux de différentes antennes.
- **Soft Handover** : Le mobile utilise plusieurs canaux appartenant à deux Node B différents. Le choix du canal optimal est géré par le RNC.

## 4G : LTE (1)

La 4G **LTE (Long-Term Evolution)** abandonne la commutation de circuit au profit du tout IP, utilisant **VoLTE (Voice over LTE)** pour les communications vocales. La nouvelle architecture comprend des éléments tels que **eNode B**, **SGW (Serving Gateway)**, **MME (Mobility Management Entity)**, **PGW (PDN Gateway)**, **HSS (Home Subscriber Server)**, et **UE (User Equipment)**.

## 4G : LTE (2)

Le processus de communication commence par l'attachement de l'UE au MME, suivi de la vérification du MME auprès du HSS de l'IMSI et du profil d'activité. Une connexion IP est ensuite établie entre PGW, SGW, eNB, et UE.

## 4G : Sécurité

Les défis de sécurité en LTE incluent l'authentification pour éviter l'utilisation frauduleuse du réseau, le chiffrement pour prévenir l'écoute des échanges, la vérification de l'intégrité des messages, et la mise en place d'identités temporaires (**TMSI**) pour protéger la localisation des utilisateurs.

## Authentification (1)

L'authentification LTE utilise une clé secrète partagée entre la SIM et le HSS. Le processus inclut l'envoi de l'IMSI en clair, la génération d'un nombre RAND par le HSS, et le calcul des valeurs XRES et AUTN. Une clé dérivée **Kasme** est envoyée au MME pour simplifier les demandes ultérieures.

## Identité Temporaire

L'IMSI unique permet de suivre un utilisateur. Pour protéger la vie privée, un identifiant temporaire **TMSI (Temporary Mobile Subscriber Identity)** est utilisé, chiffré entre l'UE et le MME. Le **GUTI (Globally Unique Temporary UE Identity)** est également utilisé pour les requêtes réseau.

## 4G : Fréquence

Les fréquences 4G varient par pays. En France, elles incluent les bandes **3**, **7**, **20**, et **28**. La technologie **OFDMA (Orthogonal Frequency-Division Multiple Access)** est utilisée pour augmenter les débits théoriques jusqu'à 300 Mb/s.

## Antennes

Les antennes 4G peuvent être omnidirectionnelles, bidirectionnelles, ou tri-sectorielles, placées stratégiquement pour optimiser la couverture et la capacité.

## 5G

La 5G introduit trois services principaux :
- **eMBB (enhanced Mobile Broadband)** : Pour les téléphones mobiles.
- **mMTC (Massive Machine Type Communication)** : Pour les IoT.
- **URLLC (Ultra Reliable Low Latency Communication)** : Pour les applications nécessitant une faible latence (< 1 ms). 

La technologie **MIMO (Multiple Input Multiple Output)** et les ondes millimétriques sont utilisées pour atteindre des débits >= 1 Gb/s.

## Attribution Fréquence 5G

Les bandes de fréquences 5G en France (3,4 à 3,8 GHz) ont été mises aux enchères par l'ARCEP. Les opérateurs **Free**, **Bouygues**, **Orange**, et **SFR** ont acquis des portions de ces bandes pour un total d'environ 3 milliards d'euros.

## 5G (Suite)

L'architecture 5G est similaire à celle de la 4G, mais avec moins de tunnels et la possibilité de communiquer directement via des adresses MAC sans utiliser IP. De nouveaux équipements internes comme l'**UPF (User Plane Function)** remplacent les anciens SGW et PGW, et le MME est remplacé par l'**AMF (Access and Mobility Function)** et le **SMF (Session Management Function)**. Le HSS est divisé en **AUSF (Authentication Server Function)** et **UDM (Unified Data Management)**.

# Chapitre 7 : La Couche Physique

## La Couche Physique

L'objectif de la couche physique est de relier physiquement deux éléments pour permettre la communication. Elle utilise diverses technologies telles que les signaux électriques, les ondes lumineuses et les ondes électromagnétiques, chacune ayant ses avantages et ses inconvénients.

## Types de Médium (1)

- **Câble à paires torsadées blindées (STP)** : Utilise des conducteurs de cuivre isolés et enroulés hélicoïdalement pour réduire le bruit électromagnétique. Les caractéristiques incluent une bande passante (BP) de 500 kHz, un débit de 10 à 1000 Mb/s et une longueur maximale de 100 m. Le coût est modéré.
- **Câble à paires torsadées non blindées (UTP)** : Comprend 4 paires de fils avec des débits allant jusqu'à 10 Gb/s et une longueur maximale de 100 m. Les catégories varient de 2 à 8, avec des débits respectifs de 4 Mb/s à 40 Gb/s. C'est le type de câble le moins onéreux.

## Types de Médium (2)

- **Fibre optique** : Offre une bande passante supérieure à 1 GHz et un débit de plus de 1 Tb/s sur des distances allant jusqu'à 10 000 km. Elle utilise des réflexions successives de la lumière, avec des fibres monomodes et multimodes, et des sources lumineuses comme les lasers ou les LED. Elle est exempte de bruit électromagnétique.

## Types de Médium (3)

- **Faisceau satellitaire** : Utilise des antennes directionnelles pour communiquer avec des satellites géostationnaires à 36 000 km de la terre, offrant un débit de 140 Mb/s.
- **Faisceau hertzien** : Utilise la bande ISM et d'autres bandes pour des communications de courte à moyenne distance (jusqu'à 200 m pour les normes Wi-Fi et Bluetooth, et jusqu'à 50 km pour le Wimax). Les débits varient de 1 Mb/s à 300 Mb/s selon les normes utilisées.

## Récapitulatif

- **Câble coaxial** : Débit de 10 Mb/s, longueur maximale de 200 à 500 m.
- **Paire torsadée (STP/UTP)** : Débit de 10 Mb/s à 10 Gb/s, longueur maximale de 100 m.
- **Fibre optique** : Débit de 100 Mb/s à 100 Gb/s, longueur de quelques km.
- **Faisceau satellitaire** : Débit de 140 Mb/s, distance de quelques centaines à quelques milliers de km.
- **Faisceau hertzien** : Débit de 1 Mb/s à 300 Mb/s, distance de quelques cm à quelques km.

## Raccordements Physiques en RJ45

- **Câble droit (straight-through cable)** : Utilisé pour connecter des équipements différents, les broches 1-2 sont pour l'émission et 3-6 pour la réception.
- **Câble croisé (crossover cable)** : Utilisé pour connecter des équipements similaires.

## Codage de l'Information

Il existe deux modes de transmission :
- **Bande de base** : Différents codages comme NRZ (Non-Return to Zero), Miller, Manchester, et bipolaire.
- **Bande modulée** : Modulation de sinusoïdes en amplitude, fréquence, phase, ou combinée.

## NRZ

Utilisé pour relier deux ordinateurs par câble croisé. Les inconvénients incluent l'absence de transition lors de longues séquences de 0 ou de 1, rendant la synchronisation difficile.

## Manchester

Utilisé pour les liaisons Ethernet. Chaque bit est codé avec une transition au milieu : montante pour 1, descendante pour 0. Il double la largeur de la bande passante par rapport à NRZ et ne supporte pas l'inversion des polarités.

## Modulations

- **Amplitude** : La hauteur de l'onde varie.
- **Fréquence** : La fréquence de l'onde varie.
- **Phase** : La phase de l'onde varie.

## Les Problèmes Physiques

- **Atténuation** : Baisse d'amplitude du signal nécessitant une régénération.
- **Discontinuité d'impédance** : Mauvaise adaptation du câble sur la prise.
- **Diaphonie** : Transmission de signaux entre fils proches, locale ou distante.
- **Test physique d'un câble** : Inclut le schéma de câblage, affaiblissement d'insertion, diaphonie, délai de propagation, et longueur de câble.

## Les Équipements Physiques

- **Câbles variés**.
- **Équipements actifs** : HUB, modem, répéteur, pont, routeur, et antennes.

## Protocoles (1)

- **Sigfox** : Protocole propriétaire pour longue distance (50 km+), très économe en énergie, débit de 0,3 à 50 kbps.
- **LoRaWAN** : Standardisé, utilisé par des opérateurs comme Bouygues et Orange, débit de 0,3 à 50 kbps.

## Protocoles (2)

- **Courte distance, sans internet** : Bluetooth, Z-Wave, 433 MHz, NFC, etc.
- **Courte distance, avec internet** : ZigBee, 6LowPan, Thread.
- **Haut niveau** : HTTP, XMPP, MQTT, DDS.

## Enocean

Utilise l'énergie photovoltaïque ou piézo-électrique pour fonctionner sans fil ni énergie. Utilisé pour les maisons intelligentes, fréquence 868 MHz, portée de 30 m intérieur à 300 m extérieur, débit de 125 kbps.

## Bluetooth (1)

Développé par Ericsson en 1994, norme 802.15.1, bande passante de 2,4 GHz, utilisant FHSS avec 79 canaux. Débit de 100 Mb/s sur 100 m.

## Bluetooth (2)

Fonctionne avec une topologie en étoile (piconet) avec un maître et jusqu'à 7 esclaves. Communication entre maître-esclave uniquement.

## Bluetooth (3)

Phases de connexion incluent l'inquisition et le paging. Utilise L2CAP et RFCOMM pour la communication.

## Z-Wave (1)

Développé par Zen-SYS, puces fabriquées par Sigma Designs. Utilisé pour la domotique, portée de 30 m intérieur et 300 m extérieur, débit de 9,6 kb/s à 100 kb/s, fréquence 868 MHz.

## Z-Wave (2)

Types de nœuds incluent les esclaves et les contrôleurs (primaire et secondaire). Les contrôleurs gèrent la carte des nœuds.

## Z-Wave (3)

Inclut des dispositifs pour l'alarme avec une durée de vie de 12 à 20 mois et un chiffrement AES 128 bits.

## 802.15.4 (1)

Utilisé par ZigBee et 6lowPAN, bande passante de 2,4 GHz ou 868 MHz. Utilise DSSS et des canaux sans chevauchement.

## 802.15.4 (2)

Le coordinateur envoie une trame beacon pour indiquer le PAN ID. Un nœud fait une requête d'association en indiquant le PAN ID et reçoit une adresse courte de 16 bits.

## Internet

## Différent Web

- **TOR (The Onion Router)** : Logiciel libre permettant une connexion anonyme via des relais, protégeant contre le traçage et la censure. Utilise le chiffrement et un passage par au moins trois relais.

## TOR (2)

L'utilisateur demande trois clés AES pour chacun des relais, encapsule les messages et les envoie via les relais. La sortie n'est pas chiffrée.

## TOR (3)

Également appelé "routage en oignon" en raison des multiples couches de chiffrement et de relais.

