# Web Application with Login

A Spring Boot web application with a secure login page and authenticated home page.

## Features

- ✅ Login/Logout functionality with Spring Security
- ✅ Responsive login page with modern UI
- ✅ Authenticated home page (requires login)
- ✅ Demo user accounts for testing
- ✅ Role-based access (USER and ADMIN roles)
- ✅ Thymeleaf templates for HTML rendering
- ✅ In-memory user authentication

## Requirements

- Java 11+
- Maven 3.6+

## Demo User Credentials

| Username | Password | Role |
|----------|----------|------|
| user     | password | USER |
| admin    | admin123 | ADMIN |

## Build & Run

### Option 1: Using Maven (Recommended)

```powershell
cd C:\Users\kanil\git\SampleRepo\HelloWorld
mvn -f .\webapp spring-boot:run
```

### Option 2: Build JAR and Run

```powershell
cd C:\Users\kanil\git\SampleRepo\HelloWorld
mvn -f .\webapp clean package
java -jar .\webapp\target\webapp-1.0-SNAPSHOT.jar
```

## Access the Application

Once running, open your browser and navigate to:

```
http://localhost:8080
```

You will be redirected to the login page. Use one of the demo credentials above to log in.

## Project Structure

```
webapp/
├── pom.xml                                 (Maven configuration)
├── src/
│   ├── main/
│   │   ├── java/com/example/helloworld/web/
│   │   │   ├── WebApplication.java         (Spring Boot main class)
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java     (Spring Security configuration)
│   │   │   └── controller/
│   │   │       ├── AuthController.java     (Login/Logout handling)
│   │   │       └── HomeController.java     (Home page handling)
│   │   └── resources/
│   │       ├── application.properties      (Application configuration)
│   │       └── templates/
│   │           ├── login.html              (Login page)
│   │           └── home.html               (Home page)
│   └── test/
└── README.md                               (This file)
```

## Security Features

- **Password Encryption**: BCrypt password encoding
- **CSRF Protection**: Enabled by default with Spring Security
- **Form-based Login**: Standard form-based authentication
- **Session Management**: Automatic session management
- **Role-based Authorization**: Different access levels for USER and ADMIN roles

## Customization

### Add More Users

Edit `SecurityConfig.java` in the `userDetailsService()` method to add more users:

```java
UserDetails newUser = User.builder()
    .username("newuser")
    .password(passwordEncoder.encode("newpassword"))
    .roles("USER")
    .build();
```

### Change Server Port

Edit `application.properties`:

```properties
server.port=9090
```

### Enable HTTPS

Add to `application.properties`:

```properties
server.ssl.key-store=classpath:keystore.jks
server.ssl.key-store-password=password
```

## Troubleshooting

### Port Already in Use

If port 8080 is already in use, change it in `application.properties`:

```properties
server.port=8081
```

### Maven Not Found

Ensure Maven is installed and added to your PATH:

```powershell
mvn --version
```

### Dependency Download Issues

Clear Maven cache:

```powershell
rmdir -r C:\Users\%USERNAME%\.m2\repository
```

Then rebuild.

## Future Enhancements

- [ ] Database-backed user authentication (JPA/MySQL)
- [ ] User registration page
- [ ] Password reset functionality
- [ ] Email verification
- [ ] OAuth2/OpenID Connect integration
- [ ] API authentication (JWT tokens)
- [ ] User management dashboard

## License

MIT License
