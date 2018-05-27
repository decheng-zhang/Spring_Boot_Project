package com.lindedin.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.lindedin.learning.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity,Long>{

}
