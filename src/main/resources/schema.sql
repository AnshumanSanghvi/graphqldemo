CREATE TABLE public."user"
(
    id         bigserial    NOT NULL,
    username   varchar(20)  NOT NULL,
    "password" varchar(100) NOT NULL,
    email      varchar(25)  NOT NULL,
    "role"     varchar(50)  NOT NULL DEFAULT USER,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_un_email UNIQUE (email),
    CONSTRAINT user_un_username UNIQUE (username)
);

CREATE TABLE public.rotten_tomatoes_rating
(
    id                     int4        NOT NULL,
    rotten_tomatoes_id     varchar(25) NOT NULL,
    rotten_tomatoes_rating float4      NOT NULL,
    film_id                int4        NOT NULL,
    last_update            timestamp   NOT NULL DEFAULT now(),
    CONSTRAINT rotten_tomatoes_film_id_un UNIQUE (film_id),
    CONSTRAINT rotten_tomatoes_id_pk PRIMARY KEY (id),
    CONSTRAINT rotten_tomatoes_rotten_tomatoes_id_un UNIQUE (rotten_tomatoes_id)
);

CREATE TABLE public.imdb_rating
(
    id          int4        NOT NULL,
    imdb_id     varchar(25) NOT NULL,
    imdb_rating float4      NOT NULL,
    film_id     int4        NOT NULL,
    last_update timestamp   NOT NULL DEFAULT now(),
    CONSTRAINT imdb_film_id_un UNIQUE (film_id),
    CONSTRAINT imdb_id_pk PRIMARY KEY (id),
    CONSTRAINT imdb_imdb_id_un UNIQUE (imdb_id)
);

CREATE UNIQUE INDEX imdb_film_id_un ON public.imdb_rating USING btree (film_id);

CREATE UNIQUE INDEX imdb_id_pk ON public.imdb_rating USING btree (id);

CREATE UNIQUE INDEX imdb_imdb_id_un ON public.imdb_rating USING btree (imdb_id);

CREATE UNIQUE INDEX rotten_tomatoes_film_id_un ON public.rotten_tomatoes_rating USING btree (film_id);

CREATE UNIQUE INDEX rotten_tomatoes_id_pk ON public.rotten_tomatoes_rating USING btree (id);

CREATE UNIQUE INDEX rotten_tomatoes_rotten_tomatoes_id_un ON public.rotten_tomatoes_rating USING btree (rotten_tomatoes_id);

CREATE SEQUENCE public.imdb_id_sequence
    INCREMENT BY 50
    NO MINVALUE
    MAXVALUE 2147483647 CACHE 10
	NO CYCLE;



INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (102, 'lNInrOPiBL', 6.375932, 1133938638, '2022-10-11 15:51:52.106697'),
       (103, 'DOxFcxglcA', 7.6826715, 1006582214, '2022-10-11 15:51:52.112122'),
       (104, 'szdOLBScSF', 4.3309813, 1090705963, '2022-10-11 15:51:52.112184'),
       (105, 'gaEMyNnyIo', 1.2977943, 1095007629, '2022-10-11 15:51:52.112241'),
       (106, 'gUvXerjhAz', 1.6842134, 1285218152, '2022-10-11 15:51:52.112295'),
       (107, 'XatzONAzNz', 8.612588, 1775105961, '2022-10-11 15:51:52.112344'),
       (108, 'shhmeUUyIA', 4.36617, 1104859468, '2022-10-11 15:51:52.112393'),
       (109, 'hSlhRECuaC', 3.8743641, 975768258, '2022-10-11 15:51:52.11244'),
       (110, 'sKbDztuVAz', 7.650924, 576597740, '2022-10-11 15:51:52.112516'),
       (111, 'LSaACYYeOs', 1.8121547, 1090686811, '2022-10-11 15:51:52.112555');
INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (112, 'ocUhaxvTaY', 6.251516, 1499431530, '2022-10-11 15:51:52.112599'),
       (113, 'RactWEWKdm', 0.45660138, 622707527, '2022-10-11 15:51:52.112644'),
       (114, 'BnsbWVlZFU', 1.4230397, 50190072, '2022-10-11 15:51:52.112681'),
       (115, 'heEVRVCkcm', 0.31395477, 28634790, '2022-10-11 15:51:52.112718'),
       (116, 'YMnuybARSQ', 0.1915451, 2132427257, '2022-10-11 15:51:52.112757'),
       (117, 'XRBJRGDuTi', 4.7467585, 31512391, '2022-10-11 15:51:52.112794'),
       (118, 'OokcHgchFz', 3.8435192, 302745124, '2022-10-11 15:51:52.112831'),
       (119, 'JmxuljchaW', 0.7586306, 1298961289, '2022-10-11 15:51:52.112871'),
       (120, 'KuracJpiAD', 1.7713517, 749915390, '2022-10-11 15:51:52.112908'),
       (121, 'ZqiDIdnrRx', 9.404698, 1057486640, '2022-10-11 15:51:52.112944');
INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (122, 'xtREUGbVZf', 1.1220186, 1849471280, '2022-10-11 15:51:52.112979'),
       (123, 'RwCFHuLAun', 2.7156007, 1479919207, '2022-10-11 15:51:52.113013'),
       (124, 'stuImZCZyH', 3.5428164, 489079486, '2022-10-11 15:51:52.113064'),
       (125, 'paUTcQXNmO', 0.20068908, 221635299, '2022-10-11 15:51:52.113105'),
       (126, 'ucpJvOHqfS', 3.9190934, 476310777, '2022-10-11 15:51:52.113143'),
       (127, 'rdNKoGtWKy', 4.9604983, 2015121673, '2022-10-11 15:51:52.113183'),
       (128, 'GJoveGXsth', 6.270567, 165396984, '2022-10-11 15:51:52.113219'),
       (129, 'mEBRdkwBFX', 3.3714185, 1421148422, '2022-10-11 15:51:52.113255'),
       (130, 'kcyNIBQKPB', 2.9872656, 1417300506, '2022-10-11 15:51:52.11329'),
       (131, 'YoEupEWyLQ', 5.794594, 1405638536, '2022-10-11 15:51:52.113326');
INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (132, 'VKZcZdTIth', 2.0718696, 748435487, '2022-10-11 15:51:52.113366'),
       (133, 'udkgRzzcKX', 0.3006099, 1805245527, '2022-10-11 15:51:52.113406'),
       (134, 'MzQNqyeIVW', 4.897094, 8492774, '2022-10-11 15:51:52.113442'),
       (135, 'sYoRaoaNba', 9.891864, 195983920, '2022-10-11 15:51:52.113476'),
       (136, 'qZdjOVqfHv', 3.7256505, 620547118, '2022-10-11 15:51:52.113511'),
       (137, 'lYLbGXQSRJ', 2.9522924, 1534204983, '2022-10-11 15:51:52.113545'),
       (138, 'aNKseCYXLI', 3.5736423, 1013910470, '2022-10-11 15:51:52.113588'),
       (139, 'YgaGvrACVV', 9.872247, 393499329, '2022-10-11 15:51:52.113635'),
       (140, 'jyYBXQsYJD', 9.279392, 1817157828, '2022-10-11 15:51:52.11371'),
       (141, 'lAuyjUkARv', 8.1039, 1859939137, '2022-10-11 15:51:52.113774');
INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (142, 'gpJGaIAIEI', 3.378529, 1915076370, '2022-10-11 15:51:52.113817'),
       (143, 'wwVhAYhDpp', 6.8250318, 73602603, '2022-10-11 15:51:52.113856'),
       (144, 'lDzuVpCbkW', 8.748615, 1376466146, '2022-10-11 15:51:52.113893'),
       (145, 'gDHGEmqxvi', 8.358018, 798613157, '2022-10-11 15:51:52.11394'),
       (146, 'IeCPGzimQT', 7.2949734, 1092424624, '2022-10-11 15:51:52.113979'),
       (147, 'KfavdXoLDi', 0.34419918, 806056064, '2022-10-11 15:51:52.114024'),
       (148, 'vUhdPqGDwW', 0.18222466, 2122136240, '2022-10-11 15:51:52.114058'),
       (149, 'QkjPMjxdMZ', 3.155745, 775430628, '2022-10-11 15:51:52.114092'),
       (150, 'HMtnrEdHnB', 9.408053, 331559728, '2022-10-11 15:51:52.114126'),
       (151, 'uTaRVIsoPP', 5.2879424, 1071255571, '2022-10-11 15:51:52.114161');
INSERT INTO public.rotten_tomatoes_rating (id, rotten_tomatoes_id, rotten_tomatoes_rating, film_id, last_update)
VALUES (152, 'ZcxehmwsPO', 5.657978, 586356014, '2022-10-11 15:51:52.114195'),
       (153, 'EbmDazmIIe', 3.5712676, 406880330, '2022-10-11 15:51:52.115324'),
       (154, 'QoyZebBjhy', 4.3196387, 193625926, '2022-10-11 15:51:52.115367'),
       (155, 'hfCATmvMWj', 9.437473, 633295485, '2022-10-11 15:51:52.11541'),
       (156, 'CeWLPnNGXh', 3.8758578, 1000615732, '2022-10-11 15:51:52.115446'),
       (157, 'ZVesRnAfWx', 0.5363894, 286474988, '2022-10-11 15:51:52.115481'),
       (158, 'YoNhknjWQZ', 7.3972125, 1361317698, '2022-10-11 15:51:52.115515'),
       (159, 'rXNliyRwyS', 3.5056171, 1489751097, '2022-10-11 15:51:52.115548'),
       (160, 'bHjyiVMNSR', 4.9331636, 1114104952, '2022-10-11 15:51:52.115581'),
       (161, 'uJsiGmNiFh', 7.3254013, 1088952646, '2022-10-11 15:51:52.115614');


INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1451, 'wFOBhYggyV', 3.8415108, 1133938638, '2022-10-11 15:51:52.053043'),
       (1452, 'WlqlOzeHSS', 2.0704994, 1006582214, '2022-10-11 15:51:52.062474'),
       (1453, 'yOgGBEoEBm', 5.0306826, 1090705963, '2022-10-11 15:51:52.062559'),
       (1454, 'OjMKYHeOhK', 0.11539258, 1095007629, '2022-10-11 15:51:52.062618'),
       (1455, 'qWTmVjsVVw', 1.5906792, 1285218152, '2022-10-11 15:51:52.062681'),
       (1456, 'baVhyjLRVB', 3.6531603, 1775105961, '2022-10-11 15:51:52.062733'),
       (1457, 'qQcMpTBRis', 4.5721264, 1104859468, '2022-10-11 15:51:52.062782'),
       (1458, 'NCJroEKimR', 3.7082691, 975768258, '2022-10-11 15:51:52.062831'),
       (1459, 'VTGLYJsIvk', 2.4051285, 576597740, '2022-10-11 15:51:52.062884'),
       (1460, 'QPUfTYyhOy', 8.626723, 1090686811, '2022-10-11 15:51:52.062933');
INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1461, 'yQVdYPJKwj', 1.8161201, 1499431530, '2022-10-11 15:51:52.062979'),
       (1462, 'HsBHETBGKc', 2.8718126, 622707527, '2022-10-11 15:51:52.063025'),
       (1463, 'zjyUxykoup', 2.465078, 50190072, '2022-10-11 15:51:52.063071'),
       (1464, 'wRxrHHLFso', 1.4912188, 28634790, '2022-10-11 15:51:52.063118'),
       (1465, 'CdDPyLuade', 4.1804523, 2132427257, '2022-10-11 15:51:52.063164'),
       (1466, 'tLVmhSKKjC', 3.4601827, 31512391, '2022-10-11 15:51:52.06321'),
       (1467, 'AELSuCvDsU', 2.3452632, 302745124, '2022-10-11 15:51:52.063262'),
       (1468, 'snLcYpqJOt', 8.814777, 1298961289, '2022-10-11 15:51:52.063309'),
       (1469, 'LkNtYoggyO', 2.3461187, 749915390, '2022-10-11 15:51:52.063362'),
       (1470, 'dRVsJysLCT', 4.397505, 1057486640, '2022-10-11 15:51:52.06341');
INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1471, 'FkKoImnnJB', 9.297228, 1849471280, '2022-10-11 15:51:52.06346'),
       (1472, 'PagWxnOypZ', 8.41985, 1479919207, '2022-10-11 15:51:52.063508'),
       (1473, 'jClqCTIUzB', 3.4098685, 489079486, '2022-10-11 15:51:52.063571'),
       (1474, 'rIlHqFLysE', 6.0847564, 221635299, '2022-10-11 15:51:52.063621'),
       (1475, 'BpHShMiZNN', 3.4664109, 476310777, '2022-10-11 15:51:52.063674'),
       (1476, 'zlBWHMhngA', 4.794033, 2015121673, '2022-10-11 15:51:52.06373'),
       (1477, 'SoOvxtwmlC', 2.605483, 165396984, '2022-10-11 15:51:52.063861'),
       (1478, 'gIqAKbrrTk', 4.485746, 1421148422, '2022-10-11 15:51:52.063933'),
       (1479, 'gZwjKDNxZt', 8.915915, 1417300506, '2022-10-11 15:51:52.063994'),
       (1480, 'YRpwBshScq', 4.304898, 1405638536, '2022-10-11 15:51:52.064044');
INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1481, 'aPdzOtBFqa', 2.7127485, 748435487, '2022-10-11 15:51:52.064104'),
       (1482, 'qHQHMTQlFn', 2.9139936, 1805245527, '2022-10-11 15:51:52.064163'),
       (1483, 'ICIsaULNuw', 7.526106, 8492774, '2022-10-11 15:51:52.064217'),
       (1484, 'yFghHjkqzv', 4.5111413, 195983920, '2022-10-11 15:51:52.064264'),
       (1485, 'fSGGkVcwJa', 0.12659548, 620547118, '2022-10-11 15:51:52.064311'),
       (1486, 'PVOYvUGKUH', 3.1614277, 1534204983, '2022-10-11 15:51:52.064365'),
       (1487, 'aIdabinhJo', 8.848966, 1013910470, '2022-10-11 15:51:52.064413'),
       (1488, 'bgnhBXclku', 2.8877172, 393499329, '2022-10-11 15:51:52.064468'),
       (1489, 'YaOnjKBhVY', 9.22489, 1817157828, '2022-10-11 15:51:52.06452'),
       (1490, 'IirjSYDmXZ', 2.541474, 1859939137, '2022-10-11 15:51:52.064567');
INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1491, 'EaSTwFfuOO', 1.8183823, 1915076370, '2022-10-11 15:51:52.064611'),
       (1492, 'pbjtMFnhKP', 1.6205509, 73602603, '2022-10-11 15:51:52.064658'),
       (1493, 'mftqVgNqjz', 2.1518862, 1376466146, '2022-10-11 15:51:52.06471'),
       (1494, 'qMXRMjLref', 3.6632502, 798613157, '2022-10-11 15:51:52.064771'),
       (1495, 'XZhLbTTXJN', 4.8523226, 1092424624, '2022-10-11 15:51:52.064818'),
       (1496, 'wXZSLqdfpj', 6.776796, 806056064, '2022-10-11 15:51:52.064863'),
       (1497, 'AwfaNlcJOI', 9.425358, 2122136240, '2022-10-11 15:51:52.064906'),
       (1498, 'yrxatadUoa', 9.310216, 775430628, '2022-10-11 15:51:52.064949'),
       (1499, 'mUSOqLwIgJ', 9.33011, 331559728, '2022-10-11 15:51:52.064992'),
       (1500, 'hAcBrYudlH', 2.1194925, 1071255571, '2022-10-11 15:51:52.065038');
INSERT INTO public.imdb_rating (id, imdb_id, imdb_rating, film_id, last_update)
VALUES (1501, 'qsTrOCTCEl', 7.8789444, 586356014, '2022-10-11 15:51:52.065081'),
       (1502, 'pzlVfFqVGi', 5.6657724, 406880330, '2022-10-11 15:51:52.065965'),
       (1503, 'yUAtvHdfRR', 7.6877785, 193625926, '2022-10-11 15:51:52.066021'),
       (1504, 'eIPyZEMKAA', 0.11088062, 633295485, '2022-10-11 15:51:52.066072'),
       (1505, 'QUtGdzmHXu', 6.367257, 1000615732, '2022-10-11 15:51:52.066159'),
       (1506, 'yrwzwMQzWW', 3.3099403, 286474988, '2022-10-11 15:51:52.066231'),
       (1507, 'SrNQnkGLAd', 1.4467654, 1361317698, '2022-10-11 15:51:52.066285'),
       (1508, 'IhCPDVNSxa', 5.2365093, 1489751097, '2022-10-11 15:51:52.066337'),
       (1509, 'TaRPknpjrO', 6.389832, 1114104952, '2022-10-11 15:51:52.066388'),
       (1510, 'tBCeCcIacx', 6.9053993, 1088952646, '2022-10-11 15:51:52.066441');
