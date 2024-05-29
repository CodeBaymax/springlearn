package com.learn.mttransaction;


import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class TaskError {

    private AtomicBoolean errorFlag;

    public void setErrorFlag() {
        errorFlag.set(true);
    }
}
