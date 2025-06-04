------------------------- Ex02JDBCTemplate
DROP TABLE springboard;
DROP TABLE springboard_seq;

CREATE TABLE springboard(
    idx number primary key, 
	name varchar2(30) not null, 
	title varchar2(200) not null, 
	content varchar2(4000) not null, 
	postdate date default sysdate, 
	hit number default 0,
           bgroup number default 0,
          bstep number default 0,
          bindent number default 0,
	pass varchar2(30) 
);

create sequence springboard_seq
    increment by 1  -- 1씩 증가
    start with 1    -- 시작값 1
    minvalue 1      -- 최소값 1
    nomaxvalue      -- 최대값은 무한대
    nocycle         -- 순환하지 않음.
    nocache;   

INSERT INTO springboard VALUES (springboard_seq.nextval, '잡코리아1', 
    '스프링게시판 첫번째 입니다.', '내용입니다.', sysdate, 0,
    springboard_seq.nextval, 0, 0, '1234');
INSERT INTO springboard VALUES (springboard_seq.nextval, '잡코리아2', 
    '스프링게시판 두번째 입니다.', '내용입니다.', sysdate, 0,
    springboard_seq.nextval, 0, 0, '1234');
INSERT INTO springboard VALUES (springboard_seq.nextval, '잡코리아3', 
    '스프링게시판 세번째 입니다.', '내용입니다.', sysdate, 0,
    springboard_seq.nextval, 0, 0, '1234');
INSERT INTO springboard VALUES (springboard_seq.nextval, '잡코리아4', 
    '스프링게시판 네번째 입니다.', '내용입니다.', sysdate, 0,
    springboard_seq.nextval, 0, 0, '1234');
INSERT INTO springboard VALUES (springboard_seq.nextval, '잡코리아5', 
    '스프링게시판 다섯번째 입니다.', '내용입니다.', sysdate, 0,
    springboard_seq.nextval, 0, 0, '1234');
    
SELECT * FROM springboard;

commit;

