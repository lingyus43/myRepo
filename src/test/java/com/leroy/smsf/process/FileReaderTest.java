package com.leroy.smsf.process;

import com.leroy.smsf.exception.FileReadException;
import com.leroy.smsf.process.FileReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FileReaderTest {
    @Test
    public void fileReaderTest() throws Exception{
        FileReader fileReader = new FileReader();
        String fundDetail = fileReader.readFund("src/test/resources/SMSFTestFile.txt");
        List<String > membersDetail = fileReader.readMembers("src/test/resources/SMSFTestFile.txt");
        System.out.println(fundDetail);
        membersDetail.forEach(detail -> System.out.println(detail));
        System.out.println(membersDetail.get(0).isEmpty());
    }

    @Test(expected = FileReadException.class)
    public void fileReaderExceptionTest() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.readFund("src/test/resources/testFile.txt");
    }
}