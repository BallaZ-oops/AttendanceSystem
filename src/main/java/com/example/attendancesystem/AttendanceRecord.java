package com.example.attendancesystem;

import javafx.beans.property.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AttendanceRecord {

    private final StringProperty employee = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> checkIn = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> checkOut = new SimpleObjectProperty<>();
    private final StringProperty hoursWorked = new SimpleStringProperty("");

    public AttendanceRecord(String employee, LocalDateTime checkIn) {
        this.employee.set(employee);
        this.checkIn.set(checkIn);
    }

    public String getEmployee() { return employee.get(); }
    public LocalDateTime getCheckIn() { return checkIn.get(); }
    public LocalDateTime getCheckOut() { return checkOut.get(); }
    public String getHoursWorked() { return hoursWorked.get(); }

    public void setCheckOut(LocalDateTime time) {
        checkOut.set(time);

        long hours = ChronoUnit.HOURS.between(checkIn.get(), time);
        long minutes = ChronoUnit.MINUTES.between(checkIn.get(), time) % 60;

        hoursWorked.set(hours + "h " + minutes + "m");
    }
}
