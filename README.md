		Présentation
Ce document concerne la partie pratique du module de TAA.
L'objectif du module TAA est de parcourir les problématiques de structuration du code côté Back-end en implementant certains framework comme le framework SpringBOOt. Ainsi le but est de comprendre le développement des services ainsi que de leur articulation dans une architecture large.
Dans ce TP, le mapping relationnel object a été fait avec JPA et tout le développement Backend du sujet ci -dessous est réalisé par le frameWork SpringBoot.

		Template de projet pour le TP SPRING BOOT -JPA

Sujet (un doctolib pour voir les profs)

On souhaite développer l'application suivante de type prise de RDV pour tout professionnel. L’idée est de pouvoir connecter l’application à un agenda externe (zimbra, google) pour identifier les plages disponibles pour un utilisateur donné afin de pouvoir prendre un RDV. Chaque professionnel de l’application pourra se créer un compte, se loguer, mettre à disposition l’url de récupération des créneaux disponibles, définir la durée nominal d’un RDV et les intitulés possibles d’un RDV. 
Un utilisateur souhaitant consulter un professionnel pourra aussi se créer un compte, se loguer et consulter la liste des créneaux disponibles et réserver un créneau.

Prenez la liberté de compléter ce modèle métier au maximum en s’inspirant d’application de tableau de kanban existant. 
		I-Diagramme UML


		II- Quick Start
		
1- Récupérer le projet sur le Github par une commande git clone 
	url: https://github.com/CecilebougmaIstic/TP3SpringCecile.git
2- En ligne de commande, démarrr le contener pour la base de données:
	sudo docker start some-mysql myadmin
3-Sur un browser ouvrir PHPMyAdmin à l'adresse ip: http://localhost:8082
	 utilisateur root, password my-secret-pw
4-Pour lancer l'application:
	pakage doctolib_service.data.jpa, click droit sur la classe SampleDataJpaApplication et sélectionner run as java application.
5 -Pour effectuer les tests des Api, se connecter à Swagger à l'Url:
	http://localhost:8080/swagger-ui.html
Veuillez consulter la section partie Swagger ci -dessous ( III Description du projet) pour prendre connaissances de certains spécificités à effectuer sur les données générées par Swagger avant d'executer sur Swagger.
		
		
		
		
		
		III- Description du Projet


# partie Gestion des Exeptions

Les exeptions ont été gérées par des messages String personnalisés passées en argument à la méthode ResponseEntity(). Le code de statut associé à l'exception est également retourné en argument de la méthode ResponseEntity().
Exemple: return new ResponseEntity<>("Error deleting the user:" + e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
Nous avons également décid de gérer le message d'erreur affiché lorqu'un de nos entités est introuvable. Par exemple si un Customer n'existe pas.Le pakage doctolib_service.data.jpa.exeption a été créé, dans lequel, dans classe nommée NotFoundDoctolibExeption.java, nous avons personnalisé le retour de message d'erreur 500 Internal Server Error pour le cas où une ressource cherchée est introuvable; le code 404 Not Found, correspondant est retourné. Nous avons placé l'annotation 
@ResponseStatus(HttpStatus.NOT_FOUND) au dessus de la classe .java.
De même la classe AlreadyExistDoctolibExeption permet de générer un message qui informa à un customer qu'un RDV est déjà résevé.

-----OU
Dans le package doctolib_service.data.jpa.exeption, des classes de type exception ont permis de gérer dans les différentes lévés d'exception de cette application. La classe nommée DoctolibSServiceExceptionResponse a permit de créer des messages personnalisés d'ereurs.
Au niveau des différentes classes (xxControler) du package doctolib_service.data.jpa.web, on nte des méthodes dont le type de retour est de type ResponseEntity, et d'autres méthodes dont le type de retour  DoctolibSServiceExceptionResponse. Dans une première version, seul le type de retour ResponseEntity a été utilisé dans le package doctolib_service.data.jpa.web. Puis dans les Versions ultérieures de l'application, où le package doctolib_service.data.jpa.exeption a été développé, certaines méthodes dans les classes du  package doctolib_service.data.jpa.web ont été réfactorées afin de retourner un type de réponse de type DoctolibSServiceExceptionResponse.


# partie Spring AOP

L'implémentattion de Aspect J dans le projet est géré au niveau du package doctolib_service.data.jpa.aspectJ.
un aspect de logging(Classe TraceInvocationAspect.java) et un système de supervision des performances de notre application.
La Classe TraceInvocationAspect.java grâce à 1 @pointcut, une méthode annotée avec @Pointcut (@Pointcut("execution(* doctolib_service..*Controller.*(..))") public void methodLog() {}) permet tracer les appels aux méthodes de toutes les classes dont le nom se termine par Controller et situées dans le package doctolib_service.
Le greffon (advice) @Before s’applique avant l’appel à une méthode et le greffon avec l’annotation @AfterThrowing est exécuté uniquement si la méthode se termine par une exception.
Pour tracer des alertes lorsque l’exécution de certaines méthodes dure trop longtemps. Nous avons définit l’annotation @Supervision avec l’attribut dureeMillis qui permet de préciser la durée maximale d’exécution en millisecondes. Pour plus de détails voir les classe d'interface Supervision.java et la classe MonitorePerfAspect.java. l'anntation @Around permet de définir un greffon qui doit envelopper l’appel à une méthode. La coupe est définie par :"@annotation(supervision)".Cette déclaration se lit : lors de l’appel à une méthode qui porte l’annotation donnée par supervision. Ce dernier correspond au paramètre du même nom de la méthode et qui est du type Supervision. Donc ce greffon concerne l’appel à toutes les méthodes annotées avec @Supervision. Exemple voir dans la classe AppointementController au dessus de la métode getAllAppointementsOfCustomer().
un ordre d'exécution des 2 aspects a été implémenté.

# partie Connexion à l'API Zimbra

Pour récupérer les rdv d'un Worker à partir de son compte sur Zimbr, il faudrait:
1- Créer un compte pour ce utilisateur sur le compte Zimbra de l'Université de Rennes1 ou tout utilisateur ayant un compte sur Zimbra.
 Exemple: cecile.bougma@etudiant.univ-rennes1.fr
 2- Créer ce utilisateur en tant que Worker dans la base de données doctolibServiceSpringB.


