package hr.fer.ruazosa.sharemylocation.control;

import hr.fer.ruazosa.sharemylocation.dao.ShareMyLocationDAO;
import hr.fer.ruazosa.sharemylocation.entity.ErrorStatus;
import hr.fer.ruazosa.sharemylocation.entity.Group;
import hr.fer.ruazosa.sharemylocation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created on 16/06/17.
 */

@RestController
public class ShareMyLocationController {

    @Autowired
    private ShareMyLocationDAO shareMyLocationDAO;


    @PostMapping(value = "/registerUser")
    public ResponseEntity registerUser(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Field validation error");
            errorStatus.setFieldValidationErrors(result.getFieldErrors());
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        };

        if (shareMyLocationDAO.registerUser(user) == null) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Username already registered");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(value = "/loginUser")
    public ResponseEntity loginUser(@RequestParam("userName") String userName,
                                    @RequestParam("userPassword") String userPassword)
    {

       if (userName == null || userName.isEmpty()) {
           ErrorStatus errorStatus = new ErrorStatus();
           errorStatus.setErrorMessage("userName must be specified in GET request");
           return new ResponseEntity(errorStatus, HttpStatus.OK);
       }
        if (userPassword == null || userPassword.isEmpty()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("userPassword must be specified in GET request");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }
        User user = shareMyLocationDAO.loginUser(userName, userPassword);

        if (user == null) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Invalid username or password");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }


    @GetMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestParam("userName") String userName,
                                        @RequestParam("userPassword") String userPassword) {

        if (userName == null || userName.isEmpty()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("userName must be specified in GET request");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }
        if (userPassword == null || userPassword.isEmpty()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("userPassword must be specified in GET request");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        User bUser=shareMyLocationDAO.updatePassword(userName, userPassword);
        if(bUser==null){
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Invalid username or password");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        return new ResponseEntity(bUser, HttpStatus.OK);


    }

    @PostMapping(value = "/createGroup")
    public ResponseEntity createGroup(@Valid @RequestBody Group group, BindingResult result) {

        if (result.hasErrors()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Field validation error");
            errorStatus.setFieldValidationErrors(result.getFieldErrors());
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        };

        if (shareMyLocationDAO.createGroup(group) == null) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Group name already in use. Try another one.");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        return new ResponseEntity(group, HttpStatus.OK);
    }

    @GetMapping(value = "/testGroup")
    public ResponseEntity testGroup(@RequestParam("groupName") String groupName)
    {

        if (groupName == null || groupName.isEmpty()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("userName must be specified in GET request");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }
        if (groupName == null || groupName.isEmpty()) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("userPassword must be specified in GET request");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }
        Group bGroup = shareMyLocationDAO.testGroup(groupName);

        if (bGroup == null) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.setErrorMessage("Invalid username or password");
            return new ResponseEntity(errorStatus, HttpStatus.OK);
        }

        return new ResponseEntity(bGroup, HttpStatus.OK);
    }

}
