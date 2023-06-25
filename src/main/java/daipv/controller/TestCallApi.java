package daipv.controller;

import daipv.callAPI.CallAPI;
import daipv.model.Users;
import daipv.service.Iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin("*")
@RequestMapping
@RequiredArgsConstructor
public class TestCallApi {
    private final IUserService service;

    private final CallAPI callAPI;
    @GetMapping("/auth/call")
    public ResponseEntity<Users> get(){
        return ResponseEntity.ok()
                .body(callAPI.getUser());
    }

    @GetMapping("/call")
    public ResponseEntity<Users> call(){
        return new ResponseEntity<>(callAPI.getUserByToken(), HttpStatus.OK);
    }
}
