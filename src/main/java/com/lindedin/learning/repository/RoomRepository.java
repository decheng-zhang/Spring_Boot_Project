package com.lindedin.learning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.lindedin.learning.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long>{
	Optional<RoomEntity> findById(Long id);
}
