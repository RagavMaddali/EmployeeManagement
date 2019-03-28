FROM openjdk:8-jdk-alpine
WORKDIR /
ADD / / 
COPY . .
RUN ./gradlew clean build -x test
CMD java -jar ./build/libs/EmployeeManagement-0.0.1.jar