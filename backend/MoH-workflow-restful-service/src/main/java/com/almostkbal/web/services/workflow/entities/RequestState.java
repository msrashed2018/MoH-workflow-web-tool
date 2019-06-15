package com.almostkbal.web.services.workflow.entities;

public enum RequestState {
	OLD("old"),
	NEW("new"),
	PAYMENT_DONE("payment-done"),
	DOCUMENTS_SCANNING("document-scanning"),
	EYE_REVEAL("eye-reveal"),
	BONES_REVEAL("bones-reveal"),
	EYE_REVEAL_REGISTERED("eye-reveal-registered"), BONES_REVEAL_REGISTERED("bones-reveal-registered"),
	REVIEWING("reviewing");
    
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
