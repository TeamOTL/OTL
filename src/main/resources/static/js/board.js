$(document).ready(function() {
    // 검색 버튼 클릭 이벤트
    $('#searchBtn').click(function() {
        // 검색어와 검색 타입을 가져옵니다.
        let keyword = $('input[name="keyword"]').val();
        let type = $('select[name="type"]').val();

        console.log(keyword, type);

        // AJAX 요청을 보냅니다.
        $.ajax({
            type: "GET", // GET 방식 요청
            url: "/board", // 요청할 URL
            data: { // 요청에 포함할 데이터
                "type": type, // 검색 타입
                "keyword": keyword // 검색어
            },
            success: function(response) { // 성공했을 때 실행될 콜백 함수
                console.log("게시글을 검색 중입니다.");

                console.log(response);

                // 성공한 경우, #boardSearch 부분만 업데이트
                let contentWrapper = $(response).find('#boardSearch').html();
                $('#boardSearch').html(contentWrapper);

                // // 검색 결과가 있는지 확인
                // if ($(response).find('#boardTableBody').children().length === 0) {
                //     // 실패한 경우
                //     alert("검색 결과가 없습니다.");
                // } else {
                //     // 성공한 경우, #content-wrapper 부분만 업데이트
                //     let contentWrapper = $(response).find('#content-wrapper').html();
                //     $('#content-wrapper').html(contentWrapper);
                // }
            },
            error: function(xhr, status, error) { // 실패했을 때 실행될 콜백 함수
                console.error("검색 요청 실패:", error);
                console.error("상태 코드: ", status);
                console.error("XHR 객체: ", xhr);
            }
        });
    });
});
