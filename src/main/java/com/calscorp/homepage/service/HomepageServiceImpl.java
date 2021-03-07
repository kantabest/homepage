package com.calscorp.homepage.service;

import java.util.List;
import java.util.Map;

import com.calscorp.homepage.common.pojo.contact;
import com.calscorp.homepage.common.pojo.content;
import com.calscorp.homepage.common.pojo.field;
import com.calscorp.homepage.common.pojo.fieldItem;
import com.calscorp.homepage.common.pojo.history;
import com.calscorp.homepage.common.pojo.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomepageServiceImpl implements HomepageService {

    private final HomepageMapper homepageMapper;



    @Autowired
    public HomepageServiceImpl(HomepageMapper homepageMapper){
        // 생성자 주입
        this.homepageMapper = homepageMapper;
    }


    @Override
    public List<Map<String, String>> selectCateList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectCateList(langType);
    }

    @Override
    public List<field> selectFieldList(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return homepageMapper.selectFieldList(param);
    }

    @Override
    public List<fieldItem> selectFieldItemList(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return homepageMapper.selectFieldItemList(param);
    }

    @Override
    public List<specification> selectItemSpecList(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return homepageMapper.selectItemSpecList(param);
    }

    @Override
    public List<Map<String, String>> selectMsgList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectMsgList(langType);
    }

    @Override
    public List<history> selectHistoryList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectHistoryList(langType);
    }

    @Override
    public List<content> selectContentsCodeList(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return homepageMapper.selectContentsCodeList(param);
    }

    @Override
    public List<contact> selectContactList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectContactList(langType);
    }

    @Override
    public List<Map<String, String>> selectFooterList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectFooterList(langType);
    }

    @Override
    public int selectblog_Count(Map<String, String> param) {
        // TODO Auto-generated method stub
        return homepageMapper.selectblog_Count(param);
    }

    @Override
    public List<Map<String, String>> selectFileSearchList(String langType) {
        // TODO Auto-generated method stub
        return homepageMapper.selectFileSearchList(langType);
    }
    
}
