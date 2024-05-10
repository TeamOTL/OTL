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
            <style>
            .profile-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
            .rounded-circle {
                width: 50px;
                height: 50px;
            }
            #selectedCategories {
                    display: flex;
                    flex-wrap: wrap;
                    gap: 5px;
            }
            .category-item {
                display: inline-flex;
                align-items: center;
                background-color: #4d72dc;
                border-radius: 12px;
                padding: 5px 10px;
                margin: 5px;
                font-size: 14px;
                color: white;
                
                
            }
            .category-item .remove-btn {
                margin-left: 8px;
                cursor: pointer;
                color: red;
            }
            .cropper-container {
                    display: none;
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    z-index: 1050;
                    background-color: white;
                    padding: 20px;
                    border-radius: 10px;
                }
                .cropper-container img {
                    max-width: 100%;
                }
                .cropper-buttons {
                    text-align: center;
                    margin-top: 10px;
                }
            </style>
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
                                                <input type="file" id="profileImageInput" accept="image/*" style="display: none;">
                                                <button type="button" class="btn btn-primary" id="changeImageBtn">이미지 변경</button>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <h6>닉네임</h6>
                                    <input type="text" id="nicknameInput" class="form-control rounded mr-3">
                                    <br>
                                    <h6>관심 분야</h6>
                                    <select class="form-select rounded" aria-label="Default select example" style="width: 100%;" id="mySelect">
                                        <option selected disabled>카테고리</option>
                                        <option value="1">자바</option>
                                        <option value="2">리눅스</option>
                                        <option value="3">김강현</option>
                                    </select>
                                    <div id="selectedCategories" class="category-container"></div>
                                    
                                    <br>
                                                                      
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
                <div class="cropper-container" id="cropperContainer">
                <img id="cropperImage" src="">
                <div class="cropper-buttons">
                    <button type="button" class="btn btn-success" id="cropBtn">자르기</button>
                    <button type="button" class="btn btn-danger" id="cancelCropBtn">취소</button>
                </div>
            </div>
            `;
        let cropper;

        // 이미지 변경 버튼 클릭 시 파일 입력 클릭
        this.querySelector('#changeImageBtn').addEventListener('click', () => {
            this.querySelector('#profileImageInput').click();
        });

        // 이미지 파일이 선택되면 미리보기와 업로드 준비
        this.querySelector('#profileImageInput').addEventListener('change', (event) => {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (e) => {
                    const cropperImage = this.querySelector('#cropperImage');
                    cropperImage.src = e.target.result;
                    this.querySelector('#cropperContainer').style.display = 'block';

                    if (cropper) {
                        cropper.destroy();
                    }
                    cropper = new Cropper(cropperImage, {
                        aspectRatio: 1,
                        viewMode: 1,
                    });
                };
                reader.readAsDataURL(file);
            }
        });

        // 크롭 버튼 클릭 시 크롭된 이미지 설정
        this.querySelector('#cropBtn').addEventListener('click', () => {
            const canvas = cropper.getCroppedCanvas({
                width: 200,
                height: 200,
            });
            const profileImage = this.querySelector('#profileImage');
            profileImage.src = canvas.toDataURL();
            this.querySelector('#cropperContainer').style.display = 'none';
        });

        // 크롭 취소 버튼 클릭 시 크롭 취소
        this.querySelector('#cancelCropBtn').addEventListener('click', () => {
            this.querySelector('#cropperContainer').style.display = 'none';
            cropper.destroy();
        });


        // 폼 제출 이벤트 리스너 추가
        this.querySelector('#userForm').addEventListener('submit', async (event) => {
            event.preventDefault();
            const nickname = this.querySelector('#nicknameInput').value;
            const intro = this.querySelector('#introInput').value;
            const profileImage = this.querySelector('#profileImage').src;

            try {
                const response = await fetch('/api/user', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ nickname, intro, profileImage: profileImageSrc, }),
                });

                if (!response.ok) {
                    throw new Error('나의 정보 업데이트 실패');
                }

                // 성공적으로 업데이트되었을 때 처리
                alert('나의 정보를 성공적으로 수정완료 했습니다.');
                location.reload();
            } catch (error) {
                console.error('Error updating user info:', error);
                alert('나의 정보 수정실패 했습니다.');
            }
        });


        // 카테고리 선택 시 이벤트 리스너 추가
        this.querySelector('#mySelect').addEventListener('change', (event) => {
            const selectedOption = event.target.options[event.target.selectedIndex].text;
            const selectedCategoriesDiv = this.querySelector('#selectedCategories');

            // "카테고리" 옵션을 선택했을 경우 무시
            if (selectedOption === "카테고리") {
                return;
            }

            // 기존에 선택된 카테고리들과 중복되지 않도록 체크
            if (!Array.from(selectedCategoriesDiv.children).some(category => category.innerText.includes(selectedOption))) {
                const categoryItem = document.createElement('div');
                categoryItem.className = 'category-item';
                categoryItem.innerText = selectedOption;
                const removeBtn = document.createElement('span');
                removeBtn.className = 'remove-btn';
                removeBtn.innerText = '×';
                removeBtn.onclick = () => {
                    selectedCategoriesDiv.removeChild(categoryItem);
                };
                categoryItem.appendChild(removeBtn);
                selectedCategoriesDiv.appendChild(categoryItem);
            }
        });
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
