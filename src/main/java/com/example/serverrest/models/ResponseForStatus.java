package com.example.serverrest.models;



public class ResponseForStatus {
    private Long id;
    private boolean oldStatus;
    private boolean newStatus;

    public ResponseForStatus(Long id, boolean oldStatus, boolean newStatus) {
        this.id = id;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(boolean oldStatus) {
        this.oldStatus = oldStatus;
    }

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }
}
