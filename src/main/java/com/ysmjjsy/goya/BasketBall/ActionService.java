package com.ysmjjsy.goya.BasketBall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActionService implements Runnable{
    private String name;
    private CompareChoose choose;

    @Override
    public void run() {

    }
}
