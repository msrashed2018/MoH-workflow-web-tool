package com.almostkbal.web.services.workflow.entities;

public enum RequestState {
	OLD("old"),
	NEW("new"),
	PAYMENT("payment"),
	DOCUMENTS_SCANNING("document-scanning"),
	EYE_REVEAL("eye-reveal"),
	BONES_REVEAL("bones-reveal"),
	REVEAL_REGISTERING("reveal-registering"),
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
