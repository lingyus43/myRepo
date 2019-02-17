package com.leroy.smsf.process;

import com.leroy.smsf.exception.FileReadException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileReader {
    org.slf4j.Logger log = LoggerFactory.getLogger("Log");

    private List<String> readFile(String filePath) throws FileReadException {
        try {
            Path path = Paths.get(filePath);
            List<String> content = Files.readAllLines(path);
            return content;
        }catch (IOException e){
            log.error("Failed reading file");
            throw new FileReadException("Failed reading file");
        }
    }

    public String readFund(String filePath) throws FileReadException {
        List<String> content = readFile(filePath);
        if (content.get(0).isEmpty()){
            log.error("No fund information input.");
            throw new FileReadException("Please make sure to list income, expense, and tax rate at the first line");
        }
        String fundDetail;
        fundDetail = content.get(0);
        return fundDetail;
    }

    public List<String> readMembers(String filePath) throws FileReadException{
        List<String> members = readFile(filePath);
        return members.subList(1, members.size()).stream().filter(member -> !member.isEmpty()).collect(Collectors.toList());
    }
}
