package sunil.project3.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class content_guardian extends web_content {

    public class Example {

        @SerializedName("response")
        @Expose
        private Response response;

        /**
         *
         * @return
         * The response
         */
        public Response getResponse() {
            return response;
        }

        /**
         *
         * @param response
         * The response
         */
        public void setResponse(Response response) {
            this.response = response;
        }

    }

    public class Response {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("userTier")
        @Expose
        private String userTier;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("startIndex")
        @Expose
        private Integer startIndex;
        @SerializedName("pageSize")
        @Expose
        private Integer pageSize;
        @SerializedName("currentPage")
        @Expose
        private Integer currentPage;
        @SerializedName("pages")
        @Expose
        private Integer pages;
        @SerializedName("orderBy")
        @Expose
        private String orderBy;
        @SerializedName("results")
        @Expose
        private List<Result> results = new ArrayList<Result>();

        /**
         * @return The status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return The userTier
         */
        public String getUserTier() {
            return userTier;
        }

        /**
         * @param userTier The userTier
         */
        public void setUserTier(String userTier) {
            this.userTier = userTier;
        }

        /**
         * @return The total
         */
        public Integer getTotal() {
            return total;
        }

        /**
         * @param total The total
         */
        public void setTotal(Integer total) {
            this.total = total;
        }

        /**
         * @return The startIndex
         */
        public Integer getStartIndex() {
            return startIndex;
        }

        /**
         * @param startIndex The startIndex
         */
        public void setStartIndex(Integer startIndex) {
            this.startIndex = startIndex;
        }

        /**
         * @return The pageSize
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         * @param pageSize The pageSize
         */
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        /**
         * @return The currentPage
         */
        public Integer getCurrentPage() {
            return currentPage;
        }

        /**
         * @param currentPage The currentPage
         */
        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        /**
         * @return The pages
         */
        public Integer getPages() {
            return pages;
        }

        /**
         * @param pages The pages
         */
        public void setPages(Integer pages) {
            this.pages = pages;
        }

        /**
         * @return The orderBy
         */
        public String getOrderBy() {
            return orderBy;
        }

        /**
         * @param orderBy The orderBy
         */
        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        /**
         * @return The results
         */
        public List<Result> getResults() {
            return results;
        }

        /**
         * @param results The results
         */
        public void setResults(List<Result> results) {
            this.results = results;
        }

    }

    public class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("sectionId")
        @Expose
        private String sectionId;
        @SerializedName("sectionName")
        @Expose
        private String sectionName;
        @SerializedName("webPublicationDate")
        @Expose
        private String webPublicationDate;
        @SerializedName("webTitle")
        @Expose
        private String webTitle;
        @SerializedName("webUrl")
        @Expose
        private String webUrl;
        @SerializedName("apiUrl")
        @Expose
        private String apiUrl;
        @SerializedName("isHosted")
        @Expose
        private Boolean isHosted;

        /**
         * @return The id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return The sectionId
         */
        public String getSectionId() {
            return sectionId;
        }

        /**
         * @param sectionId The sectionId
         */
        public void setSectionId(String sectionId) {
            this.sectionId = sectionId;
        }

        /**
         * @return The sectionName
         */
        public String getSectionName() {
            return sectionName;
        }

        /**
         * @param sectionName The sectionName
         */
        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        /**
         * @return The webPublicationDate
         */
        public String getWebPublicationDate() {
            return webPublicationDate;
        }

        /**
         * @param webPublicationDate The webPublicationDate
         */
        public void setWebPublicationDate(String webPublicationDate) {
            this.webPublicationDate = webPublicationDate;
        }

        /**
         * @return The webTitle
         */
        public String getWebTitle() {
            return webTitle;
        }

        /**
         * @param webTitle The webTitle
         */
        public void setWebTitle(String webTitle) {
            this.webTitle = webTitle;
        }

        /**
         * @return The webUrl
         */
        public String getWebUrl() {
            return webUrl;
        }

        /**
         * @param webUrl The webUrl
         */
        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        /**
         * @return The apiUrl
         */
        public String getApiUrl() {
            return apiUrl;
        }

        /**
         * @param apiUrl The apiUrl
         */
        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        /**
         * @return The isHosted
         */
        public Boolean getIsHosted() {
            return isHosted;
        }

        /**
         * @param isHosted The isHosted
         */
        public void setIsHosted(Boolean isHosted) {
            this.isHosted = isHosted;
        }

    }
}
