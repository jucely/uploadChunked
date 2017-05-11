package com.hotmart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotmart.utils.FileUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilTest {

	@Test
	public void calcularQtdChunkes() {
		assertThat(FileUtil.calcChunkes(1024l), is(equalTo(1l)));

		// 1Mega = 1 chunk
		assertThat(FileUtil.calcChunkes(1048576l), is(equalTo(1l)));

		// 1,5Mega = 2 chunks
		assertThat(FileUtil.calcChunkes(1572864l), is(equalTo(2l)));

		// 1gb = 1024 chunks
		assertThat(FileUtil.calcChunkes(1073741824l), is(equalTo(1024l)));

	}
	
	@Test
	public void recuperarFileSizeofContentRange(){
		//bytes 0-1048575/1052958720
		
		assertThat(FileUtil.fileSizeOfContentRange("bytes 0-1048575/1052958720"), is(equalTo(1052958720l)));
		
		assertThat(FileUtil.fileSizeOfContentRange("0-1048575/1052958720"), is(equalTo(1052958720l)));
		
		assertThat(FileUtil.fileSizeOfContentRange("1048575/1052958720"), is(equalTo(1052958720l)));
		
		assertThat(FileUtil.fileSizeOfContentRange("/1052958720"), is(equalTo(1052958720l)));
		
		assertThat(FileUtil.fileSizeOfContentRange("1052958720"), is(equalTo(1052958720l)));
	}
}
