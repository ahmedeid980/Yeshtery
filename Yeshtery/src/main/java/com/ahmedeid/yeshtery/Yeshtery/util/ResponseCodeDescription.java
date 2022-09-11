package com.ahmedeid.yeshtery.Yeshtery.util;

import lombok.Getter;

public enum ResponseCodeDescription {
    USER_NOT_SAVED("user not save please try again later", 300),
    PRODUCT_NOT_SAVED("product not save please try again later", 301),
    NAME_REQUIRED("name is required", 321),
    EMAIL_REQUIRED("email is required", 322),
    PASSWORD_REQUIRED("password is required", 323),
    ROLE_CODE_REQUIRED("role code is required", 324),
    CATEGORY_REQUIRED("category is required", 325),
    DESCRIPTION_REQUIRED("description is required", 326),
    PHOTO_PATH_REQUIRED("photo path is required", 327),
    EXTENSION_OF_IMAGE_INVALID("extension of image is invalid", 328),
    UPDATE_FAILED("update failed to product", 330),
    CATEGORY_VALIDATION("category must be in ( living thing, machine or natural )", 331),
    ROLE_INVALID("your role not be valid", 332),
    LOGIN_INVALID("email or password invalid", 333),
    FILE_SIZE_INVALID("file image must be low of 2MB", 334),
    UPDATED_BEFORE("this element updated before", 335),
    USER_EXISTED("user already exists", 343),
    CAN_NOT_RETRIEVE_DATA_OF_PRODUCT("can't retrieve data of product to general page", 329);

    @Getter
    private final String errorMessage;
    @Getter
    private final long statusCode;

    ResponseCodeDescription(String errorMessage, long statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

}
