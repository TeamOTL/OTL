class studyInfoModal extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <!-- The Modal -->
            <div class="modal fade" id="studyInfoModal" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content p-3">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title" style="color: #FD7B38;">스터디 상세 정보</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <form action="" method="post">
                            <!-- Modal Body -->
                            <div class="modal-body" style="color: black;">
                                <!-- 스터디 이름 -->
                                <div class="row mb-3">
                                    <div class="col d-flex">
                                        <label class="col col-form-label">스터디 이름</label>
                                        <div class="col-sm-8">
                                            <input type="text" readonly class="form-control-plaintext" id="modal-study-name">
                                        </div>
                                    </div>
                                </div>
                                <!-- 카테고리 & 모집인원 -->
                                <div class="row mb-3 justify-content-between">
                                    <div class="col d-flex">
                                        <label class="col col-form-label">카테고리</label>
                                        <div class="col-sm-3">
                                            <input type="text" readonly class="form-control-plaintext" id="modal-category">
                                        </div>
                                        <label class="col col-form-label">모집 인원</label>
                                        <div class="col-sm-3">
                                            <input type="text" readonly class="form-control-plaintext" id="modal-max-member">
                                        </div>
                                    </div>
                                </div>
                                <!-- 관심분야 -->
                                <div class="row mb-3 justify-content-between">
                                    <div class="col d-flex">
                                        <label class="col col-form-label">관심분야</label>
                                        <div class="col-sm-9" id="modal-interests">
                                            <!-- 관심사 뱃지가 여기에 추가됩니다 -->
                                        </div>
                                    </div>
                                </div>
                                <!-- 모집기간 -->
                                <div class="row mb-3">
                                    <div class="col d-flex">
                                        <label class="col-sm-4 col-form-label">모집 기간</label>
                                        <input type="text" readonly class="form-control-plaintext" id="modal-start-date">
                                        <label class="col col-form-label">~</label>
                                        <input type="text" readonly class="form-control-plaintext" id="modal-end-date">
                                    </div>
                                </div>
                                <!-- 스터디 시작일 -->
                                <div class="row mb-3">
                                    <div class="col d-flex">
                                        <label class="col-sm-4 col-form-label">스터디 시작일</label>
                                        <input type="text" readonly class="form-control-plaintext" id="modal-first-date">
                                    </div>
                                </div>
                                <!-- 한 줄 설명 -->
                                <div class="row mb-3">
                                    <div class="col d-flex">
                                        <label class="col-sm-2 col-form-label">설명</label>
                                        <input type="text" readonly class="form-control-plaintext" id="modal-description">
                                    </div>
                                </div>
                                <!-- 일정 -->
                                <div class="row mb-3">
                                    <div class="col d-flex">
                                        <label class="col-sm-2 col-form-label">일정</label>
                                        <input type="text" readonly class="form-control-plaintext" id="modal-plan">
                                    </div>
                                </div>
                            </div>
                            <!-- Modal Footer -->
                            <div class="modal-footer">
                                <input type="text" class="form-control bg-white" placeholder="방장에게 한마디(15자 내외)">
                                <button type="submit" class="btn btn-primary">완료</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        `;
    }
}
customElements.define("custom-study-info-modal", studyInfoModal);
