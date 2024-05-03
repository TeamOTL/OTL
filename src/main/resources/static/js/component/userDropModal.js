class userDropModal extends HTMLElement {
    /** - 작성자 : 김강현 
     * 
     * data-toggle="modal" data-target="#태그아이디"에서
     * data-target은 보여줄 태그 아이디입니다.
     * 
     */
    connectedCallback() {
        this.innerHTML = `
        <!-- metting Modal -->
        <div class="modal fade" id="userDropModal" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content p-3">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title" style="color: #F87D7D;">스터디 탈퇴</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- 주 차표시 -->
                    <!-- Modal Body -->
                    <form action="#" method="post">
                        <div class="modal-body" style="color: black;">

                            <!-- 날짜 -->
                            <div class="row mb-3">
                                <div class="col text-center ">
                                    <label type="text" class="col col-form-label"
                                    value="">정말 탈퇴하시겠습니까?<br>탈퇴하시면 스터디 내의 모든 기록들 사라집니다.
                                    <br>복구가 어렵다는 점 양해 부탁드립니다.</label>
                                </div>
                            </div>
                        </div>
                            <!-- 방장에게 전송할 관심분야 -->
                            <div class="flex-grow-1 text-center">
                                <button type="submit" class=" btn btn-danger btn-block">삭제하기</button>
                            </div>
                    </form>

                </div>
            </div>
        </div>
       `;
    }
}

customElements.define("custom-user-drop-modal", userDropModal);