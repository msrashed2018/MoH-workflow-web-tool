package com.almostkbal.web.services.workflow.entities;

public enum DocumentType {
	PERSONAL("personal"),
	MEDICAL("medical");

	DocumentType(final String name) {
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
