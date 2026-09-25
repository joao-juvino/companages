.PHONY: up down build test logs clean backend frontend
up:
	docker compose up --build -d
down:
	docker compose down
build:
	docker compose build
test:
	mvn test
	cd frontend && npm test -- --watch=false
logs:
	docker compose logs -f
clean:
	docker compose down -v
	mvn clean
	cd frontend && npm run build -- --delete-output-path
backend:
	mvn spring-boot:run
frontend:
	cd frontend && npm start
