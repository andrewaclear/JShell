package runtime;

import io.StandardOutput;

import java.lang.reflect.Array;
import java.util.Arrays;

import commands.*;

public class ErrorHandler {

    public static void Exit(String[] tokens) {
        StandardOutput.println(Exit.badInput+Arrays.toString(Arrays.copyOfRange(tokens, 1, tokens.length)));
    }
}
