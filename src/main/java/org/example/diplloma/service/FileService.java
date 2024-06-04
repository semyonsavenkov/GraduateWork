package org.example.diplloma.service;

import org.example.diplloma.exceptions.InputException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.diplloma.entity.File;
import org.example.diplloma.entity.User;
import org.example.diplloma.exceptions.AuthorizationException;
import org.example.diplloma.repository.AuthRepository;
import org.example.diplloma.repository.FileRepository;
import org.example.diplloma.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class FileService {

    FileRepository fileRepository;
    AuthRepository authorizationRepository;
    UserRepository userRepository;

    public void uploadFile(String authToken, String name, MultipartFile file) throws IOException {
        final User user = getUser(authToken);
        if (user == null) {
            throw new AuthorizationException("Unauthorized error");
        }
        fileRepository.save(new File(name, file.getContentType(), file.getSize(), file.getBytes(), user));
        log.info("User {} upload file {}", user.getLogin(), name);
    }

    public void deleteFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Delete file error");
            throw new AuthorizationException("Unauthorized error");
        }
        log.info("User {} delete file {}", user.getLogin(), filename);
        fileRepository.removeByUserAndFilename(user, filename);
    }

    public File downloadFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            throw new AuthorizationException("Unauthorized error");
        }
        final File file = fileRepository.findByUserAndFilename(user, filename);
        if (file == null) {
            log.error("Download file error");
            throw new InputException("Error input data");
        }
        log.info("User {} download file {}", user.getLogin(), filename);
        return file;
    }

    public void editFileName(String authToken, String filename, String newFileName) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Edit file error");
            throw new AuthorizationException("Unauthorized error");
        }

        if (newFileName == null) throw new InputException("Error input data");

        fileRepository.editFileNameByUser(user, filename, newFileName);
        log.info("User {} edit file {}", user.getLogin(), filename);
    }

    public List<File> getAllFiles(String authToken, Integer limit) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Get all files error");
            throw new AuthorizationException("Unauthorized error");
        }
        log.info("User {} get all files", user.getLogin());
        return fileRepository.findAllByUser(user, Sort.by("filename"));
    }

    private User getUser(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        final String username = authorizationRepository.getUserNameByToken(authToken);
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new AuthorizationException("Unauthorized error"));
    }
}
