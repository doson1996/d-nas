package com.ds.nas.lib.common.base.response;

import lombok.Data;

/**
 * @author ds
 * @date 2023/1/23
 * @description 返回类型为String的返回包装类
 */
@Data
public class StringResponse extends BaseResponse {

    String data;

    public static StringResponse.Builder builder() {
        return new StringResponse.Builder();
    }

    public static final class Builder {

        private String data;

        private Builder() {
        }

        public StringResponse.Builder withData(String data) {
            this.data = data;
            return this;
        }

        /**
         * Build StringResponse.
         *
         * @see StringResponse
         */
        public StringResponse build() {
            StringResponse response = new StringResponse();
            response.setData(data);
            return response;
        }
    }

}
