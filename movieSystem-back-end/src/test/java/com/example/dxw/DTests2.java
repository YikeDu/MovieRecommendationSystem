package com.example.dxw;

import com.example.dxw.movie.mapper.*;
import com.example.dxw.movie.service.impl.MailImpl;
import com.example.dxw.movie.service.impl.RatingsServiceImpl;
import org.junit.jupiter.api.Test;
import org.python.core.PyFunction;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
class DTests2 {
    @Autowired
    private SimUsersMapper simUsersMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RatingsServiceImpl ratingsService;
    @Autowired
    private PMovieMapper pMovieMapper;
    @Autowired
    private RatingsMapper ratingsMapper;
    @Autowired
    private PMovieXstjMapper pMovieXstjMapper;
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private LbMapper lbMapper;
    @Autowired
    private MailImpl mail;

    @Test
    void contextLoads() throws UnsupportedEncodingException {

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\1.py");
        PyFunction func = interpreter.get("open",PyFunction.class);

    }
}
