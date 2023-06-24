package daipv.controller;

import daipv.DTO.request.SignInForm;
import daipv.DTO.request.SignupForm;
import daipv.DTO.response.JWTResponse;
import daipv.DTO.response.ResponseMessage;
import daipv.fileManipulation.export.ExcelExporter;
import daipv.service.serviceIMPL.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ExcelExporter excelExporter;

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody SignupForm signupForm){
            return ResponseEntity.ok(authService.register(signupForm));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTResponse> login(@RequestBody SignInForm signInForm){
        return ResponseEntity.ok(authService.login(signInForm));
    }

    @GetMapping("/excel")
    public void export(HttpServletResponse response){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
        excelExporter.reportExcel(response);
    }
}
