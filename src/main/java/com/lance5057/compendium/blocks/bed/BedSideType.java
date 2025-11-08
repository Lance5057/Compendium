package com.lance5057.compendium.blocks.bed;

import net.minecraft.util.StringRepresentable;

public enum BedSideType implements StringRepresentable {
	CENTER("center"),
    LEFT("left"),
    RIGHT("right"),
	SINGLE("single");

    private final String name;

    private BedSideType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public BedSideType getOpposite() {
        return switch (this) {
            case CENTER -> CENTER;
            case SINGLE -> SINGLE;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
