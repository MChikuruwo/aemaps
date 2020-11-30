package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddUserDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.GenerateCredentialsDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.LoginDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateUserDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Login;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.User;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.security.JwtTokenProvider;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmailService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoginService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.RoleService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final UserService userService;
    private final LoginService loginService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, LoginService loginService, RoleService roleService, ModelMapper modelMapper, EmailService emailService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.loginService = loginService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all users", response = ApiResponse.class)
    public ApiResponse getAllUsers(){
        return new ApiResponse(200, "SUCCESS", userService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one user by their ID", response = ApiResponse.class)
    public ApiResponse getOneUser(@PathVariable("id") Integer id) {
        return new ApiResponse(200, "SUCCESS", userService.getOne(id));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete user by their ID", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteUser(@PathVariable("id") Integer id){
        return new ApiResponse(200, "SUCCESS", userService.delete(id));
    }

    @PostMapping(value = "/register/{role-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register a user to the AEMAPS platform", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addUser(@RequestBody AddUserDto addUserDto, @PathVariable("role-id") Integer roleId, HttpServletRequest request){
        User user = modelMapper.map(addUserDto, User.class);

        // Assign the role of the user
        user.setRoles(Collections.singleton(roleService.getOne(roleId)));

        // Generate the password for user which needs to be reset
        String password = generatePassword(user.getName());

        // Set the user password to the generated password
        user.setPassword(password);

        // Set user active true by default
        user.setActive(true);

        // Send a confirmation email message
        String appUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath();

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmailAddress());
        registrationEmail.setSubject("AEMAPS Platform");
        registrationEmail.setText(" Dear " + user.getName() + ", \n You have been registered as a  " + roleService.getOne(roleId).getName()
                + " of the AEMAPS Platform.\n Email: " + user.getEmailAddress() +"\n Employee Code: "+user.getEmployeeCode() +"\n Password: " + password +
                ". \n Please keep your password safe and reset it whenever it's necessary.");
        registrationEmail.setFrom("mchikuruwo@hotmail.com");

        emailService.sendEmail(registrationEmail);

        return new ApiResponse(200,  "SUCCESS", userService.add(user));
    }

    private String generatePassword(String username) {
        String generatedPassword;
        // Generate random number to append to user's first name
        Random randomNumber = new Random();
        int n = 1000 + randomNumber.nextInt(9999);
        // Concatenate the random number and first name
        generatedPassword = username.concat(String.valueOf(n));
        return generatedPassword;
    }

    @PutMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a current user's details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable("id") Integer id){
        User user = modelMapper.map(updateUserDto, User.class);
        return new ApiResponse(200, "SUCCESS", userService.update(user));
    }

    @PostMapping(value = "/login/email")
    @ApiOperation("Enables a user to login with email address and password")
    public ResponseEntity loginWithEmailAndPassword(@RequestBody LoginDto accountCredentials) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        accountCredentials.getEmailAddress(),
                        accountCredentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        //Check if the authentication was successful. If it is, then return the details of the user
        ApiResponse response;
        if (authentication.isAuthenticated()){
            User authenticatedUser = userService.findByEmailAddress(accountCredentials.getEmailAddress());

            // Log user login in database
            Login login = new Login();
            login.setUser(authenticatedUser);
            loginService.add(login);

            response = new ApiResponse(200, "SUCCESS", authenticatedUser);
            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + " " + jwt).body(response);
        }
        else {
            response = new ApiResponse(400,"Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN).body(response);

        }
    }

    @PostMapping(value = "/login/employeeCode")
    @ApiOperation("Enables a user to login with employee code and password")
    public ResponseEntity loginWithEmployeeCodeAndPassword(@RequestBody LoginDto accountCredentials) {
        Authentication authentication1 = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        accountCredentials.getEmployeeCode(),
                        accountCredentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication1);
        String jwt = jwtTokenProvider.generateToken(authentication1);
        //Check if the authentication was successful. If it is, then return the details of the user
        ApiResponse response;
        if (authentication1.isAuthenticated()){
            User authenticatedUser = userService.findByEmployeeCode(accountCredentials.getEmployeeCode());

            // Log user login in database
            Login login = new Login();
            login.setUser(authenticatedUser);
            loginService.add(login);

            response = new ApiResponse(200, "SUCCESS", authenticatedUser);
            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + " " + jwt).body(response);
        }
        else {
            response = new ApiResponse(400,"Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN).body(response);

        }
    }

    @PostMapping("/generate-credentials")
    @ApiOperation(value = "Generates new password for user.", response = ApiResponse.class)
    public ApiResponse generateCredentialsForUser(@RequestBody GenerateCredentialsDto credentialsDto){
        // Get user by their email address
        User user = userService.findByEmailAddress(credentialsDto.getEmailAddress());

        // Generate the password for user which needs to be reset
        String password = generatePassword(user.getName());

        // Set the user password to the generated password
        user.setPassword(password);

        SimpleMailMessage generatedCredentialsEmail = new SimpleMailMessage();
        generatedCredentialsEmail.setTo(user.getEmailAddress());
        generatedCredentialsEmail.setSubject("AEMAPS Platform");
        generatedCredentialsEmail.setText(" Dear " + user.getName() + " Your new credentials are as follows: \n Email: " + user.getEmailAddress() +"\n Password: " + password +
                ". \n Please keep your password safe and reset it whenever it's necessary.");
        generatedCredentialsEmail.setFrom("mchikuruwo@hotmail.com");

        emailService.sendEmail(generatedCredentialsEmail);

        return new ApiResponse(200,  "SUCCESS", userService.add(user));
    }
}
