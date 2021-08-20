to run this project correctly you need add application.properties file with the content:

spring.datasource.url= ```url of database```

spring.datasource.username=```username in database```

spring.datasource.password=```password of user in database```

spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.jpa.show-sql=true

spring.datasource.jpa.hibernate.ddl-auto=update

spring.datasource.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy

//management.endpoints.web.exposure.include="*" ```optional if include micrometer```

spring.thymeleaf.cache=false  

spring.security.oauth2.client.registration.google.client-id= ```id of google api client```

spring.security.oauth2.client.registration.google.client-secret= ```secrot of google api```

spring.security.oauth2.client.registration.facebook.client-id= ```id of facebook api client```

spring.security.oauth2.client.registration.facebook.client-secret= ```secret of facebook client```
