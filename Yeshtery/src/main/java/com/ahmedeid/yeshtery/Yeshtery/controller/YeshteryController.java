package com.ahmedeid.yeshtery.Yeshtery.controller;

import com.ahmedeid.yeshtery.Yeshtery.ExceptionHandling.YeshteryExceptionHandler;
import com.ahmedeid.yeshtery.Yeshtery.dto.CommonResponseDto;
import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import com.ahmedeid.yeshtery.Yeshtery.manager.IntegrationManager;
import com.ahmedeid.yeshtery.Yeshtery.servcies.ProductService;
import com.ahmedeid.yeshtery.Yeshtery.servcies.UserService;
import com.ahmedeid.yeshtery.Yeshtery.servcies.YeshteryService;
import com.ahmedeid.yeshtery.Yeshtery.validate.SystemValidation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ahmedeid.yeshtery.Yeshtery.util.ResponseCodeDescription.*;
import static com.ahmedeid.yeshtery.Yeshtery.util.ServiceName.*;

@RestController
@RequestMapping("Yeshtery")
@CrossOrigin
public class YeshteryController {

    /**
     * we have to use security jwt
     * it will be better to use it
     * for know whose user use this service now
     * better than send user every time from ui
     */

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private YeshteryService yeshteryService;

    @Value("${image.path}")
    private final String FILE_IMAGE_PATH = null;

    /**
     * save Product
     *
     * @param file
     * @param productUI
     * @return
     */
    @PostMapping(value = SAVE_NEW_PRODUCT)
    public CommonResponseDto saveProduct(@RequestParam(value = "file", required = true) MultipartFile file,
                                         @RequestParam(value = "productObj") String productUI) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Product productObj = gson.fromJson(productUI, Product.class);

        String fileName = IntegrationManager.updateImage(file, FILE_IMAGE_PATH);
        productObj.setPhotoPath(fileName);
        Optional<Product> product = this.productService.saveNewProduct(productObj);
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        product.ifPresentOrElse((productThatSaved) -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(productThatSaved);
            commonResponseDto.setSuccess(true);
        }, () -> {
            throw new YeshteryExceptionHandler(PRODUCT_NOT_SAVED);
        });

        return commonResponseDto;

    }

    /**
     * save new user for yeshtery
     *
     * @param user
     * @return
     */
    @PostMapping(SAVE_NEW_USER)
    public CommonResponseDto saveNewUser(@RequestBody User user) {

        //check if user already exists
        Optional<List<User>> users = this.userService.getAllUsers();
        List<User> userList = users.get().stream()
                .filter((userInFilter) -> user.getEmail().equals(userInFilter.getEmail())).collect(Collectors.toList());
        if(userList.size() > 0)
            throw new YeshteryExceptionHandler(USER_EXISTED);

        // add condition if admin
        if(user.getName().equals("admin"))
            user.setRoleCode(1);
        else
            user.setRoleCode(0);

        Optional<User> userSaved = this.userService.saveUser(user);
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        userSaved.ifPresentOrElse((target) -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(userSaved.get());
            commonResponseDto.setSuccess(true);
        }, () -> {
            throw new YeshteryExceptionHandler(USER_NOT_SAVED);
        });
        return commonResponseDto;
    }

    /**
     * return all product to general page
     * witch accepted to show
     * @return
     */
    @PostMapping(GET_PRODUCT_TO_GENERAL_PAGE)
    public CommonResponseDto getProductToGeneralPage() {
        CommonResponseDto commonResponseDto = new CommonResponseDto();

        Optional<List<Product>> productList = this.yeshteryService.getProductToGeneralPage(FILE_IMAGE_PATH);
        System.out.println(productList);
        productList.ifPresentOrElse(products -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(productList.get());
            commonResponseDto.setSuccess(true);
        }, () -> {
            throw new YeshteryExceptionHandler(CAN_NOT_RETRIEVE_DATA_OF_PRODUCT);
        });
        return commonResponseDto;
    }

    /**
     * return all product to administrator page
     * witch none accepted or rejected to show
     * @return
     */
    @PostMapping(GET_PRODUCT_TO_ADMINISTRATOR_PAGE)
    public CommonResponseDto getProductToAdministratorPage(@RequestBody User user) {
        // validate that user at first to know who use this service now
        if(!SystemValidation.VALIDATE_USER_ADMINISTRATOR.test(user))
            throw new YeshteryExceptionHandler(ROLE_INVALID);

        CommonResponseDto commonResponseDto = new CommonResponseDto();

        Optional<List<Product>> productList = this.yeshteryService.getProductToAdministratorPage(FILE_IMAGE_PATH);
        productList.ifPresentOrElse(products -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(productList.get());
            commonResponseDto.setSuccess(true);
        }, () -> {
            throw new YeshteryExceptionHandler(CAN_NOT_RETRIEVE_DATA_OF_PRODUCT);
        });
        return commonResponseDto;
    }

    /**
     * get product by id to show
     * @param productId
     * @return
     */
    @PostMapping(GET_PRODUCT_TO_SHOW+"/{productId}")
    public CommonResponseDto getProductToShow(@PathVariable String productId) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        this.yeshteryService.getProductToShow(Integer.parseInt(productId), FILE_IMAGE_PATH).ifPresentOrElse((product -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(product);
            commonResponseDto.setSuccess(true);
        }), () -> {
            throw new YeshteryExceptionHandler(CAN_NOT_RETRIEVE_DATA_OF_PRODUCT);
        });

        return commonResponseDto;
    }

    /**
     * get product by id to update
     * @param productId
     * @return
     */
    @PostMapping(GET_PRODUCT_TO_UPDATE+"/{productId}")
    public CommonResponseDto getProductToUpdate(@PathVariable String productId, @RequestBody User user) {

        // validate that user at first to know who use this service now
        if(!SystemValidation.VALIDATE_USER_ADMINISTRATOR.test(user))
            throw new YeshteryExceptionHandler(ROLE_INVALID);

        CommonResponseDto commonResponseDto = new CommonResponseDto();
        this.yeshteryService.getProductToUpdate(Integer.parseInt(productId), FILE_IMAGE_PATH).ifPresentOrElse((product -> {
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(product);
            commonResponseDto.setSuccess(true);
        }), () -> {
            throw new YeshteryExceptionHandler(CAN_NOT_RETRIEVE_DATA_OF_PRODUCT);
        });

        return commonResponseDto;
    }

    /**
     * update product to accept to be shown from users
     * @param productId
     * @return
     */
    @PostMapping(PRODUCT_UPDATE_TO_ACCEPTED+"/{productId}")
    public CommonResponseDto productUpdateToAccepted(@PathVariable String productId, @RequestBody User user) {

        // validate that user at first to know who use this service now
        if(!SystemValidation.VALIDATE_USER_ADMINISTRATOR.test(user))
            throw new YeshteryExceptionHandler(ROLE_INVALID);

        // validate if this product updated with reject or not
        Optional<Product> productToValidateAcceptance = this.productService.getProductById(Integer.parseInt(productId));
        if(productToValidateAcceptance.isPresent() && productToValidateAcceptance.get().getRejected() == 1)
            throw new YeshteryExceptionHandler(UPDATED_BEFORE);

        boolean statusOfUpdateToAccepted = this.yeshteryService.productUpdateToAccepted(Integer.parseInt(productId));
        CommonResponseDto commonResponseDto = null;
        if (statusOfUpdateToAccepted) {
            commonResponseDto = new CommonResponseDto();
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData("UPDATED SUCCESS");
            commonResponseDto.setSuccess(true);
        } else {
            throw new YeshteryExceptionHandler(UPDATE_FAILED);
        }
        return commonResponseDto;
    }

    /**
     * update product to reject so no one can see it again
     * @param productId
     * @return
     */
    @PostMapping(PRODUCT_UPDATE_TO_REJECTED+"/{productId}")
    public CommonResponseDto productUpdateToRejected(@PathVariable String productId, @RequestBody User user) {

        // validate that user at first to know who use this service now
        if(!SystemValidation.VALIDATE_USER_ADMINISTRATOR.test(user))
            throw new YeshteryExceptionHandler(ROLE_INVALID);

        // validate if this product updated with accept or not
        Optional<Product> productToValidateAcceptance = this.productService.getProductById(Integer.parseInt(productId));
        if(productToValidateAcceptance.isPresent() && productToValidateAcceptance.get().getAccepted() == 1)
            throw new YeshteryExceptionHandler(UPDATED_BEFORE);

        boolean statusOfUpdateToAccepted = this.yeshteryService.productUpdateToRejected(Integer.parseInt(productId));
        CommonResponseDto commonResponseDto = null;
        if (statusOfUpdateToAccepted) {
            commonResponseDto = new CommonResponseDto();
            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData("UPDATED SUCCESS");
            commonResponseDto.setSuccess(true);
        } else {
            throw new YeshteryExceptionHandler(UPDATE_FAILED);
        }
        return commonResponseDto;
    }

    @PostMapping(LOGIN+"/{email}/{password}")
    public CommonResponseDto login(@PathVariable String email, @PathVariable String password) {
        Optional<User> user = this.yeshteryService.loginUser(email, password);
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        user.ifPresentOrElse((userLogging) -> {

            commonResponseDto.setCode(200);
            commonResponseDto.setMessage("Success");
            commonResponseDto.setData(userLogging);
            commonResponseDto.setSuccess(true);
        }, () -> {
            throw new YeshteryExceptionHandler((LOGIN_INVALID));
        });
        return commonResponseDto;
    }
}
