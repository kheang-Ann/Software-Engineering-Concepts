# JavaFX MVVM Example

This repository contains a simple JavaFX application that demonstrates the Model-View-ViewModel (MVVM) pattern. The example is purposely minimal to show how to structure an app using data binding and separation of concerns.

Key Files
- `src/main/java/com/example/Main.java` — JavaFX application entry point.
- `src/main/java/com/example/Student.java` — Domain model representing a student (Model).
- `src/main/java/com/example/StudentViewModel.java` — ViewModel exposing bindable properties.
- `src/main/java/com/example/StudentController.java` — Controller that wires the FXML view and ViewModel.
- `src/main/resources/student_view.fxml` — The FXML view (View).

Features
- Add new users (students) with first name, last name, email and birth date.
- Edit users by double-clicking in the list.
- Delete selected users.
- Live form validation and live full name display.
- Sort and search the list of users.

Prerequisites
- JDK 11 or later (11+ recommended). Set `JAVA_HOME` and ensure `java` and `javac` are on your PATH.
- Maven 3.6 or later.
- (Optional) An IDE like IntelliJ IDEA or Eclipse for running or developing the project.

Quick Start — Run with Maven (Recommended)
1. Open a command prompt or PowerShell and navigate to the project root:
```powershell
cd "D:\Code for Year3\Software Engineering Concepts\MvvmJavaFXExample"
```

2. Compile the project:
```powershell
mvn clean compile
```

3. Run the application using the JavaFX Maven plugin:
```powershell
mvn javafx:run
```

Notes:
- The `javafx-maven-plugin` in the `pom.xml` ensures the correct JavaFX runtime libraries are used. On Windows, Maven activates the `windows` profile automatically and will download the native JavaFX runtime suitable for your platform.
- If you prefer running with an explicit profile (optional):
```powershell
mvn -Pwindows javafx:run
```

Alternative — Run via exec plugin
```powershell
mvn exec:java -Dexec.mainClass="com.example.Main"
```

Build a Runnable JAR
1. Package a JAR file (fat JAR / shaded JAR that contains dependencies):
```powershell
mvn clean package
```

2. Look in the `target` directory for a JAR file; the name will typically be like `mvvm-javafx-example-1.0.0.jar` or similar. If you used the shade plugin, it may have a `-shaded` suffix. Example:
```powershell
dir .\target\
```

3. Run the JAR (if you created a shaded JAR which contains JavaFX dependencies):
```powershell
java -jar target\mvvm-javafx-example-1.0.0-shaded.jar
```

Note about running JARs: Native JavaFX modules for a specific OS are packaged in the dependencies using classifiers. If you see an error like "JavaFX runtime components are missing, and are required to run this application", verify you built a fat/shaded JAR that includes dependencies for your OS or use the `javafx:run` plugin, which sets up the runtime automatically.

Running from an IDE
- IntelliJ IDEA: Open the project as a Maven project. Set the project SDK to Java 11 or later, then run `com.example.Main` from the IDE. If there are runtime errors about missing JavaFX, use the `javafx` Maven plugin to run, or add the platform-specific JavaFX SDK to your VM options.
- Eclipse: Install the m2e Maven plugin (usually bundled) and import the Maven project. Run `Main` with the project’s classpath. If JavaFX runtime components are missing, use the `javafx:run` Maven command from a terminal.

Basic Usage
- Add a student: Fill in first name, last name, email and birthdate, then click `Save User`.
- Edit a student: Double-click a student in the list to load their data into the input form, edit and save.
- Delete a student: Select a student in the list and click `Delete Selected`.
- Sort and Search: Use the sort combo box and the search field to sort and filter the list.

Troubleshooting
- JavaFX Not Found Error: Use `mvn javafx:run` or install a JavaFX SDK for your JDK if you run the JAR directly.
- Wrong JDK Version: Ensure your JDK is >= 11 and that `JAVA_HOME` is pointing to the right JDK.
- Lombok compile issue in IDE: Lombok is declared provided in `pom.xml`. You may need to install Lombok support plugin (in IDE) or configure annotation processing.

Project Structure
```
MvvmJavaFXExample/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/example/
        │   ├── Main.java
        │   ├── Student.java
        │   ├── StudentViewModel.java
        │   └── StudentController.java
        └── resources/
            └── student_view.fxml
```

Contributing and Notes
- This project is meant for education and demonstration. You can extend it by adding features such as:
  - Validation frameworks
  - Dependency injection (e.g., Dagger, Guice)
  - Persistence (JPA, SQLite, JSON file storage)
  - Unit and UI tests (TestFX)

License
- This project is provided as course material for educational purposes. Consult your course or instructor for license details if applicable.

If you want me to add more developer notes, environment variable instructions or a Windows-specific run script/powershell file, tell me and I’ll draft that next.
# JavaFX MVVM Example

This project demonstrates the **Model-View-ViewModel (MVVM)** design pattern implementation using JavaFX. It showcases how to create a data-driven user interface with proper separation of concerns between the presentation layer and business logic.

## 🎯 Project Overview

The application implements a simple user management system where you can:
- Add users with first and last names
- View all users in a list
- Delete selected users
- Edit existing users (double-click to edit)
- Real-time display of full name as you type

## 🏗️ MVVM Architecture

### Components

1. **Model** (`User.java`)
   - Represents the user data entity
   - Contains business logic for user operations
   - Independent of UI concerns

2. **View** (`user_view.fxml`)
   - Defines the user interface using FXML
   - Contains UI elements and layout
   - Declaratively binds to ViewModel properties

3. **ViewModel** (`UserViewModel.java`)
   - Exposes properties for data binding
   - Manages presentation logic
   - Bridges the gap between Model and View

4. **Controller** (`UserController.java`)
   - Handles the interaction between View and ViewModel
   - Sets up data binding and event handlers
   - Implements the Controller part of MVVM

### Key MVVM Features Demonstrated

- **Data Binding**: Two-way binding between UI controls and ViewModel properties
- **Property Change Notification**: Automatic UI updates when data changes
- **Computed Properties**: Full name automatically updates when first/last name changes
- **Command Pattern**: Button actions handled through ViewModel methods
- **Observable Collections**: ListView automatically reflects changes in user list
- **Form Validation**: Save button enabled/disabled based on form validity

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- JavaFX 17+ (included via Maven dependencies)

### Building the Project

1. **Clone or navigate to the project directory:**
   ```bash
   cd MvvmJavaFXExample
   ```

2. **Compile the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn javafx:run
   ```

   Alternative method:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.Main"
   ```

### Building a JAR

To create a standalone JAR file:

```bash
mvn clean package
```

This creates a fat JAR in the `target/` directory that includes all dependencies.

## 📋 How to Use

1. **Adding Users:**
   - Enter first and last name in the text fields
   - Watch the full name update automatically
   - Click "Save User" to add to the list
   - The form clears automatically after saving

2. **Managing Users:**
   - View all users in the list below the form
   - Select a user and click "Delete Selected" to remove
   - Double-click any user in the list to edit them
   - Use "Clear Form" to reset the input fields

3. **Real-time Features:**
   - Full name updates as you type
   - Save button is disabled when form is invalid
   - Delete button is disabled when no user is selected
   - User count updates automatically

## 🔧 Project Structure

```
MvvmJavaFXExample/
├── pom.xml                              # Maven configuration
├── README.md                            # This file
└── src/
    └── main/
        ├── java/com/example/
        │   ├── Main.java                # Application entry point
        │   ├── User.java                # Model class
        │   ├── UserViewModel.java       # ViewModel class
        │   └── UserController.java      # Controller class
        └── resources/
            └── user_view.fxml           # FXML View definition
```

## 💡 MVVM Benefits Demonstrated

1. **Separation of Concerns:**
   - UI logic separated from business logic
   - Each component has a single responsibility
   - Easy to modify one layer without affecting others

2. **Testability:**
   - ViewModel can be unit tested without UI
   - Business logic isolated from presentation
   - Mock dependencies easily

3. **Data Binding:**
   - Automatic synchronization between UI and data
   - Reduced boilerplate code
   - Declarative UI updates

4. **Maintainability:**
   - Clear code organization
   - Loose coupling between components
   - Easy to extend and modify

## 🧪 Testing

The project includes test dependencies for:
- **JUnit 5**: Unit testing framework
- **TestFX**: JavaFX UI testing framework

To run tests:
```bash
mvn test
```

## 🔧 Development Tips

### Adding New Features

1. **New Model Properties:**
   - Add properties to `User.java`
   - Update `UserViewModel.java` to expose new properties
   - Modify `user_view.fxml` to include new UI elements
   - Update `UserController.java` for data binding

2. **New Commands:**
   - Add methods to `UserViewModel.java`
   - Create corresponding handlers in `UserController.java`
   - Add buttons or menu items in `user_view.fxml`

### Data Binding Best Practices

- Use `StringProperty`, `IntegerProperty`, etc. for bindable properties
- Implement bidirectional binding for input controls
- Use `ObservableList` for collections
- Leverage `Bindings.createStringBinding()` for computed properties

### Common Issues

1. **FXML Loading Errors:**
   - Ensure FXML file is in `src/main/resources`
   - Check that `fx:controller` attribute matches your controller class
   - Verify all `fx:id` attributes have corresponding `@FXML` fields

2. **Data Binding Not Working:**
   - Ensure properties are of correct JavaFX property types
   - Check bidirectional vs unidirectional binding usage
   - Verify property change notifications are working

## 📚 Learning Resources

- [JavaFX Documentation](https://openjfx.io/javadoc/17/)
- [MVVM Pattern Overview](https://docs.microsoft.com/en-us/xamarin/xamarin-forms/enterprise-application-patterns/mvvm)
- [JavaFX Data Binding Tutorial](https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm)

## 🤝 Contributing

Feel free to submit issues, fork the repository, and create pull requests for any improvements.

## 📄 License

This project is created for educational purposes and is part of the International Software Engineering Concepts course materials.

---

**Note:** This example demonstrates core MVVM concepts in JavaFX. In production applications, you might want to add features like dependency injection, navigation management, validation frameworks, and more sophisticated state management.