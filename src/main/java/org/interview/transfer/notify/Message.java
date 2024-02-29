package org.interview.transfer.notify;

public record Message(String msg) {
    public String getMsg() {
        return msg;
    }
}
