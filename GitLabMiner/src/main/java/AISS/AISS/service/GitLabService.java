package AISS.AISS.service;



import AISS.AISS.model.Issue;
import AISS.AISS.model.Project;
import AISS.AISS.model.Issue;
import AISS.AISS.model.Commit;
import AISS.AISS.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class GitLabService {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "https://gitlab.com/api/v4/projects/";
    private final String token = "glpat--Zwx8PLpZ5yYPuP_KXAa";

    public Integer defSinceCommits = 2;
    public Integer defSinceIssues = 20;
    public Integer defMaxPages = 2;

    public Project findOne(String projectId, Optional<Integer> sinceCommits, Optional<Integer> sinceIssues, Optional<Integer> maxPages) {

        String url = uri + projectId;
        Project project = restTemplate.getForObject(url, Project.class);

        sinceCommits.ifPresent(value -> defSinceCommits = value);
        sinceIssues.ifPresent(value -> defSinceIssues = value);
        maxPages.ifPresent(value -> defMaxPages = value);

        if (project != null) {
            project.setCommits(getCommits(projectId));
            project.setIssues(getIssues(projectId));
        }

        return project;

    }

    private List<Issue> getIssues(String projectId) {

        String url = uri + projectId + "/issues";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (defSinceIssues != 0) builder.queryParam("sinceIssues", Utils.getDifferenceBetweenDates(defSinceIssues));
        ResponseEntity<Issue[]> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null, null), Issue[].class);

        List<Issue> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (defMaxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < defMaxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = restTemplate.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null, null), Issue[].class);
                    }
                    if (response.getBody() != null) {
                        res.addAll(Arrays.stream(response.getBody()).toList());
                    }
                }
            }
        }

        return res;
    }

    private List<Commit> getCommits(String projectId) {
        String url = uri + projectId + "/repository/commits";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (defSinceCommits != 0) builder.queryParam("since", Utils.getDifferenceBetweenDates(defSinceCommits));
        ResponseEntity<Commit[]> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null, null), Commit[].class);

        List<Commit> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (defMaxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < defMaxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = restTemplate.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null, null), Commit[].class);
                    }
                    if (response.getBody() != null) {
                        res.addAll(Arrays.stream(response.getBody()).toList());
                    }
                }
            }
        }
        return res;
    }

    public Project postProject(Project project) {

        String url = "http://localhost:8080/gitminer/projects";
        HttpEntity<Project> request = new HttpEntity<>(project);
        ResponseEntity<Project> response = restTemplate.exchange(url, HttpMethod.POST, request, Project.class);
        return response.getBody();
    }

}