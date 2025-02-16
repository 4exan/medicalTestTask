package ua.dev.medicaltesttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import ua.dev.medicaltesttask.dto.NewVisitDto;
import ua.dev.medicaltesttask.dto.ResponseDto;
import ua.dev.medicaltesttask.service.VisitService;

import java.util.List;

@RestController
@RequestMapping("/test-task/visit")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public ResponseDto getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<Long> doctorIds){
        Pageable p = PageRequest.of(page, size);
        return visitService.getAllVisits(search, doctorIds, p);
    }

    @PostMapping
    public HttpStatusCode createNewVisit(@RequestBody NewVisitDto res){
        visitService.createNewVisit(res);
        return HttpStatus.CREATED;
    }

}
