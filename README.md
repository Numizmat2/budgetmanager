# Budget Manager Demo
=====

Project is a demo for budget manager application. Currently it consists of 3 endpoints and supports behaviour
of recharging budget registers, transferring funds between them and returning the current balance.

Database has been design to support some of future use cases described in design documentation.

It was written using Java 13, Spring Boot 2.5.4 and Gradle 6.8.1 and was tested in such configuration.

# Running
- setting no additional properties is required in configuration
- In memory H2 database should create and fill with initial values automatically.
- Database console can be accessed in url http://localhost:8080/h2-console using
auth credentials provided in configuration
- API can be build and run from IDE with gradle and sdk versions mentioned above
- Calling endpoints can by done via curl e.g.
curl -X PUT -H "Content-Type: application/json"  "http://localhost:8080/api/transfer/wallet/insurance_policy/750"