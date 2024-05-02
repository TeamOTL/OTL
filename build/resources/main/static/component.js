class sidebar extends HTMLElement {

    connectedCallback() {
       this.innerHTML = `
       <!-- Sidebar -->
       <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

           <!-- Sidebar - Brand -->
           <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
               <div class="sidebar-brand-icon rotate-n-15">
                   <i class="fas fa-laugh-wink"></i>
               </div>
               <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
           </a>

           <hr>
           <!-- Nav Item - Dashboard -->
           <li class="nav-item">
               <a class="nav-link" href="index.html">
                   <i class="fas fa-fw fa-tachometer-alt"></i>
                   <span>대시보드</span></a>
           </li>

           <!-- Nav Item - Pages Collapse Menu -->
           <li class="nav-item">
               <a class="nav-link" href="#">
                   <i class="fas fa-fw fa-chart-area"></i>
                   <span>게시판</span></a>
           </li>

           <!-- Nav Item - Pages Collapse Menu -->
           <li class="nav-item">
               <a class="nav-link" href="#">
                   <i class="fas fa-fw fa-chart-area"></i>
                   <span>스터디룸</span></a>
           </li>

           <!-- Nav Item - Pages Collapse Menu -->
           <li class="nav-item">
               <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                   aria-expanded="true" aria-controls="collapsePages">
                   <i class="fas fa-fw fa-folder"></i>
                   <span>스터디 모집</span>
               </a>
               <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                   <div class="bg-white py-2 collapse-inner rounded">
                       <h6 class="collapse-header">내가 가입한 스터디:</h6>
                       <a class="collapse-item" href="login.html">Login</a>
                       <a class="collapse-item" href="register.html">Register</a>
                       <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                       <div class="collapse-divider"></div>
                       <h6 class="collapse-header">Other Pages:</h6>
                       <a class="collapse-item" href="404.html">404 Page</a>
                       <a class="collapse-item" href="blank.html">Blank Page</a>
                   </div>
               </div>
           </li>

           <!-- Nav Item - Charts -->
           <li class="nav-item">
               <a class="nav-link" href="#">
                   <i class="fas fa-fw fa-chart-area"></i>
                   <span>환경설정</span></a>
           </li>

        <hr>
           <!-- Sidebar Toggler (Sidebar) -->
           <div class="text-center d-none d-md-inline">
               <button class="rounded-circle border-0" id="sidebarToggle"></button>
           </div>

       </ul>
       <!-- End of Sidebar -->`;
    }
 }
 
 customElements.define("custom-sidebar", sidebar);