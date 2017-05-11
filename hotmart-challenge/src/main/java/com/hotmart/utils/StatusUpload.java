package com.hotmart.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusUpload {
	EM_ANDAMENTO("Em andamento"),
	FALHA("falha"),
	CONCLUIDO("concluído");
	
	private String desc;
	
	private StatusUpload(String desc) {
		this.desc = desc;
	}

	@JsonValue
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
