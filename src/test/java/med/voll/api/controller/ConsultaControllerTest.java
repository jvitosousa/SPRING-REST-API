package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsuta;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;

import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAngendamentoConsulta> dadosAngendamentoConsultaJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJacksonTester;

    @MockBean
    private AgendaDeConsuta agendaDeConsuta;


    @Test
    @DisplayName("Devolve codigo 400 quando as informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mockMvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Devolve codigo 200 quando as informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var dadosDetalhamentoConsulta = new DadosDetalhamentoConsulta( 2l, 5l, data);
        when(agendaDeConsuta.agendarConsulta(any())).thenReturn(dadosDetalhamentoConsulta);

        var response = mockMvc.perform(
                post("/consulta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAngendamentoConsultaJacksonTester.write(
                                new DadosAngendamentoConsulta(2l, 5l, data, Especialidade.CARDIOLOGIA)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJacksonTester.write(
                dadosDetalhamentoConsulta
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}