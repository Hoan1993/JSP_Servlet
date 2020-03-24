-- jsp_member 테이블 생성

create table jsp_member(
	member_id varchar2(20) primary key,
	member_pwd varchar2(20) not null,
	member_name varchar2(30) not null,
	member_nickname varchar2(50) not null,
	member_zip1 varchar2(3) not null,	-- 우편번호(앞 3자리)
	member_zip2 varchar2(3) not null,
	member_addr1 varchar2(100) not null,	-- 주소1
	member_addr2 varchar2(100) not null,	-- 주소2
	member_regdate date,			-- 회원 가입일
	member_state number(1),			-- 회원 상태(1,2)
	member_delcont varchar2(1000),	-- 회원 탈퇴 사유
	member_deldate date				-- 회원 탈퇴일
);

insert into jsp_member(member_id, member_pwd, member_name, member_nickname, member_zip1, member_zip2,
    member_addr1, member_addr2, member_regdate, member_state) values('hong1', '1234', '홍길동', '홍홍', '123', '456', '서울시', '마포구', sysdate, 1);

insert into jsp_member(member_id, member_pwd, member_name, member_nickname, member_zip1, member_zip2,
    member_addr1, member_addr2, member_regdate, member_state) values('hong2', '1234', '홍길동2', '홍홍', '123', '456', '서울시', '마포구', sysdate, 1);
    
insert into jsp_member(member_id, member_pwd, member_name, member_nickname, member_zip1, member_zip2,
    member_addr1, member_addr2, member_regdate, member_state) values('hong3', '1234', '홍길동3', '홍홍', '123', '456', '서울시', '마포구', sysdate, 1);  