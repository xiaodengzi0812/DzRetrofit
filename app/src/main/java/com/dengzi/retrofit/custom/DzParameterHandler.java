package com.dengzi.retrofit.custom;

/**
 * @author Djk
 * @Title: 参数解析器 (很多策略，Query ,Part , QueryMap ,Field 等等)
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public interface DzParameterHandler<T> {

    void apply(DzRequestBuilder requestBuilder, T value);

    // DzQuery策略,添加Get参数
    class DzQuery<T> implements DzParameterHandler<T> {
        private String key;

        public DzQuery(String key) {
            this.key = key;
        }

        @Override
        public void apply(DzRequestBuilder requestBuilder, T value) {
            requestBuilder.addQueryName(key, value.toString());
        }
    }

    // DzPath策略,拼接字符器的占位符
    class DzPath<T> implements DzParameterHandler<T> {
        private String key;

        public DzPath(String key) {
            this.key = key;
        }

        @Override
        public void apply(DzRequestBuilder requestBuilder, T value) {
            requestBuilder.addPathSegment(key, value.toString());
        }
    }

}
