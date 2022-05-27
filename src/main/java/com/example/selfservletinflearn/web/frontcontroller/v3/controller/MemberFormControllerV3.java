package com.example.selfservletinflearn.web.frontcontroller.v3.controller;

import com.example.selfservletinflearn.web.frontcontroller.ModelView;
import com.example.selfservletinflearn.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 논리 명만 리턴
        return new ModelView("new-form");
    }
}
