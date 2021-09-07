-- main category
INSERT INTO interest_main_category(id, name) values (1, '프로그래밍');
INSERT INTO interest_main_category(id, name) values (2, '언어/외국어');
INSERT INTO interest_main_category(id, name) values (3, '면접/취업');
INSERT INTO interest_main_category(id, name) values (4, '자격증');
INSERT INTO interest_main_category(id, name) values (5, '기타');

--detail category
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('JavaScript', 1);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('Java', 1);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('React', 1);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('알고리즘', 1);

INSERT INTO interest_detail_category(name, interest_main_category_id) values ('영어', 2);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('일본어', 2);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('중국어', 2);

INSERT INTO interest_detail_category(name, interest_main_category_id) values ('NCS 면접', 3);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('공무원 면접', 3);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('마케팅 직군 면접', 3);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('IT 면접', 3);

INSERT INTO interest_detail_category(name, interest_main_category_id) values ('SQLD 자격증', 4);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('정보처리기사 자격증', 4);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('한국사능력검정시험', 4);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('운전면허 필기', 4);

INSERT INTO interest_detail_category(name, interest_main_category_id) values ('와인 공부하기', 5);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('뜨개질', 5);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('캘리그라피', 5);
INSERT INTO interest_detail_category(name, interest_main_category_id) values ('독서', 5);





