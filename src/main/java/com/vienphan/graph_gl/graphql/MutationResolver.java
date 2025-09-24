package com.vienphan.graph_gl.graphql;

import com.vienphan.graph_gl.model.Category;
import com.vienphan.graph_gl.model.Product;
import com.vienphan.graph_gl.model.User;
import com.vienphan.graph_gl.repository.CategoryRepository;
import com.vienphan.graph_gl.repository.ProductRepository;
import com.vienphan.graph_gl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class MutationResolver {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public static record UserInput(String fullname, String email, String password, String phone) {}
    public static record CategoryInput(String name, String images) {}
    public static record ProductInput(String title, Integer quantity, String desc, Double price, Long userId, Long categoryId) {}

    // User CRUD
    @MutationMapping
    public User createUser(@Argument("input") UserInput input) {
        User u = User.builder()
                .fullname(input.fullname())
                .email(input.email())
                .password(input.password())
                .phone(input.phone())
                .build();
        return userRepository.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument("input") UserInput input) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setFullname(input.fullname());
        existing.setEmail(input.email());
        existing.setPassword(input.password());
        existing.setPhone(input.phone());
        return userRepository.save(existing);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    // Category CRUD
    @MutationMapping
    public Category createCategory(@Argument("input") CategoryInput input) {
        Category c = Category.builder()
                .name(input.name())
                .images(input.images())
                .build();
        return categoryRepository.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument("input") CategoryInput input) {
        Category existing = categoryRepository.findById(id).orElseThrow();
        existing.setName(input.name());
        existing.setImages(input.images());
        return categoryRepository.save(existing);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepository.existsById(id)) return false;
        categoryRepository.deleteById(id);
        return true;
    }

    // Product CRUD
    @MutationMapping
    public Product createProduct(@Argument("input") ProductInput input) {
        User user = userRepository.findById(input.userId()).orElseThrow();
        Category category = categoryRepository.findById(input.categoryId()).orElseThrow();
        Product p = Product.builder()
                .title(input.title())
                .quantity(input.quantity())
                .desc(input.desc())
                .price(BigDecimal.valueOf(input.price()))
                .user(user)
                .category(category)
                .build();
        return productRepository.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument("input") ProductInput input) {
        Product p = productRepository.findById(id).orElseThrow();
        if (input.title() != null) p.setTitle(input.title());
        if (input.quantity() != null) p.setQuantity(input.quantity());
        if (input.desc() != null) p.setDesc(input.desc());
        if (input.price() != null) p.setPrice(BigDecimal.valueOf(input.price()));
        if (input.userId() != null) {
            User user = userRepository.findById(input.userId()).orElseThrow();
            p.setUser(user);
        }
        if (input.categoryId() != null) {
            Category category = categoryRepository.findById(input.categoryId()).orElseThrow();
            p.setCategory(category);
        }
        return productRepository.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
