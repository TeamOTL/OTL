class studyCreateModal extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <!-- The Modal -->
            <div class="modal fade" id="studyCreateModal" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content p-3">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title" style="color: #FD7B38;">스터디 생성</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div>
                            <form id="studyForm" action="#" method="post">
                                <div class="modal-body" style="color: black;">

                                    <!-- 스터디 이름 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">studyName</label>
                                        <div class="col">
                                            <input type="text" class="form-control" id="studyName" required>
                                        </div>
                                    </div>
                                    <!-- 관심사 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">interests</label>
                                        <div class="col">
                                            <input type="text" class="form-control interests-input">
                                        </div>
                                    </div>
                                    <!-- 스터디 설명 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">studyDescription</label>
                                        <div class="col">
                                            <input type="text" class="form-control" id="studyDescription" required>
                                        </div>
                                    </div>
                                    <!-- 스터디 일정 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">studyPlan</label>
                                        <div class="col">
                                            <input type="text" class="form-control" id="studyPlan">
                                        </div>
                                    </div>
                                    <!-- 스터디 인원 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">maxMember</label>
                                        <div class="col">
                                            <input type="number" class="form-control" id="maxMember" required>
                                        </div>
                                    </div>
                                    
                                    <!-- 스터디 시작일 -->
                                    <div class="row mb-3">
                                        <label class="col col-form-label">firstDate</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="firstDate" class="form-select form-control">
                                        </div>
                                    </div> 
                                    
                                    <!-- 모집 시작일 -->
                                    <div class="row mb-3">
                                        <label class="col col-form-label">startDate</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="r_start_date" class="form-select form-control">
                                        </div>
                                    </div> 
                                    <!-- 모집 종료일 -->
                                    <div class="row mb-3">
                                        <label class="col col-form-label">endDate</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="r_end_date" class="form-select form-control">
                                        </div>
                                    </div>

                                    <!-- 카테고리 선택 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">Category</label>
                                        <div class="col">
                                            <select class="form-control" id="categorySelect">
                                                <!-- Db의 category 정보를 가져와서 화면에 뿌려주기 -->
                                            </select>
                                        </div>
                                    </div>
                                   
                                    <!-- 주차 추가 -->
                                    <div id="dynamicTasks">
                                        <!-- 기본 입력 필드 -->
                                        <div class="row mb-3 dynamic-input">
                                            <label class="col-sm-2 form-label">Task</label>
                                            <div class="col d-flex">
                                                <input type="date" class="form-control mr-1 task-date-input" placeholder="Task Date" id="taskDate">
                                                <input type="text" class="form-control mr-1 task-title-input" placeholder="Task Title" id="taskTitle">
                                                <button type="button" class="btn btn-primary add-task-btn">+</button>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 관심사 추가 -->
                                    <div id="dynamicInterests">
                                        <div class="row mb-3 dynamic-input">
                                            <label class="col-sm-2 form-label">Interest</label>
                                            <div class="col d-flex">
                                                <input type="text" id="interestName" class="form-control mr-1 interests-input" placeholder="Interest Name">
                                                <button type="button" class="btn btn-primary add-interest-btn">+</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                                </div>
                            </form>
                         <button type="submit" class="btn btn-primary" id="btn-save">완료</button>

                        </div>
                    </div>
                </div>
            </div>
        `;

        let index = {
            init: function () {
                // + 버튼 클릭 이벤트
                $(document).on("click", ".add-task-btn", function () {
                    index.addTaskInput();
                });

                $(document).on("click", ".add-interest-btn", function () {
                    index.addInterestInput();
                });

                // - 버튼 클릭 이벤트
                $(document).on("click", ".remove-input-btn", function () {
                    index.removeInput(this);
                });

                // DB에 저장되어 있는 Category 목록 가져오기
                this.loadCategories();

                $("#btn-save").on("click", (event) => {
                    event.preventDefault(); // 폼 submit 이벤트 방지
                    this.save();
                });
            },
            // 동적 Task 입력 필드 추가 함수
            addTaskInput: function () {
                let newInput = `
                <div class="row mb-3 dynamic-input">
                    <label class="col-sm-2 form-label">Task</label>
                    <div class="col d-flex">
                        <input type="date" class="form-control mr-1 task-date-input" placeholder="Task Date" id="taskDate">
                        <input type="text" class="form-control mr-1 task-title-input" placeholder="Task Title" id="taskTitle">
                        <button type="button" class="btn btn-danger remove-input-btn">-</button>
                    </div>
                </div>`;
                $("#dynamicTasks").append(newInput);
            },
            // 동적 Interest 입력 필드 추가 함수
            addInterestInput: function () {
                let newInput = `
                <div class="row mb-3 dynamic-input">
                    <label class="col-sm-2 form-label">Interest</label>
                    <div class="col d-flex">
                        <input type="text" id="interestName" class="form-control mr-1 interests-input" placeholder="Interest Name">
                        <button type="button" class="btn btn-danger remove-input-btn">-</button>
                    </div>
                </div>`;
                $("#dynamicInterests").append(newInput);
            },
            // 입력 필드 제거 함수
            removeInput: function (element) {
                $(element).closest('.dynamic-input').remove();
            },
            loadCategories: function () {
                $.ajax({
                    type: "GET",
                    url: "/api/categories",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done(function (resp) {
                    console.log("Categories loaded:", resp); // 응답 데이터를 콘솔에 출력하여 확인
                    let categorySelect = $("#categorySelect");
                    resp.forEach(category => {
                        // 카테고리 옵션 추가
                        categorySelect.append(new Option(category.category_name, category.cno));
                    });

                    // 카테고리 이미지를 표시할 div 추가
                    let categoryImagesDiv = $("#categoryImages");
                    resp.forEach(category => {
                        // 카테고리 이미지 추가
                        categoryImagesDiv.append(`<img src="${category.category_image}" alt="${category.category_name}" style="width:50px; height:50px; margin:5px;">`);
                    });
                }).fail(function (error) {
                    console.error("Failed to load categories:", error); // 오류를 콘솔에 출력하여 확인
                    alert("Failed to load categories");
                });
            },

            save: function () {
                let data = {
                    sno: null,
                    studyName: $("#studyName").val(),
                    studyDescription: $("#studyDescription").val(),
                    studyPlan: $("#studyPlan").val(),
                    maxMember: $("#maxMember").val(),
                    firstDate: $("#firstDate").val(),
                    rStartDate: $("#r_start_date").val(),
                    rEndDate: $("#r_end_date").val(),
                    dDay: null,
                    cno: $("#categorySelect").val(),  // 선택한 카테고리 ID 추가
                    interests: [], // 빈 배열로 초기화
                    tasks: [], // 빈 배열로 초기화
                    customTasks: [], // 빈 배열로 초기화
                    customInterests: [] // 빈 배열로 초기화
                };
                console.log(data);

                // 동적 입력 필드에서 값 수집
                $(".task-date-input").each(function () {
                    let taskDateValue = $(this).val();
                    let taskTitleValue = $(this).siblings('.task-title-input').val();
                    if (taskDateValue.trim() !== "" && taskTitleValue.trim() !== "") {
                        data.customTasks.push({ taskDate: taskDateValue, taskTitle: taskTitleValue });
                    }
                });

                $(".interests-input").each(function () {
                    let interestValue = $(this).val();
                    if (interestValue.trim() !== "") {
                        data.customInterests.push({ interestName: interestValue });
                    }
                });

                // 비동기 호출
                $.ajax({
                    type: "POST",
                    url: "/api/studyCreate",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "text"
                }).done(function (resp) {
                    alert("스터디 생성 성공");
                    console.log(resp);
                }).fail(function (error) {
                    alert("스터디 생성 실패");
                    console.error(error);
                });
            },
        }
        index.init();
    }
}

customElements.define("custom-study-create-modal", studyCreateModal);
