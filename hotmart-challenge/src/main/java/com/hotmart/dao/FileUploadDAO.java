package com.hotmart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotmart.utils.StatusUpload;
import com.hotmart.vo.FileUploadVO;

@Service
public class FileUploadDAO {

	private static final List<FileUploadVO> files = new ArrayList<FileUploadVO>();

	private static Long sequence = 0l;

	public FileUploadVO create(String nomeArquivo, String idUsuario, Long qtdBlocos) {
		sequence++;
		FileUploadVO fileUploadVO = new FileUploadVO();
		fileUploadVO.setId(sequence);
		fileUploadVO.setNomeArquivo(nomeArquivo);
		fileUploadVO.setDtEnvio(new Date());
		fileUploadVO.setIdUsuario(idUsuario);
		fileUploadVO.setStatusUpload(StatusUpload.EM_ANDAMENTO);
		fileUploadVO.setQtdBlocos(qtdBlocos);
		fileUploadVO.setQtdBlocosGravados(0l);
		fileUploadVO.setLinkDownload("http://localhost:8080/hotmart-challenge/download/" + fileUploadVO.getId());
		return this.save(fileUploadVO);
	}

	public FileUploadVO save(FileUploadVO fileUploadVO) {
		if (!files.contains(fileUploadVO)) {
			files.add(fileUploadVO);
			return fileUploadVO;
		} else {
			files.remove(fileUploadVO);
			files.add(fileUploadVO);
		}
		return fileUploadVO;
	}

	public FileUploadVO findById(Long id) {
		FileUploadVO fileUploadVO = new FileUploadVO();
		fileUploadVO.setId(id);
		if (files.contains(fileUploadVO)) {
			int index = files.indexOf(fileUploadVO);
			return index != -1 ? files.get(index) : null;
		}
		return null;
	}

	public FileUploadVO findByName(String name) {
		FileUploadVO fileUploadVO = new FileUploadVO();
		fileUploadVO.setNomeArquivo(name);
		if (files.contains(fileUploadVO)) {
			int index = files.indexOf(fileUploadVO);
			return index != -1 ? files.get(index) : null;
		}
		return null;
	}

	public void delete(FileUploadVO fileUploadVO) {
		files.remove(fileUploadVO);
	}

	public List<FileUploadVO> findAll() {
		return files;
	}

}
