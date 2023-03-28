package reddit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reddit.blog.payloads.ApiResponse;
import reddit.blog.payloads.CategoryDto;
import reddit.blog.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    // Create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
    return new ResponseEntity<CategoryDto>(this.service.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        return new ResponseEntity<CategoryDto>(this.service.updateCategory(categoryDto, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryDtoId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryDtoId){
        this.service.deleteCategory(categoryDtoId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully !!", false), HttpStatus.OK);
    }

    @GetMapping("/{categoryDtoId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryDtoId){
        return new ResponseEntity<CategoryDto>(this.service.getCategory(categoryDtoId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<List<CategoryDto>>(this.service.getAllCategory(), HttpStatus.OK);
    }
}
