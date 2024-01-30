# API Transacao financeira - Bootcamp IBM
API de transações financeiras com simples implementação de frontend e backend

# Transação Financeira (Backend)
Para utilização deste projeto na máquina local basta que seja realizado um clone do repositório `git clone https://github.com/mfre1re/transacao-financeira-bootcampIBM.git`. Após o clone, abra o terminal, vá até o caminho onde está localizado o pom.xml e execute: `mvn clean install` para instalar as bibliotecas externas do projeto.

## Frameworks e Bibliotecas Utilizadas
- [Java JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html): ambiente de desenvolvimento para construção de aplicações utilizando a linguagem de programação Java.
- [Spring Boot](https://spring.io/projects/spring-boot): Framework para desenvolvimento rápido de aplicativos Java.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa): Facilita o acesso a dados usando o Java Persistence API (JPA).
- [Lombok](https://projectlombok.org/): Biblioteca que ajuda a reduzir a verbosidade do código Java.
- [MySQL](https://www.mysql.com/downloads/): sistema de gerenciamento de banco de dados relacional de código aberto.

## Descrição
O backend é a parte estrutural responsável por processar as requisições CRUD, estabelecendo a interface entre os clientes e a base de dados por meio dos endpoints da API. Aqui temos os devidos endpoints com suas funcionalidades tais como requisitar (GET) as transações ou categorias, inserir ou atualizar transações (POST), deletar transações, todas ou única (DELETE). 

## Endpoints da API

### 1. Listar todas as transações
<details><summary>Endpoint: GET  /transacao </summary>(http://localhost:8080/transacao): Retorna todas as transações cadastradas.</details>

### 2. Somar transações por categoria
<details><summary>Endpoint: GET  /transacao/somartransacoes?id={id}</summary>(http://localhost:8080/transacao/somartransacoes?id=1): Retorna a soma dos valores de transações para uma categoria específica.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/21cc871a-a373-434a-8a02-483f04b45783)
</details>

### 3. Inserir transações
<details><summary>Endpoint: POST  /transacao/inserir </summary>(http://localhost:8080/transacao/inserir): Insere uma ou mais transações na base de dados.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/142d4ed6-224f-47d9-892f-6aec174d15ac)
</details>

### 4. Atualizar transação
<details><summary>Endpoint: POST  /transacao/atualizar </summary>(http://localhost:8080/transacao/atualizar): Atualiza uma transação existente com base no ID.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/c110efef-ca4a-4c92-b615-32490efd6ee6)
</details>

### 5. Deletar transação por ID
<details><summary>Endpoint: DELETE  /transacao?id={id}</summary>(http://localhost:8080/transacao?id=3): Deleta uma transação usando o ID fornecido.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/b42f3571-709b-48e6-82f2-07ffde23c170)
</details>

### 6. Deletar todas as transações
<details><summary>Endpoint: DELETE  /transacao/apagartudo</summary>(http://localhost:8080/transacao/apagartudo): Deleta todas as transações da base de dados.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/111a257e-b18b-45d5-a4ae-d4aa9fa2c08b)
</details>

### 7. Listar todas as categorias
<details><summary>Endpoint: GET  /categoria</summary>(http://localhost:8080/categoria): Retorna todas as categorias cadastradas.
  
![image](https://github.com/mfre1re/transacao-financeira-bootcampIBM/assets/88170132/da4d9f60-281e-47fc-ba02-3710ce483481)
</details>


## Estruturação
O projeto está organizado em devidos pacotes "br.com.desafio02bootcamp" sendo estruturado assim os seguintes pacotes:
- config:

  *CORS* - para permitir que o backend receba solicitações de diferentes origens.
- controllers:
  
     *1. TransacaoController* - trata as operações relacionadas às transações financeiras fornecendo endpoints para realizar operações CRUD (Create, Read, Update, 
               Delete) sobre as transações.
  
     *2. CategoriaController* - gerencia as operações relacionadas as categorias de transações.
  
- entites:
  
     *1. TransacaoEntity* - representa uma transação financeira no sistema.
  
     *2. CategoriaEntity* - representa uma categoria associada as transações.
  
- repositories:

     *1. TransacaoRepository* - interage com o banco de dados para operações relacionadas às transações financeiras. Ele estende a interface `JpaRepository` do 
                Spring Data JPA, fornecendo métodos para realizar operações.
     *2. CategoriaRepository* - gerencia as operações relacionadas as categorias no banco de dados. Assim como o `TransacaoRepository`, ele estende a interface   
                `JpaRepository`.
- services:

     *1.TransacaoService* - encapsula a lógica de negócios relacionada as transações financeiras.

  
     *2.CategoriaService* - gerencia a lógica de negócios associadada as categorias de transações. Assim como o `TransacaoService`, ele interage com o 
            `CategoriaRepository` para realizar operações no banco de dados.

## Entendendo os dados
### TransacaoEntity
- **Atributos:**
  - `id` (Integer): Identificador único da transação.
  - `valor` (double): Valor da transação.
  - `categoria` (CategoriaEntity): Categoria associada à transação.
  - `data` (LocalDate): Data da transação.

### CategoriaEntity
- **Atributos:**
  - `id` (Integer): Identificador único da categoria.
  - `categoria` (String): Nome da categoria.
