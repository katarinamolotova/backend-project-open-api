package edu.school21.backend.services;

import edu.school21.backend.dto.ProductDTO;
import edu.school21.backend.entities.ImageEntity;
import edu.school21.backend.repositories.ImageRepository;
import edu.school21.backend.utils.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {

    private final ProductService productService;
    private final ImageRepository repository;

    public UUID add(final MultipartFile image, final Long productId) {
        final ProductDTO product = productService.getById(productId);

        final ImageEntity entity = new ImageEntity();
        entity.setImage(getImageBytes(image));
        final ImageEntity savedEntity = repository.save(entity);

        product.setImageId(savedEntity.getId());
        productService.update(product);

        return savedEntity.getId();
    }

    public void update(final UUID id, final MultipartFile image) {
        final ImageEntity entity = repository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found image by id = " + id));

        entity.setImage(getImageBytes(image));
        repository.save(entity);
    }

    public void deleteById(final UUID id) {
        repository
                .findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> { throw ExceptionUtil.createNotFoundException("Not found image by id = " + id); }
                );
    }

    public byte[] getImageBytesById(final UUID id) {
        return repository
                .findById(id)
                .map(ImageEntity::getImage)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found image by id = " + id));
    }

    public byte[] getImageBytesByProductId(final Long productId) {
        final ProductDTO product = productService.getById(productId);
        final UUID imageId = product.getImageId();

        if (imageId == null) {
            throw ExceptionUtil.createNotFoundException("Not found image by product id = " + productId);
        }

        return getImageBytesById(imageId);
    }

    private static byte[] getImageBytes(final MultipartFile image) {
        try {
            return image.getBytes();
        } catch (final IOException ex) {
            throw ExceptionUtil.createBadRequestException("Bad file byte array");
        }
    }
}
