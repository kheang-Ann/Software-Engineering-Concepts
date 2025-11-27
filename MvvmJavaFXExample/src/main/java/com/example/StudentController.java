package com.example;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class StudentController implements Initializable {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Label studentCountLabel;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> sortByComboBox;

    @FXML
    private CheckBox sortAscendingCheckBox;

    private StudentViewModel viewModel;

    public StudentController() {
        this.viewModel = new StudentViewModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDataBinding();
        setupEventHandlers();
        setupListView();
        populateSortByComboBox();
        addInitialData();
    }

    private void addInitialData() {
        viewModel.getStudentsMasterList().addAll(
                new Student("Do", "Davin", "alice.wonderland@example.com", LocalDate.of(1999, 1, 15)),
                new Student("Sitha", "Houth", "bob.builder@example.com", LocalDate.of(1998, 5, 20)),
                new Student("Ann", "Kheang", "charlie.chocolate@example.com", LocalDate.of(2000, 11, 30))
        );
    }

    private void setupDataBinding() {
        firstNameField.textProperty().bindBidirectional(viewModel.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(viewModel.lastNameProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        birthDateField.valueProperty().bindBidirectional(viewModel.birthDateProperty());
        searchField.textProperty().bindBidirectional(viewModel.searchQueryProperty());
        sortByComboBox.valueProperty().bindBidirectional(viewModel.sortByProperty());
        sortAscendingCheckBox.selectedProperty().bindBidirectional(viewModel.sortAscendingProperty());

        saveButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !viewModel.isValidStudent(),
                viewModel.firstNameProperty(),
                viewModel.lastNameProperty(),
                viewModel.emailProperty(),
                viewModel.birthDateProperty()
        ));

        deleteButton.disableProperty().bind(
                studentListView.getSelectionModel().selectedItemProperty().isNull()
        );

        studentCountLabel.textProperty().bind(Bindings.createStringBinding(
                () -> "Total Students: " + viewModel.getStudentCount(),
                viewModel.getStudents()
        ));
    }

    private void setupEventHandlers() {
        saveButton.setOnAction(event -> handleSaveStudent());
        clearButton.setOnAction(event -> handleClearForm());
        deleteButton.setOnAction(event -> handleDeleteStudent());

        studentListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    handleEditStudent(selectedStudent);
                }
            }
        });
    }

    private void setupListView() {
        studentListView.setItems(viewModel.getStudents());
    }

    private void populateSortByComboBox() {
        sortByComboBox.setItems(FXCollections.observableArrayList("Name", "Email", "Birthdate"));
    }

    @FXML
    private void handleSaveStudent() {
        if (viewModel.isValidStudent()) {
            viewModel.addStudent();
        }
    }

    @FXML
    private void handleClearForm() {
        viewModel.clearForm();
        studentListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleDeleteStudent() {
        Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            viewModel.removeStudent(selectedStudent);
        }
    }

    private void handleEditStudent(Student student) {
        if (student != null) {
            viewModel.setFirstName(student.getFirstName());
            viewModel.setLastName(student.getLastName());
            viewModel.setEmail(student.getEmail());
            viewModel.setBirthDate(student.getBirthDate());
        }
    }

    public StudentViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(StudentViewModel viewModel) {
        this.viewModel = viewModel;
    }
}