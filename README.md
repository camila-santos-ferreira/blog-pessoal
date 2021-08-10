# Projeto Blog Pessoal 

## Etapas Concluídas:

### Implementação do Projeto

   ✔ Criação do Projeto Spring  
   
   ✔ Configuração das Dependências do Projeto:
  - Spring Web  
  - Spring Boot Dev Tools
  - Spring Data JPA
  - MySQL Connector
  - Validation
  - Spring Security
  - Commons Codec
  
  ✔ Configuração do Banco de Dados:
  - application.properties
 
  ✔ Criação da Camada Model:
  - Postagem
  - Tema
  - Usuario
  - UsuarioLogin
 
  ✔ Criação do Relacionamento:
  - (One to Many) - Classe Tema com a Classe Postagem
  - (Many to one) - Classe Postagem com a Classe Tema
  - (One to Many) - Classe Usuario com a Classe Postagem
  - (Many to one) - Classe Postagem com a Classe Usuario
  
  ✔ Criação da Camada Repository:
  - PostagemRepository
  - UsuarioRepository 
  - TemaRepository
  
  ✔ Criação da Camada Controller:
  - PostagemController
  - UsuarioController
  - TemaController
 
  ✔ Criação da Camada Security:
  - BasicSecurityConfig
  - UserDetailsImpl 
  - UserDetailsService 
  
  ✔ Criação da Camada Service:
  - UsuarioService 
 
 ### Implementação de Testes
  
   ✔ Alteração da Dependência:
  - JUnit

  ✔ Configuração do Banco de Dados:
  - application.properties
 
 ✔ Criação da Camada Model:
  - UsuarioTest
 
  ✔ Criação da Camada Repository:
  - UsuarioRepositoryTest
  
  ✔ Criação da Camada Controller:
  - br.org.generation.blogPessoal.controller

### Funcionalidades
  
  ✔ Testes executados no Postman:
  - POST, PUT, GET e DELETE de usuários
  - POST, PUT, GET e DELETE de categorias
  - POST, PUT, GET e DELETE de postagens
