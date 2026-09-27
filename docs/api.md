# Guia da API

A documentação executável fica em `http://localhost:8080/swagger-ui.html`. Exceto cadastro, login, refresh, recuperação e validação de convite, envie `Authorization: Bearer <access-token>`.

## Principais recursos

| Domínio | Endpoints |
| --- | --- |
| Autenticação | `POST /api/auth/register`, `/login`, `/refresh`, `/logout` |
| Senha e perfil | `POST /api/auth/forgot-password`, `/reset-password`, `/change-password`; `GET /api/auth/me`; `PUT /api/users/me` |
| Organizações | `GET|POST /api/organizations`; `GET|PUT /api/organizations/{id}`; `POST .../archive`, `.../restore` |
| Membros | `GET|POST /api/organizations/{id}/members`; `GET .../members/search`; `GET|PUT|DELETE .../members/{memberId}` |
| Equipes | `GET|POST .../teams`; `GET|PUT|DELETE .../teams/{teamId}` |
| Cargos | `GET|POST .../positions`; `GET .../positions/search`; `GET|PUT|DELETE .../positions/{positionId}` |
| Hierarquia | `GET /api/organizations/{id}/organization-chart` |
| Convites | `GET|POST .../invitations`; `POST .../{invitationId}/resend`; `DELETE .../{invitationId}`; `GET /api/invitations/validate/{token}`; `POST /api/invitations/accept` |
| Dashboard e auditoria | `GET .../{id}/dashboard`; `GET .../{id}/activities` |
| Notificações | `GET /api/notifications`, `/summary`; `POST /{id}/read`, `/read-all` |

Coleções paginadas retornam `content`, `page`, `size`, `totalElements`, `totalPages`, `first` e `last`. Use `page`, `size` e `sort`; filtros adicionais dependem do recurso (`search`, `status`, `teamId`, `action`, `from`, `to`).

## Erros

Erros retornam status HTTP adequado e um corpo estável com `timestamp`, `status`, `error`, `message`, `path` e `validationErrors`. A API usa `404` para um tenant inacessível, `403` para permissão insuficiente, `409` para conflitos de domínio e `400` para entrada ou token inválido.
