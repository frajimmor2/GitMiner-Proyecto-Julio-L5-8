package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitParentAux {

    @JsonProperty("sha")
    private String id;

    @JsonProperty("commit")
    private CommitAux commit;

    @JsonProperty("html_url")
    private String web_url;

    public String getId() {
        return id;
    }

    public CommitAux getCommit() {
        return commit;
    }

    public String getWeb_url() {
        return web_url;
    }


}
