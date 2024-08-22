const goodBtn = document.getElementById("good-btn");
const badBtn = document.getElementById("bad-btn");
const modifyPassword = document.getElementById("modify-password");
const modifyBtn = document.getElementById("modify-btn");

goodBtn.addEventListener("click", (e) => {
	location.href = `./eval?board_id=${board_id}&pick=g`;
});

badBtn.addEventListener("click", (e) => {
	location.href = `./eval?board_id=${board_id}&pick=b`;
});

if (modifyPassword)

modifyBtn.addEventListener("click", (e) => {
	location.href = `./modify`
});