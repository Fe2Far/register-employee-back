# Employees Register
API do sistema de cadastro de funcionários com Java e Spring Boot.
### Detalhes da API RESTful
A API RESTful de cadastro de funcionários contém as seguintes características:  
* Projeto criado com Spring Boot e Java 8
* Banco de dados H2 (Memória) com JPA e Spring Data JPA
* Testes unitários e de integração com JUnit e Mockito

### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
```
git clone https://github.com/Fe2Far/register-employee-back.git
cd register-employee-back
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```
### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.