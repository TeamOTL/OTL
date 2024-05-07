class scrollTopBTN extends HTMLElement {

    connectedCallback() {
        this.innerHTML = `
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
       `;
    }
}

customElements.define("custom-scroll-top-btn", scrollTopBTN);