package com.example.attendancesystem;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;

public class HelloController {

    @FXML
    private TextField nameField;

    @FXML
    private TableView<AttendanceRecord> table;

    @FXML
    private TableColumn<AttendanceRecord, String> employeeColumn;

    @FXML
    private TableColumn<AttendanceRecord, LocalDateTime> checkInColumn;

    @FXML
    private TableColumn<AttendanceRecord, LocalDateTime> checkOutColumn;

    @FXML
    private TableColumn<AttendanceRecord, String> hoursColumn;

    private final ObservableList<AttendanceRecord> records = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        employeeColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getEmployee())
        );

        checkInColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(data.getValue().getCheckIn())
        );

        checkOutColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(data.getValue().getCheckOut())
        );

        hoursColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getHoursWorked())
        );

        table.setItems(records);
    }

    @FXML
    private void onCheckIn() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) return;

        records.add(new AttendanceRecord(name, LocalDateTime.now()));

        nameField.clear();
    }

    @FXML
    private void onCheckOut() {
        AttendanceRecord selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        selected.setCheckOut(LocalDateTime.now());
        table.refresh();
    }

    @FXML
    private void onDelete() {
        AttendanceRecord selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            records.remove(selected);
        }
    }
}
