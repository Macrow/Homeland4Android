package org.mstudio.homeland4android.data.network.model.raw;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author : Macrow
 * e-mail : Macrow_wh@163.com
 * time   : 2017/06/07
 * desc   :
 */
public class RawNodes {

    @SerializedName("nodes")
    private List<NodesBean> nodes;

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }

    public static class NodesBean {
        /**
         * id : 3
         * name : Gem
         * topics_count : 1211
         * summary : 在这里讨论 Ruby Gem，分享使用心得，发现有趣实用的 Gem
         * section_id : 1
         * sort : 97
         * section_name : Ruby
         * updated_at : 2015-03-01T22:35:21.668+08:00
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("topics_count")
        private int topicsCount;
        @SerializedName("summary")
        private String summary;
        @SerializedName("section_id")
        private int sectionId;
        @SerializedName("sort")
        private int sort;
        @SerializedName("section_name")
        private String sectionName;
        @SerializedName("updated_at")
        private String updatedAt;

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

        public int getTopicsCount() {
            return topicsCount;
        }

        public void setTopicsCount(int topicsCount) {
            this.topicsCount = topicsCount;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
