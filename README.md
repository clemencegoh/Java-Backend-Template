# Appliance Case study Backend

## Aim
Aim of this project is to build a backend microservice
to support an "appliance management system"

## Environment
- Java 14
- maven

## Getting Started

### Install Maven
Install on Linux/AMI
```
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn --version
```

### Run application
use `mvnw spring-boot:run` if using command line

## Testing it out
Documentation is available at `<ip>:<port>/swagger-ui.html` 

for example, `http://localhost:8080/swagger-ui.html` once application has been deployed locally
