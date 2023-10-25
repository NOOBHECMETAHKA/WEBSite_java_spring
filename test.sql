-- Adminer 4.8.1 PostgreSQL 14.5 dump

\connect "test";

DROP TABLE IF EXISTS "address";
DROP SEQUENCE IF EXISTS address_id_seq;
CREATE SEQUENCE address_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 2 CACHE 1;

CREATE TABLE "public"."address" (
    "id" integer DEFAULT nextval('address_id_seq') NOT NULL,
    "apartment" character varying(255) NOT NULL,
    "city" character varying(255) NOT NULL,
    "entrance" character varying(255) NOT NULL,
    "house" character varying(255) NOT NULL,
    "street" character varying(255) NOT NULL,
    "address_person_id" bigint,
    CONSTRAINT "address_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "address";

INSERT INTO "address" ("id", "apartment", "city", "entrance", "house", "street", "address_person_id") VALUES
(1,	'20',	'Москва',	'Москва',	'11',	'Липовая',	1);

DROP TABLE IF EXISTS "application";
DROP SEQUENCE IF EXISTS application_id_seq;
CREATE SEQUENCE application_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 3 CACHE 1;

CREATE TABLE "public"."application" (
    "id" integer DEFAULT nextval('application_id_seq') NOT NULL,
    "local_date" date,
    "reason" character varying(255),
    "text" character varying(255),
    "application_person_id" bigint,
    CONSTRAINT "application_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "application";

INSERT INTO "application" ("id", "local_date", "reason", "text", "application_person_id") VALUES
(1,	'2023-10-22',	'Не работает покупка',	'Нажал на кнопку ничего не произошло',	1),
(3,	'2023-10-23',	'Не работают ссылки',	'Требуется пересмотр',	2);

DROP TABLE IF EXISTS "category";
DROP SEQUENCE IF EXISTS category_id_seq;
CREATE SEQUENCE category_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 4 CACHE 1;

CREATE TABLE "public"."category" (
    "id" integer DEFAULT nextval('category_id_seq') NOT NULL,
    "name" character varying(255),
    CONSTRAINT "category_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "category";

INSERT INTO "category" ("id", "name") VALUES
(1,	'Кроватки'),
(3,	'Подушки'),
(4,	'Стульчики');

DROP TABLE IF EXISTS "model_user";
DROP SEQUENCE IF EXISTS model_user_id_user_seq;
CREATE SEQUENCE model_user_id_user_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 2 CACHE 1;

CREATE TABLE "public"."model_user" (
    "id_user" bigint DEFAULT nextval('model_user_id_user_seq') NOT NULL,
    "active" boolean NOT NULL,
    "password" character varying(255),
    "username" character varying(255),
    CONSTRAINT "model_user_pkey" PRIMARY KEY ("id_user")
) WITH (oids = false);

TRUNCATE "model_user";

INSERT INTO "model_user" ("id_user", "active", "password", "username") VALUES
(1,	'1',	'$2a$08$k/pcCT66sIX7yX.WIl1/aeCt72.BF5zfbp8x1PyRq28aMMw9dB74W',	'root@gmail.com'),
(2,	'1',	'$2a$08$Izuadyyg7O2I.DoNEmR.0OHKGv0Ibx/.6ZDwkYrej1hl.ojrsX8Q2',	'misha@gmail.com');

DROP TABLE IF EXISTS "order_list";
CREATE TABLE "public"."order_list" (
    "product_id" integer NOT NULL,
    "order_id" integer NOT NULL
) WITH (oids = false);

TRUNCATE "order_list";

DROP TABLE IF EXISTS "orders";
DROP SEQUENCE IF EXISTS orders_id_seq;
CREATE SEQUENCE orders_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 4 CACHE 1;

CREATE TABLE "public"."orders" (
    "id" integer DEFAULT nextval('orders_id_seq') NOT NULL,
    "date_created" date,
    "status_id" integer,
    "address_orders_id" integer,
    CONSTRAINT "orders_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "orders";

INSERT INTO "orders" ("id", "date_created", "status_id", "address_orders_id") VALUES
(1,	'2023-10-22',	1,	1),
(2,	'2023-10-22',	1,	1),
(4,	'2023-10-23',	1,	1);

DROP TABLE IF EXISTS "product";
DROP SEQUENCE IF EXISTS product_id_seq;
CREATE SEQUENCE product_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 3 CACHE 1;

CREATE TABLE "public"."product" (
    "id" integer DEFAULT nextval('product_id_seq') NOT NULL,
    "description" character varying(255),
    "name" character varying(255),
    "price" double precision,
    "category_product_id" integer,
    CONSTRAINT "product_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "product";

INSERT INTO "product" ("id", "description", "name", "price", "category_product_id") VALUES
(1,	'Красный',	'Помидор',	200,	1),
(3,	'Белая, твороженная',	'Сметана',	20,	4);

DROP TABLE IF EXISTS "status";
DROP SEQUENCE IF EXISTS status_id_seq;
CREATE SEQUENCE status_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 11 CACHE 1;

CREATE TABLE "public"."status" (
    "id" integer DEFAULT nextval('status_id_seq') NOT NULL,
    "name" character varying(255),
    CONSTRAINT "status_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

TRUNCATE "status";

INSERT INTO "status" ("id", "name") VALUES
(3,	'На складе'),
(11,	'Помидор'),
(1,	'В разработке'),
(7,	'У курьер');

DROP TABLE IF EXISTS "user_role";
CREATE TABLE "public"."user_role" (
    "user_id" bigint NOT NULL,
    "roles" character varying(255)
) WITH (oids = false);

TRUNCATE "user_role";

INSERT INTO "user_role" ("user_id", "roles") VALUES
(2,	'USER'),
(1,	'PRODUCT_MANAGER');

ALTER TABLE ONLY "public"."address" ADD CONSTRAINT "fk3flt9d2h1d916dy8c1bq7gisf" FOREIGN KEY (address_person_id) REFERENCES model_user(id_user) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."application" ADD CONSTRAINT "fk9g4l0l14hqxmvm384fhe4pygp" FOREIGN KEY (application_person_id) REFERENCES model_user(id_user) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."order_list" ADD CONSTRAINT "fk84ksds192y1tymsjmq1qmqro8" FOREIGN KEY (order_id) REFERENCES orders(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."order_list" ADD CONSTRAINT "fkcj44h53c87hr23l208dyshj1n" FOREIGN KEY (product_id) REFERENCES product(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."orders" ADD CONSTRAINT "fk72hhjqantcpqvn1ne94lu2gcm" FOREIGN KEY (address_orders_id) REFERENCES address(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."orders" ADD CONSTRAINT "fknoettwqr56yaai4i8nwxg4huo" FOREIGN KEY (status_id) REFERENCES status(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."product" ADD CONSTRAINT "fkrc1flv72sydvkd8819topwr4j" FOREIGN KEY (category_product_id) REFERENCES category(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."user_role" ADD CONSTRAINT "fkhnk3nw6rsvkly3ww7umdq7ys1" FOREIGN KEY (user_id) REFERENCES model_user(id_user) NOT DEFERRABLE;

-- 2023-10-25 02:04:53.536047+03
