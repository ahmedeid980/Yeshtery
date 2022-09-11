package com.ahmedeid.yeshtery.Yeshtery.validate;

import com.ahmedeid.yeshtery.Yeshtery.ExceptionHandling.YeshteryExceptionHandler;
import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import com.ahmedeid.yeshtery.Yeshtery.util.ConstantValue;
import com.ahmedeid.yeshtery.Yeshtery.util.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Locale;
import java.util.function.Predicate;

import static com.ahmedeid.yeshtery.Yeshtery.util.ResponseCodeDescription.*;
import static com.ahmedeid.yeshtery.Yeshtery.util.UserRole.*;

public class SystemValidation {

    public static void validateUser(User user) {
        if(StringUtils.isBlank(user.getName()))
            throw new YeshteryExceptionHandler(NAME_REQUIRED);

        if(StringUtils.isBlank(user.getEmail()))
            throw new YeshteryExceptionHandler(EMAIL_REQUIRED);

        if(StringUtils.isBlank(user.getPassword()))
            throw new YeshteryExceptionHandler(PASSWORD_REQUIRED);

    }

    public static void validateProduct(Product product) {
        if(StringUtils.isBlank(product.getCategory()))
            throw new YeshteryExceptionHandler(CATEGORY_REQUIRED);
        else
            if(!ConstantValue.FIXED_CATEGORY.contains(product.getCategory().toLowerCase()))
                throw new YeshteryExceptionHandler(CATEGORY_VALIDATION);

        if(StringUtils.isBlank(product.getDescription()))
            throw new YeshteryExceptionHandler(DESCRIPTION_REQUIRED);

        if(StringUtils.isBlank(product.getPhotoPath()))
            throw new YeshteryExceptionHandler(PHOTO_PATH_REQUIRED);

    }

    public static final Predicate<User> VALIDATE_USER_ADMINISTRATOR = (user) -> {
        return user.getRoleCode() == ADMIN.getCodeOfRole();
    };

}
