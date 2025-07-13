package com.project.oln.model.graph.enums;
import com.project.oln.exceptions.OperationNotAllowedException;

public enum NodesType {
    WAREHOUSE("Depósito"),
    DELIVERYPOINT("Punto de Entrega"), 
    INTERSECTION("Intersección");
    
    private final String displayName;

    NodesType(String displayName) {
        this.displayName = displayName;
    }

    public static NodesType fromString(String name) {
        if (name == null) {
            return null;
        }

        String nameTrim = name.trim();

        for (NodesType type : NodesType.values()) {
            String displayNameTrim = type.displayName.trim();
            if (displayNameTrim.equals(nameTrim)) {
                return type;
            }
        }
        try {
            return NodesType.valueOf(nameTrim.toUpperCase());
        } catch (Exception e) {
            throw new OperationNotAllowedException("The type is not correct: " + name);
        }
    }
}
