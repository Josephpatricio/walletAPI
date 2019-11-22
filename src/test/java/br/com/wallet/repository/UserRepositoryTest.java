package br.com.wallet.repository;

import br.com.wallet.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    private static final String EMAIL = "email@teste.com";

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp(){
        User user = new User();
        user.setName("setUp Test");
        user.setPassword("Senha123");
        user.setEmail(EMAIL);
        userRepository.save(user);
    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setName("Teste");
        user.setPassword("1234");
        user.setEmail("teste@teste.com");

        User response = userRepository.save(user);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = userRepository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}