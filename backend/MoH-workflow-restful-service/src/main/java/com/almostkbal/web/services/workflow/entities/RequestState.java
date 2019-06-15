package com.almostkbal.web.services.workflow.entities;

public enum RequestState {
//	OLD("old"),
//	NEW("new"),
	PENDING_PAYMENT("pending-payment"),
//	PAYMENT_DONE("payment-done"),
	PENDING_CONTINUE_REGISTERING("pending-continue-registering"),
	CONTINUE_REGISTERING_DONE("continue-registering-done"),


//	REVEALS_DONE("reveals-done"),
//
//	EYE_REVEAL("eye-reveal"),
//	BONES_REVEAL("bones-reveal"),
//
//	PENDING_REVEALS_REGISTERING("pending-reveals-registering"),
//
//	EYE_REVEAL_REGISTERED("eye-reveal-registered"), BONES_REVEAL_REGISTERED("bones-reveal-registered"),

	REVIEWED("reviewed");
    
    RequestState(final String name) {
        this.name = name;
	}
    private final String name;

    @Override
    public String toString() {
        return name;
    }

	public String getName() {
		return name;
	}
    
}
