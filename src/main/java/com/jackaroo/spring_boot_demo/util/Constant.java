package com.jackaroo.spring_boot_demo.util;


import org.apache.commons.lang3.StringUtils;

/**
 * @author JackarooZhang
 * @date 2018/6/7 12:59
 */
public class Constant {

    public enum ResponseStatus {
        SUCCESS(1001, "成功"), FAIL(1002, "失败");

        private Integer code;
        private String desc;

        ResponseStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static ResponseStatus codeOf(int code) {
            for (ResponseStatus status : values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new RuntimeException("没有找到状态码对应的状态枚举");
        }
    }

    public enum CommonStatus {

        NORMAL(1, "正常"), FORBID(2, "禁止"), DELETE(3, "删除"), OTHER(4, "其他");

        private Integer id;
        private String status;

        CommonStatus(int id, String status) {
            this.id = id;
            this.status = status;
        }

        public Integer getId() {
            return this.id;
        }

        public String getStatus() {
            return this.status;
        }

        public static CommonStatus codeOf(int id) {
            for (CommonStatus status : values()) {
                if (status.getId() == id) {
                    return status;
                }
            }
            throw new RuntimeException("没有找到状态码对应的状态枚举");
        }

    }

    public enum EmployeeStatus {

        NORMAL(1, "正常"), FORBID(2, "禁止"), DELETE(3, "删除");

        private Integer id;
        private String status;

        EmployeeStatus(int id, String status) {
            this.id = id;
            this.status = status;
        }

        public Integer getId() {
            return this.id;
        }

        public String getStatus() {
            return this.status;
        }

        public static EmployeeStatus codeOf(int id) {
            for (EmployeeStatus status : values()) {
                if (status.getId() == id) {
                    return status;
                }
            }
            throw new RuntimeException("没有找到状态码对应的状态枚举");
        }

        public static EmployeeStatus getStatus(String status) {
            if (!StringUtils.isNotBlank(status)) {
                return null;
            }
            if (status.equals(FORBID.status)) {
                return FORBID;
            } else if (status.equals(DELETE.status)) {
                return DELETE;
            } else {
                return NORMAL;
            }
        }

    }

    public enum  Gender {

        MALE(1, "男"), FEMALE(2, "女");
        private int id;
        private String name;

        private Gender(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static Gender getSex(int id) {
            if (id == 1)
                return MALE;
            else if (id == 2)
                return FEMALE;
            else
                return null;
        }

        public static Gender getGender(String gender) {
            if (!StringUtils.isNotBlank(gender)) {
                return null;
            }
            if (gender.equals(Gender.MALE.name)) {
                return MALE;
            } else if (gender.equals(Gender.FEMALE.name)) {
                return FEMALE;
            } else {
                return MALE;
            }
        }

    }



}
