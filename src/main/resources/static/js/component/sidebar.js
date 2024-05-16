class sidebar extends HTMLElement {
    connectedCallback() {
        const path = window.location.pathname;
        let sPath = "";

        if (path.indexOf("templates") > 0) sPath = "../static/";

        this.innerHTML = `
       <!-- Sidebar -->
       <ul class="navbar-nav bg-dong sidebar sidebar-dark accordion " id="accordionSidebar">

           <!-- Sidebar - Brand -->
           <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/dashBoard">
               <div class="sidebar-brand-icon">
                   <img src="${sPath}img/main_icon.svg" />
               </div>
           </a>

           <br>

           <!-- Nav Item - Dashboard -->
           <li class="nav-item">
               <a class="nav-link list-group-item-action list-group-item-danger rounded text-gray-900"
                   href="/dashBoard">
                   <i class="fas fa-fw fa-th-large"></i>
                   <span>대시보드</span></a>
           </li>
           <!-- Nav Item - Board -->
           <li class="nav-item">
               <a class="nav-link list-group-item-action list-group-item-danger rounded text-gray-900"
                   href="/board">
                   <i class="fas fa-fw fa-clipboard"></i>
                   <span>게시판</span></a>
           </li>

           <!-- Nav Item - Pages Collapse Menu -->
           <li class="nav-item">
               <a class="nav-link list-group-item-action list-group-item-danger rounded text-gray-900" href="#"
                   data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo"
                   id="studyRoomToggle">
                   <i class="fas fa-fw fa-users"></i>
                   <span>스터디룸</span>
               </a>
               <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                   <div class="bg-white py-2 collapse-inner rounded">
                       <h6 class="collapse-header">나의 스터디룸</h6>
                       <div id="myStudyRooms" href="studyRoom_yu"></div>
                   </div>
               </div>
           </li>

           <!-- Nav Item - Study Recruitment -->
           <li class="nav-item">
               <a class="nav-link list-group-item-action list-group-item-danger rounded text-gray-900"
                   href="/studyJoin">
                   <i class="fas fa-fw fa-user-plus"></i>
                   <span>스터디 모집</span></a>
           </li>

           <!-- Sidebar Toggler (Sidebar) -->
           <div class="text-center d-none d-md-inline">
               <button class="rounded-circle border-0 bg-light" id="sidebarToggle"></button>
           </div>
           
           <div class="text-center d-none d-md-inline">
               <button class="rounded-circle border-0 bg-secondary" id="sidebarToggle"></button>
           </div>

       </ul>
       <!-- End of Sidebar -->
       `;
        this.setupEventListeners();
    }

    setupEventListeners() {
        const studyRoomToggle = this.querySelector('#studyRoomToggle');
        studyRoomToggle.addEventListener('click', async (event) => {
            event.preventDefault();
            await this.loadStudyRooms();
            const collapseElement = this.querySelector('#collapseTwo');
            if (!collapseElement.classList.contains('show')) {
                await this.loadStudyRooms();
            }
            collapseElement.classList.toggle('show');
        });
    }

    // 스터디룸 데이터를 로드하는 함수
    async loadStudyRooms() {
        try {
            console.log("스터디룸 데이터 로드 시도"); // 디버그 로그
            const response = await fetch('/api/studyRooms'); // 올바른 API 엔드포인트로 수정
            if (!response.ok) {
                throw new Error("API 요청 실패");
            }
            const studyRooms = await response.json();
            console.log("스터디룸 데이터 로드 성공", studyRooms); // 디버그 로그
            if (!Array.isArray(studyRooms)) {
                throw new Error("API 응답이 배열이 아닙니다.");
            }
            this.updateStudyRooms(studyRooms);
        } catch (error) {
            console.error('스터디룸 데이터 로드 중 오류:', error); // 한글 로그
        }
    }

    // 스터디룸 데이터를 업데이트하는 함수
    updateStudyRooms(studyRooms) {
        const studyRoomsContainer = this.querySelector('#myStudyRooms');
        studyRoomsContainer.innerHTML = ''; // 기존 내용 초기화

        studyRooms.forEach(room => {
            if (room.sno && room.studyName) { // room 객체에 sno와 studyName 속성이 존재하는지 확인
                const roomElement = document.createElement('a');
                roomElement.className = 'collapse-item';
                roomElement.href = `/studyRoom_yu/${room.sno}`; // 각 스터디룸으로 이동할 수 있도록 링크 설정
                roomElement.innerHTML = `<div>${room.studyName}</div>`; // 스터디 이름으로 표시
                studyRoomsContainer.appendChild(roomElement);
            } else {
                console.error('스터디룸 데이터에 sno 또는 studyName 속성이 없습니다:', room);
            }
        });
    }
}

customElements.define("custom-sidebar", sidebar);
