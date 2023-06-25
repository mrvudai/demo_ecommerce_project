package daipv.controller;

import daipv.model.Users;
import daipv.service.Iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TestCallApi {
    private final IUserService service;
    @GetMapping("/call")
    public ResponseEntity<Page<Users>> getAll(Pageable pageable){
        return ResponseEntity.ok()
                .body(service.findAll(pageable));
    }
}
