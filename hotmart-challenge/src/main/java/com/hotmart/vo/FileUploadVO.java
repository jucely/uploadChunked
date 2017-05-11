package com.hotmart.vo;

import java.util.Date;

import com.hotmart.utils.StatusUpload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("FileUploadVO Entity")
public class FileUploadVO {

	@ApiModelProperty(value = "Id", required = true)
	private Long id;
	@ApiModelProperty(value = "Id do Usuário", required = true)
	private String idUsuario;

	private String nomeArquivo;

	private StatusUpload statusUpload;
	private Date dtEnvio;
	private Long qtdBlocos;
	private Long qtdBlocosGravados;
	private String linkDownload;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public StatusUpload getStatusUpload() {
		return statusUpload;
	}

	public void setStatusUpload(StatusUpload statusUpload) {
		this.statusUpload = statusUpload;
	}

	public Date getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public Long getQtdBlocos() {
		return qtdBlocos;
	}

	public void setQtdBlocos(Long qtdBlocos) {
		this.qtdBlocos = qtdBlocos;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getLinkDownload() {
		return linkDownload;
	}

	public void setLinkDownload(String linkDownload) {
		this.linkDownload = linkDownload;
	}

	public Long getQtdBlocosGravados() {
		return qtdBlocosGravados;
	}

	public void setQtdBlocosGravados(Long qtdBlocosGravados) {
		this.qtdBlocosGravados = qtdBlocosGravados;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof FileUploadVO) {
			FileUploadVO objAux = (FileUploadVO) obj;
			equals = (id != null && objAux.getId() != null && id.equals(objAux.getId())) || (nomeArquivo != null
					&& objAux.getNomeArquivo() != null && nomeArquivo.equals(objAux.getNomeArquivo()));
		}
		return equals;
	}

}
