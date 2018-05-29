# webstore
Webstore app created using Java, Maven, Spring MVC, Spring Webflow, Spring Security, Apache Tiles, Hibernate.

App to work properly needs a MySQL database. Connection settings can be adjusted in /src/main/resources/db.properties. 
At the first run app imports three basic users with the following credentials:
  1. user: admin, password: admin
  2. user: supervisor, password: supervisor
  3. user: user, password: user
Feel free to use them to see how webstore is working as different users with different authorities log in.
