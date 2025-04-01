package com.swp391.skincare_products_sales_system.config;

import com.github.slugify.Slugify;
import com.swp391.skincare_products_sales_system.constant.PredefinedRole;
import com.swp391.skincare_products_sales_system.entity.*;
import com.swp391.skincare_products_sales_system.enums.Gender;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.repository.*;
import com.swp391.skincare_products_sales_system.util.SlugUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    private final CategoryRepository categoryRepository;
    private final Slugify slugify;
    private final SlugUtil slugUtil;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, CategoryRepository categoryRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                List<User> listAccount = new ArrayList<>();
                Role customerRole = initRole(PredefinedRole.CUSTOMER_ROLE);
                roleRepository.save(customerRole);
                Role managerRole = initRole(PredefinedRole.MANAGER_ROLE);
                roleRepository.save(managerRole);
                Role staffRole = initRole(PredefinedRole.STAFF);
                roleRepository.save(staffRole);
                Role deliveryRole = initRole(PredefinedRole.DELIVERY);
                roleRepository.save(deliveryRole);
                Role adminRole = initRole(PredefinedRole.ADMIN_ROLE);
                roleRepository.save(adminRole);

                User admin = initAccount(ADMIN_USER_NAME, ADMIN_PASSWORD, adminRole, "Hy", "Thái Hòa");
                listAccount.add(admin);
                User phuocAdmin = initAccount("phuocadmin", "phuocadmin", adminRole, "Dũng", "Nguyễn Tiến");
                listAccount.add(phuocAdmin);
                User minhAdmin = initAccount("minhadmin", "minhadmin", adminRole, "Quát", "Cao Cường");
                listAccount.add(minhAdmin);
                User customer = initAccount("customer", "customer", customerRole, "Oai", "Đặng Xuân Oai");
                listAccount.add(customer);
                User phuocCustomer = initAccount("phuoccustomer", "phuoccustomer", customerRole, "Hồng", "Hoàng Phi");
                listAccount.add(phuocCustomer);
                User minhCustomer = initAccount("minhcustomer", "minhcustomer", customerRole, "Bảo", "Lý Tiểu");
                listAccount.add(minhCustomer);
                User manager = initAccount("manager", "manager", managerRole, "Linh", "Quang");
                listAccount.add(manager);
                User staff = initAccount("staff", "staff", staffRole, "Mục", "Hằng Du");
                listAccount.add(staff);
                User staff2 = initAccount("staff2", "staff2", staffRole, "Quang Diệu", "Lý");
                listAccount.add(staff2);
                User staff3 = initAccount("staff3", "staff3", staffRole, "Tôn", "Thiên");
                listAccount.add(staff3);
                User delivery = initAccount("delivery", "delivery", deliveryRole, "Nam", "Phan Thành");
                listAccount.add(delivery);
                User delivery2 = initAccount("delivery2", "delivery2", deliveryRole, "Dũng", "Nguyễn Tấn");
                listAccount.add(delivery2);
                userRepository.saveAll(listAccount);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            if(categoryRepository.findAll().isEmpty()){
                initCategory("Mỹ Phẩm High-End", "<p>Mỹ Phẩm High-End</p>", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5PCbbOnOwyRZwWQaDxBnN0UXBkm84WQDJdA&s");
                initCategory("Chăm Sóc Da Mặt", "<p>Chăm Sóc Da Mặt</p>", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZK8ITHG6BjWqi9_UVUierLKZmQH_pGzo15g&s");
                initCategory("Trang Điểm", "<p>Trang Điểm</p>", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2gjbrBpyGxWYVw2x2HJhxLfLO7dMbHDNXAw&s");
                initCategory("Chăm Sóc Tóc Và Da Đầu", "<p>Chăm Sóc Tóc Và Da Đầu</p>", "https://bizweb.dktcdn.net/100/368/763/files/cham-soc-da-mat-cosmocare-57-f3e85698-3037-462d-bd32-e8d0ee404cd8.jpg?v=1584430320330");
                initCategory("Thực Phẩm Chức Năng", "<p>Thực Phẩm Chức Năng</p>", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6DS3bVsKjZ5fNzaxwVE-7Prj5-44M7i7OJw&s");
                initCategory("Nước Hoa", "<p>Nước Hoa</p>", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_Mpzdo4Qb6WSMO6USaWzZe9b7WXjHbboTw&s");
            }
            if(brandRepository.findAll().isEmpty()){
                initBrand("16plain", "<p>16plain là thương hiệu chăm sóc da từ Hàn Quốc, nổi bật với các dòng mặt nạ giấy phục hồi, dưỡng sáng và ngừa lão hóa, phù hợp mọi loại da. Với cam kết chất lượng và hiệu quả, thương 16plain đang trở thành lựa chọn số một của nhiều khách hàng yêu thích dưỡng da tại Việt Nam và trên thế giới.</p>", "https://media.hcdn.vn//hsk/brand16plain1678875836.jpg?v=1");
                initBrand("A Bonne", "<p>A Bonne là thương hiệu chăm sóc cơ thể nổi tiếng từ Thái Lan, ra đời năm 1983. Sản phẩm chủ yếu là muối tắm tự nhiên giúp loại bỏ tế bào chết, làm mềm da, thư giãn và cải thiện tuần hoàn máu. A Bonne được ưa chuộng tại Đông Nam Á nhờ cam kết chất lượng và hiệu quả.</p>", "https://media.hcdn.vn//hsk/branda-bonne1677577061.jpg?v=1");
                initBrand("Boom De Ah Dah", "<p>Boom De Ah Dah là thương hiệu mỹ phẩm Hàn Quốc chuyên cung cấp sản phẩm chăm sóc da tự nhiên, an toàn, không chứa thành phần gây hại. Cam kết không thử nghiệm trên động vật, và sử dụng bao bì tái chế thân thiện với môi trường. Nổi bật với các dòng tinh chất cấp ẩm, phục hồi và trẻ hóa da.</p>", "https://media.hcdn.vn/brand/1663037592bom.jpg?v=1");
                initBrand("Ciracle", "<p>Ciracle</p>", "https://media.hcdn.vn/brand/1480321521ciracle-logo.jpg?v=1");
                initBrand("Deep Fresh", "<p>Deep Fresh</p>", "https://media.hcdn.vn/brand/1502187154deepfresh.jpg?v=1");
                initBrand("Felina", "<p>Felina</p>", "https://media.hcdn.vn/brand/1615522821Felina.jpg?v=1");
                initBrand("Obagi", "<p>Obagi</p>", "https://media.hcdn.vn//hsk/brandobagi-logo1736930217.jpg?v=1");
                initBrand("La Roche-Posay", "<p>La Roche-Posay</p>", "https://media.hcdn.vn//hsk/brandLa-roche-posay-logo1690281599.jpg?v=1");
            }
            if(productRepository.findAll().isEmpty()){
                initProduct("Kem Chống Nắng La Roche-Posay Phổ Rộng, Nâng Tông Kiềm Dầu 50ml" +
                        "Anthelios XL SPF 50+ PA++++", 464000D, categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("la-roche-posay"), "", "", "", "https://media.hcdn.vn/wysiwyg/MaiQuynh/kem-chong-nang-la-roche-posay-spf-50-bao-ve-da-50ml-4.jpg", Specification.builder()
                        .brandOrigin("N/A")
                        .origin("N/A")
                        .manufacturingLocation("N/A")
                        .skinType("N/A")
                        .build());
                initProduct("Serum La Roche-Posay Giảm Thâm Nám & Dưỡng Sáng Da 30ml\n" +
                        "Mela B3 Serum", 843000D, categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("la-roche-posay"), "", "", "","https://media.hcdn.vn/wysiwyg/MaiQuynh/serum-la-roche-posay-giam-tham-nam-duong-sang-da-30ml-1.jpg", initSpecification());
                initProduct("Kem Dưỡng La Roche-Posay Giúp Phục Hồi Da Đa Công Dụng 40ml\n" +
                        "Cicaplast Baume B5+ Ultra-Repairing Soothing Balm", 289000D, categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("la-roche-posay"), "", "", "", "https://media.hcdn.vn/wysiwyg/MaiQuynh/kem-duong-la-roche-posay-giup-phuc-hoi-da-da-cong-dung-2.jpg", initSpecification());
                initProduct("Kem Dưỡng Obagi Retinol 1.0% Trẻ Hóa Da, Ngừa Mụn 28g\n" +
                        "Retinol 1.0 Cream", 1538000D,categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("obagi"), "", "", "", "https://media.hcdn.vn/wysiwyg/MaiQuynh/kem-duong-obagi-retinol-1.jpg", initSpecification());
                initProduct("Serum Obagi Vitamin C 15% Sáng Da & Chống Oxy Hóa 12.5ml\n" +
                        "Professional-C Serum 15% L-Ascorbic Acid", 890000D, categoryRepository.findBySlug("my-pham-high-end"), brandRepository.findBySlug("obagi"), "", "", "", "https://media.hcdn.vn/wysiwyg/kimhuy/serum-obagi-vitamin-c-sang-da-chong-oxy-hoa-30ml-4.jpg", initSpecification());
                initProduct("Kem Dưỡng Obagi Cấp Ẩm Giàu Dưỡng Chất 48g\n" +
                        "Hydrate Luxe Moisture-Rich Cream", 1289000D, categoryRepository.findBySlug("my-pham-high-end"), brandRepository.findBySlug("obagi"), "", "", "", "https://media.hcdn.vn/wysiwyg/kimhuy/kem-duong-obagi-cap-am-giau-duong-chat-48g-2.jpg", initSpecification());
                initProduct("Serum Obagi Clinical Giảm Thâm Nám & Làm Đều Màu Da 30ml\n" +
                        "Clinical Dark Spot Disruptor Discoloration Correcting Serum", 1980000D, categoryRepository.findBySlug("my-pham-high-end"), brandRepository.findBySlug("obagi"), "", "", "", "https://media.hcdn.vn/wysiwyg/kimhuy/serum-obagi-clinical-giam-tham-nam-lam-deu-mau-da-30ml-1.jpg", initSpecification());
                initProduct("Kem Tắm Tẩy Tế Bào Chết Felina Chiết Xuất Bơ 400g\n" +
                        "Body Scrub Cream #Avocado", 1488000D,categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("felina"), "", "", "","https://media.hcdn.vn/wysiwyg/Chau/kem-tam-tay-te-bao-chet-felina-chiet-xuat-bo-1.jpg", initSpecification());
                initProduct("Nước Rửa Móng Felina Cao Cấp Hương Hoa Cỏ 100ml\n" +
                        "Nonacetone Remover - Flower Fragrance", 50000D, categoryRepository.findBySlug("trang-iem"), brandRepository.findBySlug("felina"), "", "", "", "https://media.hcdn.vn/wysiwyg/Chau/nuoc-rua-mong-felina-cao-cap-100ml-1.jpg", initSpecification());
                initProduct("Miếng Dán Ciracle Hỗ Trợ Hút Và Đẩy Mụn Đầu Đen (1 Miếng)" +
                        "Goodbye Blackhead", 20000D, categoryRepository.findBySlug("cham-soc-da-mat"), brandRepository.findBySlug("ciracle"), "", "", "", "https://media.hcdn.vn/wysiwyg/Chau/ciracle-day-mun-dau-den-1.jpg", initSpecification());
                initProduct("Muối Tắm A Bonne Vitamin C Chiết Xuất Đu Đủ & Chanh 350g\n" +
                        "Spa White C Salt", 25000D,categoryRepository.findBySlug("cham-soc-toc-va-da-au"), brandRepository.findBySlug("a-bonne") , "", "", "", "https://media.hcdn.vn/wysiwyg/HaNguyen2/muoi-tam-a-bonne-tay-te-bao-chet-350g-1.jpg", initSpecification());

            }
            log.info("Application initialization completed .....");
        };
    }

    private Role initRole(String role) {
        Role newRole = Role.builder()
                .name(role)
                .description(role)
                .build();
        newRole.setIsDeleted(false);
        return newRole;
    }

    private Specification initSpecification(){
        return Specification.builder()
                .skinType("N/A")
                .brandOrigin("N/A")
                .origin("N/A")
                .manufacturingLocation("N/A")
                .build();
    }


    private void initProduct(String name, Double price, Category category, Brand brand, String description, String ingredient, String usageInstruction, String thumbnail, Specification specification){
        Product product = Product.builder()
                .name(name)
                .price(price)
                .category(category)
                .brand(brand)
                .description(description)
                .ingredient(ingredient)
                .usageInstruction(usageInstruction)
                .thumbnail(thumbnail)
                .specification(specification)
                .status(Status.ACTIVE)
                .rating(5.0)
                .build();
        product.setIsDeleted(false);
        specification.setProduct(product);
        product.setSlug(generateUniqueSlug(product.getName()));
        productRepository.save(product);
    }

    private void initCategory(String name, String description, String thumbnail){
        Category category = Category.builder()
                .name(name)
                .description(description)
                .thumbnail(thumbnail)
                .build();
        category.setIsDeleted(false);
        category.setSlug(generateUniqueSlug(category.getName()));
        categoryRepository.save(category);
    }
    private void initBrand(String name, String description, String thumbnail){
        Brand brand = Brand.builder()
                .name(name)
                .description(description)
                .thumbnail(thumbnail)
                .build();
        brand.setIsDeleted(false);
        brand.setSlug(generateUniqueSlug(brand.getName()));
        brandRepository.save(brand);
    }

    private User initAccount(String username, String password, Role role, String firstName, String lastName) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .point(0)
                .lastName("")
                .firstName("")
                .phone("")
                .birthday(LocalDate.of(2003, 3, 3))
                .firstName(firstName)
                .lastName(lastName)
                .avatar("https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg")
                .status(Status.ACTIVE)
                .build();
        user.setIsDeleted(false);
        return user;
    }
    private String generateUniqueSlug(String name) {
        String baseSlug = slugify.slugify(name);
        String uniqueSlug = baseSlug;

        while (categoryRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = baseSlug + "-" + slugUtil.generateRandomString(6);
        }
        return uniqueSlug;
    }
}
