# Companages

Companages é uma aplicação fullstack para organizar empresas e equipes. Cada usuário possui workspaces privados onde gerencia organizações, membros, cargos e as associações entre pessoas e responsabilidades.

## Funcionalidades

- Cadastro e login com JWT e senhas BCrypt
- Dashboard com indicadores e membros recentes
- CRUD completo de organizações, membros e cargos
- Associação de membros a cargos
- Isolamento de dados por proprietário em todos os recursos
- Validação de formulários, feedback de ações e estados de loading, erro e vazio
- Interface responsiva em estilo SaaS
- API documentada com OpenAPI/Swagger

## Stack

| Camada | Tecnologias |
| --- | --- |
| Frontend | Angular 22, TypeScript strict, Router, Reactive Forms, HttpClient, Signals/RxJS, SCSS |
| Backend | Java 17, Spring Boot 3, Spring Web, Data JPA, Security, Validation, JWT |
| Banco | PostgreSQL 17, Flyway |
| Infraestrutura | Docker, Docker Compose, Nginx |
| Testes | JUnit, Spring Boot Test, MockMvc, H2, Vitest |

## Arquitetura

```text
Angular / Nginx (:4200)
        │ /api
        ▼
Spring Boot REST API (:8080)
        │ JPA + Flyway
        ▼
PostgreSQL
```

O backend é um monólito modular simples. Controllers tratam HTTP, services concentram regras de negócio e ownership, repositories restringem consultas ao usuário autenticado e DTOs separam a API das entidades JPA.

## Modelo de domínio

```text
User 1 ── * Organization
Organization 1 ── * Member
Organization 1 ── * Position
Organization 1 ── * Assignment
Assignment * ── 1 Member
Assignment * ── 1 Position
```

Uma associação só pode combinar membro e cargo da mesma organização. Exclusões em cascata mantêm o domínio consistente.

## Executando com Docker

Pré-requisitos: Docker Desktop com Docker Compose.

```bash
cp .env.example .env
docker compose up --build
```

No PowerShell:

```powershell
Copy-Item .env.example .env
docker compose up --build
```

Troque `POSTGRES_PASSWORD` e `JWT_SECRET` no `.env` antes de ambientes compartilhados.

| Serviço | URL |
| --- | --- |
| Aplicação | http://localhost:4200 |
| API | http://localhost:8080/api |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Health check | http://localhost:8080/actuator/health |

Para encerrar:

```bash
docker compose down
```

Use `docker compose down -v` apenas se também quiser apagar os dados locais do PostgreSQL.

## Execução separada

Backend (com PostgreSQL disponível e variáveis configuradas):

```bash
./mvnw spring-boot:run
```

No Windows: `mvnw.cmd spring-boot:run` ou `mvn spring-boot:run`.

Frontend:

```bash
cd frontend
npm ci
npm start
```

O servidor de desenvolvimento usa `proxy.conf.json`, portanto o frontend continua consumindo apenas `/api`.

## Variáveis de ambiente

| Variável | Descrição |
| --- | --- |
| `POSTGRES_DB` | Nome do banco |
| `POSTGRES_USER` | Usuário do PostgreSQL |
| `POSTGRES_PASSWORD` | Senha do PostgreSQL |
| `DATABASE_URL` | URL JDBC usada fora do Compose |
| `JWT_SECRET` | Chave de assinatura (mínimo de 32 caracteres) |
| `JWT_EXPIRATION` | Validade do token em milissegundos |
| `CORS_ALLOWED_ORIGIN` | Origem permitida para o frontend |

O repositório contém somente valores locais de fallback e placeholders, nunca credenciais reais.

## Testes e build

```bash
# Backend
./mvnw verify

# Frontend
cd frontend
npm test -- --watch=false
npm run build
```

## API principal

### Autenticação

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`

### Organizações

- `POST /api/organizations`
- `GET /api/organizations`
- `GET|PUT|DELETE /api/organizations/{id}`

### Membros e cargos

- `POST|GET /api/organizations/{organizationId}/members`
- `GET|PUT|DELETE /api/organizations/{organizationId}/members/{memberId}`
- `POST|GET /api/organizations/{organizationId}/positions`
- `GET|PUT|DELETE /api/organizations/{organizationId}/positions/{positionId}`

### Associações e dashboard

- `POST|GET /api/organizations/{organizationId}/assignments`
- `DELETE /api/organizations/{organizationId}/assignments/{assignmentId}`
- `GET /api/dashboard`

## Segurança

- Senhas são persistidas exclusivamente como hashes BCrypt.
- O JWT é enviado como `Authorization: Bearer <token>`.
- Todas as rotas de negócio exigem autenticação.
- O ID do proprietário nunca é aceito do cliente; ele vem do usuário autenticado.
- Buscas de organizações são filtradas por `id + ownerId`, e recursos filhos só são resolvidos depois da validação de ownership.
- Erros têm formato consistente e não expõem stack traces.

## Estrutura

```text
.
├── src/main/java/com/companages
│   ├── config, controller, dto, entity
│   ├── exception, repository, security, service
├── src/main/resources/db/migration
├── src/test
├── frontend/src/app
│   ├── core, features, layout, shared
├── Dockerfile
├── docker-compose.yml
└── .github/workflows/ci.yml
```

## Dados de demonstração

Não há credenciais ou dados fake obrigatórios. Registre um usuário pela tela inicial; o fluxo completo fica disponível imediatamente.

## Licença

Distribuído sob a licença [MIT](LICENSE).
