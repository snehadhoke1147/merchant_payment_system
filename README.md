# Merchant Payment  System

A production-style REST API built with Java 21 and Spring Boot for managing merchants, KYC, wallets/accounts and payments.

## Highlights
- RESTful APIs with layered Controller -> Service -> Repository architecture
- Spring Security + JWT stateless authentication
- PostgreSQL with Flyway migrations
- JPA/Hibernate entity relationships and optimistic locking
- Validation and global exception handling
- Pagination and search
- Idempotency key for payment requests
- Swagger/OpenAPI documentation
- Docker and Docker Compose
- JUnit/Spring Boot testing foundation

## Tech Stack
Java 21 | Spring Boot 3.5.5 | Spring Security | JWT | Spring Data JPA | Hibernate | PostgreSQL | Maven | Swagger/OpenAPI | Docker

## Run locally
### 1. Start PostgreSQL
```bash
docker compose up postgres -d
```

### 2. Start application
```bash
./mvnw spring-boot:run
```

On Windows:
```powershell
mvnw.cmd spring-boot:run
```

Swagger: http://localhost:8080/swagger-ui.html

## Main APIs
- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/merchants`
- `GET /api/merchants`
- `GET /api/merchants/{id}`
- `PATCH /api/merchants/{id}/deactivate`
- `POST /api/kyc`
- `PATCH /api/kyc/{id}/approve`
- `PATCH /api/kyc/{id}/reject`
- `POST /api/accounts/{merchantId}`
- `GET /api/accounts/{merchantId}`
- `POST /api/accounts/{merchantId}/credit?amount=1000`
- `POST /api/accounts/{merchantId}/debit?amount=100`
- `POST /api/transactions`
- `GET /api/transactions/merchant/{merchantId}`

## Payment idempotency
Every payment request requires a unique `idempotencyKey`. Retrying the same key returns the existing transaction instead of charging the account twice.

## Example login
```json
{
  "email": "sneha@example.com",
  "password": "Password@123"
}
```
Use the returned JWT as:
`Authorization: Bearer <token>`

## GitHub
```bash
git init
git add .
git commit -m "Initial merchant payment REST API"
git branch -M main
git remote add origin https://github.com/<your-username>/merchant-payment-system.git
git push -u origin main
```

## Interview talking points
1. Why DTOs instead of exposing entities?
2. How JWT authentication works through the security filter chain.
3. Why `@Transactional` is important for payment/account updates.
4. How the database unique constraint protects idempotency.
5. Why optimistic locking is used on account balance updates.
6. How Flyway keeps schema changes version-controlled.
7. How pagination prevents loading large transaction datasets at once.
