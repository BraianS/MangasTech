# MangasTech

MangasTech é um projeto para organizar, inserir, editar, visualizar os Mangas. Usando Spring Boot com JSON Web Token (JWT), AngularJs e BootStrap no Front-End.

# Requisitos
* Java 1.8
* MySql

# Instalação

1. [Clone o repositorio MangasTech](https://github.com/BraianS/MangasTech.git).
1. Importe o projeto usando Maven

# Uso

 Iniciar o projeto.
> mvnw clear spring-boot:run

 Abra a Url
> http://localhost:8080/

# Construido com

* AngularJS 1.7.2
* BootStrap 3.3.7
* Thymeleaf
* JSON Web Token (JWT) 0.9.1
* Spring Boot
* Spring Data(Jpa)
* Spring Security
* Java 1.8
* MySql

# Explicação

Caso queirar alterar o nome da Database, basta abrir o `application.properties`

```
spring.datasource.url = jdbc:mysql://localhost:3306/DATABASE-NAME?useSSL=false
spring.datasource.username = USER
spring.datasource.password = PASSWORD
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
```

Se não quiser usar MySql pode comentar as linhas acima no `Application.properties`, e adicionar o H2 Database ao pom.xml.

```
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

# autor

**Braian Silva** - Twitter [@braiancode](https://twitter.com/braiancode) - Git [Git BraianS](https://github.com/BraianS)