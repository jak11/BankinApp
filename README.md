# Description
This code base depicts small banking app with following APIS. 
- Retrieve a customer for a given customer id - GET /customers/{id}
- Retrieve an account for a given account id - GET /accounts/{id}
- Retrieve a transaction for a given transaction id - GET /transactions/{id}
- Retrieve a list of transactions for a given account id in reverse time order - GET /accounts/{id}/transactions

# Pre-requisite
- Gradle
- java 8
- Spring Boot

# Instruction to Run the project
`./gradlew bootRun`

# Endpoints
1. **Base URL** : http://localhost:8080/bankingapp/v1/
2. Fields Description:
    - lean-token: Token is mandatory and should be passed in request header.
   
# Testing
1. Valid Calls : 
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/accounts/1 | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/accounts/2 | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/accounts/3 | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/accounts/1/transactions | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/accounts/2/transactions | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/transactions/2/ | json_pp`
    - `curl -H "lean-token:<token_value_from_properties>" -v http://localhost:8080/bankingapp/v1/transactions/1/ | json_pp`
2. Invalid call missing Header field: 
    - `curl -v http://localhost:8080/bankingapp/v1/accounts/1` 

#Assumptions
- Junits are not covered as it would take lots of effort in writing them.
- Used DB entities instead of writing separate DTO for request/response of APIS.  