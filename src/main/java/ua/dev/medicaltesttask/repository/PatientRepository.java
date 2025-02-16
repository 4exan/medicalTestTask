package ua.dev.medicaltesttask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.dev.medicaltesttask.entity.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<Patient> findAll(Pageable pageable);

    @Query("""
    SELECT DISTINCT p FROM Patient p
    LEFT JOIN FETCH p.visits v
    LEFT JOIN FETCH v.doctor d
    WHERE (:search IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :search, '%')))
    AND (:doctorIds IS NULL OR d.id IN :doctorIds)
""")
    List<Patient> findPatientsWithVisits(
            @Param("search") String search,
            @Param("doctorIds") List<Long> doctorIds,
            Pageable pageable);
}
