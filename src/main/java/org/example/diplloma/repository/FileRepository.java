package org.example.diplloma.repository;

import org.example.diplloma.entity.User;
import org.example.diplloma.entity.File;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    File findByUserAndFilename(User user, String name);
    void removeByUserAndFilename(User user, String name);
    List<File> findAllByUser(User user, Sort sort);

    @Modifying
    @Query("update file set file.name = :newName where file.name = :name and file.user = :user")
    void editFileNameByUser(@Param("user") User user, @Param("name") String filename, @Param("newName") String newName);
}
