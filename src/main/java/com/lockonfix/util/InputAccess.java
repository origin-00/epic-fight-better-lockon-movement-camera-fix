package com.lockonfix.util;

import net.minecraft.client.player.ClientInput;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.phys.Vec2;

/**
 * Reads and writes the movement input as the pre-1.21.2 mutable {@code Input} fields did. Since
 * 1.21.2 the input is a {@link ClientInput}: impulses live in its move vector (x = strafe, y =
 * forward) and the pressed keys in an immutable {@link Input} record.
 */
public final class InputAccess {
    private InputAccess() {
    }

    public static float forward(ClientInput input) {
        return input.getMoveVector().y;
    }

    public static float strafe(ClientInput input) {
        return input.getMoveVector().x;
    }

    public static void setForward(ClientInput input, float forward) {
        input.moveVector = new Vec2(input.getMoveVector().x, forward);
    }

    public static void setStrafe(ClientInput input, float strafe) {
        input.moveVector = new Vec2(strafe, input.getMoveVector().y);
    }

    public static boolean shift(ClientInput input) {
        return input.keyPresses.shift();
    }

    public static void setUp(ClientInput input, boolean value) {
        Input k = input.keyPresses;
        input.keyPresses = new Input(value, k.backward(), k.left(), k.right(), k.jump(), k.shift(), k.sprint());
    }

    public static void setDown(ClientInput input, boolean value) {
        Input k = input.keyPresses;
        input.keyPresses = new Input(k.forward(), value, k.left(), k.right(), k.jump(), k.shift(), k.sprint());
    }

    public static void setLeft(ClientInput input, boolean value) {
        Input k = input.keyPresses;
        input.keyPresses = new Input(k.forward(), k.backward(), value, k.right(), k.jump(), k.shift(), k.sprint());
    }

    public static void setRight(ClientInput input, boolean value) {
        Input k = input.keyPresses;
        input.keyPresses = new Input(k.forward(), k.backward(), k.left(), value, k.jump(), k.shift(), k.sprint());
    }
}
