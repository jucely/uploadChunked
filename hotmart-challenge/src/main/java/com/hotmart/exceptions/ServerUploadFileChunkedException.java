package com.hotmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServerUploadFileChunkedException extends RuntimeException {
	
	private static final long serialVersionUID = -2183025609584979377L;

	public ServerUploadFileChunkedException(String message) {
        super(message);
    }

    public ServerUploadFileChunkedException(String message, Throwable cause) {
        super(message, cause);
    }

}
