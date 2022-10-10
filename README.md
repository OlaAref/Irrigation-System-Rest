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
- Configure the plots and list all details<br />
`http://localhost:8085/IrrigationSystem/plots/`

- List the agricultural crops and configure it.<br />
`http://localhost:8085/IrrigationSystem/crops/`

- Send a request to the sensor<br />
`http://localhost:8085/IrrigationSystem/sensor/call/{id}`

- All Endpoints are documented by Swagger and OpenApi at<br />
`http://localhost:8085/IrrigationSystem/swagger-ui.html`

## Unit Test Reports
-   You can generate the unit test report by run this commands at the project directory<br/>
`mvn clean test`<br/>
`mvn site -DgenerateReport=false`

- And unit test report will be available at<br/> `target/site/surefire-report.html`

- Code Coverage Report available at <br/>`target/site/jococo/index.html`
