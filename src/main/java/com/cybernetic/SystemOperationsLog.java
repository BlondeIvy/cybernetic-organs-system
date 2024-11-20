package com.cybernetic;

import java.util.ArrayList;
import java.util.List;

public class SystemOperationsLog {
    private SystemOperation[] stack;
    private int top;
    private final int capacity;

    public SystemOperationsLog(int capacity) {
        this.capacity = capacity;
        this.stack = new SystemOperation[capacity];
        this.top = -1;
    }

    public void pushOperation(SystemOperation operation) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack overflow");
        }
        stack[++top] = operation;
    }

    public SystemOperation popLastOperation() {
        if (top == -1) {
            throw new IllegalStateException("Stack underflow");
        }
        return stack[top--];
    }

    public SystemOperation peekLastOperation() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    public List<SystemOperation> getRecentOperations(int count) {
        List<SystemOperation> recent = new ArrayList<>();
        for (int i = top; i >= Math.max(0, top - count + 1); i--) {
            recent.add(stack[i]);
        }
        return recent;
    }
}