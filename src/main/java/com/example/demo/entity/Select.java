package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Select {

    @JsonProperty(value = "TimeSelect")
    private String TimeSelect;//签订时间。xxxx-xx-xx

    @JsonProperty(value = "Search")
    private String Search;//合同名称，客户名称，签单状态，合同状态，签订时间

    @JsonProperty(value = "Status")
    private String Status;//all，toSign，Signed，Paid

    @JsonProperty(value = "Page")
    private int Page;

    @JsonProperty(value = "TotalPage")
    private int TotalPage;
}
