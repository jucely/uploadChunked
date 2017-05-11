package com.hotmart.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hotmart.exceptions.ServerUploadFileChunkedException;

public class FileUtil {

	private static final Long CHUNK_SIZE = 1024l * 1024l;

	public static String readFile(File file) {
		StringBuilder str = new StringBuilder();
		try {
			FileReader br = new FileReader(file);
			BufferedReader reader = new BufferedReader(br);
			String line;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			throw new ServerUploadFileChunkedException("Erro ao ler arquivo");
		}
		return str.toString();
	}

	public static void writeFile(File file, String text) {
		try {
			FileWriter br = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(br);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			throw new ServerUploadFileChunkedException("Erro ao Gravar arquivo");
		}
	}

	public static void appendFile(File file, byte[] bytes) {
		try {
			FileUtils.writeByteArrayToFile(file, bytes, true);
		} catch (IOException e) {
			throw new ServerUploadFileChunkedException("Erro ao Gravar arquivo");
		}
	}

	public static Long calcChunkes(Long fileSize) {
		Long chunk = fileSize / CHUNK_SIZE;
		chunk += fileSize % CHUNK_SIZE > 0 ? 1 : 0;
		return chunk;
	}

	public static Long fileSizeOfContentRange(String contentRange) {
		if (contentRange != null && !contentRange.isEmpty()) {
			return Long.parseLong(contentRange.replaceFirst(".*/([^\"]+).*$", "$1"));
		}
		return 0l;
	}

	public static File[] getFilesFromDirectory(String directory) {
		File folder = new File(directory);
		if (folder.isDirectory() && folder.exists()) {
			return folder.listFiles();
		} else {
			return new File[] {};
		}
	}

	public static boolean mkdirs(String directory) {
		File diretorio = new File(directory);
		if (!diretorio.exists()) {
			return diretorio.mkdirs();
		}
		return diretorio.exists();
	}

	public static boolean delete(String directory) {
		File diretorio = new File(directory);
		return diretorio.delete();
	}

	public static boolean moveFile(String origem, String destino) {
		File fileOrigem = new File(origem);
		return fileOrigem.renameTo(new File(destino));
	}

}
