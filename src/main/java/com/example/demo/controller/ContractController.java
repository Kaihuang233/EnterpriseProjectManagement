//package com.example.demo.controller;
//
//import com.alibaba.fastjson.*;
//import com.example.demo.entity.*;
//import com.example.demo.mapper.*;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.*;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//@CrossOrigin//跨域
//@RestController
//public class ContractController {
//    @Resource
//    private ContractMapper contractMapper;//实例化对象并注入
//    @Resource
//    private ContractTypeMapper contractTypeMapper;//实例化对象并注入
//    @Resource
//    private ContractAnnexesMapper contractAnnexesMapper;//实例化对象并注入
//    private State state = new State();
//
//
//    //获取合同支付节点
//    @RequestMapping(value = "/GetPaynode", method = RequestMethod.POST)
//    public List<ProjectProgress> paynode(@RequestBody(required = false) Contract contract) {
//        System.out.println(contract.getContractName());
//        return projectProgressMapper.returnpoint(contractMapper.getid(contract.getContractName()));
//    }
//
//    //新建合同
//    @RequestMapping(value = "/Newcontract", method = RequestMethod.POST)
//    public State newcontract(@RequestBody(required = false) String contract) {
////        try {
//        System.out.println(contract);
//        JSONObject Contractdata = JSON.parseObject(contract);
//        JSONArray ListContractType = Contractdata.getJSONArray("ContractType");
//        int i = ListContractType.size();
//        ContractType[] newContractType = new ContractType[i];
//        for (int k = 0; k < i; k++) {
//            newContractType[k] = new ContractType(ListContractType.getJSONObject(k).getString("Type"),
//                    ListContractType.getJSONObject(k).getFloat("Amount"),
//                    ListContractType.getJSONObject(k).getFloat("Taxrate"),
//                    ListContractType.getJSONObject(k).getIntValue("Contractid")
//            );
//        }
//        JSONArray ListProjectProgress = Contractdata.getJSONArray("ProjectProgress");
//        int j = ListProjectProgress.size();
//        ProjectProgress[] newProjectProgress = new ProjectProgress[j];
//        for (int m = 0; m < j; m++) {
//            String a;
//            if (ListProjectProgress.getJSONObject(m).getBoolean("Completed")) {
//                a = "已完成";
//            } else {
//                a = "未完成";
//            }
//            newProjectProgress[m] = new ProjectProgress(ListProjectProgress.getJSONObject(m).getString("Category"),
//                    ListProjectProgress.getJSONObject(m).getFloat("Amountpaid"),
//                    ListProjectProgress.getJSONObject(m).getFloat("AccountConAmount"),
//                    ListProjectProgress.getJSONObject(m).getFloat("Paymentnode"),
//                    ListProjectProgress.getJSONObject(m).getString("Paymentterms"),
//                    a,
//                    ListProjectProgress.getJSONObject(m).getIntValue("Contractid")
//            );
//
//        }
//
//        Contract newContract = new Contract(
//                Contractdata.getIntValue("Contractid"),
//                Contractdata.getString("ContractName"),
//                Contractdata.getIntValue("ContractNum"),
//                Contractdata.getString("SigningTime"),
//                Contractdata.getString("Signingstatus"),
//                Contractdata.getString("BG"),
//                Contractdata.getString("BU"),
//                Contractdata.getString("Industry"),
//                Contractdata.getString("Subindustry"),
//                Contractdata.getString("Projecttype"),
//                Contractdata.getString("Region"),
//                Contractdata.getString("Institution"),
//                Contractdata.getString("Province"),
//                Contractdata.getString("ProductLine"),
//                Contractdata.getString("PartyBunit"),
//                Contractdata.getString("Maintenanceperiod"),
//                Contractdata.getString("AccountManager"),
//                Contractdata.getString("AgreedCurrency"),
//                Contractdata.getString("PerformanceBonds"),
//                Contractdata.getString("CustomerName"),
//                Contractdata.getString("Paymentmethod"),
//                newContractType,
//                Contractdata.getString("Type"),
//                Contractdata.getFloat("Amount"),
//                Contractdata.getFloat("Taxrate"),
//                Contractdata.getFloat("TaxrateAmount"),
//                newProjectProgress,
//                Contractdata.getString("Category"),
//                Contractdata.getFloat("Amountpaid"),
//                Contractdata.getFloat("AccountConAmount"),
//                Contractdata.getFloat("Paymentnode"),
//                Contractdata.getString("Paymentterms")
//        );
//
//        contractMapper.creatcon(newContract);
//        System.out.println(1);
//        for (int k = 0; k < i; k++) {
//            newContractType[k].setContractid(contractMapper.getid(newContract.getContractName()));
//            contractTypeMapper.createConType(newContractType[k]);
//        }
//
//        System.out.println(2);
//        for (int m = 0; m < j; m++) {
//            newProjectProgress[m].setContractid(contractMapper.getid(newContract.getContractName()));
//            projectProgressMapper.createProPg(newProjectProgress[m]);
//        }
//
//        System.out.println(3);
//        state.setState(200);
//        state.setMsg("增加合同成功");
//        return state;
////        }catch (Exception e){
////            state.setState(400);
////            state.setMsg("增加合同失败");
////            return state;
////        }
//    }
//
//
//    //修改合同
//    @RequestMapping(value = "/enterContract/updata", method = RequestMethod.POST)
//    public State updatacontract(@RequestBody(required = false) String contract) {
//        try {
//            JSONObject Contractdata = JSON.parseObject(contract);
//
//            contractTypeMapper.delete(contractMapper.getid(Contractdata.getString("ContractName")));
//            projectProgressMapper.delete(contractMapper.getid(Contractdata.getString("ContractName")));
//
//            contractMapper.deledecontract1(contractMapper.getid(Contractdata.getString("ContractName")));
//            newcontract(contract);
//
//            state.setState(200);
//            state.setMsg("修改合同成功");
//            return state;
//        } catch (Exception e) {
//            state.setState(400);
//            state.setMsg("修改合同失败");
//            return state;
//        }
//    }
//
//    @RequestMapping(value = "/Newcontract/upload", method = RequestMethod.POST)
//    public String up(MultipartFile photo, HttpServletRequest request) throws IOException {
//
//        saveFile(photo);
//        return "上传成功";
//    }
//
//    public void saveFile(MultipartFile photo) throws IOException {
//        File dir = new File("H:/vuedownload/");
//        if (!dir.exists()) {//目录不存在则创建
//            dir.mkdir();
//        }
//        File file = new File("H:/vuedownload/" + photo.getOriginalFilename());//拼接上文件名称创建文件
//        System.out.println("H:/vuedownload/" + photo.getOriginalFilename());
//        photo.transferTo(file);
//    }
//
//    //获取合同
//    @RequestMapping(value = "/enterContract", method = RequestMethod.POST)
//    public List<Contract> enterContract(@RequestBody(required = false) Contract contract) {
//        contract = contractMapper.enterContract(contract.getContractName());
//        List<Contract> list = new ArrayList<>();
//        list.add(contract);
//        return list;
//    }
//
//    //获取合同类型
//    @RequestMapping(value = "/enterContractType", method = RequestMethod.POST)
//    public List<ContractType> enterContractType(@RequestBody(required = false) Contract contract) {
//        List<ContractType> list = new ArrayList<>();
//        list = contractTypeMapper.enterContractType(contractMapper.getid((contract.getContractName())));
//        return list;
//    }
//
//    //关闭合同
//    @RequestMapping(value = "/closeContract", method = RequestMethod.POST)
//    public State deleteContract(@RequestBody(required = false) String contract)//
//    {
//        JSONObject Contractdata = JSON.parseObject(contract);
//        contractMapper.deledecontract(contractMapper.getid(Contractdata.getString("ContractName")), "已支付合同");
//        contractMapper.closecontract(contractMapper.getid(Contractdata.getString("ContractName")), "已签订合同");
//        state.setState(401);
//        state.setMsg("删除合同");
//        return state;
//    }
//
//    //返回合同数据对象
//    @RequestMapping(value = "/getContract", method = RequestMethod.POST)
//    public List<BackPack> databack1(@RequestBody Select select) {
//        List<Integer> listid1 = contractMapper.forgetid(select.getStatus());//获取某种类型的全部合同id
//        List<Integer> listid2 = contractMapper.timegetid(select.getTimeSelect());//获取某个年份的全部合同id
//        List<BackPack> list = new ArrayList<BackPack>();
//        for (Integer value : listid1) {
//            for (Integer integer : listid2) {
//                if (Objects.equals(value, integer)) {
//                    BackPack backpack = new BackPack(contractMapper.getCustomerName(value),
//                            contractMapper.getContractName(value),
//                            contractMapper.getSigningTime(value),
//                            contractMapper.getContractAmount(value),
//                            contractMapper.getCompletedAmount(value),
//                            contractMapper.getContractState(value),
//                            value,
//                            contractMapper.getSigningstatus(value)
//                    );
//                    list.add(backpack);
//                }
//            }
//            if (list.size() >= 6) {//只返回前六个表
//                break;
//            }
//        }
//        System.out.println(list);
//        return list;
//    }
//
//    //返回合同数据对象
//    @RequestMapping(value = "/ContractTopic", method = RequestMethod.POST)
//    public List<BackPack> databack2(@RequestBody Select select) {
//        String state = switch (select.getStatus()) {
//            case "toSign" -> "未签订合同";
//            case "Signed" -> "已签订合同";
//            case "Paid" -> "已支付合同";
//            default -> "";
//        };
//
//        int b = 0;
//        List<Integer> listid1;//Status
//        List<Integer> listid2;//TimeSelect
//
//        if (state.equals("")) {
//            listid1 = contractMapper.getallid();
//        } else if (state.equals("已支付合同")) {
//            listid1 = contractMapper.forgetid(state);//获取某种类型的全部合同id
//        } else if (state.equals("已签订合同")) {
//            listid1 = contractMapper.SpeSigngetid(state, "履约合同");//获取已签订合同并且为履约合同的全部合同id
//        } else {
//            listid1 = contractMapper.Signgetid(state);//获取某种类型的全部合同id
//            System.out.println("listid1:" + listid1);
//        }
//
//
//        if (Objects.equals(select.getTimeSelect(), "")) {
//            listid2 = contractMapper.getallid();
//        } else {
//            listid2 = contractMapper.daytimegetid(select.getTimeSelect());//获取某个日期的全部合同id
//        }
//        List<Integer> listid3 = contractMapper.searchgetid(select.getSearch());//获取某个条件的全部合同id Search
//
//        List<BackPack> list = new ArrayList<BackPack>();
//
//        ArrayList<BackPack> aList = new ArrayList<BackPack>();
//        for (Integer item : listid1) {
//            for (Integer value : listid2) {
//                if (Objects.equals(item, value)) {//当获取1和2有共同的id
//                    for (Integer integer : listid3) {
//                        if (Objects.equals(value, integer)) {//当2和3有共同id
//                            BackPack backpack = new BackPack(contractMapper.getCustomerName(item),
//                                    contractMapper.getContractName(item),
//                                    contractMapper.getSigningTime(item),
//                                    contractMapper.getContractAmount(item),
//                                    contractMapper.getCompletedAmount(item),
//                                    contractMapper.getContractState(item),
//                                    item,
//                                    contractMapper.getSigningstatus(item)
//                            );
//                            aList.add(backpack);
//                            System.out.println(aList.get(b));
//                            b++;
//                        }
//                    }
//                }
//            }
//        }
//
//        list.add(new BackPack(aList.size(), contractMapper.Signgetid("未签订合同").size(),
//                contractMapper.forgetid("履约合同").size(), contractMapper.forgetid("已支付合同").size()));
//
//        if (!aList.isEmpty()) {
//            for (int i = 12 * (select.getPage() - 1); i < 12 * select.getPage(); i++) {
//                list.add(aList.get(i));
//                if (i + 1 >= aList.size()) {
//                    break;
//                }
//            }
//        }
//        System.out.println(list);
//        return list;
//    }
//}
//
