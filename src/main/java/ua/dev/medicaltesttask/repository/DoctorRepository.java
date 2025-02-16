package ua.dev.medicaltesttask.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.dev.medicaltesttask.entity.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT v.doctor.id, COUNT(DISTINCT v.patient) FROM Visit v WHERE v.doctor.id IN :doctorIds GROUP BY v.doctor.id")
    List<Object[]> countPatientsForDoctors(@Param("doctorIds") List<Long> doctorIds);

}
