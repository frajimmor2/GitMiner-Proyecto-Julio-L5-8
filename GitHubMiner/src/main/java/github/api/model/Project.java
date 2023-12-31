
package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Project")
public class Project {

    @Id
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    @NotEmpty(message = "The name of the project cannot be empty")
    public String name;

    @JsonSetter("html_url")
    @NotEmpty(message = "The URL of the project cannot be empty")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("web_url")
    public String webUrl;

    @JsonProperty("commits")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private List<Commit> commits;

    @JsonProperty("issues")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId")
    private List<Issue> issues;

    public Project() {
        commits = new ArrayList<>();
        issues = new ArrayList<>();
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Project.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null) ? "<null>" : this.commits));
        sb.append(',');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.issues == null) ? "<null>" : this.issues));
        sb.append(',');
        sb.append(this.commits);

        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
