package ru.egorov.StoreCrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.StoreCrawler.model.SneakerheadProduct;

@Repository
public interface SneakerheadProductRepository extends JpaRepository<SneakerheadProduct, Integer> {
}
