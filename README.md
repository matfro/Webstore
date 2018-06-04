# webstore
Webstore app created using Java, Maven, Spring MVC, Spring Webflow, Spring Security, Apache Tiles, Hibernate, REST, JUnit.

Application needs an SQL database to work properly. Connection settings can be adjusted in /src/main/resources/db.properties. 
At the first run application imports three basic users with the following credentials:
  1. username: admin, password: admin
  2. username: supervisor, password: supervisor
  3. username: user, password: user
    
Feel free to use them to see how webstore works as different users with different authorities sign in.

Remember to change the "hibernate.hbm2ddl_auto" property value to "update" after first run!
