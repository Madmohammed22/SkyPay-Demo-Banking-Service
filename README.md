# Banking Service Demo

A Java-based banking application that demonstrates account management with deposit, withdrawal, and statement generation capabilities.

## Project Structure

```
src/
├── main/java/com/Banking_Service/Demo/
│   ├── DemoApplication.java           # Main application entry point
│   ├── Interface/
│   │   └── AccountService.java        # Service interface
│   └── Service/
│       ├── Account.java               # Account implementation
│       ├── Transaction.java           # Transaction model
│       └── TransactionType.java       # Transaction type enum
└── test/java/com/Banking_Service/Demo/
    └── AccountTest.java               # Unit tests
```

## Architecture

![UML Documentation](Documentation/UML_Documentation.png)

### Key Components

- **AccountService Interface**: Defines the contract for account operations
- **Account Class**: Implements account logic and transaction management
- **Transaction Class**: Represents individual transaction records
- **TransactionType Enum**: Defines DEPOSIT and WITHDRAWAL types

## Development

### Running in Development Mode

The project includes Spring Boot DevTools for development convenience:

```bash
./gradlew bootRun
```

## Testing

The project includes comprehensive unit tests covering:
- Deposit operations
- Withdrawal operations
- Statement generation
- Balance calculation
- Input validation
- Exception handling


### Running Tests

```bash
./gradlew test
```
