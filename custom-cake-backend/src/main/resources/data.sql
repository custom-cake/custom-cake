INSERT IGNORE INTO operator (id, email, password, name, phone, is_authenticated, last_conn_date)
VALUES (1, 'jjaen0823@gmail.com', 'jjaen0823', '최재은', '010-4663-3422', 1, '2022-03-30 15:01:00'),
       (2, 'jungme@gmail.com', 'jungme', '권정미', '0507-1470-0981', 1, '2022-04-04 15:01:00');

INSERT IGNORE INTO user (id, name, nickname, phone, social_type, social_account_id, is_authenticated, last_conn_datetime)
VALUES (1, '김영기', 'kimyoungi99', '010-0000-0000', 'KAKAO', '1', 1, '2022-03-30 15:01:00'),
       (2, '황서진', 'swiftie1230', '010-8991-2306', 'KAKAO', '2', 1, '2022-03-30 15:02:00');

INSERT IGNORE INTO store (id, operator_id, business_registration_no, representative_name, zip_code, base_address, detail_address, region,
    phone, name, description, open_time, reservation_period, reservation_per_period_count, thumbnail_image_url, view_count, rating_sum)
VALUES (1, 1, '5740901522', '신혜수', '07006', '서울 동작구 사당로 271', '2층', '성북구', NULL , '레이네 케이크', '레이네 케이크 주문 채널입니다 :) \n 디자인 케이크는 물론 도시락 케이크도 판매하고 레터링 케이크 클래스도 운영하고 있습니다.',
        '{\"TUE\": \"13:00~22:00\", \"WED\": \"13:00~22:00\", \"THR\": \"13:00~22:00\", \"FRI\": \"13:00~22:00\", \"SAT\": \"12:00~19:00\"}',
        30, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png', 0, 5),
    (2, 2, '1208147521', '권정미', '08289', '서울 구로구 새말로 102', '신도림포스빌 지하1층 B123-2호', '구로구', '0507-1470-0981', '플랑케이크',
     '플랑케이크와 함께 :) 생크림케이크 맛집 신도림케이크/영등포케이크/구로케이크/양천케이크',
     '{\"MON\": \"12:00~19:30\", \"TUE\": \"12:00~19:30\", \"WED\": \"12:00~19:30\", \"THR\": \"12:00~19:30\", \"FRI\": \"12:00~19:30\", \"SAT\": \"12:00~17:00\", \"SUN\": \"12:00~17:00\"}',
     30, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_2/flan_cake_store_image.jpeg', 0, 0);

INSERT IGNORE INTO store_image (id, store_id, url, is_thumbnail)
VALUES (1, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_thumbnail_image.png', 1),
       (2, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_1.jpeg', 0),
       (3, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_2.jpeg', 0),
       (4, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_1/reine_cake_image_3.jpeg', 0),
       (5, 2, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/store-image/store_2/flan_cake_store_image.jpeg', 1);

INSERT IGNORE INTO dayoff (id, store_id, dayoff_type, dayoff_day, dayoff_date)
VALUES (1, 1, 'FIXED', 'SUN', NULL),
       (2, 1, 'FIXED', 'MON', NULL),
       (3, 1, 'DESIGNATED', NULL, '2023-04-11'),
       (4, 1, 'DESIGNATED', NULL, '2023-04-25'),
       (5, 2, 'FIXED', 'SUN', NULL),
       (6, 2, 'FIXED', 'MON', NULL);

INSERT IGNORE INTO cake_item (id, store_id, name, category, description, thumbnail_image_url, is_onsale, is_deleted, price, view_count, order_count)
VALUES (1, 1, '카모마일 & 작약 케이크', 'DESIGN_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_1.jpeg',
    1, 0, 50000, 1, 1),
    (2, 1, '블루 계란꽃 케이크', 'DESIGN_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_2.jpeg',
    1, 0, 50000, 0, 0),
    (3, 1, '도시락 케이크', 'LUNCH_BOX_CAKE', NULL, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_3.jpeg',
    1, 0, 22000, 0, 0),
    (4, 2, '2023 어버이날 케이크', 'DESIGN_CAKE', '어버이날 케이크는 고정디자인으로 운영됩니다. 한정수량으로 조기 소진 될 수 있습니다!', 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_2/flan_cake_item_image_1.jpeg',
     1, 0, 43000, 0, 0);

INSERT IGNORE INTO cake_item_image (id, cake_item_id, url, is_thumbnail)
VALUES (1, 1, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_1.jpeg', 1),
       (2, 2, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_2.jpeg', 1),
       (3, 3, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_1/cake_item_1/reine_cake_item_image_3.jpeg', 1),
       (4, 4, 'https://custom-cake.s3.ap-northeast-2.amazonaws.com/cake-item-image/store_2/flan_cake_item_image_1.jpeg', 1);

INSERT IGNORE INTO inquiry (id, user_id, store_id, title, content, is_answered, answer)
VALUES (1, 1, 1, '당일 케이크 문의', '당일 케이크는 언제 구매가 가능한가요?', 1, '목, 금, 토요일에 가능합니다 :)');

INSERT IGNORE INTO cake_option1 (id, store_id, cake_shape, cake_size, lettering_limit, cake_layer, price, is_used, is_deleted)
VALUES (1, 1, 'CIRCLE', 'NO_0', '10', 'LAYER_1', 0, 1, 0),
       (2, 1, 'HEART', 'NO_0', '10', 'LAYER_1', 0, 1, 0),
       (3, 1, 'CIRCLE', 'NO_1', '10', 'LAYER_1', 0, 1, 0),
       (4, 1, 'CIRCLE', 'NO_2', '15', 'LAYER_1', 10000, 1, 0),
       (5, 2, 'CIRCLE', 'NO_0', '10', 'LAYER_1', 0, 1, 0),
       (6, 2, 'CIRCLE', 'NO_1', '15', 'LAYER_1', 10000, 1, 0),
       (7, 2, 'CIRCLE', 'NO_2', '20', 'LAYER_1', 15000, 1, 0);

INSERT IGNORE INTO cake_option2 (id, store_id, cake_sheet, cake_inner_cream, cake_outer_cream, price, is_used, is_deleted)
VALUES (1, 1, 'BANILLA_SHEET', 'MILK', 'CREAMCHEESE', 0, 1, 0),
       (2, 1, 'CHOCO_SHEET', 'MILK', 'CREAMCHEESE', 2000, 1, 0),
       (3, 1, 'BANILLA_SHEET', 'CHOCO', 'CREAMCHEESE', 3000, 1, 0),
       (4, 1, 'BANILLA_SHEET', 'STRAWBERRY', 'CREAMCHEESE', 3000, 1, 0),
       (5, 2, 'BANILLA_SHEET', 'MILK', 'MILK', 0, 1, 0),
       (6, 2, 'BANILLA_SHEET', 'MILK', 'CREAMCHEESE', 0, 1, 0),
       (7, 2, 'CHOCO_SHEET', 'CHOCO', 'OREO', 3000, 1, 0);

INSERT IGNORE INTO cake_option3 (id, store_id, name, price, is_used, is_deleted)
VALUES (1, 1, '판 문구(15자 이내)', 1000, 1, 0),
       (2, 1, '바탕색', 2000, 1, 0),
       (3, 1, '터치, 그라데이션(색상당)', 1000, 1, 0),
       (4, 1, '꼭지체리(6ea)', 1000, 1, 0),
       (5, 1, '제철과일', 2000, 1, 0),
       (6, 1, '오레오', 1000, 1, 0),
       (7, 1, '아이스팩', 3000, 1, 0),
       (8, 2, '제철과일', 6000, 1, 0),
       (9, 2, '아이스팩', 3000, 1, 0);

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
       (21, 3, 3, 7, '아이스팩', 3000, 1),
       (22, 4, 1, 5, '원형, 도시락, 1단, 레터링 10자 제한', 0, 1),
       (23, 4, 1, 6, '원형, 1호, 1단, 레터링 15자 제한', 3000, 1),
       (24, 4, 1, 7, '원형, 1호, 1단, 레터링 20자 제한', 11000, 1),
       (25, 4, 2, 5, 'BANILLA_SHEET, MILK, MILK', 0, 1),
       (26, 4, 2, 6, 'BANILLA_SHEET, MILK, CREAMCHEESE', 0, 1),
       (27, 4, 2, 7, 'CHOCO_SHEET, CHOCO, OREO', 3000, 1)
;

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
  특히 자연물의 형태를 분석하고 그에 맞는 기법을 배워보는 수업입니다.'),
    (2, 2, '플랑케이크 유통기한 및 보관 공지입니다.', '❤ 플랑 케이크는 무방부제 수제 케이크 입니다❤
- 모든 케이크는 픽업당일 가장 맛있게 드실수 있도록 제작 됩니다.
- 픽업 일로부터 1~3일 까지드실수 있습니다.
- 과일이 들어간 케이크는 시간이 지나면 물이생기므로 당일이 가장 맛있습니다.
- 온도에 민감한 생크림 케이크 이므로 픽업이후 최대한 빠르게 냉장보관 해주세요.🧊

❤다회용 보냉백❤
우유도 시원해야 맛있듯, 생크림도 시원하게 드셔야 맛있습니다.
따듯한곳에 오래방치되면, 케이크 주저앉을수 있습니다.
장시간 이동시, 보냉백 권장드립니다.
(여름철 20분이내 냉장보관 필수 / 겨울철 40분이내 냉장보관 권장)
- 보냉가방+아이스팩2개 : 4,000원 / 3-5시간정도 냉기 유지
- 아이스팩만 2개 : 1,000원 /  1시간 정도 냉기유지'),
    (3, 2, '플랑케이크 환불 공지입니다.', '❤환불 규정❤

✔취소, 환불 수수료 발생합니다✔
- 당일~3일전 : 환불 불가
- 4~7일전 : 수수료 20% 발생 (80%환불)
- 8~일전 : 수수료 10% 발생 (90%환불)
*최소 3일전까지 주문 스캐줄 1회 변경 가능합니다.

*취소 수수료 발생될수 있으니, 일정 확인후 주문 부탁드립니다❤'),
    (4, 2, '플랑케이크 카드결제 및 현금영수증 공지입니다.','맞춤케이크는요, 커스텀 케이크 이므로
입금이 되셔야 예약이 확정되십니다😀
(1:1 맞춤이므로  주문한뒤, 당일 취소 방지입니다.)

❤카드결재❤
-먼저 예약을 위해 계좌이체를 해주세요
-픽업 당일 결재수단 카드로 변경한다고 말씀해주시면,
 카드결재후 계좌이체 도와드리겠습니다

❤현금영수증❤
-예약 전에 미리 말씀해주시면됩니다.
-매장방문시 현금영수증 신청했습니다 라고 말해주세요😊
(미리 준비해둘게요❤)')
    ;

INSERT IGNORE INTO store_gallery (id, store_id, image_url_list)
VALUES (1, 1, '["https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_1.jpeg",
                "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_2.jpeg",
                "https://custom-cake.s3.ap-northeast-2.amazonaws.com/gallery/store_1/reine_cake_item_image_3.jpeg"]');

INSERT IGNORE INTO cake_design_order (id, user_id, store_id, cake_item_id, option_by_cake_id_list, requirements, order_status,
                                      payment_amount, pickup_datetime, purchase_confirmation_date)
VALUES (1, 1, 1, 1, '[1,4,7]','레터링 문구 Happy Birthday!로 부탁드립니다.', 'PICK_UP', 55000, '2023-04-11 15:00:00' ,'2023-04-11 15:02:00');


INSERT IGNORE INTO review (id, user_id, store_id, order_type, order_id, order_options_info, content, score)
VALUES (1, 1, 1, 'DESIGN', 1, '{\"option1\":\"원형, 1호, 1단, 레터링 10글자 제한\",\"option2\":\"초코시트, 우유생크림, 크림치즈\",\"option3\":\"아이스팩\"}',
        '사장님 너무 친절하시고 좋아요!', 5);