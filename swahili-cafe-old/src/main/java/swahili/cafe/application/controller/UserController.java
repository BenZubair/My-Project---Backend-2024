package swahili.cafe.application.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swahili.cafe.application.constant.FileConstant;
import swahili.cafe.application.model.Expert;
import swahili.cafe.application.model.User;
import swahili.cafe.application.service.UserService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register-expert")
    public ResponseEntity<Expert> registerNewExpert(@RequestBody Expert expert) {
        Expert newExpert = userService.registerNewExpert(expert);
        return new ResponseEntity<>(newExpert, HttpStatus.OK);
    }


    @PostMapping("/register-author")
    public ResponseEntity<User> registerNewAuthor(@RequestBody Expert expert) {
        User newExpert = userService.registerNewAuthor(expert);
        return new ResponseEntity<>(newExpert, HttpStatus.CREATED);
    }



    @PostMapping("/add-new-user")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        User users = userService.addNewUser(user);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    //    UPDATE USER
    @PutMapping("/update-expert")
    public ResponseEntity<User> updateExpert(
            @RequestParam(value = "currentUsername", required = false) String currentUsername,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "isActive", required = false) boolean isActive,
            @RequestParam(value = "isNotLocked", required = false) boolean isNotLocked,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "otherProgram", required = false) String otherProgram,
            @RequestParam(value = "computerKnowledge", required = false) String computerKnowledge,
            @RequestParam(value = "isEmployed", required = false) boolean isEmployed,
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "employedYear", required = false) String employedYear) throws IOException {

        User updateUser = userService.updateExpert(currentUsername, firstName, secondName, lastName, username, email, phoneNumber, dateOfBirth, gender, address, isActive, isNotLocked, profileImage, otherProgram, computerKnowledge,  isEmployed, companyName, position, employedYear);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    //    UPDATE USER
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(
            @RequestParam(value = "currentUsername", required = false) String currentUsername,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "isActive", required = false) boolean isActive,
            @RequestParam(value = "isNotLocked", required = false) boolean isNotLocked,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {

        User updateUser = userService.updateUser(currentUsername, firstName, secondName, lastName, username, email, phoneNumber, dateOfBirth, gender, address, role, isActive, isNotLocked, profileImage);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/all-experts")
    public ResponseEntity<List<Expert>> getAllExperts() {
        List<Expert> allExperts = userService.getAllExperts();
        return new ResponseEntity<>(allExperts, HttpStatus.OK);
    }




    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable long userId) {
        User userByUserId = userService.findUserByUserId(userId);
        return new ResponseEntity<>(userByUserId, HttpStatus.OK);
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        User userByUsername = userService.findUserByUsername(username);
        return new ResponseEntity<>(userByUsername, HttpStatus.OK);
    }



    //    DELETE USER
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //    UPDATE PROFILE IMAGE
    @PutMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(
            @RequestParam("username") String username,
            @RequestParam(value = "profileImage") MultipartFile profileImage) throws IOException {

        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //    DISPLAY PROFILE IMAGE
    @GetMapping(path = "/image/{username}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable String username, @PathVariable String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + username + "/" + fileName));
    }


    @GetMapping("/total-users")
    public ResponseEntity<Integer> getTotalCustomers() {
        Integer totalUsers = userService.getTotalUsers();
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }


    @GetMapping("/total-staffs")
    public ResponseEntity<Integer> getTotalStaffs() {
        Integer totalStaffs = userService.getTotalStaffs();
        return new ResponseEntity<>(totalStaffs, HttpStatus.OK);
    }

    @GetMapping("/total-authors")
    public ResponseEntity<Integer> getTotalAuthors() {
        Integer totalAuthors = userService.getTotalAuthors();
        return new ResponseEntity<>(totalAuthors, HttpStatus.OK);
    }


    @GetMapping("/total-experts")
    public ResponseEntity<Integer> getTotalExperts() {
        Integer totalExperts = userService.getTotalExperts();
        return new ResponseEntity<>(totalExperts, HttpStatus.OK);
    }

}
