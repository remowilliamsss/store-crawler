package ru.egorov.StoreCrawler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.StoreCrawler.model.Product;
import ru.egorov.StoreCrawler.model.SneakerheadProduct;
import ru.egorov.StoreCrawler.repository.SneakerheadProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SneakerheadProductService implements ProductService {
    private final SneakerheadProductRepository sneakerheadProductRepository;

    @Override
    public Page<SneakerheadProduct> findAll(Pageable pageable) {
        return sneakerheadProductRepository.findAll(pageable);
    }

    @Override
    public List<SneakerheadProduct> findAllByName(String name) {
        return sneakerheadProductRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public List<SneakerheadProduct> updateAll(List<Product> products) {
        sneakerheadProductRepository.deleteAll();

        sneakerheadProductRepository.flush();

        return sneakerheadProductRepository.saveAll(products
                .stream()
                .map(product -> (SneakerheadProduct) product)
                .collect(Collectors.toList()));
    }

    @Override
    public SneakerheadProduct findBySku(String sku) {
        return sneakerheadProductRepository.findBySku(sku);
    }
}
