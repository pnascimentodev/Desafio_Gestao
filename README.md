# Desafio Gestão - Project Management API

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.6**
- **PostgreSQL 15**
- **MapStruct** (mapeamento de DTOs)
- **Swagger/OpenAPI** (documentação da API)
- **Docker & Docker Compose**
- **Bean Validation**
- **Spring Data JPA**

## 📋 Pré-requisitos

- Docker e Docker Compose instalados
- Java 17+ (opcional, se quiser rodar sem Docker)

## 🐳 Como executar com Docker

### Opção 1: Apenas PostgreSQL (Desenvolvimento)
```bash
# Subir apenas o banco PostgreSQL
docker-compose -f docker-compose-dev.yml up -d

# Executar a aplicação localmente
./mvnw spring-boot:run
```

### Opção 2: Aplicação completa com Docker
```bash
# Subir a aplicação completa (PostgreSQL + App)
docker-compose up -d --build
```

## 🔗 URLs importantes

- **Aplicação**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Database**: PostgreSQL na porta 5432

## 📊 Credenciais do Banco

- **Host**: localhost
- **Port**: 5432
- **Database**: desafio_gestao
- **Username**: admin
- **Password**: admin123

## 🛠 Endpoints da API

### Projects
- **POST** `/projects` - Criar novo projeto
- **GET** `/projects` - Listar todos os projetos
- **GET** `/projects/{id}` - Buscar projeto por ID

## 📝 Exemplo de uso

### Criar um projeto:
```json
POST /projects
{
  "name": "Meu Projeto",
  "description": "Descrição do projeto",
  "startDate": "2024-01-01",
  "endDate": "2024-12-31"
}
```

### Resposta:
```json
{
  "id": 1,
  "name": "Meu Projeto",
  "description": "Descrição do projeto",
  "startDate": "2024-01-01",
  "endDate": "2024-12-31"
}
```

## 🏗 Arquitetura

O projeto segue os princípios da **Arquitetura Limpa** com as seguintes camadas:

- **Domain**: Entidades e repositórios
- **Application**: Serviços e mappers
- **Infrastructure**: Configurações
- **Presentation**: Controllers e DTOs

## ✨ Funcionalidades Implementadas

- ✅ **DTOs com Records**
- ✅ **MapStruct para mapeamento**
- ✅ **Tratamento global de erros com @ControllerAdvice**
- ✅ **Validações com Bean Validation**
- ✅ **Documentação automática com Swagger**
- ✅ **Docker para ambiente de desenvolvimento**
- ✅ **PostgreSQL como banco de dados**

## 🔧 Comandos úteis

```bash
# Parar os containers
docker-compose down

# Ver logs da aplicação
docker-compose logs -f app

# Ver logs do PostgreSQL
docker-compose logs -f postgres

# Rebuild da aplicação
docker-compose up -d --build app
```
