package com.comcast.stringinator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.comcast.stringinator.controller.StringinatorController;
import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.service.StringinatorService;
import com.comcast.stringinator.util.Utils;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
  @InjectMocks private StringinatorController stringinatorController;
  @Mock private StringinatorService stringinatorService;

  @Test
  public void getStringinateGet() {
    when(stringinatorService.stringinate(Mockito.any())).thenReturn(stringinatorResult());
    ResponseEntity<StringinatorResult> stringinatorResult =
        stringinatorController.stringinateGet(Utils.INPUT);
    assertThat(stringinatorResult.getStatusCode().value(), is(HttpStatus.OK.value()));
  }

  @Test
  public void getStringinatePost() {
    when(stringinatorService.stringinate(Mockito.any())).thenReturn(stringinatorResult());
    ResponseEntity<StringinatorResult> stringinatorResult =
        stringinatorController.stringinate(stringinatorInput());
    assertThat(stringinatorResult.getStatusCode().value(), is(HttpStatus.OK.value()));
  }

  @Test
  public void getStats() throws IOException {
    ResponseEntity<StatsResult> statsResult =
        stringinatorController.stats();
    assertThat(statsResult.getStatusCode().value(), is(HttpStatus.OK.value()));
  }

  public StringinatorResult stringinatorResult() {
    StringinatorResult stringinatorResult = new StringinatorResult(Utils.INPUT, null, 8);
    return stringinatorResult;
  }

  public StringinatorInput stringinatorInput() {
    StringinatorInput stringinatorInput = new StringinatorInput(Utils.INPUT);
    return stringinatorInput;
  }
}
