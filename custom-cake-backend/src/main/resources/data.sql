INSERT IGNORE INTO operator (id, email, password, name, phone, is_authenticated, last_conn_date)
VALUES (1, 'jjean0823@gmail.com', 'jjaen0823', '최재은', '010-4663-3422', 1, '2022-03-30 15:01:00'),
       (2, 'jjean2@gmail.com', 'jjaen2', '최재은2', '010-4663-3423', 1, '2022-04-04 15:01:00');

INSERT IGNORE INTO user (id, name, nickname, phone, social_type, social_account_id, is_authenticated, last_conn_datetime)
VALUES (1, '김영기', 'kimyoungi99', '010-0000-0000', 'KAKAO', '1', 1, '2022-03-30 15:01:00'),
       (2, '황서진', 'swiftie1230', '010-8991-2306', 'KAKAO', '2', 1, '2022-03-30 15:02:00');

INSERT IGNORE INTO store (id, operator_id, business_registration_no, representative_name, zip_code, base_address, detail_address, region,
    phone, name, description, open_time, reservation_period, reservation_per_period_count, thumbnail_image_url, view_count, rating_sum)
VALUES (1, 1, '5740901522', '신혜수', '07006', '서울 동작구 사당로 271', '2층', '성북구', NULL , '레이네 케이크', '레이네 케이크 주문 채널입니다 :) \n 디자인 케이크는 물론 도시락 케이크도 판매하고 레터링 케이크 클래스도 운영하고 있습니다.',
        '{\"TUE\": \"13:00~22:00\", \"WED\": \"13:00~22:00\", \"THR\": \"13:00~22:00\", \"FRI\": \"13:00~22:00\", \"SAT\": \"12:00~19:00\"}',
        30, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png', 0, 0);

INSERT IGNORE INTO store_image (id, store_id, url, is_thumbnail)
VALUES (1, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png', 1),
       (2, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_1.jpeg', 0),
       (3, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_2.jpeg', 0),
       (4, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_3.jpeg', 0);

INSERT IGNORE INTO dayoff (id, store_id, dayoff_type, dayoff_day, dayoff_date)
VALUES (1, 1, 'FIXED', 'SUN', NULL),
       (2, 1, 'FIXED', 'MON', NULL),
       (3, 1, 'DESIGNATED', NULL, '2023-04-11'),
       (4, 1, 'DESIGNATED', NULL, '2023-04-25');

INSERT IGNORE INTO cake_item (id, store_id, name, category, description, thumbnail_image_url, is_onsale, is_deleted, price, view_count, order_count)
VALUES (1, 1, '카모마일 & 작약 케이크', 'DESIGN_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_1.jpeg',
    1, 0, 50000, 0, 0),
    (2, 1, '블루 계란꽃 케이크', 'DESIGN_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_2.jpeg',
    1, 0, 50000, 0, 0),
    (3, 1, '도시락 케이크', 'LUNCH_BOX_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_3.jpeg',
    1, 0, 22000, 0, 0);

INSERT IGNORE INTO cake_item_image (id, cake_item_id, url, is_thumbnail)
VALUES (1, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_1.jpeg', 1),
       (2, 2, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_2.jpeg', 1),
       (3, 3, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_3.jpeg', 1);

INSERT IGNORE INTO inquiry (id, user_id, store_id, title, content, is_answered, answer)
VALUES (1, 1, 1, '당일 케이크 문의', '당일 케이크는 언제 구매가 가능한가요?', 1, '목, 금, 토요일에 가능합니다 :)');

INSERT IGNORE INTO cake_option1 (id, store_id, cake_shape, cake_size, lettering_limit, cake_layer, price, is_used, is_deleted)
VALUES (1, 1, 'CIRCLE', 'NO_0', '10', 'LAYER_1', 0, 1, 0),
       (2, 1, 'HEART', 'NO_0', '10', 'LAYER_1', 0, 1, 0),
       (3, 1, 'CIRCLE', 'NO_1', '10', 'LAYER_1', 0, 1, 0),
       (4, 1, 'CIRCLE', 'NO_2', '15', 'LAYER_1', 10000, 1, 0);

INSERT IGNORE INTO cake_option2 (id, store_id, cake_sheet, cake_inner_cream, cake_outer_cream, price, is_used, is_deleted)
VALUES (1, 1, 'BANILLA_SHEET', 'MILK', 'CREAMCHEESE', 0, 1, 0),
       (2, 1, 'CHOCO_SHEET', 'MILK', 'CREAMCHEESE', 2000, 1, 0),
       (3, 1, 'BANILLA_SHEET', 'CHOCO', 'CREAMCHEESE', 3000, 1, 0),
       (4, 1, 'BANILLA_SHEET', 'STRAWBERRY', 'CREAMCHEESE', 3000, 1, 0);

INSERT IGNORE INTO cake_option3 (id, store_id, name, price, is_used, is_deleted)
VALUES (1, 1, '판 문구(15자 이내)', 1000, 1, 0),
       (2, 1, '바탕색', 2000, 1, 0),
       (3, 1, '터치, 그라데이션(색상당)', 1000, 1, 0),
       (4, 1, '꼭지체리(6ea)', 1000, 1, 0),
       (5, 1, '제철과일', 2000, 1, 0),
       (6, 1, '오레오', 1000, 1, 0),
       (7, 1, '아이스팩', 3000, 1, 0);

INSERT IGNORE INTO option_by_cake (id, cake_item_id, cake_option_type, cake_option_id, cake_option_value, price, is_used)
VALUES (1, 1, 1, 3, '원형, 1호, 1단, 레터링 10글자 제한', 0, 1),
       (2, 1, 1, 4, '원형, 2호, 1단, 레터링 15글자 제한', 11000, 1),
       (3, 1, 2, 1, '바닐라시트, 우유생크림, 크림치즈', 0, 1),
       (4, 1, 2, 2, '초코시트, 우유생크림, 크림치즈', 2000, 1),
       (5, 1, 2, 3, '바닐라시트, 초코크림, 크림치즈', 3000, 1),
       (6, 1, 2, 4, '바닐라시트, 딸기생크림, 크림치즈', 3000, 1),
       (7, 1, 3, 7, '아이스팩', 3000, 1),
       (8, 2, 1, 3, '원형, 1호, 1단, 레터링 10글자 제한', 0, 1),
       (9, 2, 1, 4, '원형, 2호, 1단, 레터링 15글자 제한', 11000, 1),
       (10, 2, 2, 1, '바닐라시트, 우유생크림, 크림치즈', 0, 1),
       (11, 2, 2, 2, '초코시트, 우유생크림, 크림치즈', 2000, 1),
       (12, 2, 2, 3, '바닐라시트, 초코크림, 크림치즈', 3000, 1),
       (13, 2, 2, 4, '바닐라시트, 딸기생크림, 크림치즈', 3000, 1),
       (14, 2, 3, 7, '아이스팩', 3000, 1),
       (15, 3, 1, 1, '원형, 도시락, 1단, 레터링 10글자 제한', 0, 1),
       (16, 3, 1, 2, '하트, 도시락, 1단, 레터링 10글자 제한', 11000, 1),
       (17, 3, 2, 1, '바닐라시트, 우유생크림, 크림치즈', 0, 1),
       (18, 3, 2, 2, '초코시트, 우유생크림, 크림치즈', 2000, 1),
       (19, 3, 2, 3, '바닐라시트, 초코크림, 크림치즈', 3000, 1),
       (20, 3, 2, 4, '바닐라시트, 딸기생크림, 크림치즈', 3000, 1),
       (21, 3, 3, 7, '아이스팩', 3000, 1);

INSERT IGNORE INTO store_notification (id, store_id, title, content)
VALUES (1, 1, '레이네 케이크 공지', '[4~5월 디자인케이크 클래스]

<총 2주, 매주 월요일 12:00~15:00(최대16:00)>
* 1차 (4월 10일 / 4월 17일)  <마감임박>
* 2차 (4월 24일 / 5월 1일)   <마감임박>
* 3차 (5월 22일 / 5월 29일)

📌 수강대상 : 케이크 샌딩과 아이싱 가능하신 분 or 기존 케이크 매장 영업자
* 아이싱이 완벽하지 않으시더라도 기본 아이싱이 가능하다는 전제 하에 수업을 진행합니다.
* 이 수업을 통해 여러분들의 케이크 작업과 디자인이 다양한 스타일로 확대 시킬 수 있는 발판이 되었으면 합니다 ! :)

📌.레이네케이크 유화케이크 클래스는
  정형화된 케이크 기법이 아닌 다양한 요소와 그림들을 케이크 위에 자유롭게 표현하는 수업입니다.
  특히 자연물의 형태를 분석하고 그에 맞는 기법을 배워보는 수업입니다.');
# 3, 2, 7
INSERT IGNORE INTO cake_design_order (id, user_id, cake_item_id, option_by_cake_id_list, requirements, order_status,
                                      payment_amount, purchase_confirmation_date)
VALUES (1, 1, 1, '[1,4,9]','레터링 문구 Happy Birthday!로 부탁드립니다.', 'PICK_UP', 55000, '2023-04-11 15:02:00');


INSERT IGNORE INTO review (id, user_id, store_id, order_type, order_id, order_options_info, content, score)
VALUES (1, 1, 1, 'DESIGN', 1, '{\"option1\":\"원형, 1호, 1단, 레터링 10글자 제한\",\"option2\":\"초코시트, 우유생크림, 크림치즈\",\"option3\":\"아이스팩\"}',
        '사장님 너무 친절하시고 좋아요!', 5)