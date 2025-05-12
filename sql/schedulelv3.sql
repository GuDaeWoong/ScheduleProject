-- 스케줄러 테이블
CREATE TABLE schedulelv3
(
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '사용자 id',
    authorId BIGINT NOT NULL COMMENT '사용자 id',
    title VARCHAR(100) NOT NULL COMMENT '제목',
    contents TEXT NOT NULL COMMENT '내용',
    createdDate DATE NOT NULL COMMENT '작성일',
    updatedDate DATE NOT NULL COMMENT '수정일',
    PRIMARY KEY (id),
    FOREIGN KEY (authorId) REFERENCES author(id)
);
