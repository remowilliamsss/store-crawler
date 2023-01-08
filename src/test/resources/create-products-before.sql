delete from sneakerhead_product;
delete from footbox_product;

insert into sneakerhead_product(name, sku, category, brand, image, color, price, price_currency, country, sizes, gender,
                                url, store_type) values
    ('Nike x Travis Scott Air Trainer 1', 'DR7515-200', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/iblock/060/060181d6eb75f77e373df9b511db7224.jpg', 'Коричневый', 35990, 'RUB',
     'Индонезия', '8 US, 8.5 US', 'Мужское',
     'https://sneakerhead.ru/shoes/sneakers/x-travis-scott-air-trainer-1-DR7515-200/', 'sneakerhead'),
    ('Nike Air Force 1 ''07', 'DR9867-100', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/797/7978e2678bed43750d367be1b8729a62.webp', 'Белый', 15990,
     'RUB', 'Вьетнам', '8 US, 8.5 US', 'Мужское',
     'https://sneakerhead.ru/shoes/sneakers/air-force-1-07-DR9867-100/', 'sneakerhead'),
    ('Converse Chuck 70 OX', 'A00538', 'Кеды', 'Converse',
     'https://sneakerhead.ru/upload/iblock/1cd/1cd1c60e1869265c4ebb4aea040d16b9.jpg', 'Бежевый', 8100,
     'RUB', 'Вьетнам', '5.5 US, 6 US, 6.5 US, 7 US, 7.5 US', 'Женское',
     'https://sneakerhead.ru/shoes/keds/chuck-70-ox-A00538/', 'sneakerhead'),
    ('adidas Originals x Jeremy Scott Wings 4.0', 'GY4419', 'Кроссовки', 'adidas Originals',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/6fe/6fe5f8d16f41a9ea327fef4853693ec9.webp', 'Чёрный',
     15990, 'RUB', 'Китай', '6.5 US, 7 US', 'Мужское',
     'https://sneakerhead.ru/shoes/sneakers/x-jeremy-scott-wings-4-0-GY4419/', 'sneakerhead'),
    ('Nike Joyride ENV ISPA', 'BV4584-400', 'Кроссовки', 'Nike',
     'https://sneakerhead.ru/upload/resize_cache/webp/iblock/617/617772a87f646cc95a0e87c166717d0c.webp', 'Голубой',
     15990, 'RUB', 'Вьетнам', '4.5 US, 5 US, 7 US', null,
     'https://sneakerhead.ru/shoes/sneakers/joyride-env-ispa-BV4584-400/', 'sneakerhead');

insert into footbox_product(name, sku, category, brand, image, color, price, price_currency, country, sizes, gender,
                                url, store_type, composition, coloring) values
    ('Nike Travis Scott x Air Trainer 1', 'DR7515-200', 'Обувь', 'Nike',
     'https://footboxshop.ru/upload/resize_cache/iblock/cf0/600_600_2/gin5008b1tl1m8y1hiemaan1p0tq69g0.jpg',
     'Коричневый', 32999, 'RUB', 'ИНДОНЕЗИЯ', '41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR, 45 EUR', 'Мужское',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_travis_scott_x_air_trainer_1_dr7515_200/', 'footbox', null,
     'Coriander/Ashen Slate-Wheat-Light Sienna'),
    ('Nike Air Force 1 ''07', 'DR9867-100', 'Обувь', 'Nike',
     'https://footboxshop.ru/upload/resize_cache/iblock/900/600_600_2/afri8vtgg46n2khka8dms164pe66afs1.jpg',
     'Белый', 16499, 'RUB', 'ВЬЕТНАМ', '41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR', 'Мужское',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_air_force_1_07_dr9867_100/', 'footbox',
     'Верх: натуральная кожа подошва: резина', 'White/White-Khaki'),
    ('Converse Chuck 70', 'A00538', 'Обувь', 'Converse',
     'https://footboxshop.ru/upload/resize_cache/iblock/382/600_600_2/ery75p7uc7dt9we29wgh114srnd898cc.jpg',
     'Желтый', 13399, 'RUB', 'ВЬЕТНАМ', '36.5 EUR, 37 EUR, 37.5 EUR, 38 EUR, 39 EUR, 39.5 EUR, 40 EUR', 'Женское',
     'https://www.footboxshop.ru/catalog/obuv/kedy_converse_chuck_70_a00538/', 'footbox',
     'Верх: хлопок подошва: резина', 'Yellow/Orange'),
    ('Nike Air Zoom-Type SE', 'CV2220-001', 'Обувь', 'Nike',
     'https://footboxshop.ru/upload/resize_cache/iblock/9ec/600_600_2/mfs5hqhhak0256ijytzvf1s2k3cucuyz.jpg',
     'Серый', 20099, 'RUB', null, '42.5 EUR, 43 EUR, 44 EUR, 44.5 EUR, 45 EUR, 45.5 EUR, 46 EUR', 'Мужское',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_nike_air_zoom_type_se_cv2220_001/', 'footbox',
     'Верх: текстиль Подошва: резина, пластик', 'Grey Fog/Dk Smoke Grey-Campfire Orange'),
    ('Jordan Legacy 312 "Exploration Unit"', 'FB1875-141', 'Обувь', 'Jordan',
     'https://footboxshop.ru/upload/resize_cache/iblock/2a9/600_600_2/imxvwkcgltygi4msvf1hae2kbdbfd7xq.jpg',
     'Серый', 22899, 'RUB', 'КИТАЙ', '40 EUR, 40.5 EUR, 41 EUR, 42 EUR, 42.5 EUR, 43 EUR, 44 EUR', 'Мужское',
     'https://www.footboxshop.ru/catalog/obuv/krossovki_art_fb1875_141/', 'footbox',
     'Верх: натуральная кожа, текстиль подошва: резина', 'Sail/Beta Blue-Coconut Milk');