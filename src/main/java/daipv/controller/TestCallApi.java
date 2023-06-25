package daipv.controller;

import daipv.DTO.request.SignInForm;
import daipv.callAPI.CallAPI;
import daipv.model.Users;
import daipv.security.userprincipal.CustomUserDetailsService;
import daipv.service.Iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping
@RequiredArgsConstructor
public class TestCallApi {
    private final IUserService service;

    private final CustomUserDetailsService customUserDetailsService;

    private final RestTemplate restTemplate;

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

    @PostMapping("/testPost")
    public ResponseEntity<String> loginToOtherSerVer(@RequestBody SignInForm signInForm){
        String url = "http://localhost:8081/api/v4/auth/signin";
//        Users users = customUserDetailsService.getUserPrincipal();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<SignInForm> requestEntity = new HttpEntity<>(signInForm, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;

    }

}
