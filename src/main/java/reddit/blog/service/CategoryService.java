package reddit.blog.service;

import reddit.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {


    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    // Update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // Delete
    void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // getAll
    List<CategoryDto> getAllCategory();
}
