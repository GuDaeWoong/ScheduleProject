-- 작성자 테이블
CREATE TABLE author (
                        id BIGINT NOT NULL AUTO_INCREMENT COMMENT '작성자 id',
                        name VARCHAR(100) NOT NULL COMMENT '작성자 이름',
                        password VARCHAR(100) NOT NULL COMMENT '작성자 비밀번호',
                        email VARCHAR(100) NOT NULL COMMENT '작성자 이메일',
                        createdDate DATE NOT NULL COMMENT '작성일',
                        updatedDate DATE NOT NULL COMMENT '수정일',
                        PRIMARY KEY (id)
);