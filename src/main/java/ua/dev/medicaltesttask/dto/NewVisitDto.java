package ua.dev.medicaltesttask.dto;

import lombok.Data;

@Data
public class NewVisitDto {
    private String start;
    private String end;
    private int patientId;
    private int doctorId;
}
