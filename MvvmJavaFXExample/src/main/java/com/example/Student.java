package com.example;

import java.time.LocalDate;

/**
 * Student model class representing a student entity.
 * This is the Model in the MVVM pattern.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName + "    " + email + "    " + birthDate;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}