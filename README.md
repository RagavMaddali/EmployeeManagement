Employee Management:

Deployment Instructions 

Jar file:

Prerequisite : Run the project available at https://github.com/RagavMaddali/EmployeeManagement.git in the same machine where this project to be deployed or deploy it anywhere and configure the url host in appliction.proprties

1) Replace the credentials of mongoDB (spring.data.mongodb.uri),Kafka (spring.kafka.bootstrap-servers , spring.kafka.consumer.group-id, jsa.kafka.topic) in the application.properties file
2) From command prompt navigate to the folder where the build.gradle file located
3) Run the command "gradle clean"
4) Run the command "gradle bootJar" to create jar files. Location : ./build/libs/EmployeeManagement-0.0.1.jar 
5) Navigate to ./build/libs . Run the jar by "java -jar EmployeeManagement-0.0.1.jar"
6) In case of IDE, run the "com.project.employee.EmployeeManagementApplication" as application

Docker File:
Prerequisite : Run the project available at https://github.com/RagavMaddali/EmployeeManagement.git in the same machine where this project to be deployed or deploy it anywhere and configure the url host in appliction.proprties

1) Replace the credentials of mongoDB (spring.data.mongodb.uri),Kafka (spring.kafka.bootstrap-servers , spring.kafka.consumer.group-id, jsa.kafka.topic) in the application.properties file
2) From command prompt navigate to the folder. Dockerfile file will be present in this location.
3) Run "docker build -t 'employeemanagement:0.0.1' ." to create a docker image. Here name and version of the image is employeemanagement & 0.0.1
4) Run "docker run -p 8090:8014 -it employeemanagement:0.0.1" to start the application on port 8090.

Configuration:

port no : 8014 (configurable in application.properties) / exposed port from docker.

MongoDB : This api connects to the mongoDB cloud (Atlas). The Connection string is configurable in application.properties (can be replaced with onprem DB as well)

Kafka : This API connects to Kafka cluster .  The Connection string is configurable in application.properties (can be replaced with onprem DB as well)

REST end points :


PUT employee
Description : to insert employee record in mongodb. 
Body params : id,employeeName,employeeRole,phoneNumber,Age. [raw - application/JSON]

PATCH employee
Description : to update employee record in mongodb. 
Body params : id,employeeName,employeeRole,phoneNumber,Age. [raw - application/JSON]

DELETE employee/{id}
Description : to delete employee record in mongodb of given ID.

PUT employeeAddress
Description : to insert employee address in kafka cluster using given id.  
Body params : id,address. [raw - application/JSON]

GET employeeAddress/{id}
Description : to get the address of the employee for given ID from Kafka cluster.  
Header : Content-Type:application/json

GET viewemployee
Description : to get the aggregated details from Kafka and mongodb.  
Header : Content-Type:application/json

GET viewemployee/{id}
Description : to get the aggregated employee detail from Kafka and mongodb for given id.  
Header : Content-Type:application/json






