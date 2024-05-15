
class Dashboard extends HTMLElement {

    constructor() {
        super();
        this.calenderJson = [];
        this.email = '';

    }
    connectedCallback() {
        // 전역 변수 초기화
        this.email = this.getAttribute('email').replace(/"/g, '');
        this.innerHTML = `
            <style>
                #todolistContainer {
                    right: -100%;
                    z-index: 1;
                    transition: right 0.5s ease-in;
                }
                #todolistContainer.show {
                    right: 5%;
                    transition: right 0.5s ease-out;
                }
            </style>
            <div class="row justify-content-center gap-3 h-600">
                <div id="calendar" class="col-lg-8 bg-white p-3 rounded fc fc-media-screen fc-direction-ltr fc-theme-standard"></div>
                <div class="col-auto">
                    <button id="todolistPopup" class="btn border btn-facebook shadow-lg p-3 mb-5">👈TodoList</button>
                </div>
                <div id="todolistContainer" class="row position-absolute col-lg-6 col-md-6 col-sm-3 fade d-none">
                    <div class="bg-white shadow-lg rounded" id="todolist">
                        <div class="row justify-content-center">
                            <h4 class="h4 p-4 col">TodoList</h4>
                            <h4 class="h4 p-4 col-auto">
                                <button type="button" id="todolistExit" class="btn btn-danger col-auto">X</button>
                            </h4>
                        </div>
                        <form id="todoForm">
                            <label class="row justify-content-between gap-3 m-2">
                                <input class="col col-form-label form-control" type="text" placeholder="할 일 입력" name="todoTitle" id="todoTitle">
                                <button class="btn btn-facebook col-auto" onclick="submitTodo(event)">+</button>
                            </label>
                            <label class="row justify-content-center gap-5">
                                <input type="date" class="col-lg-4 col-sm-3 col-md-3 form-control input-group" name="startDate" id="startDate">~
                                <input type="date" class="col-lg-4 col-sm-3 col-md-3 form-control input-group" name="endDate" id="endDate">
                            </label>
                            <hr class="border border-dark">
                        </form>
                        <ul class="nav nav-tabs" id="todoTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="all-tab" onclick="showAllTodos()"
                                        data-bs-toggle="tab" data-bs-target="#all" type="button" role="tab"
                                        aria-controls="all" aria-selected="true">할 일
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="completed-tab" onclick="showCompletedTodos()"
                                        data-bs-toggle="tab" data-bs-target="#completed" type="button" role="tab"
                                        aria-controls="completed" aria-selected="false">완료 목록
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="pending-tab" onclick="showDeletedTodos()"
                                        data-bs-toggle="tab" data-bs-target="#pending" type="button" role="tab"
                                        aria-controls="pending" aria-selected="false">삭제 목록
                                </button>
                            </li>
                        </ul>
                        <div class="tab-content" id="todoTabsContent">
                            <div class="tab-pane fade show active" id="allTodo" role="tabpanel" aria-labelledby="all-tab">
                                <ul class="list-group" id="todoListing"></ul>
                            </div>
                            <div class="tab-pane fade" id="completedTodo" role="tabpanel" aria-labelledby="completed-tab">
                                <ul class="list-group" id="todoListCompleted"></ul>
                            </div>
                            <div class="tab-pane fade" id="deleteTodo" role="tabpanel" aria-labelledby="pending-tab">
                                <ul class="list-group" id="todoListDeleted"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;

        // 이벤트 리스너 설정
        this.setupEventListeners();

        // 초기 데이터 로딩
        this.initializeData();

    };


    /**
     *
     */
    setupEventListeners() {
        document.getElementById("todolistPopup").addEventListener("click", this.handlePopupClick.bind(this));
        document.getElementById("todolistExit").addEventListener("click", this.handleExitClick.bind(this));
        document.addEventListener("click", this.handleOutsideClick.bind(this));
        document.getElementById("todoForm").addEventListener("submit", this.submitTodo.bind(this));
    }


    /**
     *
     */
    initializeData() {
        Promise.all([
            this.fetchAllTodos(),
            this.fetchAcceptMyStudy(),
            this.fetchManageMyStudy()
        ]).then(() => {
            this.refreshCalendar(); // 모든 데이터가 로드된 후 캘린더를 새로고침
        }).catch(error => {
            console.error('Error initializing data:', error);
        });
    }

    /**
     * 가입완료 스터디 목록
     */
    fetchAcceptMyStudy() {
        fetch(`/api/todolists/tasksAccept/${this.email}`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(studies => {
                const acceptedStudies = studies.map(study => ({
                    title: study.studyName,
                    start: study.firstDate,
                    end: study.stEndDate,
                    backgroundColor: '#9d94ff', // 예를 들어 수락된 스터디에 대한 색상
                    borderColor: '#9d94ff'
                }));
                this.calenderJson.push(...acceptedStudies); // 캘린더 JSON에 추가
                this.refreshCalendar(); // 캘린더를 새로고침
            })
            .catch(error => console.error('Error fetching accepted studies:', error));
    }

    /**
     * 방장인 스터디
     */
    fetchManageMyStudy() {
        fetch(`/api/todolists/tasksManaged/${this.email}`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(studies => {
                const managedStudies = studies.map(study => ({
                    title: study.studyName,
                    start: study.firstDate,
                    end: study.stEndDate,
                    backgroundColor: '#ff9d94', // 예를 들어 관리 중인 스터디에 대한 색상
                    borderColor: '#ff9d94'
                }));
                this.calenderJson.push(...managedStudies); // 캘린더 JSON에 추가
                this.refreshCalendar(); // 캘린더를 새로고침
            })
            .catch(error => console.error('Error fetching managed studies:', error));
    }

    /**
     * 캘린더 새로고침
     */
    refreshCalendar() {
        const calendarEl = document.getElementById('calendar');
        const calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            events: this.calenderJson
        });
        calendar.render(); // 캘린더를 다시 렌더링하여 변경사항 반영
    }


    // ------------------
    /**
     * 투두리스트 버튼 클릭 시 팝업 창 이벤트
     * @param event
     */
    handlePopupClick(event) {
        const todolistContainer = document.getElementById("todolistContainer");
        event.stopPropagation();
        clearTimeout(this.todolistExitTimeout);
        todolistContainer.classList.remove("fade", "d-none");
        this.todolistPopupTimeout = setTimeout(() => {
            todolistContainer.classList.add("show");
        }, 0);
    }

    /** 투두리스트 x버튼 클릭 시 이벤트
     * @param event
     */
    handleExitClick() {
        const todolistContainer = document.getElementById("todolistContainer");
        clearTimeout(this.todolistPopupTimeout);
        this.todolistExitTimeout = setTimeout(() => {
            todolistContainer.classList.remove("show");
            todolistContainer.classList.add("fade", "d-none");
        }, 600);
    }

    /**
     * 투두리스트 영역 외 클릭 시 이벤트
     * @param event
     */
    handleOutsideClick(event) {
        const todolistContainer = document.getElementById("todolistContainer");
        if (!todolistContainer.contains(event.target)) {
            clearTimeout(this.todolistPopupTimeout);
            this.todolistExitTimeout = setTimeout(() => {
                todolistContainer.classList.remove("show");
                todolistContainer.classList.add("fade", "d-none");
            }, 600);
        }
    }

    /**
     * 투두리스트 작성시 OnClick 이벤트
     * @param e
     */
    submitTodo(e) {
        e.preventDefault(); //새로고침 방지
        const todoTitle = document.getElementById("todoTitle").value;
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;

        if (!todoTitle || !startDate || !endDate) {
            alert("내용과 날짜를 모두 입력해주세요.");
            return;
        } else if (new Date(startDate) > new Date(endDate)) {
            alert("시작 날짜는 종료 날짜보다 이후일 수 없습니다.");
            return;
        }

        alert("할 일 리스트에 업로드 되었습니다.");

        fetch('/api/todolists', {
            method: 'POST',
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify({
                todolistContent: todoTitle,
                todoStartDate: startDate,
                todoEndDate: endDate,
                memberEmail: this.email
            })
        })
            .then(response => response.json())
            .then(data => {
                this.addTodoItemToList(document.getElementById('todoListing'), data);
                document.getElementById('todoTitle').value = '';
                document.getElementById('startDate').value = '';
                document.getElementById('endDate').value = '';
                this.fetchAllTodos();
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

    /**
     * 입렫된 투두 화면 출력
     * @param listElement
     * @param todo
     */
    addTodoItemToList(listElement, todo) {
        const listItem = document.createElement('li');
        listItem.className = 'justify-content-between m-1 row shadow p-3 mb-2 bg-body rounded';
        listItem.setAttribute('id', `todo-${todo.toNo}`);
        listItem.innerHTML = `
            <div class="col">
                <strong class="row">${todo.todolistContent}</strong>
                <div class="row">${todo.todoStartDate} ~ ${todo.todoEndDate}</div>
            </div>
            <div class="col-auto">
                <button id="todoComplete-${todo.toNo}" class="col btn btn-info mb-1">V</button>
                <button id="todoDelete-${todo.toNo}" class="col btn btn-danger mt-1">X</button>
            </div>
        `;
        listElement.appendChild(listItem);

        listItem.querySelector(`#todoComplete-${todo.toNo}`).addEventListener('click', () => this.isCompletedTrue(todo.toNo));
        listItem.querySelector(`#todoDelete-${todo.toNo}`).addEventListener('click', () => this.isDeletedTrue(todo.toNo));
    }

    /**
     * 할일 완료 처리
     * @param toNo
     */
    isCompletedTrue(toNo) {
        fetch(`/api/todolists/completed/${toNo}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'}
        })
            .then(() => {
                alert('Todo marked as completed!');
                const todoItem = document.querySelector(`#todo-${toNo}`);
                if (todoItem) {
                    document.getElementById('todoListCompleted').appendChild(todoItem);
                }
                this.fetchAllTodos();
            })
            .catch(error => console.error("Error marking todo as completed:", error));
    }

    /**
     * 할일 삭제 처리
     * @param toNo
     */
    isDeletedTrue(toNo) {
        fetch(`/api/todolists/delete/${toNo}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'}
        })
            .then(() => {
                alert('Todo marked as deleted!');
                const todoItem = document.querySelector(`#todo-${toNo}`);
                if (todoItem) {
                    document.getElementById('todoListDeleted').appendChild(todoItem);
                }
                this.fetchAllTodos();
            })
            .catch(error => console.error("Error marking todo as deleted:", error));
    }

    /**
     *
     */
    fetchAllTodos() {
        fetch(`/api/todolists`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(todos => {
                const todoList = document.getElementById('todoListing');
                todoList.innerHTML = '';
                todos.forEach(todo => {
                    this.addTodoItemToList(todoList, todo);
                });
            })
            .catch(error => {
                console.error("Error fetching todos:", error);
            });
    }

    showAllTodos() {
        this.fetchAllTodos();
    }

    showCompletedTodos() {
        fetch(`/api/todolists/completed`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(todos => {
                const todoListCompleted = document.getElementById('todoListCompleted');
                todoListCompleted.innerHTML = '';
                todos.forEach(todo => {
                    this.addTodoItemToList(todoListCompleted, todo);
                });
            })
            .catch(error => console.error('Error fetching completed todos:', error));
    }

    showDeletedTodos() {
        fetch(`/api/todolists/deleted`, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })
            .then(response => response.json())
            .then(todos => {
                const todoListDeleted = document.getElementById('todoListDeleted');
                todoListDeleted.innerHTML = '';
                todos.forEach(todo => {
                    this.addTodoItemToList(todoListDeleted, todo);
                });
            })
            .catch(error => console.error('Error fetching deleted todos:', error));
    }
}

customElements.define("custom-calender", Dashboard);