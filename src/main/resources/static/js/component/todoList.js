class todoList extends HTMLElement {
    connectedCallback() {
        const path = window.location.pathname;
        let sPath = "";

        if (path.indexOf("templates") > 0) sPath = "../static/";
        this.innerHTML = `
            <!-- 모달 -->
            <div class="modal fade" id="todoListModal" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content p-3">
                        <!-- 모달 헤더 -->
                        <div class="modal-header">
                            <h4 class="modal-title" style="color: #FD7B38">할 일 목록</h4>
                            <button type="button" class="close" data-dismiss="modal">x</button>
                        </div>
                        <!-- 모달 바디 -->
                        <div class="modal-body" style="color: black">
                            <form th:action="@{/add-todo}" method="post">
                                <div class="row mb-3">
                                    <label class="col-form-label col">
                                        <div class="input-group border-bottom-info">
                                            <input class="col rounded border-0" type="text" name="todoText" placeholder="이곳에 입력하세요">
                                            <input class="col-sm-1 border-0" type="date" name="todoDate">
                                            <button class="col-1 btn rounded fs-3">+</button>
                                        </div>
                                    </label>
                                </div>
                            </form>
                            <div th:each="todo, stat : \${todoList}" class="row mb-3">
                                <div class="col col-form-label d-flex align-items-center">
                                    <span class="flex-grow-1" th:text="\${todo.text}">할 일 텍스트</span>
                                    <span class="flex-grow-0" th:text="\${todo.date}">날짜</span>
                                    <form th:action="@{/complete-todo}" method="post">
                                        <input type="hidden" name="index" th:value="\${stat.index}">
                                        <span class="flex-grow-0"><button class="btn">✔️</button></span>
                                    <span class="flex-grow-0"><button class="btn">❌</button></span>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }
}

customElements.define("custom-todo-list", todoList);

