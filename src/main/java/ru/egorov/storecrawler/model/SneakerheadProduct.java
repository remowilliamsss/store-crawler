package ru.egorov.storecrawler.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sneakerhead_product")
public class SneakerheadProduct extends Product {

    {
        setStoreType(StoreType.sneakerhead);
    }
}
