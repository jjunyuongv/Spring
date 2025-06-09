create table myboard(
    idx number primary key,
    id varchar2(50) not null, 
    name varchar2(30) not null,
    contents varchar2(4000)
);
--  시퀀스 생성
create sequence myboard_seq
    increment by 1  -- 1씩 증가
    start with 1    -- 시작값 1
    minvalue 1      -- 최소값 1
    nomaxvalue      -- 최대값은 무한대
    nocycle         -- 순환하지 않음.
    nocache;   

--더미 데이터 입력
insert into myboard values (myboard_seq.nextval, 'korea', '코리아', '방명록1 게시판 입니다.');
insert into myboard values (myboard_seq.nextval, 'korea', '고스톱', '방명록2 게시판 입니다.');
insert into myboard values (myboard_seq.nextval, 'korea', '기즈모', '방명록3 게시판 입니다.');
insert into myboard values (myboard_seq.nextval, 'korea', '스모', '방명록4 게시판 입니다.');

commit;
select * from myboard;

