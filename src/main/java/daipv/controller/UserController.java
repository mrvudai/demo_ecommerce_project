package daipv.controller;

import daipv.DTO.request.ChangeRoleDTO;
import daipv.DTO.response.ResponseMessage;
import daipv.service.Iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @PutMapping("/changeRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> changeRole(@RequestBody ChangeRoleDTO changeRoleDTO){
        return new ResponseEntity<>(userService.setRole(changeRoleDTO), HttpStatus.OK);
    }
}
