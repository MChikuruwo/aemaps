package zw.Digital_Services_Customer.Api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zw.Digital_Services_Customer.Api.dto.AddUserDto;
import zw.Digital_Services_Customer.Api.models.User;
import zw.Digital_Services_Customer.Api.service.Sms;

import java.util.Random;

@RestController
@RequestMapping("api/v1/users")
public class SmsController {

    private  final ModelMapper modelMapper;

    @Autowired
    public SmsController( ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/signup", produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String send(@RequestBody AddUserDto addUserDto, Sms registrationSms, String oneTimePin) {
        String uri = "https://bulksms.econet.co.zw/sms/sendsms.jsp?user=Tapuwam&password=@Tree123&mobiles="+addUserDto.getMobileNumber()+"&sms="+registrationSms.setMessage(generateOTP(oneTimePin))+"&senderid=Digital";

        User user = modelMapper.map(addUserDto, User.class);

        //mobileNumber = user.getMobileNumber();
        //message = registrationSms.setMessage("Done??"+user.getMobileNumber());

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri,  String.class);

        return result;
    }

    private String generateOTP(String otp) {
        String generatedOTP;
        // Generate random number to be used as the OTP
        Random randomNumber = new Random();
        int n = 1000 + randomNumber.nextInt(9999);
        generatedOTP = (String.valueOf(n));
        return generatedOTP;

    }
}