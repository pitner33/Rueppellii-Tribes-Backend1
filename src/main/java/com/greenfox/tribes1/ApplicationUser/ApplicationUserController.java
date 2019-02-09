package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class ApplicationUserController {

  private ApplicationUserService applicationUserService;

  @Autowired
  public ApplicationUserController(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws UsernameTakenException, NotValidKingdomNameException {
    ApplicationUser applicationUser = applicationUserService.registerNewUser(applicationUserDTO);
    return ResponseEntity.ok().body(applicationUserService.createDTOwithKingdomfromUser(applicationUser));
  }
}
