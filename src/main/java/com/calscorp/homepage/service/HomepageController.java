package com.calscorp.homepage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.calscorp.homepage.common.pojo.field;
import com.calscorp.homepage.common.pojo.fieldItem;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HomepageController {

    private final HomepageService homepageService;

    @Autowired
    public HomepageController(HomepageService homepageService) {
    // 콘트롤러 생성자에서 서비스 주입
        this.homepageService = homepageService;
    }
    

    @RequestMapping({"/", "/index/{langType}/{selectedCateNo}", "/views/index/{langType}/{section}/{cateNo}"})
    public String index(Model model
                        ,@PathVariable Optional<String> langType                        
                        ,@PathVariable Optional<String> section                        
                        ,@PathVariable Optional<String> selectedCateNo) {
        

        String newlangType = "KR";
        int newCateNo = 1;
        
        if (langType.isPresent()) {
            newlangType = langType.get();   //returns the id
        }

        if (selectedCateNo.isPresent()) {
            newCateNo = Integer.parseInt(selectedCateNo.get());
        }

        // Product View used.        
        Map<String, Object> pField_List = new HashMap<String, Object>();
		pField_List.put("langType",newlangType);
		pField_List.put("cate_no",newCateNo);


        // Contents View used.        
        Map<String, Object> pCerti_List = new HashMap<String, Object>();
		pCerti_List.put("langType",newlangType);
		pCerti_List.put("contents_cd","CERTIFICATIONS");        


        // Contents View used.        
        Map<String, Object> pMachine_List = new HashMap<String, Object>();
		pMachine_List.put("langType",newlangType);
		pMachine_List.put("contents_cd","MACHINES_LABS");           



        model.addAttribute("langType", newlangType); 
        model.addAttribute("cateNo", newCateNo );    
        
        
        model.addAttribute("cate_list", homepageService.selectCateList(newlangType));        
        model.addAttribute("field_list", homepageService.selectFieldList(pField_List));        
        model.addAttribute("fieldItem_list", homepageService.selectFieldItemList(pField_List));

        
        //searchProduct
        model.addAttribute("filesearch_list", homepageService.selectFileSearchList(newlangType));

        

        //certifications
        model.addAttribute("certiList", homepageService.selectContentsCodeList(pCerti_List));


        //machinesLabs
        model.addAttribute("machinesList", homepageService.selectContentsCodeList(pMachine_List));        



        // Footer desc used        
        model.addAttribute("newLineChar", "\n");
        
        model.addAttribute("msgMap", getMessage(newlangType));         
        model.addAttribute("footerList", homepageService.selectFooterList(newlangType));        

        return "views/index";
    } 


    @RequestMapping("/views/vision/{langType}")
    public String vision(Model model
                        ,@PathVariable Optional<String> langType) {


        String newlangType = "KR";

        if (langType.isPresent()) {
            newlangType = langType.get();   //returns the id
        }

        model.addAttribute("newLineChar", "\n");


        model.addAttribute("langType", newlangType); 
        model.addAttribute("msgMap", getMessage(newlangType));         
        model.addAttribute("footerList", homepageService.selectFooterList(newlangType));       

        return "views/vision";        
    }

    @RequestMapping("/views/history/{langType}")
    public String history(Model model
                        ,@PathVariable Optional<String> langType) {


        String newlangType = "KR";

        if (langType.isPresent()) {
            newlangType = langType.get();   //returns the id
        }



        //편집 전.
        List<Map<String, String>> historyOriginList = homepageService.selectHistoryList(newlangType);
        List<Map<String, String>> updatedHistoryList = new ArrayList<>();


        // 빈칸 강제 추가
        int rowCnt = 1;
        for (Map<String,String> map : historyOriginList) {
            updatedHistoryList.add(map);

            if (rowCnt % 5 == 0) {
                Map<String, String> blankMap = new HashMap<>();
                blankMap.put("history_dt", "　");
                blankMap.put("history_subject", "");

                updatedHistoryList.add(blankMap);                
            }
            rowCnt++;
        }


        // Footer desc used        
        model.addAttribute("newLineChar", "\n");


        model.addAttribute("langType", newlangType); 
        model.addAttribute("msgMap", getMessage(newlangType));         
        model.addAttribute("footerList", homepageService.selectFooterList(newlangType));
        model.addAttribute("historyList", updatedHistoryList); 


        return "views/history";        
    }    


	@PostMapping("/getFieldList")
	@ResponseBody
	public List<field> getFieldList(String langType, String cateNo) {

        // Product View used.        
        Map<String, Object> pField_List = new HashMap<String, Object>();
		pField_List.put("langType",langType);
		pField_List.put("cate_no",cateNo);


        List<field> fields = homepageService.selectFieldList(pField_List);

		return fields;
	}


	@PostMapping("/getFieldItemList")
	@ResponseBody
	public List<fieldItem> getFieldItemList(String langType, String cateNo) {

        // 
        Map<String, Object> pField_List = new HashMap<String, Object>();
		pField_List.put("langType",langType);
		pField_List.put("cate_no",cateNo);


        List<fieldItem> fieldItems = homepageService.selectFieldItemList(pField_List);

		return fieldItems;
	}



    // @RequestMapping("/views/product/{langType}/{cateNo}")
    // public String product(Model model
    //                     ,@PathVariable Optional<String> cateNo
    //                     ,@PathVariable Optional<String> langType) {



    //     String newlangType = "KR";
    //     if (langType.isPresent()) {
    //         newlangType = langType.get();
    //     }

    //     String newCateNo = "1";
    //     if (cateNo.isPresent()) {
    //         newCateNo = cateNo.get();
    //     }     

    //     // Product View used.
    //     // int newCateNo = 1;        
    //     Map<String, Object> pField_List = new HashMap<String, Object>();
	// 	pField_List.put("langType",newlangType);
	// 	pField_List.put("cate_no",newCateNo);


    //     //add attribute of model.
    //     model.addAttribute("cate_list", homepageService.selectCateList(newlangType));        
    //     model.addAttribute("field_list", homepageService.selectFieldList(pField_List));        
    //     model.addAttribute("fieldItem_list", homepageService.selectFieldItemList(pField_List));

    //     return "views/product#product";        
    // }      







    private Map<String, String> getMessage(String langType) {

        // Message Map List to HashMap. because Hashmap.get('key') Type used in Views.
        List<Map<String, String>> msgOriginList = homepageService.selectMsgList(langType);

        Map<String, String> msgMap = new HashMap<String, String>();

        // 과거에는 Hash 에 Key/Value 쌍으로 등록되었지만. List에 담아서 보내준다.
        // 아래 코드는 리스트 쌍에 있는 코드를 까서 HashMap에 K/V 쌍으로 다시 저장해 준다.
        // 기존 코드 마이그레이션 하면서 내인생 낭비했다. (그냥 리턴 형태가 바뀐거다.)

        for (Map<String,String> map : msgOriginList) {
            msgMap.put(map.get("msg_key"), map.get("msg_value"));
        }

        return msgMap;
    }

}
