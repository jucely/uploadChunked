package com.hotmart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotmart.dao.FileUploadDAO;
import com.hotmart.vo.FileUploadVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadDaoTest {

	@Autowired
	private FileUploadDAO fileDAO;

	@Test
	public void testIsNotNull() {
		assertThat(fileDAO, notNullValue());
	}

	@Test
	public void testFileUploadCRUD() {
		FileUploadVO fileUploadVO = fileDAO.create("teste.txt", "userTest", 20l);

		assertThat(fileUploadVO, notNullValue());

		Long idFile = fileUploadVO.getId();

		assertThat(idFile, notNullValue());

		FileUploadVO fileUploadAux = fileDAO.findById(idFile);

		assertThat(fileUploadAux, notNullValue());

		fileUploadVO.setNomeArquivo("teste2.txt");

		fileUploadAux = fileDAO.save(fileUploadVO);

		assertThat(fileUploadAux.getNomeArquivo(), is(equalTo("teste2.txt")));

		fileDAO.delete(fileUploadVO);

		fileUploadAux = fileDAO.findById(idFile);

		assertThat(fileUploadAux, nullValue());
	}
}
