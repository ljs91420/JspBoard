const goodBtn = document.getElementById("good-btn");
const badBtn = document.getElementById("bad-btn");

goodBtn.addEventListener("click", (e) => {
	location.href = `./eval?board_id=${board_id}&pick=g`;
});

goodBtn.addEventListener("click", (e) => {
	location.href = `./eval?board_id=${board_id}&pick=b`;
});