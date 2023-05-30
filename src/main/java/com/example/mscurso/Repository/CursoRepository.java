package com.example.mscurso.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mscurso.Entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long>{

	List<CursoEntity> findByEstado(Boolean estado);
}
