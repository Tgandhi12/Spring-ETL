package com.example.employeeetlspringboot.controller;

import com.example.employeeetlspringboot.service.DbTransferService;
import com.example.employeeetlspringboot.service.FilePipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pipeline")
@RequiredArgsConstructor
public class PipelineController {

    private final DbTransferService dbTransferService;
    private final FilePipelineService filePipelineService;

    @PostMapping("/db-transfer")
    public String transferDbToDb() {
        return dbTransferService.transferEmployees();
    }

    @PostMapping("/csv-import")
    public String importCsv() {
        return filePipelineService.importFromCsv(
                "employees.csv",
                "invalid-records.txt"
        );
    }
    @PostMapping("/json-import")
    public String importJson(){
        return filePipelineService.importFromJson(
                "employees.json",
                "invalid-records.txt"
        );
    }
}