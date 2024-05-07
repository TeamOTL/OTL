class topbar extends HTMLElement {

    connectedCallback() {
        const path = window.location.pathname;
        let sPath = "";
        
        if(path.indexOf("templates") > 0)
             sPath = "../static/";


        this.innerHTML = `
        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <!-- Sidebar Toggle (Topbar) -->
            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                <i class="fa fa-bars"></i>
            </button>

            <!-- Topbar Search -->


            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
                <!-- Nav Item - Search Dropdown (Visible Only XS) -->

                <!-- Nav Item - User Information -->
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" id="userDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">당후니</span>
                        <img class="img-profile rounded-circle" src="${sPath}img/꺄.jpeg" />
                    </a>
                    <!-- Dropdown - User Information -->
                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                        aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="${sPath}#">
                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                            프로필
                        </a>
                        <a class="dropdown-item" data-toggle="modal" data-target="#settingModal">
                            <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                            설정
                        </a>
                        <a class="dropdown-item" href="${sPath}#">
                            <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                            나의 스터디
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${sPath}#" data-toggle="modal" data-target="#logoutModal">
                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                            로그아웃
                        </a>
                    </div>
                </li>
            </ul>
        </nav>
       `;
    }
}

customElements.define("custom-topbar", topbar);