package com.zjt.graduation.designpattern.factory.complicated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalFuture extends Future{

    private String future;

    @Override
    public Future futrue(String future) {
        return new NormalFuture(future);
    }
}
