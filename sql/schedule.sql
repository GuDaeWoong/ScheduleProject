use schedule;

CREATE TABLE schedule
(
    id       BIGINT       AUTO_INCREMENT PRIMARY KEY COMMENT '메모 식별자',
    creator  VARCHAR(100) NOT NULL COMMENT '작성자',
    password    VARCHAR(100) NOT NULL COMMENT '비밀번호',
    title    VARCHAR(100) NOT NULL COMMENT '제목',
    contents TEXT COMMENT '내용',
    updatedDate  DATE COMMENT '작성일'
);