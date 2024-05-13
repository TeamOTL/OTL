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
                            <form action="#" method="post">
                                <div class="modal-body" style="color: black;">

                                    <!-- 스터디 이름 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">testName</label>
                                        <div class="col">
                                            <input type="text" class="form-control" id="testName">
                                        </div>
                                    </div>
                                    <!-- 카테고리 & 모집인원 -->
                                    <div class="row mb-3 justify-content-between">
                                       
                                        <label class="col-sm-3 col-form-label">testDescription</label>
                                         <input type="text" class="form-control" id="testDescription">
                                        <div class="col">
                                           
                                        </div>
                                    </div>
                                    <!-- 관심분야 -->
                                    <div class="row mb-3 justify-content-between">
                                        <label class="col-sm-3 col-form-label">testName2</label>
                                         <input type="text" class="form-control" id="testName2">
          
                                    </div>
                                    <!-- 스터디 일정 -->
                                     <div class="row mb-3 justify-content-between">
                                        <label class="col-sm-3 col-form-label">testDescription2</label>
                                         <input type="text" class="form-control" id="testDescription2">
          
                                    </div>
                                    <!-- 모집기간 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-3 col-form-label">모집 기간</label>
                                        <div class="col-sm-8 d-flex">
                                            <input type="date" class="form-select form-control" id="rStartDate">
                                            ~
                                            <input type="date" class="form-select form-control" id="rEndDate">
                                        </div>
                                    </div>
                                    <!-- 스터디 시작일 -->
                                    <div class="row mb-3">
                                        <label class="col col-form-label">스터디 시작일</label>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-select form-control" >
                                        </div>
                                    </div>
                                    <!-- 한 줄 설명 -->
                                    <div class="row mb-3">
                                        <label class="col-sm-2 col-form-label">설명</label>
                                        <div class="col flex-grow-1">
                                            <input type="text" class="form-control" placeholder="15글자 이하" id="studyDescription">
                                        </div>
                                    </div>
<!--                                    &lt;!&ndash; 일정 &ndash;&gt;-->
<!--                                    <div class="row mb-3">-->
<!--                                        <div class="row d-flex">-->
<!--                                            <label class="col-sm-2 form-label">일정</label>-->
<!--                                            <div class="col d-flex">-->
<!--                                                <input type="text" class="form-control mr-1" placeholder="" id="tasks">-->
<!--                                                <button type="button" class="btn btn-primary ">+</button>-->
<!--                                            </div>-->
<!--                                        </div>-->
<!--                                        <div class="col mt-2">-->
<!--                                            <textarea class="form-control bg-white" disabled></textarea>-->
<!--                                        </div>-->
<!--                                    </div>-->
                                </div>
                               
                            </form>
                             <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary"
                                         id = "btn-save">완료</button>
                                    <button type="button" class="btn btn-danger"
                                        data-dismiss="modal">취소</button>
                                </div>

                        </div>
                    </div>
                </div>
            </div>
           `;

        let index = {
            init: function () {
                $("#btn-save").on("click",() => {
                    this.save();
                });
            },


            save: function () {
                alert("study 의 save 함수 호출");
                let data = {
                    testName: $("#testName").val(),
                    testName2: $("#testName2").val(),
                    testDescription: $("#testDescription").val(),
                    testDescription2: $("#testDescription2").val(),

                    // tasks: $("#tasks").val(),


                }
                console.log(data);


                //default -> 비동기 호출
                //ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청
                $.ajax({
                    //스터디방 생성 요청

                    //요청 방식
                    type: "POST",
                    url:"/api/studyCreate",
                    //던져줄 데이터, 자바가 이해할 수 있도록 JSON 형식으로 변경
                    data:JSON.stringify(data), //http body 데이터
                    contentType:"application/json; charset=utf-8", //body 데이터 타입;MiME ; json 형식임을 알려줌
                    dataType:"json" //요청후 서버로부터 응답이 왔을떄( default : String) json으로 지정시, javaScript 오브젝트로 변환
                }).done(function (resp){
                    //생성 성공시
                    // resp 서버로부터 온 응답을 resp 파라미터로 던져줌
                    alert("good");
                    alert(resp);
                    console.log(resp);
                    // location.href="/studyJoin";
                }).fail(function (error){
                    //생성 실패
                    alert(JSON.stringify(error));
                });
            }

        }
        index.init();
    }
}

customElements.define("custom-study-create-modal", studyCreateModal);