package org.example.diplloma.service;

import org.example.diplloma.entity.User;
import org.example.diplloma.repository.AuthRepository;
import org.example.diplloma.repository.FileRepository;
import org.example.diplloma.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FileServiceTest {
    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private AuthRepository authorizationRepository;

    @Mock
    private UserRepository userRepository;

    public static final String LOGIN_1 = "login1";
    public static final String PASSWORD_1 = "pass1";
    public static final User USER_1 = new User(LOGIN_1, PASSWORD_1, null);
    public static final String TOKEN = "token";
    public static final String FILENAME_1 = "fileName1";
    public static final String FILENAME_2 = "fileName2";

    @BeforeEach
    void setUp() {
        Mockito.when(authorizationRepository.getUserNameByToken(TOKEN)).thenReturn(LOGIN_1);
        Mockito.when(userRepository.findByLogin(LOGIN_1)).thenReturn(Optional.of(USER_1));
    }

    @Test
    void editFileName() {
        fileService.editFileName(TOKEN, FILENAME_1, FILENAME_2);
        Mockito.verify(fileRepository, Mockito.times(1)).editFileNameByUser(USER_1, FILENAME_1, FILENAME_2);
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(TOKEN, FILENAME_1);
        Mockito.verify(fileRepository, Mockito.times(1)).removeByUserAndFilename(USER_1, FILENAME_1);
    }
}
