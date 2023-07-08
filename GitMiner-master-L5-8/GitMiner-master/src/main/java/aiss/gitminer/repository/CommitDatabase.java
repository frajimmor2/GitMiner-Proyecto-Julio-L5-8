package aiss.gitminer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aiss.gitminer.model.Commit;

import java.util.List;

@Repository
public interface CommitDatabase extends JpaRepository<Commit, String> {
    List<Commit> findByAuthorEmail(String email);

}