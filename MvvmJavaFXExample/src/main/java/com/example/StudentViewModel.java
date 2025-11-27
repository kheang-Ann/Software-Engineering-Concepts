package com.example;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class StudentViewModel {
    private final StringProperty firstName = new SimpleStringProperty("");
    private final StringProperty lastName = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final FilteredList<Student> filteredStudents = new FilteredList<>(students, p -> true);
    private final SortedList<Student> sortedStudents = new SortedList<>(filteredStudents);

    private final StringProperty sortBy = new SimpleStringProperty("Name");
    private final BooleanProperty sortAscending = new SimpleBooleanProperty(true);
    private final StringProperty searchQuery = new SimpleStringProperty("");

    public StudentViewModel() {
        // Add listener for search query changes
        searchQuery.addListener((observable, oldValue, newValue) -> {
            filterStudents(newValue);
        });

        // Add listener for sorting changes
        sortBy.addListener((observable, oldValue, newValue) -> {
            sortStudents();
        });
        sortAscending.addListener((observable, oldValue, newValue) -> {
            sortStudents();
        });
    }

    private void filterStudents(String query) {
        filteredStudents.setPredicate(student -> {
            if (query == null || query.isEmpty()) {
                return true;
            }
            String lowerCaseQuery = query.toLowerCase();
            return student.getFullName().toLowerCase().contains(lowerCaseQuery) ||
                   student.getEmail().toLowerCase().contains(lowerCaseQuery);
        });
    }

    private void sortStudents() {
        Comparator<Student> comparator = getComparator();
        if (!sortAscending.get()) {
            comparator = comparator.reversed();
        }
        sortedStudents.setComparator(comparator);
    }

    private Comparator<Student> getComparator() {
        switch (sortBy.get()) {
            case "Email":
                return Comparator.comparing(Student::getEmail);
            case "Birthdate":
                return Comparator.comparing(Student::getBirthDate);
            default: // "Name"
                return Comparator.comparing(Student::getFullName);
        }
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public ObservableList<Student> getStudents() {
        return sortedStudents;
    }

    public ObservableList<Student> getStudentsMasterList() {
        return students;
    }

    public StringProperty sortByProperty() {
        return sortBy;
    }

    public BooleanProperty sortAscendingProperty() {
        return sortAscending;
    }

    public StringProperty searchQueryProperty() {
        return searchQuery;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public void addStudent() {
        if (isValidStudent()) {
            Student newStudent = new Student(getFirstName(), getLastName(), getEmail(), getBirthDate());
            students.add(newStudent);
            clearForm();
        }
    }

    public void removeStudent(Student student) {
        if (student != null) {
            students.remove(student);
        }
    }

    public void clearForm() {
        setFirstName("");
        setLastName("");
        setEmail("");
        setBirthDate(null);
    }

    public boolean isValidStudent() {
        return !getFirstName().trim().isEmpty() &&
               !getLastName().trim().isEmpty() &&
               !getEmail().trim().isEmpty() &&
               getBirthDate() != null;
    }

    public int getStudentCount() {
        return students.size();
    }
}