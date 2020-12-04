package register;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class FileManagerTest {

	@Test
	void test() throws IOException {
		FileManager tester = new FileManager(); // FileManager is tested
		Info info=tester.check(); 				//We check if the file has the right format
		File file = tester.register(info);		//We write the new file
		assertTrue(file.exists());				//We check is the file exists
	
	}

}
