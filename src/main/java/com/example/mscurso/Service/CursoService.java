package com.example.mscurso.Service;

import com.example.mscurso.Dto.CursoDto;
import com.example.mscurso.Dto.ResponseDto;

public interface CursoService {
	public ResponseDto getCursosAll();
	public ResponseDto getCurso(Long id);
	public ResponseDto createCurso(CursoDto curso);
	public ResponseDto updateCurso(CursoDto curso);
	public ResponseDto deleteCurso(Long id);
	public ResponseDto getCursosActivos();
	public ResponseDto getCursosInactivos();
}
