package com.example.mscurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mscurso.Dto.CursoDto;
import com.example.mscurso.Dto.ResponseDto;
import com.example.mscurso.Service.CursoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/curso")
public class CursoController {
	@Autowired
	private CursoService curServ;
	
	@ApiOperation(value = "Metodo para listar Cursos")
	@GetMapping("/obtenertodo")
	public ResponseEntity<ResponseDto> readAllCurso(){
		return ResponseEntity.status(HttpStatus.OK).body(curServ.getCursosAll());
	}
	
	@ApiOperation(value = "Metodo para listar Cursos mediante el ID")
	@GetMapping("/obtener/{id}")
	public ResponseEntity<ResponseDto> readCurso(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(curServ.getCurso(id));
	}
	
	@ApiOperation(value = "Metodo para Crear Cursos")
	@PostMapping("/agregar")
	public ResponseEntity<ResponseDto> createCurso(@RequestBody CursoDto curso){
		return ResponseEntity.status(HttpStatus.CREATED).body(curServ.createCurso(curso));
	}
	
	@ApiOperation(value = "Metodo para Actualizar un Curso")
	@PutMapping("/modificar")
	public ResponseEntity<ResponseDto> updateCurso(@RequestBody CursoDto curso){
		return ResponseEntity.status(HttpStatus.CREATED).body(curServ.updateCurso(curso));
	}
	
	@ApiOperation(value = "Metodo para Eliminar Cursos")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<ResponseDto> deleteCurso(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(curServ.deleteCurso(id));
	}
	
	@ApiOperation(value = "Metodo para Listar Cursos Activos")
	@GetMapping("/cursosactivos")
	public ResponseEntity<ResponseDto> activeCursos(){
		return ResponseEntity.status(HttpStatus.OK).body(curServ.getCursosActivos());
	}
	
	@ApiOperation(value = "Metodo para Listar Cursos Inactivos")
	@GetMapping("/cursosinactivos")
	public ResponseEntity<ResponseDto> inactiveCursos(){
		return ResponseEntity.status(HttpStatus.OK).body(curServ.getCursosInactivos());
	}
}
