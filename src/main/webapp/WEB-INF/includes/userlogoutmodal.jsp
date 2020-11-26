<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="modal" tabindex="-1" id="logoutModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Log ud</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Er du sikker på at du vil logge ud ${sessionScope.user.name}?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Log ud</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Nej</button>
            </div>
        </div>
    </div>
</div>