package com.ahmedeid.yeshtery.Yeshtery.manager;

import com.ahmedeid.yeshtery.Yeshtery.ExceptionHandling.YeshteryExceptionHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import static com.ahmedeid.yeshtery.Yeshtery.util.ConstantValue.*;
import static com.ahmedeid.yeshtery.Yeshtery.util.ResponseCodeDescription.*;

public class IntegrationManager {

    public static String updateImage(MultipartFile file, String fileImagePath) {
        try {

            String fileName = file.getOriginalFilename();

            if(file.getSize() > 2000000) // validate of file size must be low of 2MB
                throw new YeshteryExceptionHandler(FILE_SIZE_INVALID);

            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            if(!IMAGE_EXTENSION.contains(fileExtension.replace(".","")))
                throw new YeshteryExceptionHandler(EXTENSION_OF_IMAGE_INVALID);

            fileName = UUID.randomUUID().toString() + fileExtension;

            String filePath = fileImagePath + "\\" + fileName;

            if (StringUtils.isNotBlank(filePath)) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(fileImagePath + File.separator + fileName)));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return fileName;
            }

        } catch (Exception ex) {

        }
        return null;

    }

    public static String getImageBase64(String iamgePathInDB, String fileImagePath) {
        String imageBase64 = null;
        try {

            String FileFullPath = fileImagePath + "\\" + iamgePathInDB;
            byte[] content = ITBFileUtility.getByteContentNetwFile(FileFullPath);

            imageBase64 = org.apache.commons.codec.binary.Base64.encodeBase64String(content);
        } catch (Exception ex) {

        }
        return imageBase64;

    }


}
