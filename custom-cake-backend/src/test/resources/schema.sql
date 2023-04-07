SET foreign_key_checks = 0;
DROP TABLE IF EXISTS operator, user, store, store_image, dayoff, cake_item, cake_item_image, inquiry,
    cake_option1, cake_option2, cake_option3, option_by_cake, store_notification, chat_room, chat_message,
    cake_custom_order_sheet, cake_custom_order, cake_design_order, review, push_notification, phone_auth;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS `operator` (
                                          `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                          `email`	                        VARCHAR(100)	        NOT NULL UNIQUE,
                                          `password`	                    VARCHAR(255)	        NOT NULL,
                                          `name`	                        VARCHAR(50)	            NOT NULL,
                                          `phone`	                        VARCHAR(20)	            NOT NULL,
                                          `is_authenticated`	            TINYINT	                NOT NULL,
                                          `last_conn_date`	            DATETIME	            NOT NULL,           -- 마지막 접속일
                                          `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                          `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `user` (
                                      `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      `name`	                        VARCHAR(50)	            NOT NULL,
                                      `nickname`	                    VARCHAR(20)	            NOT NULL UNIQUE,       -- DEFAULT cake-{uuid}
                                      `phone`             	        VARCHAR(20)	            NOT NULL,              -- 인증
                                      `social_type`	                VARCHAR(10)	            NOT NULL,              -- ENUM(KAKAO, NAVER)
                                      `social_account_id`	            VARCHAR(255)	        NOT NULL,              -- '소셜 계정 Unique 값'
                                      `is_authenticated`	            TINYINT	                NOT NULL,
                                      `last_conn_datetime`	        DATETIME	            NOT NULL,
                                      `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                      `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `store` (
                                       `id`                            BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       `operator_id`	                BIGINT UNSIGNED	        NOT NULL,
                                       `business_registration_no`	    CHAR(10)	            NOT NULL UNIQUE,                -- 사업자번호
                                       `representative_name`	        VARCHAR(50)	            NOT NULL,                       -- 대표자 이름
                                       `zip_code`	                    VARCHAR(5)	            NOT NULL,
                                       `base_address`	                VARCHAR(100)	        NOT NULL,
                                       `detail_address`	            VARCHAR(100)            NULL,
                                       `phone`	                        VARCHAR(20)     	    NOT NULL,
                                       `name`                      	VARCHAR(50)     	    NOT NULL,
                                       `description`	                TEXT	                NULL,
                                       `open_time`	                    JSON             	    NOT NULL,                       -- 요일 별 open 시간 상이
                                       `reservation_period`	        INT UNSIGNED            NOT NULL DEFAULT 30,            -- 5,10,15...30
                                       `reservation_per_period_count`	INT UNSIGNED	        NOT NULL DEFAULT 1,
                                       `thumbnail_image_url`	        VARCHAR(255)	        NOT NULL,
                                       `view_count`	                INT UNSIGNED	        NOT NULL DEFAULT 0,
                                       `rating_sum`	                INT UNSIGNED	        NOT NULL DEFAULT 0,
                                       `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                       `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                       FOREIGN KEY (operator_id) REFERENCES operator (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `store_image` (
                                             `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                             `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                             `url`	                        VARCHAR(255)	        NOT NULL,
                                             `is_thumbnail`	                TINYINT                	NOT NULL,
                                             `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                             `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                             FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- CREATE TABLE IF NOT EXISTS `address` (
--  	`id`	BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT PRIMARY KEY,
--  	`store_id`	BIGINT UNSIGNED	NOT NULL,
--  	`zip_code`	CHAR(5)	NULL	DEFAULT UNIQUE,
--  	`base_address`	VARCHAR(100)	NULL	DEFAULT UNIQUE,
--      `detail_address`	VARCHAR(100)	NULL	DEFAULT UNIQUE
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `dayoff` (
                                        `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                        `dayoff_type`               	VARCHAR(20)	            NOT NULL,       -- ENUM("FIXED", "DESIGNATED")
                                        `dayoff_day`                	CHAR(3)	        	    NULL,           -- ENUM("MON"~"SUN")
                                        `dayoff_date`	                DATE	                NULL,
                                        `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                        `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                        FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_item` (
                                           `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                           `name`	                        VARCHAR(20)	            NOT NULL,
                                           `category`	                    VARCHAR(20)	            NULL,
                                           `description`	                TEXT	                NULL,
                                           `thumbnail_image_url`	        VARCHAR(255)	        NOT NULL,
                                           `is_onsale`	                    TINYINT	                NOT NULL DEFAULT 1,
                                           `is_deleted`	                TINYINT	                NOT NULL DEFAULT 0,
                                           `price`	                        INT	UNSIGNED            NOT NULL,
                                           `view_count`	                INT UNSIGNED	        NOT NULL DEFAULT 0,
                                           `order_count`	                INT UNSIGNED        	NOT NULL DEFAULT 0,
                                           `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                           `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                           FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_item_image` (
                                                 `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                 `cake_item_id`	                BIGINT UNSIGNED	        NOT NULL,
                                                 `url`	                        VARCHAR(255)	        NOT NULL,
                                                 `is_thumbnail`	                TINYINT	                NOT NULL,
                                                 `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                 `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                 FOREIGN KEY (cake_item_id) REFERENCES cake_item (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- CREATE TABLE IF NOT EXISTS `sgg_area` (
-- 	`sgg_area_id`	VARCHAR(255)	NOT NULL,
--  	`adm_code`	VARCHAR(255)	NULL,
--  	`adm_name`	VARCHAR(255)	NULL,
--  	`Field3`	VARCHAR(255)	NULL,
--  	`Field4`	VARCHAR(255)	NULL
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `inquiry` (
                                         `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         `user_id`                       BIGINT UNSIGNED	        NOT NULL,
                                         `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                         `title`	                        VARCHAR(50)	            NOT NULL,
                                         `content`	                    TEXT	                NOT NULL,
                                         `is_answered`	                TINYINT	                NOT NULL DEFAULT 0,
                                         `answer`	                    TEXT	                NULL,
                                         `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                         `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                         FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_option1` (
                                              `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                              `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                              `cake_shape`	                VARCHAR(10)         	NOT NULL,          -- ENUM(CIRCLE,SQUARE,HEART)
                                              `cake_size`	                    VARCHAR(10)         	NOT NULL,          -- ENUM(NO_1, ... ,NO_6)
                                              `lettering_limit`	            INT	UNSIGNED            NOT NULL,
                                              `cake_layer`	                VARCHAR(10)         	NOT NULL,          -- ENUM(1단,2단,3단)
                                              `price`	                        INT	UNSIGNED            NOT NULL,
                                              `is_used`	                    TINYINT	                NOT NULL DEFAULT 1,
                                              `is_deleted`	                TINYINT	                NOT NULL DEFAULT 0,
                                              `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                              `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                              FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_option2` (
                                              `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                              `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                              `cake_sheet`	                VARCHAR(10)	            NOT NULL,          -- ENUM(CHOCO, BANILA)
                                              `cake_inner_cream`	            VARCHAR(20)	            NOT NULL,          -- ENUM(CREAMCHEESE, CHOCO,...)
                                              `cake_outer_cream`	            VARCHAR(20)	            NOT NULL,          -- ENUM(CREAMCHEESE, CHOCO,...)
                                              `price`	                        INT	UNSIGNED            NOT NULL,
                                              `is_used`	                    TINYINT	                NOT NULL DEFAULT 1,
                                              `is_deleted`	                TINYINT	                NOT NULL DEFAULT 0,
                                              `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                              `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                              FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_option3` (
                                              `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                              `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                              `name`	                        VARCHAR(20)	            NOT NULL,
                                              `price`	                        INT	UNSIGNED            NOT NULL,
                                              `is_used`	                    TINYINT	                NOT NULL DEFAULT 1,
                                              `is_deleted`	                TINYINT	                NOT NULL DEFAULT 0,
                                              `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                              `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                              FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `option_by_cake` (
                                                `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                `cake_item_id`	                BIGINT UNSIGNED	        NOT NULL,
                                                `cake_option_type`	            INT UNSIGNED            NOT NULL,
                                                `cake_option_id`	            BIGINT UNSIGNED	        NOT NULL,          -- not foreign_key
                                                `price`	                        INT	UNSIGNED            NOT NULL,
                                                `is_used`	                    TINYINT	                NOT NULL DEFAULT 1,
                                                `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                FOREIGN KEY (cake_item_id) REFERENCES cake_item (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `store_notification` (
                                                    `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                    `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                                    `title`	                        VARCHAR(255)	        NOT NULL,
                                                    `content`	                    TEXT	                NOT NULL,
                                                    `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                    `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                    FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `chat_room` (
                                           `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           `user_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                           `operator_id`	                BIGINT UNSIGNED	        NOT NULL,
--     `cake_custom_order_sheet_id`    BIGINT UNSIGNED	        NOT NULL,
                                           `recent_cake_custom_order_sheet_id` BIGINT UNSIGNED	        NOT NULL,
                                           `chat_status`	                VARCHAR(20)	            NOT NULL,    -- ENUM("신규채팅","채팅진행중","주문완료(결제완료)","주문취소")
                                           `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                           `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                           FOREIGN KEY (user_id) REFERENCES user (id),
                                           FOREIGN KEY (operator_id) REFERENCES operator (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `chat_message` (
                                              `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                              `chat_room_id`	                BIGINT UNSIGNED	        NOT NULL,
                                              `message`	                    TEXT        	        NOT NULL,
                                              `is_read`	                    TINYINT	                NOT NULL DEFAULT 0,
                                              `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                              `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                              FOREIGN KEY (chat_room_id) REFERENCES chat_room (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_custom_order_sheet` (
                                                         `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                         `chat_room_id`	                BIGINT UNSIGNED	        NOT NULL,
                                                         `pickup_date`	                DATETIME	            NOT NULL,
                                                         `cake_option1_id`	            BIGINT UNSIGNED	        NOT NULL,
                                                         `cake_option2_id`	            BIGINT UNSIGNED	        NOT NULL,
                                                         `cake_option3_id`	            BIGINT UNSIGNED	        NULL DEFAULT NULL,
                                                         `cake_custom_image`	            VARCHAR(255)	        NULL,           -- url
                                                         `cake_custom_sketch`	        JSON	                NULL,       	-- 'JSON 데이터 구조를 정의해 프론트와 통신 필요'
                                                         `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                         `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                         FOREIGN KEY (chat_room_id) REFERENCES chat_room (id),
                                                         FOREIGN KEY (cake_option1_id) REFERENCES cake_option1 (id),
                                                         FOREIGN KEY (cake_option2_id) REFERENCES cake_option2 (id),
                                                         FOREIGN KEY (cake_option3_id) REFERENCES cake_option3 (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_custom_order` (
                                                   `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                   `user_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                                   `cake_custom_order_sheet_id`	BIGINT UNSIGNED	        NOT NULL,
--  	`cake_option1_id`	            BIGINT UNSIGNED	        NOT NULL,
--  	`cake_option2_id`	            BIGINT UNSIGNED	        NOT NULL,
--  	`cake_option3_id`	            BIGINT UNSIGNED	        NULL DEFAULT NULL,
--  	`cake_custom_image`	            VARCHAR(100)	        NULL,
--  	`cake_custom_sketch`	        JSON	                NULL,           -- 'JSON 데이터 구조를 정의해 프론트와 통신 필요',
                                                   `payment_amount`	            INT	UNSIGNED            NOT NULL,   --        ,"주문진행중(결제완료된 주문을 승낙한 경우)","픽업완료","주문취소")
                                                   `order_status`	                VARCHAR(10)	            NOT NULL,   -- ENUM("신규주문(채팅에서 주문완료된 주문이 신규주문으로 들어옴)"
                                                   `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                   `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                   FOREIGN KEY (cake_custom_order_sheet_id) REFERENCES cake_custom_order_sheet (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `cake_design_order` (
                                                   `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                   `user_id`	                    BIGINT UNSIGNED     	NOT NULL,
                                                   `cake_option1_id`	            BIGINT UNSIGNED	        NOT NULL,
                                                   `cake_option2_id`	            BIGINT UNSIGNED	        NOT NULL,
                                                   `cake_option3_id`	            BIGINT UNSIGNED	        NULL DEFAULT NULL,
                                                   `requirements`	                VARCHAR(255)	        NULL,
                                                   `order_status`	                VARCHAR(20)	            NOT NULL,   -- ENUM("신규주문","주문진행중(결제완료된 주문을 승낙한 경우)","픽업완료","주문취소")
                                                   `payment_amount`	            INT	UNSIGNED            NOT NULL,
                                                   `purchase_confirmation_date`	DATETIME	            NULL,       -- 구매확정일
                                                   `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                   `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                                   FOREIGN KEY (cake_option1_id) REFERENCES cake_option1 (id),
                                                   FOREIGN KEY (cake_option2_id) REFERENCES cake_option2 (id),
                                                   FOREIGN KEY (cake_option3_id) REFERENCES cake_option3 (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `review` (
                                        `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        `user_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                        `store_id`	                    BIGINT UNSIGNED	        NOT NULL,
                                        `order_type`                    VARCHAR(10)        	    NOT NULL,       -- DESIGN or CUSTOM
                                        `order_id`	                    BIGINT UNSIGNED 	    NOT NULL,       -- not foreign_key, just id
                                        `content`	                    VARCHAR(255)	        NOT NULL,
                                        `score`	                        INT	UNSIGNED            NOT NULL,       -- 1~5
                                        `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                        `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL,

                                        FOREIGN KEY (user_id) REFERENCES user (id),
                                        FOREIGN KEY (store_id) REFERENCES store (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `push_notification` (
                                                   `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                   `user_id`                       BIGINT UNSIGNED	        NOT NULL,
                                                   `order_type`                    VARCHAR(10)        	    NOT NULL,       -- DESIGN or CUSTOM
                                                   `order_id`	                    BIGINT UNSIGNED 	    NOT NULL,       -- not foreign_key, just id
                                                   `message`	                    VARCHAR(255)	        NULL,
                                                   `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                                   `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `phone_auth` (
                                            `id`	                        BIGINT UNSIGNED         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                            `phone`                     	VARCHAR(20)	            NOT NULL,
                                            `auth_token`	                VARCHAR(255)	        NOT NULL,
                                            `created_at`	                TIMESTAMP DEFAULT CURRENT_TIMESTAMP                                   NOT NULL,
                                            `modified_at`                   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
