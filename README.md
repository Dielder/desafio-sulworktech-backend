# Café da Manhã - Gestão de Participantes

Este projeto é uma aplicação web para organizar os participantes dos cafés da manhã da empresa. Os colaboradores podem informar o que vão trazer para o café da manhã, e é possível marcar se trouxeram os itens no dia do evento.

## Funcionalidades

- Cadastro de participante com nome, CPF, opções de alimentos/bebidas e data do café.
- Marcação automática dos itens como "não trazidos" após a data.
- Exibição da lista de participantes com o que planejam trazer.
- Validação para evitar:
  - Duplicação de CPFs e opções para a mesma data.
  - Data de café da manhã no passado.

## Tecnologias Utilizadas

- **Backend**: Java, Spring Boot, PostgreSQL, JPA

## Pré-requisitos

- **Java 17** ou superior

## Executando o Projeto

### Backend

1. Clone o repositório:
   gitbash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   

Navegue até a pasta do backend e configure o banco de dados em src/main/resources/application.properties:

application.properties
spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
spring.datasource.username=<USER>
spring.datasource.password=<PASSWORD>
Compile e inicie o backend:

gitbash
./mvnw spring-boot:run

# Link do WEB Service: 
https://desafio-sulworktech-production-app.up.railway.app/index.html