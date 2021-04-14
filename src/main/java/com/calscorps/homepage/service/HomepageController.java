package com.calscorps.homepage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.calscorps.homepage.common.pojo.field;
import com.calscorps.homepage.common.pojo.fieldItem;
import com.calscorps.homepage.common.pojo.history;
import com.calscorps.homepage.common.pojo.specification;
import com.calscorps.homepage.common.pojo.vision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"","/"})
public class HomepageController {

    private final HomepageService homepageService;

    @Autowired
    public HomepageController(HomepageService homepageService) {
    // 콘트롤러 생성자에서 서비스 주입
        this.homepageService = homepageService;
    }
    
    @RequestMapping({"/views/index"
                    ,"/views/index/{langType}"
                    // ,"/views/index/{langType}/{selectedCateNo}"
                    })

    public String index(Model model
                        ,@PathVariable Optional<String> langType                        
                        ,@PathVariable Optional<String> selectedCateNo) {
        

        String newlangType = "KR";
        int newCateNo = 1;
        
        if (langType.isPresent()) {
            newlangType = langType.get();  
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
        // model.addAttribute("cateNo", newCateNo );
        
        
        model.addAttribute("cate_list", homepageService.selectCateList(newlangType));        
        model.addAttribute("field_list", homepageService.selectFieldList(pField_List));        
        model.addAttribute("fieldItem_list", homepageService.selectFieldItemList(pField_List));

        
        //searchProduct
        model.addAttribute("filesearch_list", homepageService.selectFileSearchList(newlangType));

        //certifications
        model.addAttribute("certiList", homepageService.selectContentsCodeList(pCerti_List));

        //machinesLabs
        model.addAttribute("machinesList", homepageService.selectContentsCodeList(pMachine_List));    
        
        // contact list
        model.addAttribute("contactList", homepageService.selectContactList(newlangType));



        // Footer desc used        
        model.addAttribute("newLineChar", "\n");
        
        model.addAttribute("msgMap", getMessage(newlangType));         
        model.addAttribute("footerList", homepageService.selectFooterList(newlangType));        

        return "/views/index";
    } 

    @RequestMapping("/vision/{langType}")
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

    @PostMapping({"/views/index/getFieldList"})
	@ResponseBody
	public List<field> getFieldList(String langType, String cateNo) {

        // Product View used.        
        Map<String, Object> pField_List = new HashMap<String, Object>();
		pField_List.put("langType",langType);
		pField_List.put("cate_no",cateNo);


        List<field> fields = homepageService.selectFieldList(pField_List);

		return fields;
	}

	@PostMapping("/views/index/getFieldItemList")
	@ResponseBody
	public List<fieldItem> getFieldItemList(String langType, String cateNo) {

        // 
        Map<String, Object> pField_List = new HashMap<String, Object>();
		pField_List.put("langType",langType);
		pField_List.put("cate_no",cateNo);


        List<fieldItem> fieldItems = homepageService.selectFieldItemList(pField_List);

		return fieldItems;
	}

	@PostMapping({"/views/index/getSpecification"})
	@ResponseBody
	public List<specification> getSpecification(String langType, int itmId) {

        // 
        Map<String, Object> pSpec_List = new HashMap<String, Object>();
		pSpec_List.put("langType",langType);
		pSpec_List.put("itm_id",itmId);


        List<specification> specItems = homepageService.selectItemSpecList(pSpec_List);

		return specItems;
	}

	@PostMapping("/views/index/getHistoryCompany")
	@ResponseBody
	public List<history> getHistoryCompany(String langType) {

        List<history> historyOriginList = homepageService.selectHistoryList(langType);
        return historyOriginList; 
	}    

    @PostMapping("/views/index/getvisionCompany")
	@ResponseBody
	public List<vision> getvisionCompany(String langType) {

        String content = "";
        // region #en
        String en = "The most suitable specialty lubricants for your demanding requirements with our best technology <br><br><br>";
        en += "We, CALS people, are composed of qualified and creative workers <br> ";
        en += "who do their best to give lots of satisfaction and pleasure to our customers through researches<br> ";
        en += "and developments for lubricating technology.<br><br>    ";
        en += "At November 11, 1980, CALS CORPORATION was established by just 5members, as a small company.<br>";
        en += "Now we are advancing toward the prime company <br>";
        en += "in Korea market producing about 300 kinds of specialty lubricants from our own factory.<br><br>";
        en += "CALS CORPORATION is …<br>    ";
        en += "<b>1. cultivating the competent and qualified staffs.</b><br><br>";
        en += "<b>2. stepping up to the customers with new technical development.</b><br><br>";
        en += "<b>3. concerning for the protection of natural environment.</b><br><br>    ";
        en += "In Specialty Lubricant Industry, we are the exporter to 15 countries,<br>";
        en += "including the USA, Japan, U.K., China, Indonesia, etc. as well as the leader of domestic market.<br>";
        en += "Also, our new items by the improved technology will make our prospect bright.<br><br>    ";
        en += "We'll always rush and go to stand the prime company in global market and we do our best to study<br>";
        en += "and make the best products which our customers are fully satisfied.<br><br>";
        // endregion #en

        // region #cn
        String cn = "采用最高技术，提供最佳润滑脂<br><br><br> ";
        cn += "我们长岩人，为了研究开发未来润滑脂，为了最大限度的满足广大客户，聚在一起，铸就了美丽洁净的长岩.<br>	";	
        cn += "長岩CALS（株）始建于 1980 年末，最初以 5 人开始，到目前已经具有占地 23,600m2, 建筑面积 4,900m2 的工厂，生产约 300 多种润滑脂，发展成为一家先导国内同行业的领头企业.<br><br>";
        cn += "長岩CALS（株）是<br>";
        cn += "<b>第一 , 造就最卓越人才的企业；</b><br><br>";
        cn += "<b>第二 , 以最新颖的技术开发面向广大客户的企业；</b><br><br>";
        cn += "<b>第三 , 对未来的环境负责，热爱自然和环境的企业.</b><br><br>";
        cn += "凭着以上三个信念，不仅发展成为特殊润滑脂行业的先导企业，而且实现了向美国和日本等世界 15 个国家出口；目前用新技术积极研发的新产品也有望开创光明的前途。<br>";
        cn += "長岩CALS承诺为了成为无愧于世界级的企业而不遗余力的付出，为了达到客户不满率为零而更加不懈地专研，为了推出最卓越品质的产品而努力.<br><br>";
        // endregion #cn
        
        // region #kr
        String kr = "최고의 기술로 최적의 윤활제를 공급하겠습니다.<br><br><br> ";
        kr += "우리 장암인은 미래의 윤활에 대한 연구와 개발로서 <br> ";
        kr += "고객에게 최대의 만족을 만들어 내는 사람들로 뭉쳐있는 아름답고 깨끗한 기업인들입니다.<br>";
        kr += "장암칼스주식회사는 1980년 말에 5인의 자그마한 회사로 출발하여<br> ";
        kr += "현재 대지 23,600m², 건평 4,900m²의 공장을 갖추고 약 300여종의 특수 윤활제를 생산해내며 <br>";
        kr += "국내의 선두주자로 나아가는 기업입니다.<br><br>    ";
        kr += "<b>첫째, 최고의 인재를 만들어내는 기업입니다.</b><br><br>";
        kr += "<b>둘째, 새로운 기술개발로 고객에게 다가서고 있습니다.</b><br><br>";
        kr += "<b>셋째, 미래환경을 위하여 책임지고 자연과 환경을 사랑합니다.</b><br><br>    ";
        kr += "위 세가지 정신으로 특수윤활제 업계의 국내 선두 자리는물론, <br>";
        kr += "미국과 일본을 비롯하여 세계 15개국에 수출하고 있으며, <br>";
        kr += "신기술로 이어지는 새로운 품목들이 앞날을 매우 밝고 만족스럽게 이어가고 있습니다.<br>";
        kr += "장암칼스주식회사는 세계속의 기업으로 손색없도록 모두가 노력할 것이며 <br>";
        kr += "고객의 불편율이 제로가 될 때까지 연구하여 항상 최고의 제품을 만들 것을 약속 드립니다.<br>";
        // endregion #kr

        if (langType.equals("EN")) {
            content = en;
        } 
        else if (langType.equals("CN")) {
            content = cn;
        }else {
            content = kr;
        }

        // 구조기작 만들어 놓음.
        List<vision> visionList = new ArrayList<vision>();
        vision vision = new vision();
        vision.setContent(content);
        visionList.add(vision);

        return visionList; 
	}    

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
