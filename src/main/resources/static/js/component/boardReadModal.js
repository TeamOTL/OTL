// 댓글 목록을 저장할 배열
let replyList = [];

class boardReadModal extends HTMLElement {
    /** - 작성자 : 유지오
     *
     * data-toggle="modal" data-target="#태그아이디"에서
     * data-target은 보여줄 태그 아이디입니다.
     *
     */
    connectedCallback() {
        const bno = this.getAttribute('board.bno');
        const boardTitle = this.getAttribute('board.boardTitle');
        const boardContent = this.getAttribute('board.boardContent');
        const nickname = this.getAttribute('board.nickname');
        const memberProfileImage = this.getAttribute('memberProfileImage');
        const regDate = this.getAttribute('board.regDate');
        const modDate = this.getAttribute('board.modDate');

        this.innerHTML = `
            <!-- The Modal -->
            <div class="modal fade modifyModal" id="boardReadModal-${bno}" data-backdrop="static">
                <div class="modal-dialog" style="max-width: 60%;">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title font-weight-bolder" style="color: #FD7B38;">자유게시판</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div>
                                <div class="modal-body" style="color: black;">
                                    <!-- 게시글 제목 -->
                                    <div class="board_title row mb-3">
                                        <span id="boardHeader">${bno}</span>
                                        <label  class="col-sm-3 col-form-label">제목</label>
                                        <div class="col-sm-8">
                                            <input id="boardTitle" type="text" class="form-control" placeholder="제목을 입력하세요"
                                            value="${boardTitle}" readonly>
                                        </div>
                                    </div>
                                    <!-- 작성자 프로필 이미지, 작성자, 작성일, 수정일-->
                                    <div class="board-container d-flex align-items-center justify-content-between"
                                         style="margin-bottom: 20px;">
                                        <!-- 작성자 프로필 이미지 -->
                                        <img id="memberProfileImage" src="${memberProfileImage}" alt="memberProfileImage" class="writer-container rounded-circle"
                                             style="width: 50px; height: 50px;">
                                        <!-- 작성자 -->
                                        <span id="nickname" class="board-container nickname">${nickname}</span>
                                        <!-- 작성일 -->
                                        <span id="regDate" class="board-container board_create_date">${regDate}</span>
                                        <!-- 수정일 -->
                                        <span id="modDate" class="board-container board_mod_date">${modDate}</span>
                                    </div>
                                    <!--게시판 주의사항-->
                                    <div class="board_warning rounded p-2" style="margin-bottom: 20px; background-color: silver;">
                                        <div class="writer-container text-center">
                                            <span class="font-weight-bolder">※ 회원 간 불쾌한 언급은 서비스 이용에 불이익을 받게 됩니다. ※</span>
                                        </div>
                                    </div>
                                    <!-- 게시글 내용 -->
                                    <div class="board_content row mb-3">
                                        <label class="col-sm-2.5 col-form-label">내용</label>
                                        <div class="col flex-grow-1">
                                               <textarea id="boardContent" type="text" class="form-control"
                                                         style="height: 30vh;" readonly>${boardContent}</textarea>
                                        </div>
                                    </div>

                                <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button id="boardModifyBtn" type="button" class="btn btn-success">수정하기</button>
                                    <button id="boardModifyFinishBtn" type="button" class="btn btn-primary" style="display: none;">저장하기</button>
                                    <button id="boardDeleteBtn" type="button" class="btn btn-danger" style="display: none;">삭제하기</button>
                                    <button id="boardCancelBtn" type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                                </div>
                                <!-- 댓글 -->
                                <div id="replyAll" class="modal-footer2">
                                        <div class="row mt-3">
                                            <!-- 댓글 리스트가 들어가는 곳 -->
                                            <div class="col-sm-13">
                                                <ul class="list-group replyList">
                                           
                                                </ul>
                                            </div>
                                            <!-- 댓글 작성 칸 -->
                                            <form id="replyForm-${bno}" action="/api/saveReply" method="POST">
                                                <div class="col-sm-13 d-flex align-items-start">
                                                    <input id="replyContent-${bno}" type="text" class="form-control mr-2" placeholder="댓글을 입력하세요">
                                                    <button type="submit" id="replysubmitBtn-${bno}" class="col-sm-2 btn btn-primary">댓글 작성</button>
                                                </div>
                                            </form>
                                        </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        `;

        // 1 : AJAX를 통해 서버로부터 memberProfileImage를 받아옴
        $.ajax({
            type: 'GET',
            url: '/api/readBoard?bno=' + bno,
            success: function (response) {
                // 서버에서 받은 응답을 처리하는 부분
                console.log("작성자 프로필 이미지: ", response);
                // 받은 데이터
                const memberProfileImage = response.memberProfileImage;

                const imgElement = document.createElement('img');
                imgElement.src = memberProfileImage;
                imgElement.alt = 'memberProfileImage';
                imgElement.className = 'writer-container rounded-circle'; // 클래스 추가
                imgElement.style.width = '50px'; // 너비 설정
                imgElement.style.height = '50px'; // 높이 설정

                // 이미 존재하는 memberProfileImage 요소를 찾아서 교체합니다.
                const memberProfileImageElement = document.getElementById('memberProfileImage');
                memberProfileImageElement.parentNode.replaceChild(imgElement, memberProfileImageElement);
            },
            error: function (xhr, status, error) {
                console.error("작성자 프로필 이미지를 불러오는데 실패했습니다: ", error);
                // 에러 처리 부분
            }
        });


        // 2 : 게시글 수정
        // id로 필요한 데이터, 버튼 가져오기
        const modifyBtn = this.querySelector("#boardModifyBtn");
        const boardTitleInput = this.querySelector("#boardTitle");
        const boardContentTextarea = this.querySelector("#boardContent");
        const modifyFinishBtn = this.querySelector("#boardModifyFinishBtn");
        const deleteBtn = this.querySelector("#boardDeleteBtn");
        const cancelBtn = this.querySelector("#boardCancelBtn");

        const email = this.getAttribute('data-email'); // 로그인한 사용자

        const replyAll = this.querySelector("#replyAll"); // 댓글 부분 전체

        // 수정하기 버튼 클릭 시
        modifyBtn.addEventListener("click", () => {

            const boardemail = this.getAttribute('board.email'); // 게시글 작성자

            if (boardemail !== email) {
                alert("게시글 작성자와 일치하지 않습니다.");
            } else {
                // 제목과 내용 입력란의 readOnly 속성 제거
                boardTitleInput.removeAttribute("readonly");
                boardContentTextarea.removeAttribute("readonly");

                // 수정하기 버튼 비활성화
                modifyBtn.style.display = "none";

                // 저장하기 버튼과 삭제하기 버튼 보이게 설정
                modifyFinishBtn.style.display = "inline-block";
                deleteBtn.style.display = "inline-block";

                // 댓글 비활성화
                replyAll.style.display = "none";
            }
        });

        // 저장하기 버튼 클릭 시
        const boardModifyFinishBtn = this.querySelector("#boardModifyFinishBtn");
        boardModifyFinishBtn.addEventListener("click", (event) => {
            event.preventDefault(); // 폼의 기본 제출을 방지
            const modifiedTitle = boardTitleInput.value; // 기존 boardTitle 값 불러오기
            const modifiedContent = boardContentTextarea.value; // 기존 boardContent 값 불러오기

            // 수정된 데이터를 객체에 담기
            const modifyData = {
                bno: bno,
                boardTitle: modifiedTitle,
                boardContent: modifiedContent
            };

            console.log("수정 데이터: ", modifyData); // 수정 데이터 확인

            if (modifyData.boardTitle === '' || modifyData.boardContent === '') {
                alert("제목과 내용을 입력해주세요.");
            } else {
                // AJAX를 통해 수정 내용을 서버로 전송
                $.ajax({
                    type: 'POST', // 수정된 내용을 서버로 전송하는 POST 요청
                    url: '/api/modifyBoard',
                    contentType: 'application/json',
                    data: JSON.stringify(modifyData), // 객체를 JSON 문자열로 변환하여 전송
                    success: function (response) {
                        // 서버에서 받은 응답을 처리하는 부분
                        console.log("수정 내용이 성공적으로 저장되었습니다.");
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error("수정 내용을 저장하는데 실패했습니다: ", error);
                        // 에러 처리 부분
                    }
                });
            }
        });

        // 3: 게시글 삭제
        // 삭제하기 버튼 클릭 시
        const boardDeleteBtn = this.querySelector("#boardDeleteBtn");
        boardDeleteBtn.addEventListener("click", () => {

            // 삭제할 데이터를 객체에 담기
            const deleteData = {
                bno: bno
            };

            // 사용자에게 확인 메시지 표시
            const confirmDelete = confirm("정말로 삭제하시겠습니까?");

            console.log("게시글이 삭제될 예정입니다.");

            if (confirmDelete) {

                // 확인 버튼을 눌렀을 때
                // AJAX를 통해 게시글을 서버에서 삭제
                $.ajax({
                    type: 'POST',
                    url: '/api/deleteBoard',
                    contentType: 'application/json', // 데이터 형식을 JSON으로 설정
                    data: JSON.stringify(deleteData), // JSON 형식으로 데이터 전송
                    success: function (response) {
                        alert("삭제가 완료되었습니다.");
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error("게시글 삭제 중 에러가 발생했습니다: ", error);
                        console.error("상태 코드: ", status);
                        console.error("XHR 객체: ", xhr);
                    }
                });
            } else {
                // 취소 버튼을 눌렀을 때
                alert("삭제가 취소되었습니다.");
            }
        });


        // 4. 취소 버튼 클릭 시
        cancelBtn.addEventListener("click", () => {
            // 수정하기 버튼 활성화
            modifyBtn.style.display = "inline-block";

            // 댓글 활성화
            replyAll.style.display = "inline-block";

            // 저장하기 버튼과 삭제하기 버튼 숨기기
            modifyFinishBtn.style.display = "none";
            deleteBtn.style.display = "none";

            $('#boardReadModal-' + bno).modal('hide');
            location.reload();
        });

        // 5. 댓글 작성
        const replyForms = this.querySelectorAll("[id^='replyForm']");

        replyForms.forEach(replyForm => {
            // 이벤트 리스너 추가
            replyForm.addEventListener('submit', handleSubmit);
        });

        function handleSubmit(event) {
            event.preventDefault(); // 폼의 기본 제출을 방지

            const replyForm = event.currentTarget; // 현재 이벤트가 발생한 폼
            const bno = replyForm.getAttribute('id').split('-')[1]; // 게시글 번호 가져오기
            const replyContent = replyForm.querySelector(`#replyContent-${bno}`).value;

            if(replyContent === ''){
                alert("댓글 내용을 입력해주세요.");
            } else {
                // 댓글로 등록할 데이터를 객체에 담기
                const replyData = {
                    bno: bno,
                    replyContent: replyContent,
                    email: email // email 변수는 어디서 가져오는지 확인 필요
                };

                console.log("댓글 데이터: ", replyData); // 댓글 데이터 확인

                $.ajax({
                    type: 'POST',
                    url: '/api/saveReply',
                    data: JSON.stringify(replyData),
                    contentType: 'application/json',
                    success: function (response) {
                        console.log("AJAX 요청 성공: ", response); // AJAX 요청 성공 확인
                        alert('댓글이 성공적으로 저장되었습니다.');
                        $('#replyContent-' + bno).val('');
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error("AJAX 요청 실패: ", error); // AJAX 요청 실패 확인
                        alert('댓글 저장에 실패했습니다: ' + error);
                    }
                });
            }
        }

        // 6. 댓글 목록/삭제
        const replyListElement = this.querySelector(".replyList");

        // 기존의 댓글 목록을 비움
        replyListElement.innerHTML = '';

        // AJAX를 통해 새로운 댓글 목록을 받아와 화면에 표시
        $.ajax({
            type: 'GET',
            url: '/api/replyList',
            data: {bno: bno, page: 0, size: 10}, // 서버로 보낼 데이터 (게시물 번호와 페이지 정보)
            contentType: 'application/json',
            success: function (response) {
                console.log("댓글 목록:", response); // 받아온 댓글 목록 출력

                // 받아온 댓글 목록을 화면에 표시하는 코드
                for (let i = 0; i < response.content.length; i++) {
                    const reply = response.content[i];
                    const replyItem = document.createElement("li");
                    replyItem.classList.add("list-group-item");

                    // 새로운 행을 생성
                    const newRow = document.createElement("tr");

                    // 댓글 번호 열을 추가
                    const replyNoCell = document.createElement("td");
                    replyNoCell.textContent = reply.replyNo;
                    // replyNoCell.textContent = "1";
                    replyNoCell.style.width = "10%";
                    newRow.appendChild(replyNoCell);

                    // 작성자의 닉네임 열을 추가
                    const nicknameCell = document.createElement("td");
                    nicknameCell.textContent = reply.nickname;
                    nicknameCell.style.color = "#FD7B38";
                    nicknameCell.style.width = "20%";
                    newRow.appendChild(nicknameCell);

                    // 댓글 내용 열을 추가
                    const contentCell = document.createElement("td");
                    contentCell.textContent = reply.replyContent;

                    contentCell.style.width = "70%";
                    newRow.appendChild(contentCell);

                    // 댓글 삭제 버튼 열을 추가
                    const deleteCell = document.createElement("td");

                    const replyDeleteBtn = document.createElement("button");
                    replyDeleteBtn.textContent = "X";
                    replyDeleteBtn.classList.add("btn", "btn-danger");
                    // 클릭 이벤트 추가
                    replyDeleteBtn.addEventListener("click", function() {
                        const replyEmail = response.content[i].email; // 댓글 작성자
                        console.log(replyEmail);

                        if (replyEmail !== email) {
                            alert("게시글 작성자와 일치하지 않습니다.");
                        } else {
                            // 삭제할 댓글 번호
                            const replyNo = response.content[i].replyNo;


                            console.log("댓글이 삭제될 예정입니다.");

                            // 댓글 삭제 요청 보내기
                            $.ajax({
                                type: 'POST',
                                url: '/api/deleteReply',
                                contentType: 'application/json', // 데이터 형식을 JSON으로 설정
                                data: JSON.stringify({replyNo: replyNo}), // JSON 형식으로 데이터 전송
                                success: function (response) {
                                    // 삭제 성공 시, 해당 댓글 행 제거
                                    newRow.remove();
                                    alert("댓글이 삭제되었습니다.");
                                    location.reload();
                                },
                                error: function (xhr, status, error) {
                                    console.error("댓글 삭제 중 에러가 발생했습니다: ", error); // AJAX 요청 실패 확인
                                    console.error("상태 코드: ", status);
                                    console.error("XHR 객체: ", xhr);
                                    alert('댓글을 삭제하는데 실패했습니다: ' + error);
                                }
                            });
                        }
                    });

                    deleteCell.style.width = "10%";
                    deleteCell.appendChild(replyDeleteBtn);
                    newRow.appendChild(deleteCell);

                    // 행을 테이블에 추가
                    replyListElement.appendChild(newRow);
                }
            },
            error: function (xhr, status, error) {
                console.error("AJAX 요청 실패: ", error); // AJAX 요청 실패 확인
                alert('댓글 목록을 불러오는데 실패했습니다: ' + error);
            }
        });

        // 8. 댓글 페이지
    }
}

customElements.define("custom-board-read-modal", boardReadModal);
