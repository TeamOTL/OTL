
class settingModal extends HTMLElement {
    /** - 작성자 : 김강현 
     * 
     * data-toggle="modal" data-target="#태그아이디"에서
     * data-target은 보여줄 태그 아이디입니다.
     * 
     */
    connectedCallback() {
        const path = window.location.pathname;
        let sPath = "";
        
        if(path.indexOf("templates") > 0)
             sPath = "../static/";
        this.innerHTML =`
        <div class="modal fade" id="settingModal" tabindex="-1" role="dialog"
            aria-labelledby="settingModalLabel" aria-hidden="true" data-backdrop="static" style="color:black;">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="settingLabel">개인 정보 수정</h4>
                        <!-- 닫기버튼 -->
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <!-- 닫기버튼 end -->
                    </div> <!-- 모달 헤더 종료-->
                    <form action="" method="post">
                        <div class="modal-body">
                            <!-- 모달 내용 (바디부분 ) -->
                            <div class="rounded p-2" style="background-color: silver;">
                                <!-- 프로필 영역 -->
                                <div
                                    class="profile-container d-flex align-items-center justify-content-between">
                                    <!-- 프로필 이미지 -->
                                    <img src="${sPath}img/꺄.jpeg" alt="profillll" class="rounded-circle"
                                        style="width: 50px; height: 50px;">
                                    <!-- 프로필 닉네임 -->
                                    <span>프로필 닉네임</span>
                                    <!-- 이미지 변경 버튼 -->
                                    <div>
                                        <button type="button" class="btn btn-primary">이미지
                                            변경</button>
                                    </div>
                                </div>
                            </div>
                            <!-- 프로필영역종료 -->
                            <br>
                            <h6>닉네임</h6>
                            <!-- 텍스트 입력 필드 -->
                            <input type="text" class="form-control rounded mr-3" placeholder="텍스트 입력">
                            <br>
                            <h6>관심 분야(카테고리 선택하는 토글)</h6>
                            <!-- 텍스트 입력 필드 -->
                            <select class="form-select rounded" aria-label="Default select example"
                                style="width: 100%;" id="mySelect">
                                <option selected>카테고리</option>
                                <option value="1">자바</option>
                                <option value="2">리눅스</option>
                                <option value="3">김강현</option>
                            </select>
                            <br>
                            <!-- 선택된 항목을 보여주는 div -->
                            <div class="mt-2">
                                <!-- 선택된 항목을 보여주는 div -->
                                <div id="selectedOption"
                                    class="selected-option btn btn-primary disabled">
                                </div>
                            </div>
                            <script>
                                // 셀렉트 박스의 선택된 항목을 보여주는 함수
                                function showSelectedOption() {
                                    var selectElement = document.getElementById("mySelect");
                                    var selectedOption = selectElement.options[selectElement.selectedIndex].text;
                                    document.getElementById("selectedOption").innerText = selectedOption;
                                }
                                 
                                // 페이지 로드시 선택된 항목 표시
                                showSelectedOption();
                                 
                                // 셀렉트 박스에서 항목이 변경될 때마다 선택된 항목을 보여줌
                                document.getElementById("mySelect").addEventListener("change", showSelectedOption);
                            </script>
                            <br>
                            <h6>한 줄 자개소개</h6>
                            <!-- 텍스트 입력 필드 -->
                            <input type="text" class="form-control rounded mr-3"
                                placeholder="최대 15글자 이하입니다.">
                            <br>
                            <h6>자기 소개</h6>
                            <!-- 텍스트 입력 필드 -->
                            <input type="text" class="form-control rounded mr-3"
                                placeholder="자기소개를 입력하세요.">
                                 
                                 
                        </div>
                        <div class="modal-footer">
                            <!-- 모달 하단부분 -->
                            <button type="submit" class="btn btn-danger">로그아웃</button>
                            <button type="submit" class="btn btn-success">수정 완료</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- modal Setting end -->
       `;
    }
}

customElements.define("custom-setting-modal", settingModal);