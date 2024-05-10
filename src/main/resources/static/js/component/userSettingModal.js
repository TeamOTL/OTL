document.addEventListener('DOMContentLoaded', () => {
    const settingModalElement = document.querySelector('custom-setting-modal');

    // 서버에서 사용자 정보를 가져오는 함수
    async function fetchUserInfo() {
        try {
            const response = await fetch('/api/user');
            if (!response.ok) {
                throw new Error('사용자 정보를 가져오는데 실패 했습니다.');
            }
            const user = await response.json();
            // 사용자 정보를 커스텀 컴포넌트에 설정
            settingModalElement.setUserInfo(user.nickname, user.profileImage, user.email, user.memberDescription);
        } catch (error) {
            console.error('에러:', error);
        }
    }

    fetchUserInfo();
});
class settingModal extends HTMLElement {
    connectedCallback() {
        // Modal HTML을 설정
        this.innerHTML = `
            <div class="modal fade" id="settingModal" tabindex="-1" role="dialog"
                    aria-labelledby="settingModalLabel" aria-hidden="true" data-backdrop="static" style="color:black;">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="settingLabel">나의 정보</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form id="userForm">
                                <div class="modal-body">
                                    <div class="rounded p-2" style="background-color: silver;">
                                        <div class="profile-container d-flex align-items-center justify-content-between">
                                            <img id="profileImage" alt="프로필 이미지" class="rounded-circle">
                                            <span id="profileNickname"></span>
                                            <div>
                                                <button type="button" class="btn btn-primary">이미지 변경</button>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <h6>닉네임</h6>
                                    <input type="text" id="nicknameInput" class="form-control rounded mr-3">
                                    <br>
                                    <h6>관심 분야</h6>
                                    <select class="form-select rounded" aria-label="Default select example" style="width: 100%;" id="mySelect">
                                        <option selected>카테고리</option>
                                        <option value="1">자바</option>
                                        <option value="2">리눅스</option>
                                        <option value="3">김강현</option>
                                    </select>
                                    <div>자바, 리눅스 , 김강현</div>
                                    <br>
                                    <div class="mt-2">
                                        <div id="selectedOption" class="selected-option btn btn-primary disabled"></div>
                                    </div>
                                    <br>
                                    <h6>한 줄 자기소개</h6>
                                    <input type="text" id="introInput" class="form-control rounded mr-3" placeholder="최대 15글자 이하입니다.">
                                    <br>
                                    <h6>자기 소개</h6>
                                    <input type="text" id="descriptionInput" class="form-control rounded mr-3" placeholder="자기소개를 입력하세요.">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" id="logoutBtn">로그아웃</button>
                                    <button type="submit" class="btn btn-success">수정 완료</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            `;
    }

        // 사용자 정보를 설정하는 메소드 추가
        setUserInfo(nickname, profileImage, email, memberDescription)
        {
            this.querySelector('#profileNickname').innerText = nickname;
            this.querySelector('#profileImage').src = profileImage;
            this.querySelector('#nicknameInput').value = nickname;
            this.querySelector('#introInput').value = memberDescription;

        }
    }




customElements.define('custom-setting-modal', settingModal);
