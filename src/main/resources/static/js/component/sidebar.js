class sidebar extends HTMLElement {

    connectedCallback() {
        const path = window.location.pathname;
        let sPath = "";

        if(path.indexOf("templates") > 0)
            sPath = "../static/";

        this.innerHTML = `
       <!-- Sidebar -->
       <ul class="navbar-nav bg-dong sidebar sidebar-dark accordion " id="accordionSidebar">

           <!-- Sidebar - Brand -->
           <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/dashBoard">
               <div class="sidebar-brand-icon">
                   <img src="${sPath}img/main_icon.svg" />
               </div>
               <!-- <div class="sidebar-brand-text mx-3"><b1 class="text-black-50">OTL</b1></div> -->
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
                   data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                   <i class="fas fa-fw fa-users"></i>
                   <span>스터디룸</span>
               </a>
               <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                   <div class="bg-white py-2 collapse-inner rounded">
                       <h6 class="collapse-header">나의 스터디룸</h6>
                       <a class="collapse-item" href="/studyRoom_yu"><img src="${sPath}img/Property 1=linux.svg">수연이와 리눅스</a>
                       <a class="collapse-item" href=""><img src="${sPath}img/Property 1=spring.svg">강현과 봄남자</a>
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
    }
}

customElements.define("custom-sidebar", sidebar);
