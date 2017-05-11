package com.hotmart.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotmart.dao.FileUploadDAO;
import com.hotmart.exceptions.ServerUploadFileChunkedException;
import com.hotmart.utils.FileUtil;
import com.hotmart.utils.StatusUpload;
import com.hotmart.vo.FileUploadVO;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadDAO fileDAO;

	private String location = "uploadDir";

	public FileUploadVO gravarArquivo(MultipartFile file, String idUser, Long fileSize) {
		String fileName = file.getOriginalFilename();
		FileUploadVO fileVO = fileDAO.findByName(fileName);

		if (fileVO == null) {
			fileVO = fileDAO.create(fileName, idUser, FileUtil.calcChunkes(fileSize));
		} else if(!fileVO.getStatusUpload().equals(StatusUpload.EM_ANDAMENTO)){
			throw new ServerUploadFileChunkedException("Este arquivo não pode ser enviado novamente!");
		}
		
		try {
			this.gravarArquivo(file.getBytes(), fileVO);

			fileVO.setQtdBlocosGravados(fileVO.getQtdBlocosGravados() + 1);

			if (fileVO.getQtdBlocos().equals(fileVO.getQtdBlocosGravados())) {
				fileVO.setStatusUpload(StatusUpload.CONCLUIDO);
			}

			fileDAO.save(fileVO);

		} catch (IOException e) {
			fileVO.setStatusUpload(StatusUpload.FALHA);
			this.deletarArquivo(fileVO);
			throw new ServerUploadFileChunkedException("Erro ao gravar arquivo");
		} catch (ServerUploadFileChunkedException e) {
			fileVO.setStatusUpload(StatusUpload.FALHA);
			this.deletarArquivo(fileVO);
			throw e;
		}
		return fileVO;
	}

	public FileUploadVO gravarArquivo(byte[] bytes, FileUploadVO fileUpload) {
		String filePath = location + File.separator + fileUpload.getNomeArquivo();
		File file = new File(filePath);
		FileUtil.mkdirs(location);
		FileUtil.appendFile(file, bytes);

		return fileUpload;
	}

	public boolean deletarArquivo(FileUploadVO fileUpload) {
		String filePath = location + File.separator + fileUpload.getNomeArquivo();
		return FileUtil.delete(filePath);
	}

}
