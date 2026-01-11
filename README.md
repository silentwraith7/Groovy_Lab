# Groovy Lab 🧪

A Groovy learning and experimentation project for exploring Groovy scripting, configuration-driven logic, and Pricefx-style pricing rules.

## Prerequisites

- **Java 21** (JDK) — automatically downloaded via Gradle toolchains if not present
- **Gradle 9.2.1** — uses the included wrapper, no manual installation needed

## Quick Start

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd Groovy_Lab
```

### 2. Build the project

```bash
./gradlew build
```

This will:
- Download dependencies (Groovy 5.0.0, Guava)
- Compile the source code
- Run tests (Spock framework)

### 3. Run the application

```bash
./gradlew run
```

Or directly:

```bash
./gradlew :app:run
```

## Project Structure

```
Groovy_Lab/
├── app/
│   └── src/
│       └── main/groovy/org/example/
│           ├── App.groovy              # Main entry point
│           ├── ItemList.groovy         # List utilities
│           └── experiments/
│               └── PriceObject.groovy  # Pricing experiments
├── gradle/                             # Gradle wrapper files
├── build.gradle                        # (in app/) Dependencies & plugins
├── settings.gradle                     # Project settings
└── gradlew / gradlew.bat              # Gradle wrapper scripts
```

## Common Commands

| Command | Description |
|---------|-------------|
| `./gradlew build` | Compile and run tests |
| `./gradlew run` | Run the main application |
| `./gradlew test` | Run Spock tests only |
| `./gradlew clean` | Remove build artifacts |
| `./gradlew tasks` | List all available tasks |

## Tech Stack

| Technology | Version |
|------------|---------|
| Groovy | 5.0.0 |
| Java | 21 |
| Gradle | 9.2.1 |
| Test Framework | Spock 2.3 |

## IDE Setup

### IntelliJ IDEA (Recommended)
1. Open the project folder
2. IntelliJ will auto-detect Gradle and import the project
3. Wait for indexing to complete

### VS Code
1. Install the "Groovy" extension
2. Open the project folder
3. Use terminal for Gradle commands

## Troubleshooting

### Java version issues
The project uses Gradle toolchains to auto-download JDK 21. If you see issues:
```bash
./gradlew -q javaToolchains
```

### Dependency issues
Force refresh dependencies:
```bash
./gradlew build --refresh-dependencies
```

## License

This project is for learning purposes.
