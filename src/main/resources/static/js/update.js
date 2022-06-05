// (1) 회원정보 수정
function update(userId) {
    let data = $("#profileUpdate").serialize();
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(response => { // 200
        console.log("update 성공", response);
        location.href = `/user/${userId}`;
    }).fail(error => { // 400
        if (error.responseJSON.data != null) {
            alert(JSON.stringify(error.responseJSON.data));
        }
        console.log("update 실패", error);
    });
}