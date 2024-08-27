package swahili.cafe.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swahili.cafe.constant.FileConstant;
import swahili.cafe.model.Expert;
import swahili.cafe.model.User;
import swahili.cafe.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Expert registerNewExpert(Expert expert) {
        validateNewUsernameAndEmail(null, expert.getUsername(), expert.getEmail());
        expert.setPassword(passwordEncoder.encode(expert.getPassword()));
        expert.setRole("ROLE_EXPERT");
        expert.setActive(true);
        expert.setNotLocked(true);
        log.info("New expert registered.");
        return userRepository.save(expert);
    }


    public User registerNewAuthor(User author) {
        validateNewUsernameAndEmail(null, author.getUsername(), author.getEmail());
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        author.setRole("ROLE_AUTHOR");
        author.setActive(true);
        author.setNotLocked(true);
        log.info("New author registered.");
        return userRepository.save(author);
    }


    public User addNewUser(User user) {
        validateNewUsernameAndEmail(null, user.getUsername(), user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setNotLocked(true);
        log.info("New user registered.");
        return userRepository.save(user);
    }



    //    UPDATE USER
    public Expert updateExpert(String currentUsername, String firstName, String secondName,  String lastName, String username,
                           String email, String phoneNumber, String dateOfBirth,
                           String gender, String address, boolean isActive, boolean isNotLocked,
                               MultipartFile profileImage, String otherProgram, String computerKnowledge,
                               boolean isEmployed, String companyName, String position, String employedYear) throws IOException {
        Expert updateExpert = (Expert) validateNewUsernameAndEmail(currentUsername, username, email);
        assert updateExpert != null;
        updateExpert.setFirstName(firstName);
        updateExpert.setSecondName(secondName);
        updateExpert.setLastName(lastName);
        updateExpert.setUsername(username);
        updateExpert.setEmail(email);
        updateExpert.setPhoneNumber(phoneNumber);
        updateExpert.setDateOfBirth(dateOfBirth);
        updateExpert.setGender(gender);
        updateExpert.setAddress(address);
        updateExpert.setActive(isActive);
        updateExpert.setNotLocked(isNotLocked);
//        updateExpert.setCollegeName(collegeName);
//        updateExpert.setQualification(qualification);
//        updateExpert.setStartYear(startYear);
//        updateExpert.setEndYear(endYear);
//        updateExpert.setProgramName(programName);
        updateExpert.setOtherProgram(otherProgram);
        updateExpert.setComputerKnowledge(computerKnowledge);
        updateExpert.setEmployed(isEmployed);
        updateExpert.setCompanyName(companyName);
        updateExpert.setPosition(position);
        updateExpert.setEmployedYear(employedYear);
        log.info("Expert updated successfully.");
        userRepository.save(updateExpert);
        saveProfileImage(updateExpert, profileImage);
        return updateExpert;
    }


    //    UPDATE USER
    public User updateUser(String currentUsername, String firstName, String secondName,  String lastName, String username,
                           String email, String phoneNumber, String dateOfBirth,
                           String gender, String address, String role, boolean isActive, boolean isNotLocked, MultipartFile profileImage) throws IOException {
        User updateUser =  validateNewUsernameAndEmail(currentUsername, username, email);
        assert updateUser != null;
        updateUser.setFirstName(firstName);
        updateUser.setSecondName(secondName);
        updateUser.setLastName(lastName);
        updateUser.setUsername(username);
        updateUser.setEmail(email);
        updateUser.setPhoneNumber(phoneNumber);
        updateUser.setDateOfBirth(dateOfBirth);
        updateUser.setGender(gender);
        updateUser.setAddress(address);
        updateUser.setRole(role);
        updateUser.setActive(isActive);
        updateUser.setNotLocked(isNotLocked);
        log.info("User updated successfully.");
        userRepository.save(updateUser);
        saveProfileImage(updateUser, profileImage);
        return updateUser;
    }


    //    DELETE USER
    public void deleteUser(long userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User by ID: " + userId + " not found");
        }

        userRepository.deleteById(userId);
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public List<Expert> getAllExperts() {
        return userRepository.getAllExperts();
    }


    public List<User> getAllAuthors() {
        return userRepository.getAllAuthors();
    }



    //    findUserByUsername
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User by username: " + username + " not found");
        }

        return user;
    }




    //    findUserByUserId
    public User findUserByUserId(long userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User by ID: " + userId + " not found");
        }

        return user;
    }


    //    SAVE PROFILE IMAGE
    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException {
        if (profileImage != null) {
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                log.info("Directory created for: " + userFolder);
            }

            Files.deleteIfExists(Paths.get(FileConstant.USER_FOLDER + user.getUsername() + "." + "jpg"));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + "." + "jpg"), REPLACE_EXISTING);
            user.setProfileImage(setProfileImage(user.getUsername()));
            userRepository.save(user);
            log.info(profileImage.getOriginalFilename());
        }
    }

    private String setProfileImage(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.USER_IMAGE_PATH + username + "/" + username + "." + "jpg").toUriString();
    }


    //    updateProfileImage
    public User updateProfileImage(String username, MultipartFile profileImage) throws IOException {
        User user = validateNewUsernameAndEmail(username, null, null);
        saveProfileImage(user, profileImage);
        return user;
    }


    //  VALIDATE NEW USERNAME AND EMAIL
    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) {
        User userByNewUsername = userRepository.findUserByUsername(newUsername);
        User userByNewEmail = userRepository.findUserByEmail(newEmail);
        if (currentUsername != null) {

            User currentUser = userRepository.findUserByUsername(currentUsername);
            if (currentUser == null) {
                log.error("NO_USER_FOUND_BY_USERNAME " + currentUsername);
                throw new RuntimeException("NO_USER_FOUND_BY_USERNAME " + currentUsername);
            }

            if (userByNewUsername != null && !currentUser.getUserId().equals(userByNewUsername.getUserId())) {
                log.error("USERNAME_ALREADY_EXISTS " + userByNewUsername);
                throw new RuntimeException("USERNAME_ALREADY_EXISTS" + userByNewUsername);
            }

            if (userByNewEmail != null && !currentUser.getUserId().equals(userByNewEmail.getUserId())) {
                log.error("EMAIL_ALREADY_EXISTS " + userByNewEmail);
                throw new RuntimeException("EMAIL_ALREADY_EXISTS " + userByNewEmail);
            }

            return currentUser;

        } else {

            if (userByNewUsername != null) {
                log.error("USERNAME_ALREADY_EXISTS " + userByNewUsername);
                throw new RuntimeException("USERNAME_ALREADY_EXISTS " + userByNewUsername);
            }

            if (userByNewEmail != null) {
                log.error("EMAIL_ALREADY_EXISTS " + userByNewEmail);
                throw new RuntimeException("EMAIL_ALREADY_EXISTS " + userByNewEmail);
            }

            return null;

        }
    }


    public Integer getTotalUsers() {
        return userRepository.getTotalUsers();
    }

    public Integer getTotalStaffs() {
        return userRepository.getTotalStaffs();
    }


    public Integer getTotalAuthors() {
        return userRepository.getTotalAuthors();
    }
    public Integer getTotalExperts() {
        return userRepository.getTotalExperts();
    }
}
