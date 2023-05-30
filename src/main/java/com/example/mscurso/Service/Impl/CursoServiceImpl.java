package com.example.mscurso.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mscurso.Dto.CursoDto;
import com.example.mscurso.Dto.ResponseDto;
import com.example.mscurso.Entity.CursoEntity;
import com.example.mscurso.Repository.CursoRepository;
import com.example.mscurso.Service.CursoService;
import com.example.mscurso.Util.Constante;
import com.example.mscurso.Util.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository curRep;

	@Override
	public ResponseDto getCursosAll() {
		try {
			List<CursoEntity> listCursoEntity = curRep.findAll();
			if (listCursoEntity.isEmpty()) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			List<CursoDto> list = new ArrayList<CursoDto>();
			for (CursoEntity cursoEntity : listCursoEntity) {
				list.add(CursoDto.builder()
						.id(cursoEntity.getId())
						.descripcion(cursoEntity.getDescripcion())
						.estado(cursoEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, list);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto getCurso(Long id) {
		try {
			CursoEntity cursoEntity = curRep.findById(id).orElse(null);
			if(null == cursoEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			CursoDto cursoDto = CursoDto.builder()
					.id(cursoEntity.getId())
					.descripcion(cursoEntity.getDescripcion())
					.estado(cursoEntity.getEstado())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, cursoDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto createCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity = CursoEntity.builder()
					.descripcion(curso.getDescripcion())
					.estado(true)
					.build();
				curRep.save(cursoEntity);
				curso.setId(cursoEntity.getId());
				return Util.getResponse(true, Constante.OPERATION_SUCCESS, curso);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto updateCurso(CursoDto curso) {
		try {
			CursoEntity cursoEntity = curRep.findById(curso.getId()).orElse(null);
			if(null == cursoEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			cursoEntity.setDescripcion(curso.getDescripcion());
			cursoEntity.setEstado(curso.getEstado());
			curRep.save(cursoEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, curso); 
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto deleteCurso(Long id) {
		try {
			CursoEntity cursoEntity = curRep.findById(id).orElse(null);
			cursoEntity.setEstado(false);
			curRep.save(cursoEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			log.error(Constante.OPERATION_FAILED, e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto getCursosActivos() {
		try {
			List<CursoEntity> obtenerCursosActivos = curRep.findByEstado(true);
			if(obtenerCursosActivos.isEmpty()) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			List<CursoDto> list = new ArrayList<CursoDto>();
			for (CursoEntity cursoEntity : obtenerCursosActivos) {
				list.add(CursoDto.builder()
						.id(cursoEntity.getId())
						.descripcion(cursoEntity.getDescripcion())
						.estado(cursoEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, list);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
	
	@Override
	public ResponseDto getCursosInactivos() {
		try {
			List<CursoEntity> obtenerCursosActivos = curRep.findByEstado(false);
			if(obtenerCursosActivos.isEmpty()) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			List<CursoDto> list = new ArrayList<CursoDto>();
			for (CursoEntity cursoEntity : obtenerCursosActivos) {
				list.add(CursoDto.builder()
						.id(cursoEntity.getId())
						.descripcion(cursoEntity.getDescripcion())
						.estado(cursoEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, list);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}
}
