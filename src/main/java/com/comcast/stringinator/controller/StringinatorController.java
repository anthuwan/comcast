package com.comcast.stringinator.controller;

import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.service.StringinatorService;
import com.comcast.stringinator.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StringinatorController {

  @Autowired private StringinatorService stringinatorService;

  @GetMapping("/")
  public String index() {
    return "<pre>\n"
        + "Welcome to the Stringinator 3000 for all of your string manipulation needs.\n"
        + "GET / - You're already here! \n"
        + "POST /stringinate - Get all of the info you've ever wanted about a string. Takes JSON of the following form: {\"input\":\"your-string-goes-here\"}\n"
        + "GET /stats - Get statistics about all strings the server has seen, including the longest and most popular strings.\n"
        + "</pre>";
  }

  @GetMapping(path = "/stringinate", produces = "application/json")
  public ResponseEntity<StringinatorResult> stringinateGet(
      @RequestParam(name = "input", required = true) String input) {
    log.info("Request Started for stringinate Get");
    HttpStatus httpStatus = HttpStatus.OK;
    return new ResponseEntity<>(
        stringinatorService.stringinate(
            new StringinatorInput(
                input.replaceAll(Utils.ALPHANUMERIC_REGEX_PATTERN, StringUtils.EMPTY))),
        httpStatus);
  }

  @PostMapping(path = "/stringinate", consumes = "application/json", produces = "application/json")
  public ResponseEntity<StringinatorResult> stringinate(@RequestBody StringinatorInput input) {
    log.info("Request Started for stringinate POST");
    HttpStatus httpStatus = HttpStatus.OK;
    return new ResponseEntity<>(stringinatorService.stringinate(input), httpStatus);
  }

  @GetMapping(path = "/stats")
  public ResponseEntity<StatsResult> stats() {
    log.info("Request Started for stats Get");
    HttpStatus httpStatus = HttpStatus.OK;
    return new ResponseEntity<>(stringinatorService.stats(),httpStatus);
  }
}
