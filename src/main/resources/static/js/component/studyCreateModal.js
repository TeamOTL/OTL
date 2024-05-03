class studyCreateModal extends HTMLElement {
    /** - 작성자 : 김강현 
     * 
     * data-toggle="modal" data-target="#태그아이디"에서
     * data-target은 보여줄 태그 아이디입니다.
     * 
     */
        connectedCallback() {
            this.innerHTML = `
            <!-- The Modal -->
            <div class="modal fade" id="studyModal" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content p-3">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title" style="color: #FD7B38;">스터디 생성</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div>
                            <form action="#" method="post">
                                <div class="modal-body" style="color: black;">

                                    <!-- 스터디 이름 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">스터디 이름</label>
                                        <div class="col">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <!-- 카테고리 & 모집인원 -->
                                    <div class="row mb-3 justify-content-between">
                                        <label class="col-sm-3 col-form-label">카테고리</label>
                                        <div class="col">
                                            <select class="form-select form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-3 col-form-label">모집 인원</label>
                                        <div class="col">
                                            <select class="form-select form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- 관심분야 -->
                                    <div class="row mb-3 justify-content-between">
                                        <label class="col-sm-3 col-form-label">관심분야</label>
                                        <div class="col">
                                            <select class="form-select form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <select class="form-select form-control">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- 모집기간 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">모집 기간</label>
                                        <div class="col-sm-8 d-flex">
                                            <input type="date" class="form-select form-control">
                                            ~
                                            <input type="date" class="form-select form-control">
                                        </div>
                                    </div>
                                    <!-- 스터디 시작일 -->
                                    <div class="row mb-3">
                                        <label class="col col-form-label">스터디 시작일</label>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-select form-control">
                                        </div>
                                    </div>
                                    <!-- 한 줄 설명 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-2 col-form-label">설명</label>
                                        <div class="col flex-grow-1">
                                            <input type="text" class="form-control" placeholder="15글자 이하">
                                        </div>
                                    </div>
                                    <!-- 일정 -->
                                    <div class="row mb-3">
                                        <div class="row d-flex">
                                            <label class="col-sm-2 form-label">일정</label>
                                            <div class="col d-flex">
                                                <input type="text" class="form-control mr-1" placeholder="">
                                                <button type="button" class="btn btn-primary ">+</button>
                                            </div>
                                        </div>
                                        <div class="col mt-2">
                                            <textarea class="form-control bg-white" disabled></textarea>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary"
                                        >완료</button>
                                    <button type="button" class="btn btn-danger"
                                        data-dismiss="modal">취소</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
           `;
        }
    }
    
    customElements.define("custom-study-create-modal", studyCreateModal);