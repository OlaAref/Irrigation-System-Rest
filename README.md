# Irrigation-System-Rest
Automatic irrigation system by Spring boot and Spring Rest

## Technologies Used
-   Java v17
-   maven
-   Spring boot v2.6.2
-   Spring Data Jpa 
-   Spring Rest
-   Hibernate v5.6.3
-   MySql

## How to Run
- Run sql scripts available at `sql` directory.
- Download the zip or clone the Git repository.
- Import the project to your IDE or go to project directory and run

	`mvn spring-boot:run`
	
## Important Rest Endpoints
- Configure the plots and list all details__
`http://localhost:8085/IrrigationSystem/plots/`

- List the agricultural crops and configure it.__
`http://localhost:8085/IrrigationSystem/crops/`

- Send a request to the sensor__
`http://localhost:8085/IrrigationSystem/sensor/call/{id}`

- All Endpoints are documented by Swagger and OpenApi at __
`http://localhost:8085/IrrigationSystem/swagger-ui.html`
