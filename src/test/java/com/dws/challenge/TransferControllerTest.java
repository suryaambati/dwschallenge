package com.dws.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;

import com.dws.challenge.domain.Account;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.TransferService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class TransferControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  void prepareMockMvc() {
    this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
  
  }

  @Test
  void TransferAmount() throws Exception {
    this.mockMvc.perform(post("/v1/transfer").contentType(MediaType.APPLICATION_JSON)
      .content("{\"accountFromId\":\"Id1\", \\\"accountToId\\\":\\\"Id2\\\",\"balance\":1000}")).andExpect(status().isCreated());

    
  }


  
 

  @Test
  void TransferAmountNegativeBalance() throws Exception {
    this.mockMvc.perform(post("/v1/transfer").contentType(MediaType.APPLICATION_JSON)
      .content("{\"accountFromId\":\"Id1\", \\\"accountToId\\\":\\\"Id2\\\",\"balance\":-1000}")).andExpect(status().isBadRequest());
  }

  @Test
  void  TransferAmountWithEmptyAccountId() throws Exception {
    this.mockMvc.perform(post("/v1/transfer").contentType(MediaType.APPLICATION_JSON)
      .content("{\"accountFromId\":\"\", \\\"accountToId\\\":\\\"Id2\\\",\"balance\":1000}")).andExpect(status().isBadRequest());
  }

  
}
