
class footer extends HTMLElement {

connectedCallback() {
    this.innerHTML = `
    <div class="row">
    <div class="col-lg-6 mb-4">
        <!-- Illustrations -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Illustrations</h6>
            </div>
            <div class="card-body">
                <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
                        src="img/undraw_posting_photo.svg" alt="...">
                </div>
                <p></p>
            </div>
        </div>
    </div>
    </div>
   `;
}
}

customElements.define("custom-footer", sidebar);