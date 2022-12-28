package com.wipro.cerner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.cerner.entity.Patient;

/*
 * patient repository will connect with jpa repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
