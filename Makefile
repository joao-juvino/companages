run:
	docker compose up -d &
	mvn spring-boot:run

clean:
	docker compose down
	mvn clean

test:
	mvn test

help: 
        echo "type 'make run' to run the current application"
