# Companages

Companages é uma aplicação SaaS multi-tenant para organizar empresas, equipes, pessoas, cargos e linhas de reporte em um único workspace.

## O que está incluído

- Autenticação com JWT, refresh token rotativo, logout e recuperação/troca de senha.
- Multi-tenancy por membership com papéis `OWNER`, `ADMIN`, `MANAGER` e `MEMBER`.
- Organizações com perfil completo, arquivamento e restauração.
- Membros com busca, filtros, equipe, cargo, gestor, status e perfil profissional.
- Equipes, cargos e organograma com proteção contra ciclos de hierarquia.
- Convites por email com expiração, reenvio, cancelamento e aceite.
- Dashboard por organização, auditoria de atividades e notificações persistidas.
- Interface Angular responsiva com seletor de organização, estados vazios e feedback de ações.
- API OpenAPI/Swagger, migrações Flyway, Docker Compose e dados demo opcionais.

## Stack

| Camada | Tecnologias |
| --- | --- |
| Frontend | Angular 22, TypeScript strict, Signals/RxJS, SCSS |
| Backend | Java 17, Spring Boot 3, Spring Security, Data JPA, Validation |
| Dados | PostgreSQL 17, Flyway |
| Desenvolvimento | Docker Compose, Nginx, Mailpit |
| Testes | JUnit, MockMvc, H2, Vitest |

## Início rápido

Requer Docker Desktop com Docker Compose.

```powershell
Copy-Item .env.example .env
docker compose up --build
```

Abra:

| Serviço | URL |
| --- | --- |
| Aplicação | http://localhost:4200 |
| API | http://localhost:8080/api |
| Swagger | http://localhost:8080/swagger-ui.html |
| Emails locais | http://localhost:8025 |
| Health check | http://localhost:8080/actuator/health |

Para encerrar, execute `docker compose down`. Use `docker compose down -v` somente quando quiser apagar também o banco local.

## Dados de demonstração

Defina `DEMO_DATA=true` no `.env` antes de subir os contêineres. A aplicação criará uma organização com equipes, cargos e hierarquia:

- Email: `demo@companages.local`
- Senha: `demo12345`

O seed é idempotente, opcional e desativado por padrão.

## Execução sem Docker

Configure um PostgreSQL e as variáveis de ambiente; depois:

```powershell
mvn spring-boot:run
cd frontend
npm ci
npm start
```

O frontend de desenvolvimento usa proxy para `/api`. Para inspecionar emails, mantenha um SMTP local na porta `1025` ou configure `MAIL_HOST` e `MAIL_PORT`.

## Envio real com EmailJS

O Mailpit continua sendo o padrão local. Para enviar convites e recuperação de senha para endereços reais, crie no EmailJS um template transacional com:

- **To Email:** `{{to_email}}`
- **Subject:** `{{subject}}`
- **Body:** use `{{message}}` e um botão ou link apontando para `{{link}}`

Depois preencha no `.env`:

```dotenv
MAIL_PROVIDER=emailjs
EMAILJS_SERVICE_ID=service_xxxxxxx
EMAILJS_TEMPLATE_ID=template_xxxxxxx
EMAILJS_PUBLIC_KEY=xxxxxxxxxxxxxxx
EMAILJS_PRIVATE_KEY=xxxxxxxxxxxxxxx
```

A chave privada é opcional no protocolo do EmailJS, mas é recomendada porque o envio é feito pelo backend. No painel do EmailJS, habilite requisições de API fora do navegador quando essa proteção estiver ativa. Reinicie o backend após alterar o `.env`.

## Configuração

| Variável | Uso |
| --- | --- |
| `POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD` | Banco PostgreSQL |
| `DATABASE_URL` | URL JDBC fora do Compose |
| `JWT_SECRET` | Assinatura JWT; use um segredo longo e aleatório |
| `JWT_EXPIRATION` | Duração do access token em ms |
| `REFRESH_TOKEN_EXPIRATION` | Duração do refresh token em ms |
| `FRONTEND_URL`, `CORS_ALLOWED_ORIGIN` | URLs públicas do frontend |
| `MAIL_HOST`, `MAIL_PORT`, `MAIL_FROM` | SMTP |
| `MAIL_PROVIDER` | `smtp` para Mailpit/SMTP ou `emailjs` para envio real |
| `EMAILJS_SERVICE_ID`, `EMAILJS_TEMPLATE_ID`, `EMAILJS_PUBLIC_KEY`, `EMAILJS_PRIVATE_KEY` | Integração opcional com EmailJS |
| `DEMO_DATA` | Ativa o seed local opcional |

Nunca grave segredos reais no repositório.

## Validação

```powershell
mvn verify
cd frontend
npm test -- --watch=false
npm run build
```

## Documentação

- [Plano de evolução](PROJECT_PLAN.md)
- [Arquitetura](docs/architecture.md)
- [Modelo de dados](docs/database.md)
- [Guia da API](docs/api.md)

## Licença

Distribuído sob a licença [MIT](LICENSE).
