package edu.school21.backend.services;

import edu.school21.backend.dto.ProductDTO;
import edu.school21.backend.dto.input.InputProductDTO;
import edu.school21.backend.entities.ProductEntity;
import edu.school21.backend.repositories.ProductRepository;
import edu.school21.backend.utils.ExceptionUtil;
import edu.school21.backend.utils.MappingUtil;
import edu.school21.backend.utils.ValidateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final MappingUtil mapper;
    private final ValidateHelper validateHelper;
    private final ProductRepository repository;

    public ProductDTO add(final InputProductDTO product) {
        final ProductEntity entity = mapper.map(product, ProductEntity.class);
        entity.setLastUpdateDate(Date.valueOf(LocalDateTime.now().toLocalDate()));

        return mapper.map(repository.save(entity), ProductDTO.class);
    }

    public void deleteById(final Long id) {
        repository
                .findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> { throw ExceptionUtil.createNotFoundException("Not found product by id = " + id); }
                );
    }

    public ProductDTO getById(final Long id) {
        return repository
                .findById(id)
                .map(product -> mapper.map(product, ProductDTO.class))
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found product by id = " + id));
    }

    public List<ProductDTO> getAll() {
        final List<ProductEntity> products = repository.findAll();
        return mapper.mapList(products, ProductDTO.class);
    }

    public ProductDTO decreaseAvailableStock(final Long id, final Integer decreaseCount) {
        validateHelper.validatePositiveNumber(decreaseCount);
        ProductEntity product = getProductEntity(id);

        final long availableStock = product.getAvailableStock();
        final long newAvailableStock = availableStock <= decreaseCount ? 0 : availableStock - decreaseCount;

        if (newAvailableStock != availableStock) {
            product.setAvailableStock(newAvailableStock);
            product.setLastUpdateDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
            product = repository.save(product);
        }

        return mapper.map(product, ProductDTO.class);
    }

    public void update(final ProductDTO product) {
        final ProductEntity oldProduct = getProductEntity(product.getId());
        final ProductEntity newProduct = mapper.map(product, ProductEntity.class);

        if (oldProduct.equals(newProduct)) {
            return;
        }

        newProduct.setLastUpdateDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        repository.save(newProduct);
    }

    private ProductEntity getProductEntity(final Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found product by id = " + id));
    }
}
