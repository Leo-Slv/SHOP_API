# 🛍️ SHOP API

> Uma API RESTful robusta para e-commerce construída com Spring Boot, PostgreSQL e Docker

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg)](https://docs.docker.com/compose/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Índice

- [Visão Geral](#-visão-geral)
- [Arquitetura](#-arquitetura)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Instalação e Execução](#-instalação-e-execução)
- [API Endpoints](#-api-endpoints)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Testes](#-testes)
- [Contribuição](#-contribuição)

## 🎯 Visão Geral

O **SHOP API** é um sistema backend moderno para e-commerce que oferece funcionalidades completas de gerenciamento de usuários com foco em segurança, escalabilidade e boas práticas de desenvolvimento. 

### Características Principais

- ✅ **Arquitetura em Camadas** - Separação clara de responsabilidades
- ✅ **Segurança Robusta** - Hash de senhas com BCrypt
- ✅ **Validação Completa** - Validação de dados em múltiplas camadas
- ✅ **Tratamento de Erros** - Sistema global de tratamento de exceções
- ✅ **Containerização** - Ambiente Docker completo
- ✅ **Testes Unitários** - Cobertura abrangente com Mockito
- ✅ **Documentação Rica** - Responses estruturados com metadados

## 🏗️ Arquitetura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │────│     Service     │────│   Repository    │
│   (REST API)    │    │ (Business Logic)│    │  (Data Access)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │    Entities     │    │   PostgreSQL    │
│ (Data Transfer) │    │  (Domain Model) │    │   (Database)    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Padrões Implementados

- **MVC (Model-View-Controller)** - Organização da aplicação
- **DTO (Data Transfer Object)** - Transferência segura de dados
- **Repository Pattern** - Abstração do acesso a dados
- **Dependency Injection** - Inversão de controle com Spring
- **Exception Handling** - Tratamento centralizado de erros

## 🛠️ Tecnologias

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.4.3** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **Spring Security Crypto** - Criptografia de senhas

### Banco de Dados
- **PostgreSQL** - Banco de dados relacional
- **Hibernate** - ORM (Object-Relational Mapping)

### DevOps & Ferramentas
- **Docker & Docker Compose** - Containerização
- **Maven** - Gerenciamento de dependências
- **JUnit 5** - Testes unitários
- **Mockito** - Mock objects para testes

## ✨ Funcionalidades

### 👥 Gerenciamento de Usuários

| Funcionalidade | Descrição | Status |
|----------------|-----------|---------|
| 🔐 **Criação de Usuário** | Registro com validação completa e hash de senha | ✅ |
| 👤 **Consulta Individual** | Busca de usuário por ID com dados completos | ✅ |
| 📋 **Listagem** | Lista todos os usuários com dados públicos | ✅ |
| ✏️ **Atualização** | Atualização parcial de dados do usuário | ✅ |
| 🗑️ **Exclusão** | Remoção segura de usuário | ✅ |
| 🔒 **Validação de Senha** | Verificação de credenciais (preparado) | 🚧 |
| 📧 **Autenticação** | Login por email (estrutura preparada) | 🚧 |

### 🛡️ Segurança

- **Hash de Senhas**: BCrypt com força 12
- **Validação Robusta**: Múltiplas camadas de validação
- **Dados Sensíveis**: Exclusão automática de passwords nos responses
- **UUIDs**: Identificadores únicos seguros
- **Constraints**: Validações de integridade no banco

## 🚀 Instalação e Execução

### Pré-requisitos

- [Docker](https://www.docker.com/get-started) e [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/) (para clonar o repositório)

### 1️⃣ Clone o Repositório

```bash
git clone https://github.com/seu-usuario/shop-api.git
cd shop-api
```

### 2️⃣ Execute com Docker

```bash
# Inicia todos os serviços
docker-compose up -d

# Visualizar logs (opcional)
docker-compose logs -f
```

### 3️⃣ Verificar Status

```bash
# Verificar se os containers estão rodando
docker-compose ps

# Saída esperada:
# NAME       SERVICE    STATUS       PORTS
# db_shop    postgres   Up          0.0.0.0:5433->5433/tcp
# shop-api   app        Up          0.0.0.0:5400->5400/tcp
```

### 4️⃣ Testar a API

```bash
# Health check básico
curl http://localhost:5400/user

# Resposta esperada: Lista vazia de usuários
{
  "success": true,
  "message": "No users found",
  "data": [],
  "timestamp": "2025-01-XX..."
}
```

### 🛑 Parar a Aplicação

```bash
# Para todos os serviços
docker-compose down

# Para e remove volumes (apaga dados do banco)
docker-compose down -v
```

## 📡 API Endpoints

### Base URL
```
http://localhost:5400
```

### 👤 Usuários (`/user`)

#### ➕ Criar Usuário
```http
POST /user
Content-Type: application/json

{
  "username": "João",
  "surname": "Silva",
  "email": "joao.silva@email.com",
  "telephone": "+5511999887766",
  "password": "minhasenha123",
  "cpf": "12345678901",
  "cep": "01234567",
  "state": "São Paulo",
  "city": "São Paulo",
  "neighborhood": "Centro",
  "street": "Rua das Flores",
  "number": "123",
  "birthDate": "1990-05-15",
  "gender": "MALE"
}
```

**Resposta (201 Created):**
```json
{
  "success": true,
  "message": "User created successfully",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "username": "João",
    "surname": "Silva",
    "email": "joao.silva@email.com",
    "telephone": "+5511999887766",
    "cpf": "12345678901",
    "cep": "01234567",
    "state": "São Paulo",
    "city": "São Paulo",
    "neighborhood": "Centro",
    "street": "Rua das Flores",
    "number": "123",
    "isAdmin": false,
    "birthDate": "1990-05-15",
    "gender": "MALE",
    "phoneVerified": false,
    "emailVerified": false,
    "status": "ACTIVE",
    "creationTimestamp": "2025-01-XX...",
    "updateTimestamp": null
  },
  "timestamp": "2025-01-XX..."
}
```

#### 🔍 Buscar Usuário por ID
```http
GET /user/{id}
```

**Exemplo:**
```bash
curl http://localhost:5400/user/123e4567-e89b-12d3-a456-426614174000
```

#### 📋 Listar Todos os Usuários
```http
GET /user
```

**Exemplo:**
```bash
curl http://localhost:5400/user
```

> **Nota**: A listagem retorna dados públicos (sem informações sensíveis como telefone, CPF, endereço completo)

#### ✏️ Atualizar Usuário
```http
PUT /user/{id}
Content-Type: application/json

{
  "username": "João Carlos",
  "password": "novasenha456",
  "city": "Rio de Janeiro",
  "birthDate": "1990-05-20"
}
```

> **Nota**: Todos os campos são opcionais na atualização

#### 🗑️ Deletar Usuário
```http
DELETE /user/{id}
```

**Exemplo:**
```bash
curl -X DELETE http://localhost:5400/user/123e4567-e89b-12d3-a456-426614174000
```

### 📊 Formato de Resposta Padrão

Todas as respostas seguem o padrão `ApiResponse`:

**Sucesso:**
```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": { ... },
  "timestamp": "2025-01-XX...",
  "path": null
}
```

**Erro:**
```json
{
  "success": false,
  "message": null,
  "data": null,
  "error": "User not found with id: 123",
  "timestamp": "2025-01-XX...",
  "path": "uri=/user/123"
}
```

### 🚨 Códigos de Status HTTP

| Código | Significado | Quando Ocorre |
|--------|-------------|---------------|
| `200` | OK | Operação realizada com sucesso |
| `201` | Created | Usuário criado com sucesso |
| `400` | Bad Request | Dados de entrada inválidos |
| `404` | Not Found | Usuário não encontrado |
| `409` | Conflict | Email ou CPF já existem |
| `500` | Internal Server Error | Erro interno do servidor |

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/SHOP_API/
│   │   ├── 🎮 controller/           # Camada de apresentação
│   │   │   ├── UserController.java
│   │   │   └── dto/                 # Data Transfer Objects
│   │   │       ├── CreateUserDto.java
│   │   │       ├── UpdateUserDto.java
│   │   │       └── response/
│   │   │           ├── ApiResponse.java
│   │   │           └── UserResponseDto.java
│   │   ├── 🏢 service/              # Lógica de negócio
│   │   │   └── UserService.java
│   │   ├── 🗃️ repository/           # Acesso a dados
│   │   │   └── UserRepository.java
│   │   ├── 📊 entity/               # Entidades JPA
│   │   │   └── User.java
│   │   ├── ⚙️ config/               # Configurações
│   │   │   └── passwordConfig.java
│   │   ├── 🚨 exception/            # Tratamento de erros
│   │   │   ├── UserNotFoundException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   └── ShopApiApplication.java  # Classe principal
│   └── resources/
│       └── application.properties   # Configurações da aplicação
├── test/                           # Testes unitários
│   └── java/com/example/SHOP_API/
│       ├── service/UserServiceTest.java
│       └── ShopApiApplicationTests.java
├── 🐳 Docker/                      # Containerização
│   ├── Dockerfile
│   └── compose.yml
└── 📋 Configurações/
    ├── pom.xml                     # Dependências Maven
    ├── .gitignore
    └── README.md
```

## 🧪 Testes

### Executar Testes Unitários

```bash
# Com Docker (recomendado)
docker-compose exec app mvn test

# Localmente (requer Java 21 e Maven)
mvn test
```

### Cobertura de Testes

O projeto possui testes abrangentes para o `UserService`:

- ✅ **Criação de usuário** - cenários de sucesso e erro
- ✅ **Busca por ID** - usuário existe e não existe
- ✅ **Listagem** - retorno de lista de usuários
- ✅ **Atualização** - atualização parcial e usuário inexistente
- ✅ **Exclusão** - exclusão bem-sucedida e usuário inexistente

### Executar Teste Específico

```bash
mvn test -Dtest=UserServiceTest
```

## 🔧 Configurações

### Banco de Dados

As configurações do PostgreSQL estão no `compose.yml`:

```yaml
postgres:
  image: 'postgres:latest'
  environment:
    - 'POSTGRES_DB=shop_api'
    - 'POSTGRES_PASSWORD=Db@User2025!'
  ports:
    - '5433:5433'
```

### Aplicação

Configurações principais no `application.properties`:

```properties
# Servidor
server.port=5400

# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5433/shop_api
spring.datasource.username=postgres
spring.datasource.password=Db@User2025!

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 🚧 Próximos Passos

### Em Desenvolvimento

- [ ] **Autenticação JWT** - Sistema completo de login/logout
- [ ] **Autorização RBAC** - Controle de acesso baseado em roles
- [ ] **Produtos** - Entidade e CRUD completo
- [ ] **Carrinho de Compras** - Funcionalidades de e-commerce
- [ ] **Pedidos** - Sistema de pedidos e pagamentos
- [ ] **Documentação OpenAPI** - Swagger UI integrado

### Melhorias Planejadas

- [ ] **Cache Redis** - Performance otimizada
- [ ] **Logs Estruturados** - Monitoramento avançado
- [ ] **CI/CD Pipeline** - Deploy automatizado
- [ ] **Testes de Integração** - Cobertura completa
- [ ] **Rate Limiting** - Proteção contra abuso
- [ ] **Health Checks** - Monitoramento de saúde da aplicação

## 🤝 Contribuição

1. **Fork** o projeto
2. **Crie** uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. **Abra** um Pull Request

### Padrões de Código

- ✅ **Nomes descritivos** para variáveis e métodos
- ✅ **Comentários** em código complexo
- ✅ **Testes unitários** para novas funcionalidades
- ✅ **Validação** em todas as camadas
- ✅ **Tratamento de exceções** adequado

## 📞 Suporte

- **Issues**: [GitHub Issues](https://github.com/seu-usuario/shop-api/issues)
- **Discussões**: [GitHub Discussions](https://github.com/seu-usuario/shop-api/discussions)
- **Email**: seu.email@exemplo.com

---

<div align="center">

**Desenvolvido com ❤️ para a comunidade**

[![GitHub stars](https://img.shields.io/github/stars/seu-usuario/shop-api?style=social)](https://github.com/seu-usuario/shop-api/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/seu-usuario/shop-api?style=social)](https://github.com/seu-usuario/shop-api/network)

</div>
