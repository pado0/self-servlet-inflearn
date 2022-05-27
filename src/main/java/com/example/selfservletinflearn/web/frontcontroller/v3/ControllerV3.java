package com.example.selfservletinflearn.web.frontcontroller.v3;

import com.example.selfservletinflearn.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // v4에서 모델뷰를 없애줄 것
    ModelView process(Map<String, String> paramMap);
}
