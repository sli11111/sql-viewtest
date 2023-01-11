package com.ysmjjsy.goya.BasketBall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@ToString
@NoArgsConstructor
@Slf4j
@AllArgsConstructor
@Component
public class RightRate {
    private double liangFen;
    private double sanFen;
    private double faQiu;
}
