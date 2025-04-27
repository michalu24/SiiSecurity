
![image.png](SecurityOauth/src/main/resources/static/images/image.png)
# Spring Boot 3 - Security Modules
## Overview

This project demonstrates three different approaches to securing a Spring Boot 3 application using Spring Security:

- **CustomLoginForm** - A custom-built login page with Spring Security integration.
- **DefaultSecurityConfig** - Standard default security configuration (out-of-the-box).
- **SecurityOauth** - Integration OAuth2 - Github

Each module is structured to showcase a specific security mechanism.

---

## Modules

### 1. CustomLoginForm
**Description:**
A module presenting a fully customized login form replacing the default Spring Security login page.

**Features:**
- Custom HTML login page.
- Form-based authentication.
- Simple user roles and access control.

**How to run:**
```bash
cd CustomLoginForm
./mvnw spring-boot:run
```

Access the application at: `http://localhost:8080/login`

---

### 2. DefaultSecurityConfig
**Description:**
A module showcasing Spring Security’s default security setup.

**Features:**
- Default generated login page.
- Basic username/password authentication.
- Predefined user with roles.

**How to run:**
```bash
cd DefaultSecurityConfig
./mvnw spring-boot:run
```

Access the application at: `http://localhost:8081

Default credentials:
- Username: `user`
- Password: (auto-generated at startup)

---

### 3. SecurityOauth
**Description:**
A module demonstrating OAuth2 authentication integration with github.

**Features:**
- Login via OAuth2 with github.

**Note:** Before running, configure your OAuth2 credentials in `application.yml` or `application.properties`.

Example:
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: your-client-id
            client-secret: your-client-secret
```

---

## Requirements
- Java 17+
- Maven 3.8+
- Internet access (for OAuth2 login)

## Author
Created by **Michał Urbański** 🚀

