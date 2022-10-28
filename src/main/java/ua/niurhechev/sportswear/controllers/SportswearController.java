package ua.niurhechev.sportswear.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.niurhechev.sportswear.models.*;
import ua.niurhechev.sportswear.services.ManufacturerService;
import ua.niurhechev.sportswear.services.OrderService;
import ua.niurhechev.sportswear.services.ProductService;
import ua.niurhechev.sportswear.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class SportswearController {
    private final UserService userService;
    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public SportswearController(UserService userService, ProductService productService, ManufacturerService manufacturerService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String startPage() {
        return "start-page";
    }
    @GetMapping("start-page")
    public String startPageReEnter() {
        return "start-page";
    }
    @GetMapping("/products-view")
    public String productsView(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products-view";
    }

    @GetMapping("/products-view-admin")
    public String productsViewAdmin(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products-view-admin";
    }

    @GetMapping("sign-up")
    public String signUpGet(Model model){
        model.addAttribute("signUpModel", new SignUpModel());
        return "sign-up";
    }

    @PostMapping("sign-up")
    public String signUpPost(@ModelAttribute SignUpModel signUpModel, Model model) {
        List<User> users = userService.getAll();
        if(userService.getByName(signUpModel.getNickname())!=null) {
            model.addAttribute("error", "This nickname is taken!");
            return "sign-up";
        }
        if(!signUpModel.getPasswd().equals(signUpModel.getConfirm())) {
            model.addAttribute("error", "Passwords aren't matching!");
            return "sign-up";
        }
        if(signUpModel.getPasswd().length()<8) {
            model.addAttribute("error", "Password must contain at least 8 characters!");
            return "sign-up";
        }
        User user = new User();
        user.setNickname(signUpModel.getNickname());
        user.setPasswd(signUpModel.getPasswd());
        user.setAdmin(false);
        userService.save(user);
        return "redirect:/start-page";
    }

    @GetMapping("admin-add")
    public String addAdminGet(Model model) {
        model.addAttribute("signUpModel", new SignUpModel());
        return "admin-add";
    }

    @PostMapping("admin-add")
    public String addAdminPost(@ModelAttribute SignUpModel signUpModel, Model model) {
        List<User> users = userService.getAll();
        if(userService.getByName(signUpModel.getNickname())!=null) {
            model.addAttribute("error", "This nickname is taken!");
            return "admin-add";
        }
        if(!signUpModel.getPasswd().equals(signUpModel.getConfirm())) {
            model.addAttribute("error", "Passwords aren't matching!");
            return "admin-add";
        }
        if(signUpModel.getPasswd().length()<8) {
            model.addAttribute("error", "Password must contain at least 8 characters!");
            return "admin-add";
        }
        User user = new User();
        user.setNickname(signUpModel.getNickname());
        user.setPasswd(signUpModel.getPasswd());
        user.setAdmin(true);
        userService.save(user);
        model.addAttribute("successMessage", "New administrator added!");
        return "admin-add";
    }

    @GetMapping("sign-in")
    public String signInGet(Model model){
        SignInModel signInModel = new SignInModel();
        model.addAttribute("signInModel", signInModel);
        return "sign-in";
    }

    @PostMapping("sign-in")
    public String signInPost(@ModelAttribute SignInModel signInModel, Model model) {
        User user = userService.getByName(signInModel.getNickname());
        if(user==null) {
            model.addAttribute("error", "A user with this nickname doesn't exist!");
            return "sign-in";
        }
        if(!user.getPasswd().equals(signInModel.getPasswd())) {
            model.addAttribute("error", "Incorrect password!");
            return "sign-in";
        }
        if(user.isAdmin())
            return "redirect:/products-view-admin";
        else
            return "redirect:/products-view";
    }

    @GetMapping("products-add")
    public String addProductGet(Model model) {
        model.addAttribute("productInfo", new ProductFormInfo());
        model.addAttribute("manufacturers", manufacturerService.getAll());
        return "products-add";
    }
    @PostMapping("products-add")
    public String addProductPost(@ModelAttribute ProductFormInfo productInfo, Model model) {
        Product product = new Product();
        product.setName(productInfo.getName());
        product.setSize(productInfo.getSize());
        product.setPrice(productInfo.getPrice());
        product.setManufacturer(manufacturerService.getById(productInfo.getManufacturerId()));
        productService.save(product);
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products-view-admin";
    }

    @GetMapping("privacy-policy")
    public String privacyPolicy() {
        return "privacy-policy";
    }

    @GetMapping("privacy-policy-admin")
    public String privacyPolicyAdmin() {
        return "privacy-policy-admin";
    }

    @GetMapping("brands-view")
    public String brandsView(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        model.addAttribute("brands", manufacturers);
        return "brands-view";
    }

    @GetMapping("brands-view-admin")
    public String brandsViewAdmin(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        model.addAttribute("brands", manufacturers);
        return "brands-view-admin";
    }

    @GetMapping("brand-sorted/{id}")
    public String brandSorted(@PathVariable int id, Model model) {
        List<Product> products = productService.getAll()
                .stream()
                .filter(product -> product.getManufacturer()
                .getManufacturerId()==id)
                .collect(Collectors.toList());
        model.addAttribute("name", manufacturerService.getById(id).getName());
        model.addAttribute("products", products);
        return "brand-sorted";
    }

    @GetMapping("brand-sorted-admin/{id}")
    public String brandSortedAdmin(@PathVariable int id, Model model) {
        List<Product> products = productService.getAll()
                .stream()
                .filter(product -> product.getManufacturer()
                        .getManufacturerId()==id)
                .collect(Collectors.toList());
        model.addAttribute("name", manufacturerService.getById(id).getName());
        model.addAttribute("products", products);
        return "brand-sorted-admin";
    }

    @GetMapping("products-manage")
    public String productsManage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products-manage";
    }

    @GetMapping("product-delete/{id}")
    public String productDelete(@PathVariable int id) {
        productService.delete(id);
        List<Product> products = productService.getAll();
        return "redirect:/products-manage";
    }

    @GetMapping("product-edit/{id}")
    public String productEditGet(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        ProductFormInfo productInfo = new ProductFormInfo();
        productInfo.setProductId(product.getProductId());
        productInfo.setManufacturerId(product.getManufacturer().getManufacturerId());
        productInfo.setName(product.getName());
        productInfo.setSize(product.getSize());
        productInfo.setPrice(product.getPrice());
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("manufacturers", manufacturerService.getAll());
        return "product-edit";
    }

    @PostMapping("product-edit")
    public String productEditPost(@ModelAttribute ProductFormInfo productInfo) {
        Product product = new Product();
        product.setProductId(productInfo.getProductId());
        product.setName(productInfo.getName());
        product.setSize(productInfo.getSize());
        product.setPrice(productInfo.getPrice());
        product.setManufacturer(manufacturerService.getById(productInfo.getManufacturerId()));
        productService.save(product);
        return "redirect:/products-manage";
    }

    @GetMapping("product-order/{id}")
    public String orderGet(@PathVariable int id, Model model) {
        Order order = new Order();
        order.setProduct(productService.getById(id).getName());
        model.addAttribute("order", order);
        return "product-order";
    }

    @PostMapping("product-order")
    public String orderPost(@ModelAttribute Order order) {
        orderService.save(order);
        return "redirect:/products-view";
    }

    @GetMapping("orders-view")
    public String ordersViewGet() {
        return "orders-view";
    }

    @PostMapping("orders-view")
    public String ordersViewPost(@RequestParam(name = "customerName") String customerName, Model model) {
        List<Order> orders = orderService.getByCustomer(customerName);
        model.addAttribute("orders", orders);
        return "orders-view";
    }

    @GetMapping("orders-manage")
    public String manageOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders-manage";
    }

    @GetMapping("order-delete/{id}")
    public String deleteOrder(@PathVariable int id, Model model) {
        orderService.delete(id);
        model.addAttribute("orders", orderService.getAll());
        return "orders-manage";
    }

    @GetMapping("error")
    public String errorMessage() {
        return "error";
    }
}
