# Base-Test-Project

<h3>To launch app:</h3>
- pull this file <br>
- start docker on your local machine <br>
- open terminal and run: mvn clean package <br>
- open terminal and run command: 'docker build -t base-test-project .' <br>
- and then run: 'docker run -p 8085:8080 base-test-project' <br>
- in your browser open http://localhost:8085/swagger-ui/#/ and http://localhost:8085/h2-console <br>
  (JDBC URL: jdbc:h2:mem:test and User Name: root) <br>
- I added some scripts for creating table and filling it with data, so... <br>
  everything prepared to work <br>

<h3>Project description:</h3>
Base-Test-Project not a big app with one endpoint: <br>
GET: /persons/{id} : get person from DB by id <br>
In service's layer of an app its derives a person from DB by id and <br>
process his current age. Some of significant methods on the service <br>
layer were covered with tests. End-To-End tests also present. <br>

<h3>In this APP were used such technologies like:</h3>
- org.apache.maven, version 4.0.0 <br>
- java, version 17 <br>
- org.hibernate <br>
- spring boot <br>
- liquibase <br>
- swagger <br>
- junit <br>
- docker <br>
