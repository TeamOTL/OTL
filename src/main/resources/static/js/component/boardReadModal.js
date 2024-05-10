// 제목과 내용을 저장할 변수 선언
let originalTitle, originalContent;

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

        // console.log('bno : ' + bno);
        // console.log('boardTitle');
        // console.log('boardContent');
        // console.log('nickname');
        // console.log('MemberProfileImage');
        // console.log('regDate');
        // console.log('modDate');

        // 현재 제목과 내용을 저장
        originalTitle = boardTitle;
        originalContent = boardContent;

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
                console.error("작성자 프로필 이미지을 불러오는데 실패했습니다: ", error);
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

        // 수정하기 버튼 클릭 시
        modifyBtn.addEventListener("click", () => {

            const boardemail = this.getAttribute('board.email'); // 게시글 작성자
            const email = this.getAttribute('data-email'); // 로그인한 사용자

            if(boardemail !== email){
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

                        // 게시글 입력 필드를 다시 readonly로 변경
                        document.getElementById("boardTitle").readOnly = true;
                        document.getElementById("boardContent").readOnly = true;

                        // 저장하기 버튼과 삭제하기 버튼 숨기기
                        document.getElementById("boardModifyBtn").style.display = "inline-block";
                        boardModifyFinishBtn.style.display = "none";
                        document.getElementById("boardDeleteBtn").style.display = "none";
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
        // const boardDeleteBtn = this.querySelector("#boardDeleteBtn");
        // boardDeleteBtn.addEventListener("click", () => {
        //     // 사용자에게 확인 메시지 표시
        //     if (confirm("정말로 삭제하시겠습니까?")) {
        //
        //         // 확인 버튼을 눌렀을 때
        //         // AJAX를 통해 게시글을 서버에서 삭제
        //         $.ajax({
        //             type: 'POST',
        //             url: '/api/deletedBoard',
        //             data: {
        //                 bno: bno
        //             },
        //             success: function (response) {
        //                 // 서버에서 받은 응답을 처리하는 부분
        //                 console.log("게시글이 성공적으로 삭제되었습니다.");
        //                 // 모달 닫기
        //                 $("#boardReadModal-" + bno).modal("hide");
        //             },
        //             error: function (xhr, status, error) {
        //                 console.error("게시글 삭제 중 에러가 발생했습니다: ", error);
        //                 // 에러 처리 부분
        //             }
        //         });
        //     } else {
        //         // 취소 버튼을 눌렀을 때
        //         alert("삭제가 취소되었습니다.");
        //     }
        // });


        // 4. 취소 버튼 클릭 시
        cancelBtn.addEventListener("click", () => {
            // 수정하기 버튼 활성화
            modifyBtn.style.display = "inline-block";

            // 저장하기 버튼과 삭제하기 버튼 숨기기
            modifyFinishBtn.style.display = "none";
            deleteBtn.style.display = "none";

            // 제목과 내용 입력란의 readOnly 속성 다시 설정
            boardTitleInput.setAttribute("readonly", true);
            boardContentTextarea.setAttribute("readonly", true);

            // 제목과 내용 원래 값으로 되돌리기 (수정 전 값으로)
            boardTitleInput.value = originalTitle;
            boardContentTextarea.value = originalContent;
        });

        // 2 : 게시글 수정 모달창로 화면 전환
        // const modalContent = this; // 모달 요소 자체에 이벤트를 붙여야 함
        //
        // // 수정하기 버튼 클릭 시
        // modalContent.querySelector('#boardModifyBtn').addEventListener('click', function () {
        //     console.log("수정 버튼이 클릭되었습니다.");
        //
        //     const bno = modalContent.getAttribute('board.bno'); // 게시글 번호 확인
        //
        //     // 현재 열려 있는 조회 모달을 닫음
        //     $('#boardReadModal-' + bno).modal('hide');
        //
        //     // 게시글 정보를 수정 모달에 전달
        //     const modifyModal = document.querySelector('custom-board-modify-modal');
        //     modifyModal.setAttribute('board-bno', bno); // 게시글 번호
        //     modifyModal.setAttribute('board-boardTitle', boardTitle); // 제목
        //     modifyModal.setAttribute('board-boardContent', boardContent); // 내용
        //     modifyModal.setAttribute('board-nickname', nickname); // 작성자
        //     modifyModal.setAttribute('board-memberProfileImage', memberProfileImage); // 작성자 프로필 이미지
        //     modifyModal.setAttribute('board-regDate', regDate); // 작성일
        //     modifyModal.setAttribute('board-modDate', modDate); // 수정일
        //
        //     console.log("수정할 게시글 번호:", bno);
        //     console.log("수정 모달 ID:", '#boardModifyModal-' + bno); // 모달 ID 확인
        //
        //     // 게시글 수정 모달창 open
        //     $('#boardModifyModal-' + bno).modal('show');
        //     console.log("게시글 수정 모달창 open되었습니다.");
        // });
    }
}

customElements.define("custom-board-read-modal", boardReadModal);