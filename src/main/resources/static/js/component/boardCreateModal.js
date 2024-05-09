class boardCreateModal extends HTMLElement {
    /** - 작성자 : 유지오
     *
     * data-toggle="modal" data-target="#태그아이디"에서
     * data-target은 보여줄 태그 아이디입니다.
     *
     */
    connectedCallback() {
        const nickname = this.getAttribute('data-nickname');
        const memberProfileImage = this.getAttribute('data-profile-image');
        const email = this.getAttribute('data-email');

        this.innerHTML = `
            <!-- The Modal -->
            <!-- 게시글 작성 모달 -->
            <form id="boardForm" action="/api/saveBoard" method="POST">
                <div class="modal fade" id="boardCreateModal" data-backdrop="static">
                    <div class="modal-dialog" style="max-width: 60%;">
                        <div class="modal-content">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title font-weight-bolder" style="color: #FD7B38;">자유게시판</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal Body -->
                            <div class="modal-body" style="color: black;">
                                <!-- 게시글 제목 -->
                                <div class="row mb-3">
                                    <label class="col-sm-2.5 col-form-label">제목</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="boardTitle" placeholder="제목을 입력하세요">
                                    </div>
                                </div>
                                <!-- 작성자 프로필 이미지, 작성자, 작성일, 수정일 -->
                                <div class="d-flex align-items-center justify-content-between" style="margin-bottom: 20px;">
                                    <img src="${memberProfileImage}" alt="profile Image" class="rounded-circle" style="width: 50px; height: 50px;">
                                    <p>작성자: <span>${nickname}</span></p>
                                    <p>Email: <span id="email">${email}</span></p>
                                    <span class="board-container board_createDate">작성일</span>
                                    <span class="board-container board_modDate">수정일</span>
                                </div>
                                <!-- 게시판 주의사항 -->
                                <div class="rounded p-2" style="margin-bottom: 20px; background-color: silver;">
                                    <div class="text-center">
                                        <span class="font-weight-bolder">※ 회원 간 불쾌한 언급은 서비스 이용에 불이익을 받게 됩니다. ※</span>
                                    </div>
                                </div>
                                <!-- 게시글 내용 -->
                                <div class="row mb-3">
                                    <label class="col-sm-2.5 col-form-label">내용</label>
                                    <div class="col flex-grow-1">
                                        <textarea type="text" class="form-control" style="height: 30vh;" id="boardContent" placeholder="본문을 입력하세요"></textarea>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal Footer -->
                            <div class="modal-footer">
                                <button type="submit" id="submitBtn" class="btn btn-primary">작성하기</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        `;
        const form = this.querySelector("#boardForm");

        const form_title = document.querySelector("#boardTitle");
        const form_Content = document.querySelector("#boardContent");

            form.addEventListener('submit', (event) => {
                event.preventDefault(); // 폼의 기본 제출을 방지
                if(form_title.value === '' || form_Content.value === ''){
                    alert("제목과 내용을 입력해주세요.")
                }else{


                const formData = {
                    boardTitle: form.querySelector("#boardTitle").value,
                    boardContent: form.querySelector("#boardContent").value,
                    email: form.querySelector("#email").innerText // 이메일 값을 읽어옴
                };

                console.log("폼 데이터: ", formData); // 폼 데이터 확인

                $.ajax({
                    type: 'POST',
                    url: '/api/saveBoard',
                    data: JSON.stringify(formData),
                    contentType: 'application/json',
                    success: function (response) {
                        console.log("AJAX 요청 성공: ", response); // AJAX 요청 성공 확인
                            alert('게시글이 성공적으로 저장되었습니다.');
                            location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error("AJAX 요청 실패: ", error); // AJAX 요청 실패 확인
                        alert('게시글 저장에 실패했습니다: ' + error);
                    }
                });
                }
            });
    }
}



customElements.define("custom-board-create-modal", boardCreateModal);
