# 🏢 Companages

**Companages** é uma aplicação projetada para facilitar o gerenciamento de empresas. Com uma interface web intuitiva e eficiente, permite criar empresas, gerenciar membros, cargos e suas alocações.

## 🚀 Tecnologias Utilizadas

O projeto é composto por um backend robusto e um frontend dinâmico:

### Backend  
- **Spring Boot** – Framework para construção da API REST  
- **PostgreSQL** – Banco de dados relacional  
- **Docker** – Containerização para facilitar a execução  
- **Spring Security** – Autenticação e autorização jwt

### Frontend  
- **React.js** – Biblioteca para construção da interface  
- **Tailwind CSS** – Estilização eficiente e responsiva
- **React Router** – Navegação entre páginas

## Como executar o projeto 
### Instalar as dependências 
```
make init
```

### Executar o projeto 
```
make run
```

### Executar os testes 
```
make test
```

### Reiniciar o banco de dados 
```
docker compose down -v
docker compose up -d
```
