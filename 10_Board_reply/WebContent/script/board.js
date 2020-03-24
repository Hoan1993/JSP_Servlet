function joinCheck() {
	if(document.frm.writer.value.length == 0) {
		alert("작성자를 써주세요");
		frm.writer.focus();
		return false;
	}
	if(document.frm.title.value == "") {
		alert("글제목을 반드시 입력해야 합니다.");
		frm.title.focus();
		return false;
	}
	if(document.frm.content.value == "") {
		alert("글내용을 반드시 입력해야 합니다.");
		frm.content.focus();
		return false;
	}
	if(document.frm.pwd.value == "") {
		alert("암호를 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	return true;
}
