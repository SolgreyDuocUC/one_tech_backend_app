package com.duocuc.one_tech.config;

import com.duocuc.one_tech.models.*;
import com.duocuc.one_tech.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    private final RegionRepository regionRepository;
    private final CommuneRepository communesRepository;
    private final AddressRepository addressesRepository;

    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ReviewRepository reviewRepository;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final OrderRepository orderRepository;
    private final OrderRepository orderItemRepository;

    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final PostCommentRepository postCommentRepository;

    private final ContactMessageRepository contactMessageRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            // Evitar poblar dos veces
            if (userRepository.count() > 0 || productRepository.count() > 0) {
                System.out.println("Datos existentes detectados, saltando DataInitializer.");
                return;
            }

            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

            // 1. Roles
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setDescription("Administrador del sistema");
            adminRole.setUserRoles(new HashSet<>());

            Role customerRole = new Role();
            customerRole.setName("ROLE_CUSTOMER");
            customerRole.setDescription("Cliente de la tienda");
            customerRole.setUserRoles(new HashSet<>());

            roleRepository.saveAll(List.of(adminRole, customerRole));

            // 2. Usuarios
            User admin = new User();
            admin.setRut("11111111-1");
            admin.setFirstName("Miguel");
            admin.setLastName("Calderón");
            admin.setEmail("admin@one-tech.cl");
            admin.setPhone("+56 9 1111 1111");
            // En un proyecto real esto debería venir ya hasheado, aquí solo de ejemplo
            admin.setPasswordHash("{noop}admin123");
            admin.setIsActive(true);
            admin.setCreatedAt(now.minusDays(10));
            admin.setUpdatedAt(now.minusDays(1));

            User cliente = new User();
            cliente.setRut("22222222-2");
            cliente.setFirstName("Camila");
            cliente.setLastName("Gamer");
            cliente.setEmail("cliente@one-tech.cl");
            cliente.setPhone("+56 9 2222 2222");
            cliente.setPasswordHash("{noop}cliente123");
            cliente.setIsActive(true);
            cliente.setCreatedAt(now.minusDays(5));
            cliente.setUpdatedAt(now.minusHours(12));

            userRepository.saveAll(List.of(admin, cliente));

            // 3. UserRoles
            UserRole adminUserRole = new UserRole();
            adminUserRole.setRoleId(new UserRoleId(admin.getId(), adminRole.getId()));
            adminUserRole.setUser(admin);
            adminUserRole.setRole(adminRole);
            adminUserRole.setGrantedAt(now.minusDays(9));

            UserRole customerUserRole = new UserRole();
            customerUserRole.setRoleId(new UserRoleId(cliente.getId(), customerRole.getId()));
            customerUserRole.setUser(cliente);
            customerUserRole.setRole(customerRole);
            customerUserRole.setGrantedAt(now.minusDays(4));

            userRoleRepository.saveAll(List.of(adminUserRole, customerUserRole));

            // 4. Regiones y comunas
            Region regionValpo = new Region();
            regionValpo.setCode("V");
            regionValpo.setName("Valparaíso");

            Region regionRm = new Region();
            regionRm.setCode("RM");
            regionRm.setName("Región Metropolitana");

            regionRepository.saveAll(List.of(regionValpo, regionRm));

            Communes zapallar = new Communes();
            zapallar.setName("Zapallar");
            zapallar.setRegion(regionValpo);

            Communes quillota = new Communes();
            quillota.setName("Quillota");
            quillota.setRegion(regionValpo);

            Communes santiago = new Communes();
            santiago.setName("Santiago");
            santiago.setRegion(regionRm);

            communesRepository.saveAll(List.of(zapallar, quillota, santiago));

            // 5. Direcciones
            Addresses addr1 = new Addresses();
            addr1.setLine1Address("Av. Gamer 123");
            addr1.setLine2Address("Depto 404");
            addr1.setPostalCodeAddress("2260000");
            addr1.setIsDefault(true);
            addr1.setCreatedAt(now.minusDays(5));
            addr1.setUser(cliente);
            addr1.setCommune(zapallar);

            Addresses addr2 = new Addresses();
            addr2.setLine1Address("Calle RGB 789");
            addr2.setPostalCodeAddress("8340000");
            addr2.setIsDefault(true);
            addr2.setCreatedAt(now.minusDays(8));
            addr2.setUser(admin);
            addr2.setCommune(santiago);

            addressesRepository.saveAll(List.of(addr1, addr2));

            // 6. Categorías (jerárquicas)
            Category rootHardware = new Category();
            rootHardware.setName("Hardware");
            rootHardware.setSlug("hardware");
            rootHardware.setIsActive(true);

            Category catPerifericos = new Category();
            catPerifericos.setName("Periféricos Gamer");
            catPerifericos.setSlug("perifericos-gamer");
            catPerifericos.setIsActive(true);
            catPerifericos.setParentCategory(rootHardware);

            Category catAudio = new Category();
            catAudio.setName("Audio Gamer");
            catAudio.setSlug("audio-gamer");
            catAudio.setIsActive(true);
            catAudio.setParentCategory(rootHardware);

            Category catMonitores = new Category();
            catMonitores.setName("Monitores");
            catMonitores.setSlug("monitores");
            catMonitores.setIsActive(true);
            catMonitores.setParentCategory(rootHardware);

            Category catPcGamer = new Category();
            catPcGamer.setName("PC Gamer");
            catPcGamer.setSlug("pc-gamer");
            catPcGamer.setIsActive(true);
            catPcGamer.setParentCategory(rootHardware);

            Category catSillas = new Category();
            catSillas.setName("Sillas Gamer");
            catSillas.setSlug("sillas-gamer");
            catSillas.setIsActive(true);
            catSillas.setParentCategory(rootHardware);

            Category catConsolas = new Category();
            catConsolas.setName("Accesorios Consolas");
            catConsolas.setSlug("accesorios-consolas");
            catConsolas.setIsActive(true);
            catConsolas.setParentCategory(rootHardware);

            Category catStreaming = new Category();
            catStreaming.setName("Streaming");
            catStreaming.setSlug("streaming");
            catStreaming.setIsActive(true);
            catStreaming.setParentCategory(rootHardware);

            categoryRepository.save(rootHardware);
            categoryRepository.saveAll(
                    Arrays.asList(
                            catPerifericos,
                            catAudio,
                            catMonitores,
                            catPcGamer,
                            catSillas,
                            catConsolas,
                            catStreaming
                    )
            );

// 7. Descuentos
            Discount descPerifericos = new Discount();
            descPerifericos.setCode("PERI10");
            descPerifericos.setDescription("10% de descuento en periféricos gamer");
            descPerifericos.setDiscountType("PERCENT");
            descPerifericos.setValue(new BigDecimal("10.00"));
            descPerifericos.setMinPurchase(new BigDecimal("20000"));
            descPerifericos.setStartDate(now.minusDays(1));
            descPerifericos.setEndDate(now.plusMonths(1));
            descPerifericos.setIsActive(true);
            descPerifericos.setAppliesToCategory(catPerifericos);

            Discount descPcGamer = new Discount();
            descPcGamer.setCode("PC50K");
            descPcGamer.setDescription("50.000 CLP de descuento en PC Gamer seleccionados");
            descPcGamer.setDiscountType("FIXED");
            descPcGamer.setValue(new BigDecimal("50000"));
            descPcGamer.setMinPurchase(new BigDecimal("300000"));
            descPcGamer.setStartDate(now.minusDays(3));
            descPcGamer.setEndDate(now.plusMonths(2));
            descPcGamer.setIsActive(true);
            descPcGamer.setAppliesToCategory(catPcGamer);

            discountRepository.saveAll(Arrays.asList(descPerifericos, descPcGamer));

// 8. Productos (tus 8 productos)
            Product teclado = new Product();
            teclado.setName("Teclado Mecánico RGB Gamer");
            teclado.setSlug("teclado-mecanico-rgb-gamer");
            teclado.setDescription("Teclado mecánico con switches azules, iluminación RGB personalizable y reposamanos extraíble. Perfecto para gaming y productividad.");
            teclado.setPrice(new BigDecimal("89990"));
            teclado.setStock(15);
            teclado.setStockCritico(3);
            teclado.setFeaturedProduct(1);
            teclado.setCreatesAt(now.minusDays(7));
            teclado.setUpdatedAt(now.minusDays(1));
            teclado.setCategory(catPerifericos);
            teclado.setDiscount(descPerifericos);

            Product audifonos = new Product();
            audifonos.setName("Audífonos Gamer 7.1 Surround");
            audifonos.setSlug("audifonos-gamer-7-1-surround");
            audifonos.setDescription("Audífonos con sonido envolvente 7.1, micrófono retráctil con cancelación de ruido e iluminación RGB.");
            audifonos.setPrice(new BigDecimal("69990"));
            audifonos.setStock(23);
            audifonos.setStockCritico(5);
            audifonos.setFeaturedProduct(1);
            audifonos.setCreatesAt(now.minusDays(6));
            audifonos.setUpdatedAt(now.minusDays(1));
            audifonos.setCategory(catAudio);

            Product monitor = new Product();
            monitor.setName("Monitor Gaming 144Hz 27\"");
            monitor.setSlug("monitor-gaming-144hz-27");
            monitor.setDescription("Monitor curvo 27 pulgadas, tasa de refresco 144Hz, tiempo de respuesta 1ms, resolución QHD 2K.");
            monitor.setPrice(new BigDecimal("349990"));
            monitor.setStock(12);
            monitor.setStockCritico(2);
            monitor.setFeaturedProduct(1);
            monitor.setCreatesAt(now.minusDays(4));
            monitor.setUpdatedAt(now.minusDays(1));
            monitor.setCategory(catMonitores);

            Product pcGamer = new Product();
            pcGamer.setName("PC Gamer RTX 4060");
            pcGamer.setSlug("pc-gamer-rtx-4060");
            pcGamer.setDescription("Computador gamer completo: Intel i7 13va gen, RTX 4060 8GB, 16GB RAM, SSD 1TB NVMe, RGB.");
            pcGamer.setPrice(new BigDecimal("1299990"));
            pcGamer.setStock(5);
            pcGamer.setStockCritico(1);
            pcGamer.setFeaturedProduct(1);
            pcGamer.setCreatesAt(now.minusDays(3));
            pcGamer.setUpdatedAt(now.minusDays(1));
            pcGamer.setCategory(catPcGamer);
            pcGamer.setDiscount(descPcGamer);

            Product silla = new Product();
            silla.setName("Silla Gamer Pro Ergonómica");
            silla.setSlug("silla-gamer-pro-ergonomica");
            silla.setDescription("Silla ergonómica con soporte lumbar ajustable, reposabrazos 4D y reclinación hasta 180°. Ideal para largas sesiones.");
            silla.setPrice(new BigDecimal("249990"));
            silla.setStock(8);
            silla.setStockCritico(2);
            silla.setFeaturedProduct(1);
            silla.setCreatesAt(now.minusDays(5));
            silla.setUpdatedAt(now.minusDays(1));
            silla.setCategory(catSillas);

            Product controlPs5 = new Product();
            controlPs5.setName("Control DualSense PS5");
            controlPs5.setSlug("control-dualsense-ps5");
            controlPs5.setDescription("Control inalámbrico PlayStation 5 con retroalimentación háptica y gatillos adaptativos.");
            controlPs5.setPrice(new BigDecimal("59990"));
            controlPs5.setStock(30);
            controlPs5.setStockCritico(10);
            controlPs5.setFeaturedProduct(0);
            controlPs5.setCreatesAt(now.minusDays(6));
            controlPs5.setUpdatedAt(now.minusDays(1));
            controlPs5.setCategory(catConsolas);

            Product mouse = new Product();
            mouse.setName("Mouse Gaming RGB 16000 DPI");
            mouse.setSlug("mouse-gaming-rgb-16000-dpi");
            mouse.setDescription("Mouse óptico con sensor de alta precisión, 7 botones programables y peso ajustable.");
            mouse.setPrice(new BigDecimal("39990"));
            mouse.setStock(42);
            mouse.setStockCritico(8);
            mouse.setFeaturedProduct(1);
            mouse.setCreatesAt(now.minusDays(2));
            mouse.setUpdatedAt(now.minusDays(1));
            mouse.setCategory(catPerifericos);
            mouse.setDiscount(descPerifericos);

            Product kitStreaming = new Product();
            kitStreaming.setName("Kit Streaming Completo");
            kitStreaming.setSlug("kit-streaming-completo");
            kitStreaming.setDescription("Kit para streamers: micrófono condensador, brazo articulado, luz LED ring, cámara web 1080p.");
            kitStreaming.setPrice(new BigDecimal("89990")); // sin precio 0
            kitStreaming.setStock(3);
            kitStreaming.setStockCritico(1);
            kitStreaming.setFeaturedProduct(1);
            kitStreaming.setCreatesAt(now.minusDays(1));
            kitStreaming.setUpdatedAt(now.minusHours(12));
            kitStreaming.setCategory(catStreaming);

            productRepository.saveAll(
                    Arrays.asList(
                            teclado,
                            audifonos,
                            monitor,
                            pcGamer,
                            silla,
                            controlPs5,
                            mouse,
                            kitStreaming
                    )
            );

// 9. Imágenes de producto (puedes ajustar URLs después)
            ProductImage imgTeclado = new ProductImage();
            imgTeclado.setUrl("https://cdn.one-tech.cl/img/teclado-rgb-1.png");
            imgTeclado.setAlt("Teclado mecánico RGB gamer");
            imgTeclado.setIsMain(true);
            imgTeclado.setProduct(teclado);

            ProductImage imgAudifonos = new ProductImage();
            imgAudifonos.setUrl("https://cdn.one-tech.cl/img/audifonos-7-1-1.png");
            imgAudifonos.setAlt("Audífonos gamer 7.1 surround");
            imgAudifonos.setIsMain(true);
            imgAudifonos.setProduct(audifonos);

            ProductImage imgMonitor = new ProductImage();
            imgMonitor.setUrl("https://cdn.one-tech.cl/img/monitor-144hz-27.png");
            imgMonitor.setAlt("Monitor gaming 144Hz 27 pulgadas");
            imgMonitor.setIsMain(true);
            imgMonitor.setProduct(monitor);

            ProductImage imgPc = new ProductImage();
            imgPc.setUrl("https://cdn.one-tech.cl/img/pc-gamer-rtx4060.png");
            imgPc.setAlt("PC gamer RTX 4060");
            imgPc.setIsMain(true);
            imgPc.setProduct(pcGamer);

            ProductImage imgSilla = new ProductImage();
            imgSilla.setUrl("https://cdn.one-tech.cl/img/silla-gamer-pro.png");
            imgSilla.setAlt("Silla gamer pro ergonómica");
            imgSilla.setIsMain(true);
            imgSilla.setProduct(silla);

            ProductImage imgControl = new ProductImage();
            imgControl.setUrl("https://cdn.one-tech.cl/img/dualsense-ps5.png");
            imgControl.setAlt("Control DualSense PS5");
            imgControl.setIsMain(true);
            imgControl.setProduct(controlPs5);

            ProductImage imgMouse = new ProductImage();
            imgMouse.setUrl("https://cdn.one-tech.cl/img/mouse-gaming-16000dpi.png");
            imgMouse.setAlt("Mouse gaming RGB 16000 DPI");
            imgMouse.setIsMain(true);
            imgMouse.setProduct(mouse);

            ProductImage imgKit = new ProductImage();
            imgKit.setUrl("https://cdn.one-tech.cl/img/kit-streaming-completo.png");
            imgKit.setAlt("Kit streaming completo");
            imgKit.setIsMain(true);
            imgKit.setProduct(kitStreaming);

            productImageRepository.saveAll(
                    Arrays.asList(
                            imgTeclado,
                            imgAudifonos,
                            imgMonitor,
                            imgPc,
                            imgSilla,
                            imgControl,
                            imgMouse,
                            imgKit
                    )
            );


            // 10. Reviews
            Review rev1 = new Review();
            rev1.setRating(5);
            rev1.setTitle("Excelente teclado");
            rev1.setContent("Muy cómodo para escribir y jugar, la iluminación RGB está genial.");
            rev1.setCreatedAt(now.minusDays(2));
            rev1.setIsVisible(true);
            rev1.setProduct(teclado);
            rev1.setAuthor(cliente);

            Review rev2 = new Review();
            rev2.setRating(4);
            rev2.setTitle("Buen sonido");
            rev2.setContent("Los audífonos suenan muy bien, el 7.1 se nota en juegos.");
            rev2.setCreatedAt(now.minusDays(1));
            rev2.setIsVisible(true);
            rev2.setProduct(audifonos);
            rev2.setAuthor(cliente);

            reviewRepository.saveAll(List.of(rev1, rev2));

            // 11. Carrito del cliente
            Cart cart = new Cart();
            cart.setStatus("OPEN");
            cart.setItemsTotal(BigDecimal.ZERO);
            cart.setDiscountTotal(BigDecimal.ZERO);
            cart.setGrandTotal(BigDecimal.ZERO);
            cart.setCreatedAt(now.minusHours(6));
            cart.setUpdatedAt(now.minusHours(2));
            cart.setUser(cliente);

            cartRepository.save(cart);

            CartItem ci1 = new CartItem();
            ci1.setCart(cart);
            ci1.setProduct(teclado);
            ci1.setQty(1);
            ci1.setUnitPrice(teclado.getPrice());
            ci1.computeTotal(); // setea total

            CartItem ci2 = new CartItem();
            ci2.setCart(cart);
            ci2.setProduct(audifonos);
            ci2.setQty(1);
            ci2.setUnitPrice(audifonos.getPrice());
            ci2.computeTotal();

            cartItemRepository.saveAll(List.of(ci1, ci2));

            // recalcular totales del carrito
            cart.setItems(List.of(ci1, ci2));
            cart.recalcTotals();
            cart.setUpdatedAt(now.minusHours(1));
            cartRepository.save(cart);

            // 12. Orden basada en ese carrito
            Order order = new Order();
            order.setOrderNumber("ONET-0000001");
            order.setStatus("PAID");
            order.setItemsTotal(BigDecimal.ZERO);
            order.setShippingTotal(new BigDecimal("4990"));
            order.setDiscountTotal(BigDecimal.ZERO);
            order.setGrandTotal(BigDecimal.ZERO);
            order.setPaymentMethod("CARD");
            order.setCreatedAt(now.minusHours(3));
            order.setPaidAt(now.minusHours(2));
            order.setUser(cliente);

// Todavía no guardo la order, primero creo los items
            OrderItem oi1 = new OrderItem();
            oi1.setOrder(order);
            oi1.setNameSnapshot(teclado.getName());
            oi1.setUnitPrice(teclado.getPrice());
            oi1.setQty(1);
            oi1.computeTotal();

            OrderItem oi2 = new OrderItem();
            oi2.setOrder(order);
            oi2.setNameSnapshot(audifonos.getName());
            oi2.setUnitPrice(audifonos.getPrice());
            oi2.setQty(1);
            oi2.computeTotal();

// Los agregas a la lista de la orden
            order.setItems(new java.util.ArrayList<>(java.util.Arrays.asList(oi1, oi2)));

// Recalculas totales
            order.recalcTotals();

// Y acá guardas todo de una
            orderRepository.save(order);

            // 13. Tags y posts del blog
            Tag tagOfertas = new Tag();
            tagOfertas.setName("Ofertas");
            tagOfertas.setSlug("ofertas");

            Tag tagSetup = new Tag();
            tagSetup.setName("Setup");
            tagSetup.setSlug("setup");

            tagRepository.saveAll(List.of(tagOfertas, tagSetup));

            Post post1 = Post.builder()
                    .title("Cómo armar un setup gamer equilibrado 2025")
                    .slug("como-armar-un-setup-gamer-equilibrado-2025")
                    .excerpt("Guía rápida para elegir monitor, periféricos y PC sin romper el bolsillo.")
                    .content("Contenido de ejemplo del post sobre setups gamer...")
                    .coverImageUrl("https://cdn.one-tech.cl/blog/setup-gamer-2025.png")
                    .publishedAt(now.minusDays(1))
                    .isPublished(true)
                    .createdAt(now.minusDays(2))
                    .updatedAt(now.minusDays(1))
                    .build();

            postRepository.save(post1);

            // PostTag: relacionar post con tags
            PostTag pt1 = new PostTag();
            pt1.setId(new PostTagId(post1.getId(), tagOfertas.getId(), admin.getId()));
            pt1.setPost(post1);
            pt1.setTag(tagOfertas);
            pt1.setUser(admin);

            PostTag pt2 = new PostTag();
            pt2.setId(new PostTagId(post1.getId(), tagSetup.getId(), admin.getId()));
            pt2.setPost(post1);
            pt2.setTag(tagSetup);
            pt2.setUser(admin);

// Los vinculas al post
            post1.getPostTags().add(pt1);
            post1.getPostTags().add(pt2);

// Guardas solo el post
            postRepository.save(post1);

            // Comentarios del post
            PostComment comment1 = new PostComment();
            comment1.setContent("Muy buena guía, me sirvió para elegir el monitor.");
            comment1.setCreatedAt(now.minusHours(10));
            comment1.setIsVisible(true);
            comment1.setPost(post1);
            comment1.setAuthor(cliente);

            postCommentRepository.save(comment1);

            // 14. Mensajes de contacto
            ContactMessage cm1 = new ContactMessage();
            cm1.setNameMessage("Luis Pérez");
            cm1.setEmail("luis@example.com");
            cm1.setPhone("+56 9 3333 3333");
            cm1.setMessage("Hola, quiero saber si el PC Gamer RTX 4060 incluye sistema operativo.");
            cm1.setVia("WEB_FORM");
            cm1.setStatus("NEW");
            cm1.setCreatedAt(now.minusHours(5));
            cm1.setUser(null); // ejemplo: no autenticado

            ContactMessage cm2 = new ContactMessage();
            cm2.setNameMessage(cliente.getFirstName() + " " + cliente.getLastName());
            cm2.setEmail(cliente.getEmail());
            cm2.setPhone(cliente.getPhone());
            cm2.setMessage("Quisiera cambiar la dirección de envío de mi orden ONET-0000001.");
            cm2.setVia("WEB_FORM");
            cm2.setStatus("IN_PROGRESS");
            cm2.setCreatedAt(now.minusHours(2));
            cm2.setUser(cliente);

            contactMessageRepository.saveAll(List.of(cm1, cm2));

            System.out.println("DataInitializer: datoss cargados correctamente.");
        };
    }
}
