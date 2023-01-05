package ru.egorov.StoreCrawler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.egorov.StoreCrawler.dto.ProductDto;
import ru.egorov.StoreCrawler.dto.SearchRequest;
import ru.egorov.StoreCrawler.dto.SearchResponse;
import ru.egorov.StoreCrawler.service.SearchService;
import ru.egorov.StoreCrawler.validator.StoreName;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductsController {
    private final SearchService searchService;

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> search(@Valid @RequestBody SearchRequest request) {
        String query = request.getQuery();

        SearchResponse searchResponse = searchService.search(query);

        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }

    @PostMapping("/find_by_sku")
    public ResponseEntity<SearchResponse> findBySku(@Valid @RequestBody SearchRequest request) {
        String query = request.getQuery();

        SearchResponse searchResponse = searchService.findBySku(query);

        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }

    @GetMapping("/{store_name}")
    public ResponseEntity<List<ProductDto>> findByStore(@PathVariable("store_name") @StoreName String storeName,
                                                        Pageable pageable) {

        List<ProductDto> productDtos = searchService.findByStore(storeName, pageable);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
