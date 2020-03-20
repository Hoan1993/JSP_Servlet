-- board1 테이블 생성

create table board1 {
	board_no number(5) primary key,		-- 게시물 글번호
	board_writer varchar2(20) not null,	-- 게시물 작성자
	board_title varchar2(20) not null,	-- 게시물 제목
	board_cont varchar2(1000),			-- 게시물 내용
	board_pwd varchar2(20) not null,	-- 게시물 비밀번호
	board_hit number(5) default 0,		-- 게시물 조회수
	board_regdate date					-- 게시물 등록날짜
};

-- board_no 칼럼에 대한 시퀀스 생성
create sequence board1_seq 
start with 0
increment by 1
nocache;

-- board1 테이블에 레코드 추가
insert into board values(board1_seq.nextval, '홍길동', '글제목1', '글내용1', '1111', default, sysdate);
insert into board values(board1_seq.nextval, '이순신', '글제목2', '글내용2', '1112', default, sysdate);
insert into board values(board1_seq.nextval, '유관순', '글제목3', '글내용3', '1113', default, sysdate);
insert into board values(board1_seq.nextval, '김유신', '글제목4', '글내용4', '1114', default, sysdate);
insert into board values(board1_seq.nextval, '김연아', '글제목5', '글내용5', '1115', default, sysdate);

