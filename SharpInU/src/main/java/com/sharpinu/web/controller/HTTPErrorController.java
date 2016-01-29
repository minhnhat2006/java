package com.sharpinu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharpinu.constant.WebConstants;

/**
 *
 * @author Mark
 *
 */
@Controller
public class HTTPErrorController {

    @RequestMapping(value="/contest404")
    public String handle404() {
    	return WebConstants.Views.CONTEST404;
    }

}