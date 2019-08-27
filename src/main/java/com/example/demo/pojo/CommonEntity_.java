package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Package： com.example.demo.pojo
 * Author:  hujin
 * Date: 2019/8/26 19:55
 * Description:
 * Version：
 */
public class CommonEntity_ {

    @Data
    public static class WJ {

        @JsonProperty("WJID")
        private String wjid;

        @JsonProperty("SLTP")
        private String sltp;

        @JsonProperty("WJLX")
        private String wjlx;

        @JsonProperty("WJMC")
        private String wjmc;

        @JsonProperty("WJMS")
        private String wjms;

        @JsonProperty("WLLJ")
        private String wllj;

        @JsonProperty("WYLJ")
        private String wylj;

        @JsonProperty("XSSX")
        private Integer xssx;

        @JsonIgnore
        public String getSltp() {
            return sltp;
        }
        @JsonIgnore
        public String getWjlx() {
            return wjlx;
        }
        @JsonIgnore
        public String getWjmc() {
            return wjmc;
        }
        @JsonIgnore
        public String getWjms() {
            return wjms;
        }
        @JsonIgnore
        public String getWllj() {
            return wllj;
        }
        @JsonIgnore
        public Integer getXssx() {
            return xssx;
        }
        @JsonIgnore
        public String getWylj() {return wylj;}
    }
}
