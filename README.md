# Task Management API

Este projeto implementa uma API RESTful para gerenciamento de tarefas, incluindo funcionalidades de registro, login e operações CRUD de tarefas, com autenticação via JWT e armazenamento de dados em um banco de dados MySQL.

## Tecnologias

- **Spring Boot** (Framework principal)
- **Spring Security** (Para segurança e autenticação)
- **JWT (JSON Web Token)** (Para autenticação sem estado)
- **MySQL** (Banco de dados relacional)
- **Spring Data JPA** (Para acesso ao banco de dados)
- **Lombok** (Para redução de código boilerplate)

## Endpoints

### Autenticação

#### **POST /api/auth/register**
Registra um novo usuário.

**Body (JSON)**:
```json
{
  "username": "nome_do_usuario",
  "password": "senha"
}
```
Resposta:

```json
{
  "message": "Usuário registrado com sucesso!"
}
```
POST /api/auth/login
Realiza o login e retorna um token JWT.

Body (JSON):

```json
{
  "username": "nome_do_usuario",
  "password": "senha"
}
```
Resposta (JSON):

```json
{
  "token": "jwt_token_aqui"
}
```
Tarefas
GET /api/tasks/{username}
Recupera todas as tarefas de um usuário específico.

Resposta (JSON):

```json
[
  {
    "id": 1,
    "title": "Título da Tarefa",
    "description": "Descrição da Tarefa",
    "completed": false
  }
]
```
POST /api/tasks/{username}
Cria uma nova tarefa para o usuário.

Body (JSON):
```json
{
  "title": "Título da Tarefa",
  "description": "Descrição da Tarefa",
  "completed": false
}
```
Resposta (JSON):

```json
{
  "id": 1,
  "title": "Título da Tarefa",
  "description": "Descrição da Tarefa",
  "completed": false
}
```
PUT /api/tasks/{id}/{username}
Atualiza uma tarefa existente.

Body (JSON):

```json
{
  "title": "Título Atualizado",
  "description": "Descrição Atualizada",
  "completed": true
}
```
Resposta (JSON):

```json
{
  "id": 1,
  "title": "Título Atualizado",
  "description": "Descrição Atualizada",
  "completed": true
}
```
DELETE /api/tasks/{id}/{username}
Deleta uma tarefa existente.

Resposta (JSON):

```json
{
  "message": "Tarefa excluída com sucesso."
}
```
Configuração do Projeto
Banco de Dados MySQL
Este projeto usa o MySQL como banco de dados. Para configurar o banco de dados, crie um banco de dados chamado AV2 e configure as credenciais no arquivo src/main/resources/application.properties:

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/AV2
spring.datasource.username=root
spring.datasource.password=SuaSenhaAqui
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```
Dependências
Certifique-se de ter as dependências necessárias no seu pom.xml:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
Geração do Token JWT
A geração do token JWT é realizada utilizando a classe JwtUtil.java, onde a chave secreta é gerada e usada para assinar os tokens. O token tem um tempo de expiração de 10 horas.

```java
public String generateToken(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
            .signWith(SECRET_KEY)
            .compact();
}
```
Links:
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
