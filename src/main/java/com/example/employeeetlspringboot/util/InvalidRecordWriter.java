package com.example.employeeetlspringboot.util;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class InvalidRecordWriter {

    public void write(String filePath, String record, String reason) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(record + " -> " + reason + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Failed to write invalid record: " + e.getMessage());
        }
    }
}