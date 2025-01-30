run:
	docker compose up -d &
	mvn spring-boot:run

clean:
	docker compose down
	mvn clean
