/*
트랜잭션 : 둘 다 에러가 안 터졌을 때만 commit
        A은행                B은행
고객    금액                  금액
박도흠 100000  70000       10000   40000
        update문             update문

두 번의 업데이트가 중앙은행 기관 입장에서 하나의 작업
중앙은행 기관에서 A,B 둘다 업데이트 했을 때 성공했을 때 1 리턴
중앙은행에서 허가하면 실행

게시판 글을 삭제하기 위해서 댓글들을 삭제하고 나서 
게시판 글을 삭제하는 것. 이상 있을 시 rollback

작업마다 1~4작업 등...어디까지 했을 때 롤백할지, 전체 롤백할지 설정할 수 있음
*/


