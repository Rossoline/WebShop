package com.shop.library.utils;

import static java.io.File.separator;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUpload {
    private final String UPLOAD_FOLDER = "admin" + separator + "src" + separator + "main"
        + separator + "resources" + separator + "static" + separator + "img"
        + separator + "image-product";


    public boolean uploadImage(MultipartFile imageProduct) {
        boolean isUpload = false;
        try {
            Files.copy(imageProduct.getInputStream(),
                    Paths.get(Path.of(UPLOAD_FOLDER).toAbsolutePath()
                            + File.separator, imageProduct.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isUpload;
    }

    public boolean checkExisted(MultipartFile imageProduct) {
        boolean isExisted = false;
        try {
            File file = new File(UPLOAD_FOLDER + separator + imageProduct.getOriginalFilename());
            isExisted = file.exists();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isExisted;
    }
}
