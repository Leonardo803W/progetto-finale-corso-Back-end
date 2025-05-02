- dipendenze utilizzate lato back-end:

1. spring-boot-starter-data-jpa
Link: https://spring.io/projects/spring-data-jpa
Scopo: Fornire supporto per l'integrazione di JPA (Java Persistence API) con Spring, 
        rendendo più semplice la gestione delle operazioni di accesso ai dati e la configurazione delle entità.

2. spring-boot-starter-web
Link: https://spring.io/projects/spring-framework#overview
Scopo: Fornire le funzionalità necessarie per costruire applicazioni web, incluse RESTful APIs, utilizzando Spring MVC.

3. spring-boot-starter-validation
Link: https://spring.io/projects/spring-framework#overview
Scopo: Aggiunge la validazione dei dati alle applicazioni Spring utilizzando Hibernate Validator o altre implementazioni della Bean Validation.

4. cloudinary-http44
Link: https://cloudinary.com/documentation/java_integration
Scopo: Fornire un SDK per l'integrazione con Cloudinary, un servizio di gestione delle immagini e dei video, per caricare, archiviare e gestire media.

5. spring-boot-starter-mail
Link: https://spring.io/projects/spring-boot
Scopo: Fornire supporto per l'invio di email nelle applicazioni Spring Boot utilizzando JavaMail.

6. javafaker
Link: https://github.com/DiUS/java-faker
Scopo: Genera dati falsi per testare applicazioni, utile per popolare database o generare dati per demo.

7. springdoc-openapi-starter-webmvc-ui
Link: https://springdoc.org
Scopo: Fornire un modo per generare automaticamente documentazione OpenAPI per le API RESTful create con Spring MVC.

8. postgresql
Link: https://jdbc.postgresql.org
Scopo: Driver JDBC per connettersi a database PostgreSQL, utilizzato in applicazioni Java per l'accesso ai dati.

9. lombok
Link: https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok
Scopo: Riduce il boilerplate nel codice Java generando automaticamente metodi come getter, setter, e costruttori.

10. spring-boot-starter-test
Link: https://spring.io/projects/spring-boot
Scopo: Fornire strumenti e librerie per il testing delle applicazioni Spring Boot, inclusi JUnit, Mockito e AssertJ.

11. Spring Boot Starter Security
Link: https://docs.spring.io/spring-boot/redirect.html#boot-features-security
Scopo: Questa dipendenza fornisce un supporto di base per la sicurezza nelle applicazioni Spring Boot, integrando Spring Security. Abilita la protezione di base delle risorse e offre configurazioni predefinite per l'autenticazione e l'autorizzazione.

12. JJWT API
Link: https://github.com/jwtk/jjwt
Scopo: Questa libreria fornisce un'API semplice e intuitiva per la creazione e la verifica di JSON Web Tokens (JWT). Può essere utilizzata per gestire l'autenticazione e l'autorizzazione nelle applicazioni.

13. JJWT Implementation
Link: https://github.com/jwtk/jjwt
Scopo: Questa libreria fornisce l'implementazione effettiva dell'API JJWT. È necessaria per gestire la logica di creazione e parsing dei JWT.

14. JJWT Jackson
Link: https://github.com/jwtk/jjwt
Scopo: Questa dipendenza fornisce il supporto per la serializzazione e deserializzazione dei JWT utilizzando Jackson, una popolare libreria per la manipolazione di JSON in Java.

15. Spring Security Test
Link: https://docs.spring.io/spring-security/reference/
Scopo: Questa dipendenza fornisce strumenti e utility per testare la sicurezza nelle applicazioni Spring. Include funzionalità per simulare l'autenticazione e l'autorizzazione durante i test.

-----------------------------------------------------------------------------------

- base per le fetch dei dati viaggio: http://localhost:8080/api/viaggi
- base per fetch per salvare un viaggio: http://localhost:8080/api/viaggi/save
- base per fetch per trovare un viaggio: http://localhost:8080/api/viaggi/findById/{id}
- base per fetch per modificare un viaggio: http://localhost:8080/api/viaggi/modifyById/{id}
- base per fetch per cancellare un viaggio: http://localhost:8080/api/viaggi/deleteById/{id}
- base per fetch per trovare tutti i viaggi: http://localhost:8080/api/viaggi/fetchall

- tutti i metodi delle varie fetch hanno un controllo di accesso per il loro utilizzo, 
- i metodi di get possono utilizzarle sia admin e user ma per tutte le altre possono utilizzarle solo gli admin.

- base per le fetch dei dati user: http://localhost:8080/api/auth
- base per fetch per registrare: http://localhost:8080/api/auth/user/register (come ruolo user)
- - base per fetch per registrare: http://localhost:8080/api/auth/admin/register (come ruolo admin)
- base per fetch per log in: http://localhost:8080/api/auth/login

- la fetch sia per il registrarsi sia per il cancellare un utente entrambe manderrano una email di conferma alla email dell'utente

- base per le fetch dei dati cloudinary: http://localhost:8080/api/images
- base per fetch mandare immagini al cloud: http://localhost:8080/api/images/uploadme

- tutti i metodi delle fetch una volta eseguite se in positivo oltre al id deve uscire anche un messaggio di successo
- in caso negativo compariranno le eccezzioni di errore o generali o personalizzate.

-----------------------------------------------------------------------------------

- http://localhost:8080/api/viaggi/findById/{id} come risposta dovrebbe uscire cosi:

{
"data": {
        "id": 1,
        "stato": "Suriname",
        "regione": "honk kong",
        "provincia": "jong",
        "citta": "Oslo",
        "titolo": "Bar Beach",
        "descrizione": "Parti in spiaggia da perdere la testa!",
        "prezzo": 282.67
        },
"message": "Viaggio trovato con successo."
}

-----------------------------------------------------------------------------------
