package com.thryve.backendTask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.controller.model.output.HeartRateOutput;
import com.thryve.backendTask.dao.EventDaoImpl;
import com.thryve.backendTask.dao.HealthDataDaoImpl;
import com.thryve.backendTask.model.HeartRateEntity;
import com.thryve.backendTask.repo.DataRepository;
import com.thryve.backendTask.repo.EventRepository;
import com.thryve.backendTask.service.HealthServiceImpl;
import com.thryve.backendTask.service.eventstore.EventPublisher;
import com.thryve.backendTask.service.eventstore.EventReceiver;
import com.thryve.backendTask.util.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({HealthServiceImpl.class, HealthDataDaoImpl.class,
        AuthTokenValidatorImplemented.class, EventPublisher.class,
        EventReceiver.class, EventDaoImpl.class})
@WebMvcTest(value = {DataController.class})
class DataControllerTest {

    private final static String BASE_URL = "/heartRates";

    @MockBean
    private DataRepository dataRepository;


    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void save_shouldSaveData() throws Exception {
        Object json = readJsonFile("src/test/resources/validData.json");

        sendPostSaveRequest("/save", HttpStatus.OK, json);

        verify(dataRepository, times(1)).saveAll(any());
        verify(eventRepository, times(1)).save(any());
    }


    @Test
    public void save_shouldNotSaveData_whenTokenIsMissing(){
        //TODO: implement test use missingToken.json
    }



    @Test
    public void getAll_shouldReturnData() throws Exception {
        final List<HeartRateEntity> heartRateEntities = createHeartRateEntities(20, 8);
        final List<HeartRateOutput> expectedOutput = createHeartRateOutput(heartRateEntities);
        when(dataRepository.findAll()).thenReturn(heartRateEntities);

        final String actualResponse = sendGetAllRequest("/all", HttpStatus.OK);
        final String expected = objectMapper.writeValueAsString(expectedOutput);
        assertEquals(expected, actualResponse);
    }

    @Test
    public void getAll_shouldNotFailIfNoDataExists() throws Exception {
        when(dataRepository.findAll()).thenReturn(new ArrayList<>());

        final String actualResponse = sendGetAllRequest("/all", HttpStatus.OK);
        assertEquals("[]", actualResponse);
    }

    @Test
    public void getByUser_shouldReturnData() throws Exception {
        final int userId = 8;
        final List<HeartRateEntity> heartRateEntities = createHeartRateEntities(20, userId);
        final List<HeartRateOutput> expectedOutput = createHeartRateOutput(heartRateEntities);
        when(dataRepository.findByUserId(userId)).thenReturn(heartRateEntities);

        final String actualResponse = sendGetAllRequest("/user?userId="+userId, HttpStatus.OK);
        final String expected = objectMapper.writeValueAsString(expectedOutput);
        assertEquals(expected, actualResponse);
    }


    @Test
    public void getAverage_shouldReturnData() throws Exception {
        final int userId = 8;
        final List<HeartRateEntity> heartRateEntities = createHeartRateEntities(20, userId);
        final Double expectedAverageHeartRate = calculateAverageHeartRate(heartRateEntities);
        when(dataRepository.findByUserId(userId)).thenReturn(heartRateEntities);

        final String actualResponse = sendGetAllRequest("/user/average?userId="+userId, HttpStatus.OK);
        final AverageHeartRateOutput actual = objectMapper.readValue(actualResponse, AverageHeartRateOutput.class);
        assertEquals(expectedAverageHeartRate, actual.getAverageHeartRate());
    }


    private String sendGetAllRequest(final String url, final HttpStatus httpStatus) throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = get(BASE_URL + url);
        final MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().is(httpStatus.value())).andReturn();
        return result.getResponse().getContentAsString();
    }

    private int sendPostSaveRequest(final String url, final HttpStatus httpStatus, final Object json) throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = post(BASE_URL + url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(json));
        final MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().is(httpStatus.value())).andReturn();
        return result.getResponse().getStatus();
    }

    private List<HeartRateEntity> createHeartRateEntities(int size, int userId) {
        final List<HeartRateEntity> heartRateEntities = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            long heartRate = ThreadLocalRandom.current().nextLong(45, 169+1);
            heartRateEntities.add(new HeartRateEntity(null, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), null,
                    3000, heartRate, userId));
        }
        return heartRateEntities;
    }

    private List<HeartRateOutput> createHeartRateOutput(final List<HeartRateEntity> heartRateEntities) {
        return Mapper.mapDtosToHeartRatesOutput(Mapper.mapEntitiesToDtos(heartRateEntities));
    }

    private Double calculateAverageHeartRate(final List<HeartRateEntity> heartRateEntities) {
        double averageHeartRate = heartRateEntities.stream()
                .mapToDouble(heartRateDto -> heartRateDto.getValue())
                .average().orElse(Double.NaN);
        DecimalFormat df = new DecimalFormat("#");
        return Double.valueOf(df.format(averageHeartRate));
    }

    private Object readJsonFile(final String file) throws IOException, ClassNotFoundException {
        Object o = objectMapper.readValue(new File(file), Object.class);
        return o;
    }
}