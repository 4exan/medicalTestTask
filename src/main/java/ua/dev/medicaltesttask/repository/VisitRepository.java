package ua.dev.medicaltesttask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.dev.medicaltesttask.entity.Visit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @EntityGraph(attributePaths = {"doctor"})
    @Query("SELECT v FROM Visit v WHERE v.patient.id IN :patientIds")
    List<Visit> findVisitsByPatientIds(@Param("patientIds") List<Long> patientIds);


    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
            "FROM Visit v WHERE v.doctor.id = :doctorId " +
            "AND ((:start BETWEEN v.startDateTime AND v.endDateTime) " +
            "OR (:end BETWEEN v.startDateTime AND v.endDateTime) " +
            "OR (v.startDateTime BETWEEN :start AND :end) " +
            "OR (v.endDateTime BETWEEN :start AND :end))")
    boolean existsOverlappingVisit(Long doctorId, LocalDateTime start, LocalDateTime end);

}
