🎬 Desafio Elite Dev: Aplicação de Lista de Filmes - Backend
Bem-vindo ao repositório do backend da aplicação de lista de filmes! Esta API foi desenvolvida com Java e Spring Boot, integrada à API do The Movie Database (TMDb) para fornecer dados de filmes e gerenciar uma lista de favoritos dos usuários. A API também utiliza o PostgreSQL como banco de dados.

📋 Visão Geral do Desafio
Objetivo
O objetivo deste desafio é desenvolver a parte do backend para uma aplicação de gerenciamento de filmes favoritos, integrando com a API do TMDb.

Funcionalidades
Gerenciamento de Favoritos: Adicionar e remover filmes favoritos.
Integração com TMDb: Realiza chamadas à API para obter detalhes dos filmes.
Compartilhamento de Favoritos: Gera links para compartilhar listas de favoritos.
Autenticação JWT: Utilizada para gerenciar a autenticação dos usuários.
Persistência com PostgreSQL: Armazena os dados de usuários e seus filmes favoritos.

🔧 Tecnologias Utilizadas
Linguagem: Java 17
Framework: Spring Boot
Banco de Dados: PostgreSQL
Autenticação: JWT (JSON Web Token)
Integração Externa: API do TMDb
Gerenciamento de Dependências: Maven

🚀 Instruções para Rodar o Projeto
Pré-requisitos
Java 17 ou superior instalado.
Maven instalado.
PostgreSQL instalado e configurado.
Conta na TMDb para obter a API key.

Passos para rodar o projeto:
Clone o repositório:

bash
git clone https://github.com/eduardocaio/movie-library-backend.git

Acesse a pasta do projeto:

bash
cd movie-library-backend

Crie o banco de dados no PostgreSQL:

sql
CREATE DATABASE name_database;

Configure as variáveis de ambiente no arquivo application.properties:

properties
spring.application.name=movie-library-backend

spring.datasource.url="URL banco de dados PostgreSQL"
spring.datasource.username="Usuário banco de dados PostgreSQL"
spring.datasource.password="Senha banco de dados PostgreSQL"

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key

tmdb.api.key="API Key de sua conta no TMDB"

host.front.mail="URL Front-End"

spring.mail.host="Host do e-mail que será usado"
spring.mail.port="Porta do e-mail que será usado"
spring.mail.username="E-mail"
spring.mail.password="Senha de Aplicativo E-mail"
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

Importante: Criação de Chaves Públicas e Privadas
Para garantir o funcionamento adequado do sistema, é necessário criar as chaves públicas e privadas. Essas chaves devem ser geradas e armazenadas no diretório resources do projeto.

Siga as instruções fornecidas no site CryptoTools para gerar suas chaves RSA. O processo geralmente envolve os seguintes passos:

Acesse o site CryptoTools: Vá para CryptoTools RSA Key Generator.
Configure a geração das chaves: Escolha o tamanho da chave e qualquer outra configuração necessária.
Gere as chaves: Clique no botão para gerar as chaves.
Baixe e salve as chaves: O site fornecerá arquivos contendo as chaves pública e privada. Salve esses arquivos no diretório resources do seu projeto.
Certifique-se de que as chaves estejam corretamente localizadas e nomeadas conforme a especificação do projeto para que possam ser utilizadas sem problemas.

Execute a aplicação:
A API estará disponível em http://localhost:8080.

🗄️ Endpoints Disponíveis
Autenticação
POST /auth/login: Realiza login do usuário e retorna um token JWT.
POST /auth/signup: Registra um novo usuário.

Filmes
GET /movies/search={nome do filme}: Busca filmes no TMDb com base no nome.
GET /movies/{id}: Obtém detalhes de um filme específico.
GET /movies/discover: Retorna lista dos filmes populares no momento

Favoritos
POST /users/add-movie/{id do filme}: Adiciona um filme à lista de favoritos do usuário autenticado.
DELETE /users/remove-movie/{id do filme}: Remove um filme da lista de favoritos.
GET /users/{nome de usuário}/favorites-movies: Retorna a lista de filmes favoritos do usuário.

🛠️ Estrutura do Projeto
/src/main/java: Contém os pacotes principais, como controllers, services e repositórios.
/src/main/resources: Arquivos de configuração, incluindo o application.properties.

🎯 Requisitos Não Funcionais
Segurança: Autenticação JWT para proteger os endpoints.
Persistência: Utilização do PostgreSQL para armazenamento dos dados de usuários e filmes favoritos.

📨 Entrega

Repositório Back-End: [Link para o código do backend](https://github.com/eduardocaio/movie-library-backend)
Repositório Front-End: https://github.com/eduardocaio/movie-library-front?tab=readme-ov-file

Deploy: O projeto pode ser acessado em https://cajuflix.vercel.app

Obrigado! 😊
