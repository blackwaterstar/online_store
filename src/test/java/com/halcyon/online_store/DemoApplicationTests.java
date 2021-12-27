package com.halcyon.online_store;

import com.halcyon.online_store.entity.Comment;
import com.halcyon.online_store.entity.Product;
import com.halcyon.online_store.entity.vo.CartVO;
import com.halcyon.online_store.service.CartService;
import com.halcyon.online_store.service.CommentService;
import com.halcyon.online_store.service.ProductService;
import com.halcyon.online_store.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    @Resource
    private ProductService productService;

    @Resource
    private CartService cartService;

    @Test
    void contextLoads() {

    }

    @Test
    public void a() {
//        List<Comment> comments = commentService.selectCommentById((long) 100201);
//        comments.forEach(System.out::println);
//        Product product = new Product();
//        product.setPname("手套");
//        product.setTid((long) 11105);
//        productService.addProduct(product);
//        userService.login((long) 55555, "2");
//        userService.listUserDto().forEach(System.out::println);
//        System.out.println(userService.searchUserDto((long) 123456789));
        cartService.getCartVOList((long) 123456789).forEach(System.out::println);

    }


}
