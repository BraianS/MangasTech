# MangasTech

MangasTech é um projeto para organizar, inserir, editar, visualizar os Mangas. Usando Spring Boot com JSON Web Token (JWT), AngularJs e BootStrap no Front-End.

## URL

https://mangastech.herokuapp.com/

## Requisitos

* Java 1.8
* MySql
* Maven

## Instalação

1. [Clone o repositório MangasTech](https://github.com/BraianS/MangasTech.git).
1. Importe o projeto usando Maven
1. Instale o [lombok no eclipse](https://projectlombok.org/download)
    * Execute o download do jar e aponte para o eclipse.exe or sts.exe ou qualquer IDE .exe
    * Reinicie a IDE para completar
1. Se usar o Visual Studio Code basta [instalar o plugin do lombok](https://projectlombok.org/setup/vscode)

## Uso

 Digite no seu terminal para executar o projeto.
> mvnw clean spring-boot:run

 Abra a Url.
> http://localhost:8080/

*username*: **admin**
*password*: **@admin**

## Construído com

* AngularJS 1.7.2
* BootStrap 3.3.7
* JSON Web Token (JWT) 0.9.1
* Spring Boot
* Spring Data(Jpa)
* Spring Security
* Java 1.8
* MySql
* Lombok
* OpenAPI 3.0

## Contribuindo

* Faça um fork deste projeto
* Crie uma nova branch
* Commit as mudanças de sua branch
* Push para sua nova branch
* Envie um Pull request

## Explicação

Caso queira alterar o nome da Database, basta abrir o `application.properties`

```bash
spring.datasource.url = jdbc:mysql://localhost:3306/DATABASE-NAME?useSSL=false
spring.datasource.username = USER
spring.datasource.password = PASSWORD
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
```

Se não quiser usar MySql pode comentar as linhas acima no `Application.properties`, e adicionar o H2 Database ao pom.xml.

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

Para fazer delombok basta usar o comando
> mvnw lombok:delombok -Pdlombok

A saída estará na pasta `\target\generated-sources\delombok\`

Para acessar a documentação do Swagger

* http://localhost:8080/v3/api-docs
* http://localhost:8080/swagger-ui.html

## Autor

**Braian Silva** - Twitter [@braiancode](https://twitter.com/braiancode) - Git [Git BraianS](https://github.com/BraianS)

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](https://github.com/BraianS/MangasTech/blob/master/LICENSE) para detalhes

## Agradecimentos

* Inspirado por [Spring Boot Security JWT AngularJS](https://github.com/hendisantika/jwt-spring-boot-security-angular)