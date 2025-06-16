# 🚗 CRUD de Veículos - Spring Boot + MySQL

Projeto desenvolvido para fins de avaliação técnica em um processo seletivo. Trata-se de um sistema CRUD (Create, Read, Update, Delete) para gerenciamento de veículos, com autenticação de usuários e interface web moderna utilizando Thymeleaf e Bootstrap.

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
- **MySQL** (como banco de dados relacional)
- **Bootstrap 5** (estilização e responsividade)
- **IntelliJ IDEA** (IDE utilizada no desenvolvimento)
- **Maven** (gerenciador de dependências)

---

## 👤 Autenticação

O sistema possui dois tipos de usuários:

- `admin` → pode **adicionar, editar e remover veículos**
- `user` → pode **visualizar** a lista de veículos e **clicar em "Comprar"**

As credenciais estão definidas em memória no arquivo `WebSecurityConfig.java`:

.withUser("admin").password("{noop}admin").roles("ADMIN")
.withUser("user").password("{noop}user").roles("USER")

Estrutura do Projeto

crud-veiculos/
├── src/
│   ├── main/
│   │   ├── java/com/lojaveiculos/
│   │   │   ├── controllers/
│   │   │   ├── models/
│   │   │   ├── repositories/
│   │   │   └── WebSecurityConfig.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── veiculos/
│   │       ├── static/
│   │       │   └── css/style.css
│   │       └── application.properties

📸 Funcionalidades
✅ Listar veículos

✅ Cadastrar novo veículo (somente admin)

✅ Editar e excluir veículo (somente admin)

✅ Comprar veículo (usuário comum)

✅ Mensagem de sucesso após compra

✅ Estilização com tema moderno via CSS e Bootstrap

✅ Controle de acesso baseado em perfil

🚀 Como Executar
1. Clone o projeto:

git clone https://github.com/seu-usuario/crud-veiculos.git
cd crud-veiculos

2. Configure o banco de dados (application.properties).

3. Execute o projeto via IntelliJ ou terminal:

./mvnw spring-boot:run

4. Acesse:

http://localhost:8080/veiculos
