<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
		<div class="msg">${msg}</div>
		<form id="frm" class="frm" action="/user/join" method="post">
			<div id="idChkResult" class="msg"></div>
			<div>
				<input type="text" name="user_id" placeholder="아이디">
				<button type="button" onclick="chkId()">아이디 중복체크</button>
			</div>
			<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
			<div><input type="password" name="user_pwre" placeholder="비밀번호 확인"></div>
			<div><input type="text" name="nm" placeholder="이름"></div>
			<div><input type="submit" value="회원가입"></div>
		</form>
		<div><a href="/user/login">로그인</a></div>
	</div>
	<!-- 비동기 통신을 위한 라이브러리 import -->
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script type="text/javascript">
		function chkId() {
			const user_id = frm.user_id.value
			axios.get('/user/ajaxIdchk',{
			/*axios.post('user/ajaxIdChk',{'user_id'}).then)로 날려야됨*/
				params: {
					user_id // == 'user_id' : user_id
				}
			}).then(function(res){
				console.log(res)
				if(res.data.result == 2){
					idChkResult.innerText = '사용할 수 있는 아이디입니다.'
					alert('사용 가능합니다')
				}else if(res.data.result == 3){
					idChkResult.innerText = '이미 사용중입니다.'
					alert('이미 사용중입니다.')
				}
			})
		}
	</script>
</div>