package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.AnnualActivityCategoryService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.RoleService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnualActivityCategoryController {

    private final AnnualActivityCategoryService annualActivityCategoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public AnnualActivityCategoryController(AnnualActivityCategoryService annualActivityCategoryService, ModelMapper modelMapper) {
        this.annualActivityCategoryService = annualActivityCategoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all categories", response = ApiResponse.class)
    public ApiResponse getAllCategories(){
        return new ApiResponse(200, "SUCCESS", annualActivityCategoryService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one category by its ID", response = ApiResponse.class)
    public ApiResponse getOneCategoryById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", annualActivityCategoryService.getOne(id));

    }



    @GetMapping("/category-by-name")
    @ApiOperation(value = "Get one role by its name", response = ApiResponse.class)
    public ApiResponse getCategoryByName(@RequestParam("name") String name){
        return new ApiResponse(200, "SUCCESS", annualActivityCategoryService.findByCategory(name));
    }
}
