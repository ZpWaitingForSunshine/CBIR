package com.njust.cbir.web.service.impl;

import com.njust.cbir.web.dao.TokenMapper;
import com.njust.cbir.web.model.Token;
import com.njust.cbir.web.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public int insert(Token token) {
        return tokenMapper.insert(token);
    }

    @Override
    public int update(Token token) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Token selectById(Long id) {
        return null;
    }

    @Override
    public Token selectOne() {
        return null;
    }

    @Override
    public List<Token> selectList() {
        return null;
    }
}
