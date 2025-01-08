# ANTLR Compiler Construction

## Overview

This repository contains an example project for **compiler construction** using the ANTLR (Another Tool for Language Recognition) framework. It demonstrates how to create a custom compiler or interpreter by defining grammars, generating parser code, and implementing visitor patterns for semantic analysis and execution.

## Features

1. **ANTLR Grammar**: Custom grammar for parsing mathematical expressions and custom syntax.
2. **Visitor Pattern**: Implementation of the visitor design pattern for semantic analysis and execution.
3. **Lexer and Parser**: ANTLR-generated lexer and parser for tokenizing and parsing the input code.
4. **Custom Compiler Logic**: Demonstrates how to integrate ANTLR-generated code into a Java application.
5. **Unit Tests**: Ensures the correctness of the parser, lexer, and visitor logic.

## Prerequisites

To run or build this project, you will need:

- **Java Development Kit (JDK)** 8 or later.
- **ANTLR** version 4.x.
- **Maven** or **Gradle** for dependency management (optional).

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/kevincogan/Compiler-Construction.git
cd Compiler-Construction/AntlrExampleVisitor
```

### 2. Setup ANTLR

#### Option 1: Using Precompiled JAR
If you prefer to skip compilation, you can use the precompiled `expression-visitor.jar` provided in the repository.

#### Option 2: Compile ANTLR Grammar
1. Install ANTLR by downloading it from [ANTLR's official site](https://www.antlr.org/download.html).
2. Compile the grammar files:
   ```bash
   java -jar antlr-4.x-complete.jar -Dlanguage=Java -visitor -o antlr <path_to_grammar_files>
   ```
3. Include the generated files in your project.

### 3. Build and Run

#### Compile and Run with Maven
1. Build the project:
   ```bash
   mvn clean package
   ```
2. Run the application:
   ```bash
   java -jar target/expression-visitor.jar
   ```

#### Run Without Maven
If you are running directly, execute the main class:
```bash
java -cp antlr:app:expression:expression-visitor.jar MainClass
```

### 4. Testing
Run the unit tests to validate the compiler:
```bash
mvn test
```

## How It Works

1. **Define Grammar**:
   - The `.g4` files in the `antlr` directory define the syntax and structure of the input language.
2. **Generate Lexer and Parser**:
   - ANTLR generates Java code for the lexer and parser based on the grammar.
3. **Implement Visitor**:
   - The visitor pattern in the `expression` directory is used to traverse the parse tree and perform semantic analysis or execution.
4. **Run Tests**:
   - The `tests` directory contains unit tests to ensure correctness.

## Example Input and Output

### Input
```
3 + 5 * (2 - 4)
```

### Output
```
Result: -7
```

## Contribution

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or fix.
3. Commit your changes and push them to your fork.
4. Open a pull request describing your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

Build powerful compilers and interpreters with this ANTLR example project. Happy coding!

# Compiler-Construction
