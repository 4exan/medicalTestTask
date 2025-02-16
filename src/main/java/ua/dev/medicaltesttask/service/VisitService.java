package ua.dev.medicaltesttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.dev.medicaltesttask.dto.*;
import ua.dev.medicaltesttask.entity.Doctor;
import ua.dev.medicaltesttask.entity.Patient;
import ua.dev.medicaltesttask.entity.Visit;
import ua.dev.medicaltesttask.repository.DoctorRepository;
import ua.dev.medicaltesttask.repository.PatientRepository;
import ua.dev.medicaltesttask.repository.VisitRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public ResponseDto getAllVisits(String search, List<Long> doctorIds, Pageable p) {
        List<Patient> patients;
        if(search == null && doctorIds == null){
            patients = patientRepository.findAll(p).getContent();
        }else if(doctorIds != null){
            patients = patientRepository.findPatientsWithVisits(search, doctorIds, p);
        }else{
            patients = patientRepository.findByFirstNameContainingIgnoreCase(search, p).getContent();
        }

        List<Long> patientIds = patients.stream().map(Patient::getId).toList();

        List<Visit> visits = visitRepository.findVisitsByPatientIds(patientIds);

        doctorIds = visits.stream().map(v -> v.getDoctor().getId()).distinct().toList();
        Map<Long, Integer> doctorPatientCounts = doctorRepository.countPatientsForDoctors(doctorIds)
                .stream()
                .collect(Collectors.toMap(
                        obj -> (Long) obj[0],  // Doctor ID
                        obj -> ((Number) obj[1]).intValue()  // Patient count
                ));

        List<PatientDto> patientDtos = patients.stream().map(patient -> {
            List<VisitDto> lastVisits = visits.stream()
//
                    .collect(Collectors.groupingBy(v -> v.getDoctor().getId(),
                            Collectors.maxBy(Comparator.comparing(Visit::getStartDateTime))))
                    .values()
                    .stream()
                    .map(optionalVisit -> optionalVisit.orElseThrow())
                    .map(visit -> new VisitDto(visit.getStartDateTime(), visit.getEndDateTime(), new DoctorDto(
                            visit.getDoctor().getFirstName(),
                            visit.getDoctor().getLastName(),
                            doctorPatientCounts.getOrDefault(visit.getDoctor().getId(), 0)
                    ))
                    ).toList();
            return new PatientDto(patient.getFirstName(), patient.getLastName(), lastVisits);
        })
                .collect(Collectors.toList());
        return new ResponseDto(patientDtos, patientDtos.size());
    }

    public void createNewVisit(NewVisitDto res) {
        Doctor doctor = doctorRepository.findById((long)res.getDoctorId()).orElseThrow(() -> new RuntimeException("No Doctor with present id"));
        Patient patient = patientRepository.findById((long)res.getPatientId()).orElseThrow(() -> new RuntimeException("No Patient with present id"));

        ZoneId doctorZone = ZoneId.of(doctor.getTimezone());
        LocalDateTime start = LocalDateTime.parse(res.getStart());
        LocalDateTime end = LocalDateTime.parse(res.getEnd());
        ZonedDateTime startUTC = start.atZone(doctorZone).withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime endUTC = end.atZone(doctorZone).withZoneSameInstant(ZoneOffset.UTC);

        boolean OverlapExist = visitRepository.existsOverlappingVisit(doctor.getId(), startUTC.toLocalDateTime(), endUTC.toLocalDateTime());
        if(OverlapExist){
            throw new RuntimeException("Overlapping visit hours");
        }
        Visit visit = new Visit();
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setStartDateTime(startUTC.toLocalDateTime());
        visit.setEndDateTime(endUTC.toLocalDateTime());
        visitRepository.save(visit);
    }
}
