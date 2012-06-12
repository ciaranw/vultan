package com.ciaranwood.vultan.codec;

import java.util.Stack;

public class VersionStack {

    public static final VersionStack INSTANCE = new VersionStack();

    private final Stack<Integer> stack;

    public VersionStack() {
        this.stack = new Stack<Integer>();
    }

    public void pushVersion(Integer version) {
        stack.push(version);
    }

    public Integer getCurrentVersion() {
        return stack.peek();
    }

    public Integer popVersion() {
        return stack.pop();
    }
}
