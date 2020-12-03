package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.NoticesService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/notices", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/notices", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticesController {

    private final NoticesService noticesService;
    private final ModelMapper modelMapper;

    @Autowired
    public NoticesController(NoticesService noticesService, ModelMapper modelMapper) {
        this.noticesService = noticesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all notices", response = ApiResponse.class)
    public ApiResponse getAllNotices(){
        return new ApiResponse(200, "SUCCESS", noticesService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one notice by its ID", response = ApiResponse.class)
    public ApiResponse getOneNoticeById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", noticesService.getOne(id));

    }

    @GetMapping("/notice-by-title")
    @ApiOperation(value = "Get one notice by its title", response = ApiResponse.class)
    public ApiResponse getNoticeByTitle(@RequestParam("title") String title){
        return new ApiResponse(200, "SUCCESS", noticesService.findByTitle(title));
    }
}

