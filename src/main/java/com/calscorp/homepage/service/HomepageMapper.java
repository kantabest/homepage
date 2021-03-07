package com.calscorp.homepage.service;

import java.util.List;
import java.util.Map;

import com.calscorp.homepage.common.pojo.contact;
import com.calscorp.homepage.common.pojo.content;
import com.calscorp.homepage.common.pojo.field;
import com.calscorp.homepage.common.pojo.fieldItem;
import com.calscorp.homepage.common.pojo.history;
import com.calscorp.homepage.common.pojo.specification;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomepageMapper {
    
    List<Map<String, String>> selectCateList(String langType);
    List<field> selectFieldList(Map<String, Object> param);
    List<fieldItem> selectFieldItemList(Map<String, Object> param);
    List<specification> selectItemSpecList(Map<String, Object> param);
    List<Map<String, String>> selectMsgList(String langType);
    List<history> selectHistoryList(String langType);
    List<content> selectContentsCodeList(Map<String, Object> param);
    List<contact> selectContactList(String langType);
    List<Map<String, String>> selectFooterList(String langType);
    int selectblog_Count(Map<String, String> param);
    List<Map<String, String>> selectFileSearchList(String lantType);
}

