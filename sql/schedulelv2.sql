use schedulelv2;

CREATE TABLE schedulelv2
(
    id       BIGINT       AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    creator  VARCHAR(100) NOT NULL COMMENT '작성자',
    password    VARCHAR(100) NOT NULL COMMENT '비밀번호',
    title    VARCHAR(100) NOT NULL COMMENT '제목',
    contents TEXT COMMENT '내용',
    createdDate  DATE COMMENT '작성일',
    updatedDate  DATE COMMENT '수정일'
);