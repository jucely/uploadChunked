package com.hotmart.controllers;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.hotmart.dao.FileUploadDAO;
import com.hotmart.exceptions.ServerUploadFileChunkedException;
import com.hotmart.service.FileUploadService;
import com.hotmart.utils.FileUtil;
import com.hotmart.vo.FileUploadVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value = "/hotmart-challenge")
@Api(value = "FileUploadController", description = "Api Rest controle de arquivos ")
public class FileUploadController {

	@Autowired
	private FileUploadDAO fileDAO;

	@Autowired
	private FileUploadService fileService;

	@ResponseBody
	@RequestMapping(value = "/fileUploadChunk", method = RequestMethod.POST)
	@ApiOperation(value = "POST fileUploadChunk", notes = "Recebe e grava o arquivo enviado")
	@CrossOrigin
	public FileUploadVO fileUploadChunk(final HttpServletRequest request, final HttpServletResponse response) {
		StandardMultipartHttpServletRequest rq = (StandardMultipartHttpServletRequest) request;
		String idUser = request.getHeader("iduser");
		Long fileSize = FileUtil.fileSizeOfContentRange(request.getHeader("content-range"));
		Iterator<String> fileParams = rq.getFileNames();
		FileUploadVO fileVO = null;
		while (fileParams.hasNext()) {
			String fileParam = fileParams.next();

			fileVO = fileService.gravarArquivo(rq.getFile(fileParam), idUser, fileSize);
		}

		return fileVO;
	}

	@ResponseBody
	@RequestMapping(value = "/listarArquivos", method = RequestMethod.GET)
	@ApiOperation(value = "GET listarArquivos", notes = "Retorna lista de arquivos enviados")
	@CrossOrigin
	public List<FileUploadVO> listarArquivos() {
		return fileDAO.findAll();
	}

	@ResponseBody
	@RequestMapping(value = "/recuperarArquivo/{idArquivo}", method = RequestMethod.GET)
	@ApiOperation(value = "GET recuperarArquivo", notes = "Recupera arquivo pelo ID")
	@CrossOrigin
	public FileUploadVO recuperarArquivo(@ApiParam(value = "ID do arquivo") @PathVariable("idArquivo") Long idArquivo) {
		return fileDAO.findById(idArquivo);
	}

	@ResponseBody
	@RequestMapping(value = "/{idArquivo}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Excluir", notes = "Excluir arquivo pelo ID")
	@CrossOrigin
	public Boolean deletarArquivo(@ApiParam(value = "ID do arquivo") @PathVariable("idArquivo") Long idArquivo) {
		FileUploadVO fileUploadVO = fileDAO.findById(idArquivo);
		if (fileUploadVO != null) {
			fileDAO.delete(fileUploadVO);
			return fileService.deletarArquivo(fileUploadVO);
		} else {
			throw new ServerUploadFileChunkedException("Arquivo não encontrado");
		}
	}

}
