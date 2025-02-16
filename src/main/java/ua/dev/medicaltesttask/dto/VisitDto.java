package ua.dev.medicaltesttask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record VisitDto(
    LocalDateTime start,
    LocalDateTime end,
    DoctorDto doctor
){}
