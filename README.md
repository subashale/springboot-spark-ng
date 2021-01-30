# Data Analysis and Application Development
Full-stack application with Spring-boot, Apache Spark and Angular

# Requirements
1. Java 1.8+
2. Nodejs
3. Angular 11.0.5

# Run
1. download jar file from: https://drive.google.com/file/d/1jNmiv6U0mQVIPspuH1gMGdO3db0YgdQ0/view?usp=sharing
2. cd fullstack-ng  && npm install
3. java -jar springboot-spark-rest.jar or cd fullstack && ./mvnw spring-boot:run
4. cd fullstack-ng && ng serve
5. route to http://localhost:4200
6. upload files which are in data folder
7. refresh page then full results will be available in 1-2min.

# Approach / Requirement

1. write a console application that fulfills the above requirements
2. write an API (REST or GraphQL) that fulfills the above requirements
3. add an upload endpoint to the API that receives CSV files, validates their format and uses the data
in the uploaded CSV to fulfill the above requirements
4. write a web application that consumes the API, uploads files and displays the reports
5. dockerize your application

# Analysis Task
1. Average Listing Selling Price per Seller Type
2. Percentual distribution of available cars by Make
3. The average price of the 30% most contacted listings
4. The Top 5 most contacted listings per Month
5. Definition of the CSV files

